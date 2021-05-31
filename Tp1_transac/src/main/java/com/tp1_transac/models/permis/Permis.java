package com.tp1_transac.models.permis;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Permis implements Serializable {


    @Id
    @GeneratedValue
    private int id;

    private String typePermis;

    private boolean permisEnfant;

    private LocalDate datePermisVaccin;

    private LocalDate dateExpirationVaccin;

    private LocalDate datePermisTest;

    private LocalDate dateExpirationTest;

    private String region;
}
