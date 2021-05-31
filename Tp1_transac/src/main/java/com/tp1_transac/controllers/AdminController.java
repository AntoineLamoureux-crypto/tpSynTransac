package com.tp1_transac.controllers;

import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import com.tp1_transac.repositories.citoyen.CitoyenEnfantRepository;
import com.tp1_transac.repositories.citoyen.CitoyenRepository;
import com.tp1_transac.repositories.ministere.CitoyenMinistereRepository;
import com.tp1_transac.services.citoyen.CitoyenAdulteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4600")
public class AdminController {

    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    CitoyenMinistereRepository citoyenMinistereRepository;

    @Autowired
    CitoyenEnfantRepository citoyenEnfantRepository;

    @Autowired
    CitoyenAdulteServices citoyenAdulteServices;

    @RequestMapping(value = "/adminPermis/save/adulte", method = RequestMethod.POST)
    public CitoyenAdulte saveAdulte(@RequestBody CitoyenAdulte citoyen) {
        citoyenAdulteServices.inscriptionCitoyenAdulte(citoyen);
        return citoyen;
    }

    @RequestMapping(value = "/adminPermis/save/enfant", method = RequestMethod.POST)
    public CitoyenEnfant saveEnfant(@RequestBody CitoyenEnfant citoyenEnfant) {
        citoyenAdulteServices.inscriptionCitoyenEnfant(citoyenEnfant);
        return citoyenEnfant;
    }

    @RequestMapping(value = "/adminPermis/update/adulte/{id}", method = RequestMethod.PATCH)
    public CitoyenAdulte updateAdulte(@RequestBody CitoyenAdulte citoyen, @PathVariable("id") int id) {
        System.out.println("HELP");
        System.out.println(citoyen.toString());
        System.out.println(citoyenRepository.findCitoyenAdulteById(id).toString());
        System.out.println("HELP");
        citoyenRepository.findCitoyenAdulteById(id).setNom(citoyen.getNom());
        citoyenRepository.findCitoyenAdulteById(id).setPrenom(citoyen.getPrenom());
        citoyenRepository.findCitoyenAdulteById(id).setAge(citoyen.getAge());
        citoyenRepository.findCitoyenAdulteById(id).setEmail(citoyen.getEmail());
        citoyenRepository.findCitoyenAdulteById(id).setSexe(citoyen.getSexe());
        citoyenRepository.findCitoyenAdulteById(id).setNum_telphone(citoyen.getNum_telphone());
        citoyenRepository.save(findCitoyenAdulteById(id));
        return citoyen;
    }
    @RequestMapping(value = "/adminPermis/update/enfant/{id}", method = RequestMethod.PATCH)
    public CitoyenEnfant updateEnfant(@RequestBody CitoyenEnfant citoyen, @PathVariable int id) {
        System.out.println("HELP");
        System.out.println(citoyen.toString());
        citoyenEnfantRepository.findCitoyenEnfantById(id).setNom(citoyen.getNom());
        citoyenEnfantRepository.findCitoyenEnfantById(id).setPrenom(citoyen.getPrenom());
        citoyenEnfantRepository.findCitoyenEnfantById(id).setSexe(citoyen.getSexe());
        citoyenEnfantRepository.findCitoyenEnfantById(id).setAge(citoyen.getAge());
        citoyenEnfantRepository.save(findCitoyenEnfantById(id));
        return citoyen;
    }

    @RequestMapping(value = "/adminPermis/adultes", method = RequestMethod.GET)
    public List<CitoyenAdulte> getAllAdultes(){
        return citoyenRepository.findAll();
    }

    @RequestMapping(value = "/adminPermis/enfants", method = RequestMethod.GET)
    public List<CitoyenEnfant> gettAllEnfants(){
        return citoyenEnfantRepository.findAll();
    }

    @RequestMapping(value = "/adminPermis/adulte/one/{id}", method = RequestMethod.GET)
    public CitoyenAdulte findCitoyenAdulteById(@PathVariable("id") int id){
        return citoyenRepository.findCitoyenAdulteById(id);
    }

    @RequestMapping(value = "/adminPermis/enfant/one/{id}", method = RequestMethod.GET)
    public CitoyenEnfant findCitoyenEnfantById(@PathVariable("id") int id){
        return citoyenEnfantRepository.findCitoyenEnfantById(id);
    }

    @RequestMapping(value = "/adminPermis/delete/adulte/{id}", method = RequestMethod.PATCH)
    public boolean deleteAdulteByID(@PathVariable("id") int id){
        boolean flag = false;
        if (citoyenRepository.findCitoyenAdulteById(id) != null) {
            citoyenRepository.deleteById(id);
            flag  = true;
        }
        return flag;
    }
    @RequestMapping(value = "/adminPermis/delete/enfant/{id}", method = RequestMethod.PATCH)
    public boolean deleteEnfantByID(@PathVariable("id") int id){
        boolean flag = false;
        if (citoyenEnfantRepository.findCitoyenEnfantById(id) != null) {
            citoyenEnfantRepository.deleteById(id);
            flag  = true;
        }
        return flag;
    }
}
