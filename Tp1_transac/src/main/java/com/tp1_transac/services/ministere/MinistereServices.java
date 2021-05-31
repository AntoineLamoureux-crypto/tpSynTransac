package com.tp1_transac.services.ministere;

import com.tp1_transac.repositories.ministere.CitoyenMinistereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistereServices {

    @Autowired
    CitoyenMinistereRepository citoyenMinistereRepository;

    public boolean validerNumSocialCitoyenMinistere(String numSocial) {
        return citoyenMinistereRepository.findMinistereByNumSocial(numSocial).isNumSocialValid();
    }
}
