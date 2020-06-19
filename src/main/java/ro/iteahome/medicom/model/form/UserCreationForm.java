package ro.iteahome.medicom.model.form;

import ro.iteahome.medicom.model.reference.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserCreationForm { // TODO: Add a sign up process using this form.

    // NO ID. THE SYSTEM WILL AUTOMATICALLY ASSIGN AN ID TO THE NEW USER.

    @NotNull(message = "FIRST NAME CANNOT BE EMPTY.")
    private String firstName;

    @NotNull(message = "LAST NAME CANNOT BE EMPTY.")
    private String lastName;

    @NotNull(message = "CNP CANNOT BE EMPTY.")
    @Pattern(regexp = "[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}", message = "INVALID CNP")
    private String cnp;

    @NotNull(message = "MEDICAL/NURSING LICENSE NUMBER CANNOT BE EMPTY.")
    private String licenseNo;

    @NotNull(message = "ROLE CANNOT BE EMPTY.")
    private UserRole role;

    @NotNull(message = "EMAIL CANNOT BE EMPTY.")
    @Email(regexp = ".+@.+\\.\\w+", message = "INVALID EMAIL ADDRESS")
    private String email;

    @NotNull(message = "PASSWORD CANNOT BE EMPTY.")
    @Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,32})", message = "INVALID PASSWORD")
    private String password;

    @NotNull(message = "PASSWORD CANNOT BE EMPTY.")
    @Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,32})", message = "INVALID PASSWORD")
    private String retypedPassword;
    // TODO: Add mechanism to check if the two passwords match.

    // NO STATUS. THE SYSTEM WILL AUTOMATICALLY ASSIGN "ACTIVE" STATUS TO THE NEW USER.

    public UserCreationForm() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }
}
