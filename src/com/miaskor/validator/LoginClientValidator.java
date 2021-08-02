package com.miaskor.validator;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.LoginClientDto;
import com.miaskor.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.miaskor.util.ValidationVariable.MAX_LENGTH_LOGIN;
import static com.miaskor.util.ValidationVariable.MAX_LENGTH_PASSWORD;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginClientValidator implements Validator<LoginClientDto>{

    private static final LoginClientValidator INSTANCE = new LoginClientValidator();
    private final ClientDaoImpl clientDaoImpl = ClientDaoImpl.getInstance(false);

    @Override
    public ValidationResult isValid(LoginClientDto object) {
        ValidationResult validationResult = new ValidationResult();
        String givenLogin = object.getLogin();
        String givenPassword = object.getPassword();
        checkFields(validationResult, givenLogin, givenPassword);
        if(!validationResult.isValid())
            return validationResult;
        checkFieldsAtDB(validationResult, givenLogin, givenPassword);
        return validationResult;
    }

    private void checkFieldsAtDB(ValidationResult validationResult, String givenLogin, String givenPassword) {
        Optional<Client> client = clientDaoImpl.readByLogin(givenLogin);
        if(client.isEmpty()){
            validationResult.add(new ErrorMessage("login","login or password is invalid"));
        }else if(!(client.get().getPassword().equals(givenPassword))){
            validationResult.add(new ErrorMessage("password","password is invalid"));
        }
    }

    private void checkFields(ValidationResult validationResult, String givenLogin, String givenPassword) {
        if(givenLogin.length()> MAX_LENGTH_LOGIN){
            validationResult.add(new ErrorMessage("login","login or password is invalid"));
        }else if(givenPassword.length()> MAX_LENGTH_PASSWORD){
            validationResult.add(new ErrorMessage("password","password is invalid"));
        }
    }

    public static LoginClientValidator getInstance(){
        return INSTANCE;
    }
}
