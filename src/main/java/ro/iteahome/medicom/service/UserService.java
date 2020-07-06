package ro.iteahome.medicom.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.iteahome.medicom.config.rest.RestConfig;
import ro.iteahome.medicom.exception.business.GlobalNotFoundException;
import ro.iteahome.medicom.exception.business.NotNhsRegisteredException;
import ro.iteahome.medicom.model.dto.ConsultDTO;
import ro.iteahome.medicom.model.dto.UserRegistrationDTO;
import ro.iteahome.medicom.model.entity.Role;
import ro.iteahome.medicom.model.entity.User;
import ro.iteahome.medicom.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

// DEPENDENCIES: -------------------------------------------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestConfig restConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

// C.R.U.D. METHODS: ---------------------------------------------------------------------------------------------------

    public String addUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1);
        setNhsRole(user);
        if (isNhsDoctor(user.getCnp())){
            userRepository.save(user);
            return "home";
        }
        else {return "Not Nhs Registered User";}
    }

// OTHER METHODS: ------------------------------------------------------------------------------------------------------

    private Boolean isNhsDoctor(String cnp) {
        return restTemplate.exchange(
                restConfig.getSERVER_URL() + restConfig.getDOCTORS_URI() + "/existence/by-cnp/" + cnp,
                HttpMethod.GET,
                new HttpEntity<>(restConfig.buildAuthHeaders(restConfig.getCREDENTIALS())),
                Boolean.class).getBody();
    }

    private void setNhsRole(User user) {
        if (isNhsDoctor(user.getCnp())) {
            Role role = roleService.findByName("DOCTOR");
            user.setRole(role);
        } else {
            throw new NotNhsRegisteredException();
        }
    }

    public ConsultDTO[] findAllConsults(String cnp) {
        ResponseEntity<ConsultDTO[]> consultDTOResponse =
                restTemplate.exchange(
                        restConfig.getSERVER_URL() + restConfig.getPATIENTS_URI() + "/find-consult/" + cnp,
                        HttpMethod.GET,
                        new HttpEntity<>(restConfig.buildAuthHeaders(restConfig.getCREDENTIALS())),
                        ConsultDTO[].class);
        ConsultDTO[] consultDTO = consultDTOResponse.getBody();
        if (consultDTO != null) {
            return consultDTO;
        } else {
            throw new GlobalNotFoundException("Consult");
        }
    }

    public Boolean addConsult(ConsultDTO consultDTO) {
        ResponseEntity<Boolean> consultDTOResponseEntity =
                restTemplate.exchange(
                        restConfig.getSERVER_URL() + restConfig.getPATIENTS_URI() + "/add-consult",
                        HttpMethod.POST,
                        new HttpEntity<>(consultDTO, restConfig.buildAuthHeaders(restConfig.getCREDENTIALS())),
                        Boolean.class);
        return consultDTOResponseEntity.getBody();
    }

    public String getInstitutions(String cnp){
        ResponseEntity<String> institutionsResponse =
                restTemplate.exchange(
                        restConfig.getSERVER_URL() + restConfig.getDOCTORS_URI() + "/institution_cui" + cnp,
                        HttpMethod.GET,
                        new HttpEntity<>(restConfig.buildAuthHeaders(restConfig.getCREDENTIALS())),
                        String.class);
                return institutionsResponse.getBody();
    }
// OVERRIDDEN "UserDetailsService" METHODS: ----------------------------------------------------------------------------

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
