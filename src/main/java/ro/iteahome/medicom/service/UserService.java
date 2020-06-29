package ro.iteahome.medicom.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.iteahome.medicom.config.RestUrlConfig;
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
    private RoleService roleService;

    @Autowired
    ModelMapper modelMapper;

// FIELDS: -------------------------------------------------------------------------------------------------------------

    private final String DOCTORS_URL = RestUrlConfig.SERVER_ROOT_URL + "/doctors";
    private final String NURSES_URL = RestUrlConfig.SERVER_ROOT_URL + "/nurses";

// C.R.U.D. METHODS: ---------------------------------------------------------------------------------------------------

    // TODO: Add CRUD methods for Users.

    public void addUser(UserRegistrationDTO userCreationForm) {
        User user = modelMapper.map(userCreationForm, User.class);
        user.setStatus(1);
        setNhsRole(user);
        userRepository.save(user);
    }

// OTHER METHODS: ------------------------------------------------------------------------------------------------------

    private Boolean isNhsDoctor(String cnp) {
        return restTemplate.getForObject(DOCTORS_URL + "/existence/by-cnp/" + cnp, Boolean.class);
    }

    private Boolean isNhsNurse(String cnp) {
        return restTemplate.getForObject(NURSES_URL + "/existence/by-cnp/" + cnp, Boolean.class);
    }

    private void setNhsRole(User user) {
        if (isNhsDoctor(user.getCnp())) {
            Role role = roleService.findByName("DOCTOR");
            user.setRole(role);
        } else if (isNhsNurse(user.getCnp())) {
            Role role = roleService.findByName("NURSE");
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
