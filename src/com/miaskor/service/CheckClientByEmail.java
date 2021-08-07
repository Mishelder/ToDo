package com.miaskor.service;

import com.miaskor.dao.ClientDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckClientByEmail {
    private static final CheckClientByEmail INSTANCE = new CheckClientByEmail();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    public boolean checkByEmail(String str){
        return clientDao.readByEmail(str).isPresent();
    }

    public static CheckClientByEmail getInstance(){
        return INSTANCE;
    }
}
