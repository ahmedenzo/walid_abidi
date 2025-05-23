package com.service;

import com.model.Plat;
import com.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatService {

    @Autowired
    private PlatRepository platRepository;

    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    public Optional<Plat> getPlatById(Integer id) {
        return platRepository.findById(id);
    }

    public Plat savePlat(Plat plat) {
        return platRepository.save(plat);
    }

    public void deletePlat(Integer id) {
        platRepository.deleteById(id);
    }
}