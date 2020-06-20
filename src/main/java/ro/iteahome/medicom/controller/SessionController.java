package ro.iteahome.medicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.iteahome.medicom.model.form.UserCredentialsForm;
import ro.iteahome.medicom.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/session")
public class SessionController {

// DEPENDENCIES: -------------------------------------------------------------------------------------------------------

    @Autowired
    UserService userService;

// LINK "GET" REQUESTS: ------------------------------------------------------------------------------------------------

    @GetMapping("/login-form")
    public String showLoginForm(UserCredentialsForm userCredentialsForm) {
        return "session/login-form";
    }

// METHODS: ------------------------------------------------------------------------------------------------------------

    @PostMapping("/login-credentials")
    public String logIn(@Valid UserCredentialsForm userCredentialsForm) {
        userService.logIn(userCredentialsForm.getEmail(), userCredentialsForm.getPassword());
        return "session/home";
    }

    @GetMapping("/the-end")
    public String signOut(UserCredentialsForm userCredentialsForm) {
        userService.signOut();
        return "session/login-form";
    }
}
