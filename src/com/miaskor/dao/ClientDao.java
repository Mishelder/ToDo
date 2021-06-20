package com.miaskor.dao;

import java.util.Optional;

public interface ClientDao<K,T> extends Dao<K,T>{
    Optional<T> readByLogin(String login);
    Optional<T> readByEmail(String email);
}
