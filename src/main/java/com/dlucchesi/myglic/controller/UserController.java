package com.dlucchesi.myglic.controller;

import com.dlucchesi.myglic.model.imp.LoginImp;
import com.dlucchesi.myglic.model.imp.UserImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @PostMapping
    @RequestMapping("/doLogin")
    ResponseEntity<?> doLogin(LoginImp login, HttpServletRequest request);

    @GetMapping
    @RequestMapping("/{login}")
    ResponseEntity<?> findByLogin(@PathVariable("login") String login, HttpServletRequest request);

    @GetMapping
    @RequestMapping("/{id}")
    ResponseEntity<?> find(@PathVariable("id") Long id, HttpServletRequest request);

    @PostMapping
    ResponseEntity<?> save(@RequestBody UserImp user, HttpServletRequest request);
}
