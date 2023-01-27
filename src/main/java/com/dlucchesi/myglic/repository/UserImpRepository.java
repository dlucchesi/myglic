package com.dlucchesi.myglic.repository;

import com.dlucchesi.myglic.model.imp.UserImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImpRepository extends JpaRepository<UserImp, Long> {

    Optional<UserImp> findByLogin(String login);
}
