package ro.iteahome.medicom.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserLoginDTO {

// FIELDS: -------------------------------------------------------------------------------------------------------------

    @NotBlank(message = "EMAIL CANNOT BE NULL.")
    @Email(regexp = ".+@.+\\..+", message = "INVALID EMAIL ADDRESS")
    private String email;

    @NotBlank(message = "PASSWORD CANNOT BE NULL.")
    @Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,100})", message = "INVALID PASSWORD")
    private String password;

// METHODS: ------------------------------------------------------------------------------------------------------------

    public UserLoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
