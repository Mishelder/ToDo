package com.miaskor.dao;

import java.util.List;
import java.util.Optional;

public interface Dao <K,T>{
    List<T> findAll();
    T create(T object);
    Optional<T> read(K index);
    void update(T object);
    boolean delete(K index);
}
