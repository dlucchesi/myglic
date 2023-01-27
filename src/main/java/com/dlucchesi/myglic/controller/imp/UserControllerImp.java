package com.dlucchesi.myglic.controller.imp;

import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.model.imp.UserImp;
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

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserControllerImp implements com.dlucchesi.myglic.controller.UserController {

    final UserService   userService;

    @Override
    @GetMapping
    @RequestMapping("/login/{login}")
    public ResponseEntity<?> findByLogin(@PathVariable("login") String login, HttpServletRequest request){
        if (!isNull(login)) {
            Optional<User> optU = userService.findByLogin(login);
            if (optU.isPresent()){
                User user = optU.get();
                log.debug("User found! User {}", user);
                return ResponseEntity.ok(user);
            } else {
                log.info("User not found. Login {}", login);
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
            Optional<User> optU = userService.find(id);
            if (optU.isPresent()) {
                User ret = optU.get();
                log.debug("User found! User {}", ret);
                return ResponseEntity.ok(ret);
            } else {
                log.warn("User not found! Id {}", id);
                return ResponseEntity.ok().build();
            }
        } else {
            log.warn("Receive empty req from IP: {}", request.getRemoteAddr());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserImp user, HttpServletRequest request){
        if (!isNull(user)){
            Optional<User> fromDb = userService.save(user);
            if (fromDb.isPresent()){
                User ret = fromDb.get();
                log.debug("User saved! User {}", ret);
                return ResponseEntity.ok(ret);
            } else {
                log.warn("User NOT saved! Data {}", user);
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
