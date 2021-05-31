package com.tp1_transac.controllers;

import com.tp1_transac.models.ministere.Ministere;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.ministere.CitoyenMinistereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MinistereController {

    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    CitoyenMinistereRepository citoyenMinistereRepository;

    @Autowired
    CitoyenEnfantRepository citoyenEnfantRepository;


    @RequestMapping(value = "/ministere/validity/{nasm}", method = RequestMethod.GET)
    public boolean checkCitoyenValidity(@PathVariable("nasm") String nasm){
        return citoyenMinistereRepository.findMinistereByNumSocial(nasm).isNumSocialValid();
    }

    @RequestMapping(value = "/ministere/{nas}", method = RequestMethod.GET)
    public Integer validateCitoyenInfo(@PathVariable("nas") String nas) {
        int response = -1;
        Ministere citoyenInfo = citoyenMinistereRepository.findMinistereByNumSocial(nas);
        if (citoyenInfo.isPermisTestValid() && !citoyenInfo.isPermisSanteValid()) {
            response = 1;
        }
        else if (citoyenInfo.isPermisTestValid() && citoyenInfo.isPermisSanteValid()) {
            response = 0;
        }
        else if (citoyenInfo.isPermisSanteValid() && !citoyenInfo.isPermisTestValid()) {
            response = 2;
        }
        return response;
    }
}
