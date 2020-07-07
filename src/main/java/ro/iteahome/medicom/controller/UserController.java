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
import ro.iteahome.medicom.model.entity.User;
import ro.iteahome.medicom.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;

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

    @GetMapping("/find-consults")
    public String showFindForm(ConsultDTO consultDTO) {
        return "consult/find-form";
    }

    @GetMapping("/add-consult")
    public String showAddForm(ConsultDTO consultDTO) {
        return "consult/add-form";
    }

    @GetMapping("/institution-screen")
    public String[] showGetInstitutionScreen (User user) {
        return userService.getInstitutions(user.getCnp());
    }

// METHODS: ------------------------------------------------------------------------------------------------------------


    @PostMapping("/institution-screen")
    public ModelAndView getInstitutionScreen(String[] cuiArray) {

        return new ModelAndView("/institution-screen").addObject(cuiArray);
    }


    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

}
