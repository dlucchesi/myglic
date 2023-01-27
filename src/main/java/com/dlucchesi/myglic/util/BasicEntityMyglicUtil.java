package com.dlucchesi.myglic.util;

import com.dlucchesi.myglic.model.BasicMyglicEntity;
import org.hibernate.annotations.Comment;

import static java.util.Objects.isNull;

@Comment("basicEntityMyglicUtil")
public class BasicEntityMyglicUtil {

    public static Boolean isNew(BasicMyglicEntity entity){
        return isNull(entity.getId());
    }

    public static void makeNew(BasicMyglicEntity entity){
        if (!isNull(entity)){
            entity.setIsActive(Boolean.TRUE);
            entity.setIsDeleted(Boolean.FALSE);
        }
    }

    public static void makeDeleted(BasicMyglicEntity entity){
        if (!isNull(entity)){
            entity.setIsActive(Boolean.FALSE);
            entity.setIsDeleted(Boolean.TRUE);
        }
    }

    public static void makeInactive(BasicMyglicEntity entity){
        if (!isNull(entity)){
            entity.setIsActive(Boolean.FALSE);
        }
    }
}
