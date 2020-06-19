package ro.iteahome.medicom.model.entity;

import ro.iteahome.medicom.model.reference.UserRole;
import ro.iteahome.medicom.model.reference.UserStatus;

public class Admin extends User {

    public Admin() {
        this.setRole(UserRole.ADMIN);
        this.setStatus(UserStatus.ACTIVE);
    }
}
