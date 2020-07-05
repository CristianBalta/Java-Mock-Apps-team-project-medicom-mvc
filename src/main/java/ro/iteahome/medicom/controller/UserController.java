package ro.iteahome.medicom.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.iteahome.medicom.model.dto.ConsultDTO;
import ro.iteahome.medicom.model.dto.UserRegistrationDTO;
import ro.iteahome.medicom.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

// DEPENDENCIES: -------------------------------------------------------------------------------------------------------

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

// LINK "GET" REQUESTS: ------------------------------------------------------------------------------------------------

    @GetMapping("/registration")
    public String showUserRegistrationForm(UserRegistrationDTO userRegistrationDTO) {
        return "registration";
    }

// METHODS: ------------------------------------------------------------------------------------------------------------

    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

    @PostMapping("/add-consult")
    public void addConsult(ConsultDTO consultDTO) {
        userService.addConsult(consultDTO);
    }

    @GetMapping("/find-Consults")
    public ConsultDTO[] findConsults(String cnp) {
        return userService.findAllConsults(cnp);
    }
}
