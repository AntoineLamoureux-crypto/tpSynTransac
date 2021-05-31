package com.tp1_transac;
import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import com.tp1_transac.services.permis.PermisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComApplication {

    @Autowired
    CitoyenAdulteServices citoyenAdulteServices;

    @Autowired
    CitoyenRepository citoyenRepository;

    public static void main(String[] args) {
        SpringApplication.run(ComApplication.class, args);
    }

        /*PermisServices permisServices = new PermisServices();

        CitoyenAdulte citoyenAdulte = new CitoyenAdulte();
        citoyenAdulte.setSexe("Homme");
        citoyenAdulte.setNum_social("1232435");
        citoyenAdulte.setPrenom("Lamoureux");
        citoyenAdulte.setNom("Antoi");
        citoyenAdulte.setUsername("AntoineTest");
        citoyenAdulte.setPassword("Antoine1234");
        citoyenAdulte.setEmail("antoine@gmail.com");
        citoyenAdulte.setAge(20);
        citoyenAdulte.setNum_telphone("514-232-1231");

        CitoyenEnfant citoyenEnfant = new CitoyenEnfant();

        citoyenEnfant.setNum_social("321432");
        citoyenEnfant.setNom("Anas");
        citoyenEnfant.setPrenom("Berlam");
        citoyenEnfant.setCitoyenAdulteId(citoyenAdulte.getId());

        Permis permis = new Permis();


        Permis permisEnfant = new Permis();

        permisEnfant.setTypePermis("VA");

        citoyenEnfant.setPermis(permisEnfant);

        citoyenAdulte.setPermis(permis);

        permis.setTypePermis("TE");

        permisServices.createNewQRAdulte(citoyenAdulte);

        citoyenAdulte.setNom("Pierre");

        permisServices.renewQrAdulte(citoyenAdulte);

        permisServices.createNewQREnfant(citoyenEnfant);

        permisServices.createNewPDFEnfant(citoyenEnfant);

        //citoyenAdulteServices.inscriptionCitoyenAdulte(citoyenAdulte, "Vaccin");*/
}
