package utils;

import exceptions.InvalidUserInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ParsingHelpers {

    private static final String INVALID_NUMBER_FIELD_MESSAGE = "";
    private static final String INVALID_LOCALDATE_FIELD_MESSAGE = "";

    public static int tryParseInteger(String valueToParse, String parameterName) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(String.format(INVALID_NUMBER_FIELD_MESSAGE, parameterName));
        }
    }

    public static LocalDate tryParseLocalDate(String valueToParse, String parameterName) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(valueToParse, formatter);
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(String.format(INVALID_LOCALDATE_FIELD_MESSAGE, parameterName));
        }
    }
    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

}