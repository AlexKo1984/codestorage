package com.ank.codestorage.service;

import com.ank.codestorage.model.AbstractEntity;

import java.util.List;

public interface CommonStorage <E extends AbstractEntity> {
    E findById(Long id);

    List<E> findAll();

    E create(E data);

    E update(E data);

    default void delete(Long id) {
        throw new UnsupportedOperationException("Операция удаление не поддерживается.");
    };
}