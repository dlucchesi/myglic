package com.dlucchesi.myglic.controller;

import com.dlucchesi.myglic.model.imp.MeasureImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MeasureController {
    @GetMapping
    @RequestMapping("/userId/{id}/all")
    ResponseEntity<?> findAllByUser(@PathVariable("id") Long id, HttpServletRequest request);

    @GetMapping
    @RequestMapping("/userId/{id}")
    ResponseEntity<?> findByUser(@PathVariable("id") Long id, HttpServletRequest request);

    @GetMapping
    @RequestMapping("/{id}")
    ResponseEntity<?> find(@PathVariable("id") Long id, HttpServletRequest request);

    @PostMapping
    ResponseEntity<?> save(@RequestBody MeasureImp measure, HttpServletRequest request);

    @PostMapping
    @RequestMapping("/inactivate/{id}")
    ResponseEntity<?> inactivate(@PathVariable("id") Long id, HttpServletRequest request);
}
