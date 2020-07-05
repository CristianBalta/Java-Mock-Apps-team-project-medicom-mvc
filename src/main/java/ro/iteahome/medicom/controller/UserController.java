package ro.iteahome.medicom.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/find-Consults")
    public String showAddForm(ConsultDTO consultDTO) {
        return "consult/find-form";
    }

// METHODS: ------------------------------------------------------------------------------------------------------------

    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        userService.addUser(userRegistrationDTO);
        return "home";
    }

    @PostMapping("/add-consult")
    public void addConsult(ConsultDTO consultDTO) {
        userService.addConsult(consultDTO);
    }

    @GetMapping("/consult/by-cnp")
    public ModelAndView findConsults(ConsultDTO consultDTO) {

        return new ModelAndView("consult/home-consult")
                .addObject(userService.findAllConsults(consultDTO.getPatient_cnp()));

    }
}
