package com.dlucchesi.myglic.service.imp;

import com.dlucchesi.myglic.model.User;
import com.dlucchesi.myglic.model.imp.UserImp;
import com.dlucchesi.myglic.repository.UserImpRepository;
import com.dlucchesi.myglic.util.BasicEntityMyglicUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service("userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImp implements com.dlucchesi.myglic.service.UserService {

    final UserImpRepository userImpRepository;

    @Override
    public User create(){
        return new UserImp();
    }

    @Override
    public Set<User> find(){
        return userImpRepository.findAll().stream()
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> find(Long id){
        Optional<User> ret = Optional.empty();
        Optional<UserImp> opt = userImpRepository.findById(id);
        if (opt.isPresent()){
            UserImp tmp = opt.get();
            User entity = tmp;
            ret = Optional.of(entity);
        }
        return ret;
    }

    @Override
    public Optional<User> findByLogin(String login){
        Optional<User> ret = Optional.empty();
        Optional<UserImp> opt = userImpRepository.findByLogin(login);
        if (opt.isPresent()){
            UserImp tmp = opt.get();
            User entity = tmp;
            ret = Optional.of(entity);
        }
        return ret;
    }

    @Override
    public Optional<User> save(User instance){
        Optional<User> ret = Optional.empty();
        if (!isNull(instance)){
            UserImp userImp = validateInstance(instance);
            if (!isNull(userImp)){
                if (validateObj(userImp)){
                    ret = Optional.of(userImpRepository.save(userImp));
                }
            } else {
                log.error("Instance error! Instance: {}", instance);
            }
        } else {
            log.warn("User empty!");
        }
        return ret;
    }

    @Override
    public Boolean delete(User entity, Boolean phys){
        Boolean ret = Boolean.TRUE;
        Boolean bPhys = Boolean.FALSE;
        if (!isNull(entity)){
            Optional<UserImp> opt = userImpRepository.findById(entity.getId());
            if (opt.isPresent()) {
                if (!isNull(phys)){
                    bPhys = phys;
                } else {
                    log.warn("Boolean to physical delete is empty! Assuming FALSE");
                }
                UserImp userImp = opt.get();
                if (!bPhys){
                    BasicEntityMyglicUtil.makeDeleted(userImp);
                    userImpRepository.save(userImp);
                } else {
                    log.warn("** ATTENTION: Deleting physically! User: {}", entity);
                    userImpRepository.delete(userImp);
                }
            } else {
                log.error("User to delete, not found");
            }
        } else {
            log.warn("User empty!");
            ret = Boolean.FALSE;
        }
        return ret;
    }

    private UserImp validateInstance(User instance) {
        if (!isNull(instance) && instance instanceof UserImp){
            return (UserImp) instance;
        }
        return null;
    }

    private Boolean validateObj(UserImp imp) {
        Boolean ret = Boolean.TRUE;
        if (!isNull(imp)){
            String login = imp.getLogin();
            if (isNull(login) || login.trim().length() == 0){
                log.warn("User validation error! Login empty. User: {}", imp);
                ret = Boolean.FALSE;
            } else {
                String passwd = imp.getPasswd();
                if (isNull(passwd) || passwd.trim().length() == 0){
                    log.warn("User validation error! Password empty. User: {}", imp);
                    ret = Boolean.FALSE;
                }
            }
        }
        return ret;
    }
}
