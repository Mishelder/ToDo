package com.miaskor.validator;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.RegistrationClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationClientValidator implements Validator<RegistrationClientDto>{

    private static final RegistrationClientValidator INSTANCE = new RegistrationClientValidator();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    @Override
    public ValidationResult isValid(RegistrationClientDto object) {
        ValidationResult validationResult = new ValidationResult();
        String login = object.getLogin();
        String email = object.getEmail();
        String password = object.getPassword();

        if(login.length()<3){
            validationResult.add(new ErrorMessage("login","login might be > 2"));
        }else if(clientDao.readByLogin(login).isPresent()){
            validationResult.add(new ErrorMessage("login","login already exist"));
        }else if(login.length()>128){
            validationResult.add(new ErrorMessage("login","login is invalid"));
        }
        if(clientDao.readByEmail(email).isPresent()){
            validationResult.add(new ErrorMessage("email","email is already exist"));
        }else if(email.length()>128){
            validationResult.add(new ErrorMessage("email","email is invalid"));
        }
        if(password.length()>32){
            validationResult.add(new ErrorMessage("password","password is invalid"));
        }
        return validationResult;
    }

    public static RegistrationClientValidator getInstance(){
        return INSTANCE;
    }
}
