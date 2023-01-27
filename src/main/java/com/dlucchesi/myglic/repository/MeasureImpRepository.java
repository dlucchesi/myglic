package com.dlucchesi.myglic.repository;

import com.dlucchesi.myglic.model.imp.MeasureImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface MeasureImpRepository extends JpaRepository<MeasureImp, Long> {

    Set<MeasureImp> findByDtEntry(Date dtEntry);
}
