package ro.iteahome.medicom.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.iteahome.medicom.config.rest.RestConfig;
import ro.iteahome.medicom.exception.business.NotNhsRegisteredException;
import ro.iteahome.medicom.model.dto.UserRegistrationDTO;
import ro.iteahome.medicom.model.entity.Role;
import ro.iteahome.medicom.model.entity.User;
import ro.iteahome.medicom.repository.UserRepository;

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

    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1);
        setNhsRole(user);
        userRepository.save(user);
    }

// OTHER METHODS: ------------------------------------------------------------------------------------------------------

    private Boolean isNhsDoctor(String cnp) {
        return restTemplate.exchange(
                restConfig.getSERVER_URL() + restConfig.getDOCTORS_URI() + "/existence/by-cnp/" + cnp,
                HttpMethod.GET,
                new HttpEntity<>(restConfig.buildAuthHeaders()),
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

// OVERRIDDEN "UserDetailsService" METHODS: ----------------------------------------------------------------------------

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
