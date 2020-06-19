package ro.iteahome.medicom.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.iteahome.medicom.model.form.UserCreationForm;
import ro.iteahome.medicom.model.form.UserCredentialsForm;
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

    @GetMapping("/add-form")
    public String showUserAddForm(UserCreationForm userCreationForm) {
        return "user/user-add-form";
    }

    @GetMapping("/admins/add-form")
    public String showAdminAddForm() {
        // TODO: Add mechanism to check that the link was accessed by a logged in admin.
        return "user/admin-add-form";
    }

// METHODS: ------------------------------------------------------------------------------------------------------------

    // TODO: Add CRUD methods for Users.
    // TODO: Incorporate exception handling.

    @PostMapping
    public ModelAndView add(@Valid UserCreationForm userCreationForm) {
        userService.add(userCreationForm);
        ModelAndView loginMV = new ModelAndView("login.html");
        UserCredentialsForm userCredentialsForm = modelMapper.map(userCreationForm, UserCredentialsForm.class);
        loginMV.addObject(userCredentialsForm);
        return loginMV;
    }
}
