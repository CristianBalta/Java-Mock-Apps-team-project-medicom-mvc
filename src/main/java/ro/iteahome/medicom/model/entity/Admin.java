package ro.iteahome.medicom.model.entity;

import ro.iteahome.medicom.model.reference.UserRole;
import ro.iteahome.medicom.model.reference.UserStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {

    // FIELDS DEFINED IN USER CLASS.

    public Admin() {
        this.setRole(UserRole.ADMIN);
        this.setStatus(UserStatus.ACTIVE);
    }
}
