package com.edu.transplantdataapi.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.Optional;

@Service
public abstract class EntityManager {

    private CrudRepository<Entity, Long> repo;

    // @Autowired
//    public EntityManager(CrudRepository<Entity, Long> repo) {
//        this.repo = repo;
//    }

    public Optional<Entity> findById(Long id) {
        return repo.findById(id);
    }

    public Iterable<Entity> findAll() {
        return repo.findAll();
    }

    public Entity save(Entity entity) {
        return repo.save(entity);
    }
}
