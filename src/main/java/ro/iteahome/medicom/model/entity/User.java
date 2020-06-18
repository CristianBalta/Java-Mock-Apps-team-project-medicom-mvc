package ro.iteahome.medicom.model.entity;

import ro.iteahome.medicom.model.reference.UserRole;
import ro.iteahome.medicom.model.reference.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "EMAIL CANNOT BE EMPTY.")
    @Email(regexp = ".+@.+\\.\\w+", message = "INVALID EMAIL ADDRESS")
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String email;

    @NotNull(message = "PASSWORD CANNOT BE EMPTY.")
    @Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,32})", message = "INVALID PASSWORD")
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(32)")
    private String password;

    @NotNull(message = "ROLE CANNOT BE EMPTY.")
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(5)")
    private UserRole role;

    @NotNull(message = "STATUS CANNOT BE EMPTY.")
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(8)")
    private UserStatus status;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
