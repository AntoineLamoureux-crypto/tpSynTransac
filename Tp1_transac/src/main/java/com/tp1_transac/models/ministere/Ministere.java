package com.tp1_transac.models.ministere;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Ministere implements Serializable {

    @Id
    @GeneratedValue
    private String numSocial;

    private boolean isNumSocialValid;

    private boolean isPermisSanteValid;

    private boolean isPermisTestValid;

}
