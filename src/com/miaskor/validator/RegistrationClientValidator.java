package com.miaskor.validator;

import com.miaskor.dao.ClientDaoImpl;
import com.miaskor.dto.RegistrationClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.miaskor.util.ValidationVariable.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationClientValidator implements Validator<RegistrationClientDto> {

    private static final RegistrationClientValidator INSTANCE = new RegistrationClientValidator();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance(false);

    @Override
    public ValidationResult isValid(RegistrationClientDto object) {
        ValidationResult validationResult = new ValidationResult();
        String givenLogin = object.getLogin().replaceAll(" ", "");
        String givenEmail = object.getEmail().replaceAll(" ", "");
        String givenPassword = object.getPassword().replaceAll(" ", "");
        checkSpaces(object, validationResult, givenLogin, givenEmail, givenPassword);
        checkFields(validationResult, givenLogin, givenEmail, givenPassword);
        if (!validationResult.isValid())
            return validationResult;
        checkFieldsAtDB(validationResult, givenLogin, givenEmail);
        return validationResult;
    }

    private void checkSpaces(RegistrationClientDto object, ValidationResult validationResult, String givenLogin, String givenEmail, String givenPassword) {
        if (givenLogin.length() != object.getLogin().length()) {
            validationResult.add(new ErrorMessage("login", "please remove space(s)"));
        } else if (givenEmail.length() != object.getEmail().length()) {
            validationResult.add(new ErrorMessage("email", "please remove space(s)"));
        } else if (givenPassword.length() != object.getPassword().length()) {
            validationResult.add(new ErrorMessage("password", "please remove space(s)"));
        }
    }

    private void checkFieldsAtDB(ValidationResult validationResult, String givenLogin, String givenEmail) {
        if (clientDao.readByLogin(givenLogin).isPresent()) {
            validationResult.add(new ErrorMessage("login", "login is already exist"));
        } else if (clientDao.readByEmail(givenEmail).isPresent()) {
            validationResult.add(new ErrorMessage("email", "email is already exist"));
        }
    }

    private void checkFields(ValidationResult validationResult, String givenLogin, String givenEmail, String givenPassword) {
        if (givenLogin.length() < MIN_LENGTH_LOGIN) {
            validationResult.add(new ErrorMessage("login", "login might be longer"));
        } else if (givenLogin.length() > MAX_LENGTH_LOGIN) {
            validationResult.add(new ErrorMessage("login", "login is invalid"));
        } else if (givenEmail.length() > MAX_LENGTH_EMAIL) {
            validationResult.add(new ErrorMessage("email", "email is invalid"));
        } else if (givenPassword.length() > MAX_LENGTH_PASSWORD) {
            validationResult.add(new ErrorMessage("password", "password is invalid"));
        } else if (givenPassword.length() <= MIN_LENGTH_PASSWORD) {
            validationResult.add(new ErrorMessage("password", "password must contains 8 symbols"));
        }
    }

    public static RegistrationClientValidator getInstance() {
        return INSTANCE;
    }
}
