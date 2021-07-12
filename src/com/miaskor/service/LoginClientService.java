package com.miaskor.service;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import com.miaskor.exception.ValidationException;
import com.miaskor.mapper.LoginClientMapper;
import com.miaskor.validator.LoginClientValidator;
import com.miaskor.validator.ValidationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginClientService {
    private static final LoginClientService INSTANCE = new LoginClientService();
    private final LoginClientValidator clientValidator = LoginClientValidator.getInstance();
    private final LoginClientMapper clientMapper = LoginClientMapper.getInstance();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    /*
    * client.get() won't throw NoSuchElementException because
    * validator have already checked client
    * */
    public Client loginClient(LoginClientDto clientDto) {
        var valid = clientValidator.isValid(clientDto);
        if (!(valid.isValid())) {
            throw new ValidationException(valid.getErrorMessages());
        }
        var clientLogin = clientDto.getLogin();
        Optional<Client> client = clientDao.readByLogin(clientLogin);
        return client.orElseGet(() -> Client.builder().build());
    }

    public static LoginClientService getInstance() {
        return INSTANCE;
    }
}
