package com.miaskor.service;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.RegistrationClientDto;
import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.RegistrationClientMapper;
import com.miaskor.validator.RegistrationClientValidator;
import com.miaskor.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationClientService {

    private static final RegistrationClientService INSTANCE = new RegistrationClientService();
    private final RegistrationClientValidator registrationClientValidator =
            RegistrationClientValidator.getInstance();
    private final RegistrationClientMapper registrationClientMapper = 
            RegistrationClientMapper.getInstance();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();

    public Client registerClient(RegistrationClientDto registrationClientDto) {
        ValidationResult validationResult = registrationClientValidator.isValid(registrationClientDto);
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrorMessages());
        }
        var client = registrationClientMapper.map(registrationClientDto);
        client = clientDao.create(client);
        return client;
    }

    public static RegistrationClientService getInstance() {
        return INSTANCE;
    }
}
