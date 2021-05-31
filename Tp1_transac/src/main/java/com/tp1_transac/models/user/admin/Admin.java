package com.tp1_transac.models.user.admin;

import com.tp1_transac.models.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Admin extends User implements Serializable {

    private String roles;
}
