package utils;

import exceptions.InvalidUserInputException;

import java.util.List;

public class ValidationHelpers {
    private static final String STRING_LENGTH_ERROR = "%s must be between %d and %d characters long.";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";


    public static void validateIntRange(int minLength, int maxLength, int actualLength, String type) {
        if (actualLength < minLength || actualLength > maxLength) {
            throw new InvalidUserInputException(String.format(STRING_LENGTH_ERROR, type, minLength, maxLength));
        }
    }

    public static void validatePositiveNumber(int value, String errorMessage) {
        if (value < 0) {
            throw new InvalidUserInputException(errorMessage);
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String type) {
        validateIntRange(minLength, maxLength, stringToValidate.length(), type);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters || list.size() > expectedNumberOfParameters) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS,
                    expectedNumberOfParameters, list.size()));
        }
    }
}
