package com.tp1_transac.controllers;

import com.tp1_transac.models.permis.Permis;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.permis.PermisRepository;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import com.tp1_transac.services.permis.PermisServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.security.Permission;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PermisSanteController {
    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    CitoyenEnfantRepository citoyenEnfantRepository;

    @Autowired
    PermisRepository permisRepository;

    @Autowired
    CitoyenAdulteServices citoyenAdulteServices;

    @RequestMapping(value = "/permisSante/{username}/{password}", method = RequestMethod.GET)
    public CitoyenAdulte login(@PathVariable("username") String username, @PathVariable("password") String password){
        return citoyenRepository.findCitoyenAdulteByUsernameAndPassword(username, password);
    }

    @RequestMapping(value = "/permisSante", method = RequestMethod.POST)
    public CitoyenAdulte subscribe(@RequestBody CitoyenAdulte citoyen) {
        citoyenAdulteServices.inscriptionCitoyenAdulte(citoyen);
        return citoyen;
    }

    @RequestMapping(value = "/permisSanteE", method = RequestMethod.POST)
    public CitoyenEnfant saveEnfant(@RequestBody CitoyenEnfant citoyenEnfant) {
        citoyenAdulteServices.inscriptionCitoyenEnfant(citoyenEnfant);
        return citoyenEnfant;
    }

    @RequestMapping(value = "/enfantService/{numSocial}/{email}", method = RequestMethod.GET)
    public List<CitoyenEnfant> findAllChilds(@PathVariable("numSocial") String numSocial, @PathVariable("email") String email) {
        return citoyenEnfantRepository.findAllByCitoyenAdulteId(citoyenRepository.findCitoyenAdulteByNumSocialAndEmail(numSocial, email).getId());
    }

    @RequestMapping(value = "/permisSante/renew/{numSocial}", method = RequestMethod.PATCH)
    public boolean renewAdultPermit(@PathVariable("numSocial") String numSocial) {
        boolean flag = false;
        if (citoyenAdulteServices.renewDureePermis(numSocial)) {
            flag = true;
        }
        return flag;
    }

    @RequestMapping(value = "/enfantService/renew/{numSocial}", method = RequestMethod.PATCH)
    public boolean renewChildPermit(@PathVariable("numSocial") String numSocial) {
        boolean flag = false;
        if (citoyenAdulteServices.renewDureePermis(numSocial)) {
            flag = true;
        }
        return flag;
    }

    @RequestMapping(value = "/permisSante/id/{num_social}", method = RequestMethod.GET)
    public int findPermitId(@PathVariable("num_social") String num_social) {
        if (citoyenRepository.findCitoyenAdulteByNumSocial(num_social) == null) {
            return citoyenEnfantRepository.findCitoyenEnfantByNumSocial(num_social).getPermis().getId();
        }
        else
            return citoyenRepository.findCitoyenAdulteByNumSocial(num_social).getPermis().getId();
    }

    @RequestMapping(value = "/permisSante/permis/{num_social}", method = RequestMethod.GET)
    public Permis findPermit(@PathVariable("num_social") String num_social) {
        if (citoyenRepository.findCitoyenAdulteByNumSocial(num_social) == null) {
            return citoyenEnfantRepository.findCitoyenEnfantByNumSocial(num_social).getPermis();
        }
        else
            return citoyenRepository.findCitoyenAdulteByNumSocial(num_social).getPermis();
    }
}
