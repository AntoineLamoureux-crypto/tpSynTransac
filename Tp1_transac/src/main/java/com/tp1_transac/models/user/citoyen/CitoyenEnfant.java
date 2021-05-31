package com.tp1_transac.models.user.citoyen;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class CitoyenEnfant implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String numSocial;

    private String parentusername;

    private String nom;

    private String prenom;

    private String sexe;

    private int age;

    private int citoyenAdulteId;


    private String type_permis;

    @OneToOne
    @JoinColumn()
    private Permis permis;
}
