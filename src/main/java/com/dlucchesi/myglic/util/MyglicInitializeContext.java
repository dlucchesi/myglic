package com.dlucchesi.myglic.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyglicInitializeContext implements InitializingBean {

    @Autowired
    private ApplicationContext appContext;


    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPasswd;

    public Boolean init(){
        Boolean ret = Boolean.TRUE;
        log.info("MyEnvironment init");
        log.info("Verify Database url");
        if (isNull(dbUrl) || dbUrl.isEmpty()){
            log.error("###### ATTENTION --------> Database url is empty!");
            log.error("###### CORRECT --------> set command to var MYGLIC_DB_URL");
            return Boolean.FALSE;
        } else {
            log.info("Database url is {}", dbUrl);
        }
        log.info("Verify Database user");
        if (isNull(dbUser) || dbUser.isEmpty()){
            log.error("###### ATTENTION --------> Database user is empty!");
            log.error("###### CORRECT --------> set command to var MYGLIC_DB_USER");
            return Boolean.FALSE;
        } else {
            log.info("Database user is {}", dbUser);
        }
        log.info("Verify Database password");
        if (isNull(dbPasswd) || dbPasswd.isEmpty()){
            log.error("###### ATTENTION --------> Database password is empty!");
            log.error("###### CORRECT --------> set command to var MYGLIC_DB_PASS");
            return Boolean.FALSE;
        } else {
            log.info("Database password is OK");
        }

        return ret;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!init()){
            log.error("Shutting down MyGlicApplication");
            int returnCode = 1;
            SpringApplication.exit(appContext, () -> returnCode);
        }

    }
}
