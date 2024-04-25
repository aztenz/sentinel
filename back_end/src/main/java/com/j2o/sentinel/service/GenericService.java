package com.j2o.sentinel.service;

import com.j2o.sentinel.exception.ItemNotFoundException;
import com.j2o.sentinel.repository.GenericRepository;
import com.j2o.sentinel.utils.Util;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class GenericService<
        E, ID, R extends GenericRepository<E, ID>,
        RQ_POST, RQ_PUT, RSP_LI, RSP_DETAILED, RSP_POST, RSP_PUT> {
    private final R genericRepository;
    private final Class<E> eClass;
    private final Class<RSP_POST> rspPostClass;
    private final Class<RSP_PUT> rspPutClass;
    private final Class<RSP_LI> rspLiClass;
    private final Class<RSP_DETAILED> rspDetailedClass;

    public void deleteById(ID id) {
        try {
            genericRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("error deleting item " + e.getMessage(), e);
        }
    }

    public RSP_PUT update(RQ_PUT request) {
        E entity = Util.cloneObject(request, eClass);
        entity = genericRepository.save(entity);
        return Util.cloneObject(entity, rspPutClass);
    }

    public RSP_POST create(RQ_POST request) {
        E entity = Util.cloneObject(request, eClass);
        entity = genericRepository.save(entity);
        return Util.cloneObject(entity, rspPostClass);
    }

    public List<RSP_LI> getAll() {
        List<E> entities = genericRepository.findAll();
        List<RSP_LI> responses = new ArrayList<>();
        entities.forEach(entity -> responses.add(Util.cloneObject(entity, rspLiClass)));
        return responses;
    }

    public RSP_DETAILED getItem(ID id) {
        E entity = genericRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("couldn't find item"));
        return Util.cloneObject(entity, rspDetailedClass);
    }
}
