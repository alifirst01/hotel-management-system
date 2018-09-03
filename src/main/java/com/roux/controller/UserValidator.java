package com.roux.controller;

import com.roux.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class UserValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Enter first name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Enter last name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Enter username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Enter email");

        if (HtmlValidate(user.getFirstName())) {
            errors.rejectValue("firstName", "field.notallowed");
        }
        if (HtmlValidate(user.getLastName())) {
            errors.rejectValue("lastName", "field.notallowed");
        }
        if (HtmlValidate(user.getUsername())) {
            errors.rejectValue("username", "field.notallowed");
        }
    }

    public static boolean HtmlValidate(String check) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_/]*");
        Matcher matcher = pattern.matcher(check);
        if (!matcher.matches()) return true;
        return false;
    }
}
