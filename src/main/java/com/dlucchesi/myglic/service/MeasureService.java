package com.dlucchesi.myglic.service;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;

import java.util.Optional;
import java.util.Set;

public interface MeasureService {
    Measure create();

    Set<Measure> findByUser(User user);

    Optional<Measure> find(Long id);

    Optional<Measure> save(Measure instance);

    Boolean delete(Measure entity, Boolean phys);
}
