package com.repository;

import com.model.PlatMenu;
import com.model.PlatMenuId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatMenuRepository extends JpaRepository<PlatMenu, PlatMenuId> {
}