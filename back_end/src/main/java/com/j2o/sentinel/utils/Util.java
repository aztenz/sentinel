package com.j2o.sentinel.utils;

import com.j2o.sentinel.exception.ItemNotFoundException;
import com.j2o.sentinel.model.User;
import com.j2o.sentinel.repository.GenericRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class Util {
    private static final String ERROR_COPYING_PROPERTIES = "Error copying properties: ";
    private static final String ITEM_NOT_FOUND = "Item not found";
    private static final String ERROR_VALIDATING_ITEM_EXISTENCE = "Error Validating Item existence ";

    public static int getCurrentUserId() {
        return ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getId();
    }

    public static <T> T cloneObject(
            Object source,
            Class<T> targetType
    ) {
        T targetObject;
        try {
            targetObject = targetType.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            throw new RuntimeException(ERROR_COPYING_PROPERTIES + e.getMessage(), e);
        }
        return targetObject;
    }

    public static <T, R> void copyProperties(
            T source,
            R target
    ) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new RuntimeException(ERROR_COPYING_PROPERTIES + e.getMessage(), e);
        }
    }

    public static <E, ID> void validateItemExistence(
            ID id,
            GenericRepository<E, ID> genericRepository
    ) {
        try {
            if(!genericRepository.existsById(id))
                throw new ItemNotFoundException(ITEM_NOT_FOUND);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException(ITEM_NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(ERROR_VALIDATING_ITEM_EXISTENCE + e.getMessage(), e);
        }
    }
}
