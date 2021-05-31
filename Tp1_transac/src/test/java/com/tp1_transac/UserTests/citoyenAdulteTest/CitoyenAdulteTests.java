package com.tp1_transac.UserTests.citoyenAdulteTest;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.permis.PermisRepository;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages = {"com.Tp1_transac.services"})
public class CitoyenAdulteTests {

    public final String citoyenAdulteUserName = "AntoineZolos";
    public final String citoyenAdultePassword = "Antoine1234";
    public final String citoyenAdulteNumSocial = "123543";
    public final String citoyenAdulteNom = "Antoine";
    public final String citoyenAdultePrenom = "Lamoureux";
    public final String citoyenAdulteSexe = "Homme";
    public final Integer citoyenAdulteAge = 30;
    public final String citoyenAdulteEmail = "antoine3wls@gmail.com";
    public final String citoyenNumTelephone = "514-555-333";


    @Test
    @BeforeAll
    void contextLoads() {
        CitoyenAdulte citoyenAdulte = new CitoyenAdulte();
        citoyenAdulte.setNumSocial(citoyenAdulteNumSocial);
        citoyenAdulte.setNom(citoyenAdulteNom);
        citoyenAdulte.setPrenom(citoyenAdultePrenom);
        citoyenAdulte.setSexe(citoyenAdulteSexe);
        citoyenAdulte.setAge(citoyenAdulteAge);
        citoyenAdulte.setEmail(citoyenAdulteEmail);
        citoyenAdulte.setNum_telphone(citoyenNumTelephone);

        Permis permisAdulte = new Permis();

        permisAdulte.setPermisEnfant(false);
        permisAdulte.setRegion("Quebec");

        citoyenAdulte.setPermis(permisAdulte);

        citoyenAdulteServices.inscriptionCitoyenAdulte(citoyenAdulte);
    }
    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private PermisRepository permisRepository;
    @Autowired
    private CitoyenAdulteServices citoyenAdulteServices;


    @Test
    public void testFindAllCiotyenAdulte(){
        Assertions.assertEquals(citoyenRepository.findAll().size(), 1);
    }

    @Test
    public void testFindCitoyenAdulteByUserName() {
        Assertions.assertNotNull(citoyenRepository.findCitoyenAdulteByUsernameAndPassword("AntoineZolos", "Antoine1234"));
    }

    @Test
    public void testInscriptionCitoyenAdulte() {
        Assertions.assertNotNull(citoyenRepository.findCitoyenAdulteByUsername(citoyenAdulteUserName));
    }

    @Test
    public void testLoginCitoyenAdulte() {
        Assertions.assertTrue(citoyenAdulteServices.login(citoyenAdulteUserName, citoyenAdultePassword));
    }
}
