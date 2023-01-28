package com.dlucchesi.myglic.controller.imp;

import com.dlucchesi.myglic.model.Measure;
import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.model.imp.MeasureImp;
import com.dlucchesi.myglic.service.MeasureService;
import com.dlucchesi.myglic.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/v1/measure")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MeasureControllerImp implements com.dlucchesi.myglic.controller.MeasureController {

    final MeasureService measureService;
    final UserService   userService;

    @Override
    @GetMapping
    @RequestMapping("/userId/{id}")
    public ResponseEntity<?> findByUser(@PathVariable("id") Long id, HttpServletRequest request){
        if (!isNull(id) && id.compareTo(0L) > 0) {
            log.info("Id param {}", id);
            Optional<User> optU = userService.find(id);
            if (optU.isPresent()){
                User user = optU.get();
                Set<Measure> measures = measureService.findByUser(user);
                return ResponseEntity.ok(measures);
            } else {
                log.info("User not found. Id {}", id);
                return ResponseEntity.ok().build();
            }
        } else {
            log.warn("Receive empty req from IP: {}", request.getRemoteAddr());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Override
    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable("id") Long id, HttpServletRequest request){
        if (!isNull(id) && id.compareTo(0L) > 0){
            Optional<Measure> opt = measureService.find(id);
            if (opt.isPresent()) {
                Measure ret = opt.get();
                log.debug("Measure found! Measure {}", ret);
                return ResponseEntity.ok(ret);
            } else {
                log.warn("Measure not found! Id {}", id);
                return ResponseEntity.ok().build();
            }
        } else {
            log.warn("Receive empty req from IP: {}", request.getRemoteAddr());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MeasureImp measure, HttpServletRequest request){
        if (!isNull(measure)){
            Optional<Measure> fromDb = measureService.save(measure);
            if (fromDb.isPresent()){
                Measure ret = fromDb.get();
                log.debug("Measure saved! Measure {}", ret);
                return ResponseEntity.ok(ret);
            } else {
                log.warn("Measure NOT saved! Data {}", measure);
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .header(HttpHeaders.CONTENT_TYPE)
                        .body("Not saved!");
            }
        } else {
            log.warn("Receive empty req from IP: {}", request.getRemoteAddr());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
