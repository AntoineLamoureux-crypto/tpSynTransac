package com.tp1_transac.models.user.citoyen;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.User;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class CitoyenAdulte extends User implements Serializable {

    private String numSocial;

    private String nom;

    private String prenom;

    private String sexe;

    private int age;

    private String email;

    private String num_telphone;

    private String type_permis;

    @OneToOne
    @JoinColumn()
    private Permis permis;
}
