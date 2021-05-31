package com.tp1_transac;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.permis.PermisRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ComApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private CitoyenRepository citoyenRepository;
    @Autowired
    private CitoyenEnfantRepository citoyenEnfantRepository;
    @Autowired
    private PermisRepository permisRepository;

    @BeforeAll
    public void insertDataCitoyen() {
        CitoyenAdulte cit1 = new CitoyenAdulte();
        Permis p1 = new Permis();

        p1.setTypePermis("VACCIN");

        cit1.setUsername("Toto"); cit1.setPassword("toto1234"); cit1.setPermis(p1);


        CitoyenEnfant citoyenEnfant = new CitoyenEnfant();
        Permis p2 = new Permis();

        p2.setTypePermis("TEST");

        //citoyenEnfant.setUsername("Toto"); citoyenEnfant.setPassword("toto1234"); citoyenEnfant.setPermis(p2);

        permisRepository.saveAll(Arrays.asList(p1, p2));

        citoyenRepository.save(cit1);
        citoyenEnfantRepository.save(citoyenEnfant);
    }

    @Test
    @Disabled
    public void testFindAllUsers(){
        Assertions.assertTrue(citoyenRepository.findAll().size() == 1);
    }
    @Test
    @Disabled
    public void testFindAllPermis(){
        Assertions.assertTrue(permisRepository.findAll().size() == 1);
    }

    @Test
    public void testFindByLogin() {
        Assertions.assertNotNull(citoyenRepository.findCitoyenAdulteByUsernameAndPassword("Toto", "toto1234"));
    }

}
