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
import java.util.ArrayList;
import java.util.List;

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

// METHODS: ------------------------------------------------------------------------------------------------------------

    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationDTO userRegistrationDTO) {
        return userService.addUser(userRegistrationDTO);
    }

    @PostMapping("/add-consult")
    public ModelAndView addConsult(ConsultDTO consultDTO) {
        ArrayList<ConsultDTO> consultDTOList = new ArrayList<>();
        consultDTOList.add(userService.addConsult(consultDTO));
        return new ModelAndView("consult/home-consult").addObject(consultDTOList);
    }

    @GetMapping("/consult/by-cnp")
    public ModelAndView findConsults(ConsultDTO consultDTO) {

        return new ModelAndView("consult/home-consult")
                .addObject(userService.findAllConsults(consultDTO.getPatient_cnp()));

    }
}
