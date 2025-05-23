package com.service;
import com.model.PlatMenu;
import com.model.PlatMenuId;
import com.repository.PlatMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatMenuService {

    @Autowired
    private PlatMenuRepository platMenuRepository;

    public List<PlatMenu> getAllPlatMenus() {
        return platMenuRepository.findAll();
    }

    public Optional<PlatMenu> getPlatMenuById(PlatMenuId id) {
        return platMenuRepository.findById(id);
    }

    public PlatMenu savePlatMenu(PlatMenu platMenu) {
        return platMenuRepository.save(platMenu);
    }

    public void deletePlatMenu(PlatMenuId id) {
        platMenuRepository.deleteById(id);
    }
}