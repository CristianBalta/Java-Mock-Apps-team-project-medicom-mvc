package ro.iteahome.medicom.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.iteahome.medicom.model.dto.UserLoginDTO;
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

//    @GetMapping("/login")
//    public String showLoginForm(UserLoginDTO userLoginDTO) {
//        return "login";
//    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(UserRegistrationDTO userRegistrationDTO) {
        return "registration";
    }

//    @GetMapping("/admins/add-form")
//    public String showAdminAddForm() {
//        return "user/admin-add-form";
//    }

// METHODS: ------------------------------------------------------------------------------------------------------------

    @PostMapping
    public String add(@Valid UserRegistrationDTO userRegistrationDTO) {
        userService.addUser(userRegistrationDTO);
        return "login";
    }
}
