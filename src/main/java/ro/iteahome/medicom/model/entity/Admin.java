package ro.iteahome.medicom.model.entity;

import ro.iteahome.medicom.model.reference.UserRole;
import ro.iteahome.medicom.model.reference.UserStatus;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String lastName;

    @Column(name = "cnp", nullable = false, updatable = false, unique = true, columnDefinition = "VARCHAR(13)")
    private String cnp;

    // NO LICENSE NUMBER (NOT A DOCTOR/NURSE).

    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(5)")
    private UserRole role;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(32)")
    private String password;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(8)")
    private UserStatus status;
    // TODO: Add mechanism for admins to change any user's status to INACTIVE.

    // NO RETYPED PASSWORD.

    public Admin() {
        this.setRole(UserRole.ADMIN);
        this.setStatus(UserStatus.ACTIVE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
