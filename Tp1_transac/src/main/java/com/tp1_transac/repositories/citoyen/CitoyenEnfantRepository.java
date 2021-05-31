package com.tp1_transac.repositories.citoyen;
import com.tp1_transac.models.user.citoyen.CitoyenAdulte;
import com.tp1_transac.models.user.citoyen.CitoyenEnfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CitoyenEnfantRepository extends JpaRepository<CitoyenEnfant, Integer> {
    CitoyenEnfant findCitoyenEnfantById(Integer id);

    CitoyenEnfant findCitoyenEnfantByNomAndPrenom(String nom, String prenom);

    List<CitoyenEnfant> getAllByAge(int age);

    List<CitoyenEnfant> findAllByCitoyenAdulteId(int id);

    CitoyenEnfant findCitoyenEnfantByNumSocial(String numSocial);

    CitoyenEnfant deleteById(int id);
}
