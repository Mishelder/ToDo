package com.miaskor.validator;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.RegistrationClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.miaskor.util.ValidationVariable.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationClientValidator implements Validator<RegistrationClientDto>{

    private static final RegistrationClientValidator INSTANCE = new RegistrationClientValidator();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    @Override
    public ValidationResult isValid(RegistrationClientDto object) {
        ValidationResult validationResult = new ValidationResult();
        String givenLogin = object.getLogin();
        String givenEmail = object.getEmail();
        String givenPassword = object.getPassword();

        boolean isNotPass = checkFields(validationResult, givenLogin, givenEmail, givenPassword);
        if(isNotPass)
            return validationResult;

        checkFieldsAtDB(validationResult, givenLogin, givenEmail);

        return validationResult;
    }

    private void checkFieldsAtDB(ValidationResult validationResult, String givenLogin, String givenEmail) {
        if(clientDao.readByLogin(givenLogin).isPresent()) {
            validationResult.add(new ErrorMessage("login", "login is already exist"));
        }else if(clientDao.readByEmail(givenEmail).isPresent()){
            validationResult.add(new ErrorMessage("email","email is already exist"));
        }
    }

    private boolean checkFields(ValidationResult validationResult, String givenLogin, String givenEmail, String givenPassword) {
        boolean isNotPass = false;
        if(givenLogin.length()<MIN_LENGTH_LOGIN){
            validationResult.add(new ErrorMessage("login","login might be longer"));
            isNotPass = true;
        }else if(givenLogin.length()>MAX_LENGTH_LOGIN){
            validationResult.add(new ErrorMessage("login","login is invalid"));
            isNotPass = true;
        }else if(givenEmail.length()>MAX_LENGTH_EMAIL){
            validationResult.add(new ErrorMessage("email","email is invalid"));
            isNotPass = true;
        }else if(givenPassword.length()>MAX_LENGTH_PASSWORD){
            validationResult.add(new ErrorMessage("password","password is invalid"));
            isNotPass = true;
        }
        return isNotPass;
    }

    public static RegistrationClientValidator getInstance(){
        return INSTANCE;
    }
}
