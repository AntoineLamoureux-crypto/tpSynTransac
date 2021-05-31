package com.tp1_transac.UserTests.citoyenEnfantTest;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.permis.PermisRepository;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CitoyenEnfantTests {

    @Autowired
    private CitoyenEnfantRepository citoyenEnfantRepository;

    @Autowired
    private CitoyenAdulteServices citoyenAdulteServices;

    public final String citoyenAdulteUserName = "AntoineZolos";
    public final String citoyenEnfantNumSocial = "123543";
    public final String citoyenEnfantNom = "Vincent";
    public final String citoyenEnfantPrenom = "Lamoureux";
    public final String citoyenEnfantSexe = "Femme";


    @Test
    @BeforeAll
    void contextLoads() {
        CitoyenEnfant citoyenEnfant = new CitoyenEnfant();
        citoyenEnfant.setNumSocial(citoyenEnfantNumSocial);
        citoyenEnfant.setParentusername(citoyenAdulteUserName);
        citoyenEnfant.setNom(citoyenEnfantNom);
        citoyenEnfant.setPrenom(citoyenEnfantPrenom);
        citoyenEnfant.setSexe(citoyenEnfantSexe);

        Permis permisEnfant = new Permis();

        permisEnfant.setPermisEnfant(true);
        permisEnfant.setRegion("Quebec");

        citoyenEnfant.setPermis(permisEnfant);

        citoyenAdulteServices.inscriptionCitoyenEnfant(citoyenEnfant);
    }

    @Test
    public void testFindAllCitoyenEnfant(){
        Assertions.assertEquals(citoyenEnfantRepository.findAll().size(), 1);
    }

    @Test
    public void testInscriptionCitoyenEnfant() {
        Assertions.assertNotNull(citoyenEnfantRepository.findCitoyenEnfantByNomAndPrenom("Vincent", "Lamoureux"));
    }

}
