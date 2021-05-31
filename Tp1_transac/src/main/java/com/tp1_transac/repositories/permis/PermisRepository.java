package com.tp1_transac.repositories.permis;

import com.tp1_transac.models.permis.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisRepository extends JpaRepository<Permis, Integer> {

    Permis findById(int id);
}
