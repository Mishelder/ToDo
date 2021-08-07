package com.miaskor.service;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.UpdateClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateClientService {
    private static final UpdateClientService INSTANCE = new UpdateClientService();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    public boolean updateClient(UpdateClientDto clientDto){
        var client = clientDao.readByEmail(clientDto.getEmail());
        return clientDao.update(client.get());
    }

    public static UpdateClientService getInstance(){
        return INSTANCE;
    }
}
