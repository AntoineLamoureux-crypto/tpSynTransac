package com.tp1_transac.models.user;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

}
