package ro.iteahome.medicom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.iteahome.medicom.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

// DEPENDENCIES: -------------------------------------------------------------------------------------------------------

    @Autowired
    private UserService adminService;

// LINK "GET" REQUESTS: ------------------------------------------------------------------------------------------------

// METHODS: ------------------------------------------------------------------------------------------------------------

    // TODO: Add CRUD methods for Users.
    // TODO: Incorporate exception handling.
}
