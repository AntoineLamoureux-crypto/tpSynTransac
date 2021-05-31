package com.tp1_transac.repositories.ministere;

import com.tp1_transac.models.ministere.Ministere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitoyenMinistereRepository extends JpaRepository<Ministere, Integer> {
    Ministere findMinistereByNumSocial(String numSocial);

}
