package com.tp1_transac.services.citoyen;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.ministere.CitoyenMinistereRepository;
import com.tp1_transac.repositories.permis.PermisRepository;
import com.tp1_transac.services.ministere.MinistereServices;
import com.tp1_transac.services.permis.PermisServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Locale;

@Service
public class CitoyenAdulteServices {

    @Autowired
    private CitoyenRepository citoyenRepository;

    @Autowired
    private CitoyenEnfantRepository citoyenEnfantRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private PermisServices permisServices;

    @Autowired
    private CitoyenMinistereRepository citoyenMinistereRepository;


    public boolean isLoginExist(String log, String mp){
        return citoyenRepository.findCitoyenAdulteByUsernameAndPassword(log, mp) != null;
    }


    public boolean login(String log, String mp) {
        return isLoginExist(log, mp);
    }


    @Transactional
    public boolean inscriptionCitoyenAdulte(CitoyenAdulte citoyenAdulte){
        if (citoyenMinistereRepository.findMinistereByNumSocial(citoyenAdulte.getNumSocial()) == null) {
            return false;
        }
        Permis permis = new Permis();
        permis.setPermisEnfant(false);
        if (citoyenAdulte.getType_permis().toLowerCase(Locale.ROOT).equals("vaccin")) {
            permis.setTypePermis("VA");
            permis.setDatePermisVaccin(LocalDate.now());
            permis.setDateExpirationVaccin(LocalDate.now().plusMonths(PermisServices.dureVaccin));
        }
        else {
            permis.setTypePermis("TE");
            permis.setDatePermisTest(LocalDate.now());
            permis.setDateExpirationTest(LocalDate.now().plusDays(PermisServices.dureTest));
        }
        permisRepository.save(permis);
        citoyenAdulte.setPermis(permis);
        citoyenRepository.save(citoyenAdulte);
        try {
            permisServices.createNewQRAdulte(citoyenAdulte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Transactional
    public boolean inscriptionCitoyenEnfant(CitoyenEnfant citoyenEnfant) {
        if (citoyenMinistereRepository.findMinistereByNumSocial(citoyenEnfant.getNumSocial()) == null) {
            return false;
        } else {
            Permis permis = new Permis();
            permis.setPermisEnfant(true);
            if (citoyenEnfant.getType_permis().toLowerCase(Locale.ROOT).equals("vaccin")) {
                permis.setTypePermis("VA");
                permis.setDatePermisVaccin(LocalDate.now());
                permis.setDateExpirationVaccin(LocalDate.now().plusMonths(PermisServices.dureVaccin));
            } else {
                permis.setTypePermis("TE");
                permis.setDatePermisTest(LocalDate.now());
                permis.setDateExpirationTest(LocalDate.now().plusDays(PermisServices.dureTest));
            }
            citoyenEnfant.setCitoyenAdulteId(citoyenRepository.findCitoyenAdulteByUsername(citoyenEnfant.getParentusername()).getId());
            permisRepository.save(permis);
            citoyenEnfant.setPermis(permis);
            citoyenEnfantRepository.save(citoyenEnfant);
            try {
                permisServices.createNewQREnfant(citoyenEnfant);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public boolean renewDureePermis(String numSocial) {
        boolean flag = false;
        if (citoyenMinistereRepository.findMinistereByNumSocial(numSocial) == null) {
            return flag;
        }
        else {
            if (citoyenRepository.findCitoyenAdulteByNumSocial(numSocial) == null) {
                CitoyenEnfant citoyenEnfant = citoyenEnfantRepository.findCitoyenEnfantByNumSocial(numSocial);
                if (citoyenEnfant.getPermis().getTypePermis().equals("VA")) {
                    citoyenEnfant.getPermis().setDatePermisVaccin(LocalDate.now());
                    citoyenEnfant.getPermis().setDateExpirationVaccin(LocalDate.now().plusMonths(PermisServices.dureVaccin));
                }
                else if (citoyenEnfant.getPermis().getTypePermis().equals("TE")) {
                    citoyenEnfant.getPermis().setDatePermisTest(LocalDate.now());
                    citoyenEnfant.getPermis().setDateExpirationTest(LocalDate.now().plusDays(PermisServices.dureTest));
                }
                citoyenEnfantRepository.save(citoyenEnfant);
                flag = true;
            }
            else {
                CitoyenAdulte citoyenAdulte = citoyenRepository.findCitoyenAdulteByNumSocial(numSocial);
                if (citoyenAdulte.getPermis().getTypePermis().equals("VA")) {
                    citoyenAdulte.getPermis().setDatePermisVaccin(LocalDate.now());
                    citoyenAdulte.getPermis().setDateExpirationVaccin(LocalDate.now().plusMonths(PermisServices.dureVaccin));
                }
                else if (citoyenAdulte.getPermis().getTypePermis().equals("TE")){
                    citoyenAdulte.getPermis().setDatePermisTest(LocalDate.now());
                    citoyenAdulte.getPermis().setDateExpirationTest(LocalDate.now().plusDays(PermisServices.dureTest));
                }
                citoyenRepository.save(citoyenAdulte);
                flag = true;
            }
        }

        System.out.println(flag);
        return flag;
    }
}
