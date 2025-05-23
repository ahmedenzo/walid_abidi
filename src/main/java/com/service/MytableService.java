package com.service;

import com.model.Mytable;
import com.repository.MytableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MytableService {

    @Autowired
    private MytableRepository mytableRepository;

    public List<Mytable> getAllTables() {
        return mytableRepository.findAll();
    }

    public Optional<Mytable> getTableById(Integer id) {
        return mytableRepository.findById(id);
    }

    public Mytable saveTable(Mytable mytable) {
        return mytableRepository.save(mytable);
    }

    public void deleteTable(Integer id) {
        mytableRepository.deleteById(id);
    }
}