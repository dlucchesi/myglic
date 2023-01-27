package com.dlucchesi.myglic.facade.imp;

import com.dlucchesi.myglic.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userFacade")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserFacadeImp {

    final UserService   userService;




}
