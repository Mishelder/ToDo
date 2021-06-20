package com.miaskor.validator;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginClientValidator implements Validator<LoginClientDto>{

    private static final LoginClientValidator INSTANCE = new LoginClientValidator();
    private final ClientDaoImpl clientDaoImpl = ClientDaoImpl.getInstance();

    @Override
    public ValidationResult isValid(LoginClientDto object) {
        ValidationResult validationResult = new ValidationResult();
        String login = object.getLogin();
        String password = object.getPassword();
        Optional<Client> client = clientDaoImpl.readByLogin(login);
        if(login.length()>128){
            validationResult.add(new ErrorMessage("login","login or password is invalid"));
        }else if(client.isEmpty()){
            validationResult.add(new ErrorMessage("login","login or password is invalid"));
        }else if(password.length()>32){
            validationResult.add(new ErrorMessage("password","password is invalid"));
        }else if(!(client.get().getPassword().equals(password))){
            validationResult.add(new ErrorMessage("password","password is invalid"));
        }
        return validationResult;
    }

    public static LoginClientValidator getInstance(){
        return INSTANCE;
    }
}
