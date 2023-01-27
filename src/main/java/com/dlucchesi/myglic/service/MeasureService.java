package com.dlucchesi.myglic.service;

import com.dlucchesi.myglic.model.Measure;

import java.util.Optional;

public interface MeasureService {
    Measure create();

    Optional<Measure> find(Long id);

    Optional<Measure> save(Measure instance);

    Boolean delete(Measure entity, Boolean phys);
}
