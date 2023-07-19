package models;

import models.contracts.User;
import utils.ValidationHelpers;

public class UserImpl implements User {

    public static final int USERS_NAME_MIN_LENGTH = 5;
    public static final int USERS_NAME_MAX_LENGTH = 15;

    private String userName;


    public UserImpl(String userName) {
        setUserName(userName);
    }

    private void setUserName(String userName) {
        ValidationHelpers.validateStringLength(userName, USERS_NAME_MIN_LENGTH, USERS_NAME_MAX_LENGTH, "User name");
        this.userName = userName;
    }

    @Override
    public String getName() {
        return userName;
    }

    public String toString() {
        return String.format("%s", getName());
    }
}

