package com.fichaexpress.back_end.repositories;

import com.fichaexpress.back_end.entities.FichaAbyssal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaAbyssalRepository extends JpaRepository<FichaAbyssal,Long> {
}
