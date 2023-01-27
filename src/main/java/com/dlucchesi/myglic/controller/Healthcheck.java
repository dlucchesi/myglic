package com.dlucchesi.myglic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface Healthcheck {
    @GetMapping
    ResponseEntity checkAll();
}
