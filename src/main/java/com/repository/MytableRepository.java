package com.repository;

import com.model.Mytable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MytableRepository extends JpaRepository<Mytable, Integer> {
}