package com.tp1_transac.permisTest;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import com.tp1_transac.services.permis.PermisServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages = {"com.Tp1_transac.services"})
public class PermisTests {

    public final String citoyenAdulteNumSocial = "12343";
    public final String citoyenAdulteNom = "Antoine";
    public final String citoyenAdulteUserName = "AntoineZolos";
    public final String citoyenAdultePrenom = "Lamoureux";
    public final String citoyenAdulteSexe = "Homme";
    public final Integer citoyenAdulteAge = 30;
    public final String citoyenAdulteEmail = "antoine3wls@gmail.com";
    public final String citoyenNumTelephone = "514-555-333";

    public final String citoyenEnfantNumSocial = "123543213";
    public final String citoyenEnfantNom = "Vincent";
    public final String citoyenEnfantPrenom = "Lamoureux";
    public final String citoyenEnfantSexe = "Homme";


    @Autowired
    private CitoyenAdulteServices citoyenAdulteServices;

    @Autowired
    private PermisServices permisServices;

    CitoyenAdulte citoyenAdulte = new CitoyenAdulte();

    CitoyenEnfant citoyenEnfant = new CitoyenEnfant();

    final private String filePathQrAdulte = "c:/WEB/420/citoyen/adulte/qr/";
    final private String filePathQrEnfant = "c:/WEB/420/citoyen/enfant/qr/";

    final private String filePathPdfAdulte = "c:/WEB/420/citoyen/adulte/pdf/";
    final private String filePathPdfEnfant = "c:/WEB/420/citoyen/enfant/pdf/";

    @Test
    @BeforeAll
    void contextLoads(){
        citoyenEnfant.setNumSocial(citoyenEnfantNumSocial);
        citoyenEnfant.setNom(citoyenEnfantNom);
        citoyenEnfant.setPrenom(citoyenEnfantPrenom);
        citoyenEnfant.setSexe(citoyenEnfantSexe);
        citoyenEnfant.setParentusername(citoyenAdulteUserName);

        citoyenAdulte.setNumSocial(citoyenAdulteNumSocial);
        citoyenAdulte.setNom(citoyenAdulteNom);
        citoyenAdulte.setPrenom(citoyenAdultePrenom);
        citoyenAdulte.setSexe(citoyenAdulteSexe);
        citoyenAdulte.setAge(citoyenAdulteAge);
        citoyenAdulte.setEmail(citoyenAdulteEmail);
        citoyenAdulte.setNum_telphone(citoyenNumTelephone);


        citoyenAdulteServices.inscriptionCitoyenAdulte(citoyenAdulte);
        citoyenAdulteServices.inscriptionCitoyenEnfant(citoyenEnfant);

        Permis permisEnfant = new Permis();

        permisEnfant.setPermisEnfant(true);
        permisEnfant.setRegion("Quebec");
        permisEnfant.setTypePermis("VA");

        Permis permisAdulte = new Permis();

        permisAdulte.setPermisEnfant(false);
        permisAdulte.setRegion("Quebec");
        permisAdulte.setTypePermis("TE");

        citoyenEnfant.setPermis(permisEnfant);
        citoyenAdulte.setPermis(permisAdulte);
    }

    @Test
    void testCreateNewQrAdulte() throws Exception {
        Path path = FileSystems.getDefault().getPath(filePathQrAdulte +  citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".PNG");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        permisServices.createNewQRAdulte(citoyenAdulte);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testCreateNewQrEnfant() throws Exception {
        Path path = FileSystems.getDefault().getPath(filePathQrEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".PNG");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        permisServices.createNewQREnfant(citoyenEnfant);

        Permis permisEnfant = new Permis();

        permisEnfant.setPermisEnfant(true);
        permisEnfant.setRegion("Quebec");
        permisEnfant.setTypePermis("VA");
        citoyenEnfant.setPermis(permisEnfant);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testCreateNewPdfAdulte() throws Exception {
        Path path = FileSystems.getDefault().getPath(filePathPdfAdulte +  citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".pdf");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        permisServices.createNewQRAdulte(citoyenAdulte);

        permisServices.createNewPDFAdulte(citoyenAdulte);

        Permis permisAdulte = new Permis();

        permisAdulte.setPermisEnfant(false);
        permisAdulte.setRegion("Quebec");
        permisAdulte.setTypePermis("TE");

        citoyenAdulte.setPermis(permisAdulte);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testCreateNewPdfEnfant() throws Exception {
        Path path = FileSystems.getDefault().getPath(filePathPdfEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".pdf");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        permisServices.createNewQREnfant(citoyenEnfant);

        permisServices.createNewPDFEnfant(citoyenEnfant);

        Permis permisEnfant = new Permis();

        permisEnfant.setPermisEnfant(true);
        permisEnfant.setRegion("Quebec");
        permisEnfant.setTypePermis("VA");
        citoyenEnfant.setPermis(permisEnfant);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testRenewQrAdulte() throws Exception {
        permisServices.createNewQRAdulte(citoyenAdulte);
        citoyenAdulte.setNom("Pierre");
        permisServices.renewQrAdulte(citoyenAdulte);

        Permis permisAdulte = new Permis();

        permisAdulte.setPermisEnfant(false);
        permisAdulte.setRegion("Quebec");
        permisAdulte.setTypePermis("TE");
        citoyenAdulte.setPermis(permisAdulte);

        Path path = FileSystems.getDefault().getPath(filePathQrAdulte +  citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".PNG");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testRenewQrEnfant() throws Exception {
        permisServices.createNewQREnfant(citoyenEnfant);
        citoyenAdulte.setNom("PierreEnfant");
        permisServices.renewQREnfant(citoyenEnfant);

        Path path = FileSystems.getDefault().getPath(filePathQrEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".PNG");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        Assertions.assertTrue(pathExists);
    }

    @Test
    void testRenewPdfAdulte() throws Exception {
        permisServices.createNewQRAdulte(citoyenAdulte);
        citoyenAdulte.setNom("Pierre");
        permisServices.renewQrAdulte(citoyenAdulte);

        Path path = FileSystems.getDefault().getPath(filePathPdfAdulte +  citoyenAdulte.getPermis().getTypePermis() + citoyenAdulte.getNom() + ".pdf");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        Assertions.assertTrue(pathExists);
    }
    @Test
    void testRenewPdfEnfant() throws Exception {
        permisServices.createNewQREnfant(citoyenEnfant);
        citoyenAdulte.setNom("PierreEnfant");
        permisServices.renewQREnfant(citoyenEnfant);
        Permis permisEnfant = new Permis();

        permisEnfant.setPermisEnfant(true);
        permisEnfant.setRegion("Quebec");
        permisEnfant.setTypePermis("VA");
        citoyenEnfant.setPermis(permisEnfant);

        Path path = FileSystems.getDefault().getPath(filePathPdfEnfant +  citoyenEnfant.getPermis().getTypePermis() + citoyenEnfant.getNom() + ".pdf");
        boolean pathExists =
                Files.exists(path,
                        LinkOption.NOFOLLOW_LINKS);

        Assertions.assertTrue(pathExists);
    }
}
