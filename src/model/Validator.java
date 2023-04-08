package model;

import exceptions.*;

public class Validator {
    public static boolean isName  (String name) throws NameNotValid {
        return name.matches("[a-zA-Z]+");
    }

    public static boolean isEmail(String email) throws EmailNotValid {
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
    }

    public static boolean isPassword(String password) throws PasswordNotValid {
        return password.matches("[a-zA-Z0-9]");
    }

    public static boolean isPhone(String phone) throws PhoneNumberNotValid {
        return phone.matches("[0-9]+");
    }

    public static boolean isAddress(String address) throws AddressNotValid {
        return address.matches("[a-zA-Z0-9]+");
    }

    public static boolean isCurrency(String currency) throws CurrencyNotValid {
        return currency.matches("[a-zA-Z]+");
    }

    public static boolean isIBAN(String IBAN) throws IBANNotValid {
        return IBAN.matches("[a-zA-Z0-9]+");
    }

    public static boolean isAmount(String amount) throws AmountNotValid {
        return amount.matches("[0-9]+");
    }

    public static boolean isDescription(String description) throws DescriptionNotValid {
        return description.matches("[a-zA-Z0-9]+");
    }

    public static boolean isCategory(String category)  throws CategoryNotValid{
        return category.matches("[a-zA-Z0-9]+");
    }

    public static boolean isDate(String date) throws DateNotValid {
        return date.matches("^\\d{2}-\\d{2}-\\d{4}$");
    }

    public static boolean isType(String type) throws TypeNotValid {
        return type.matches("[a-zA-Z0-9]+");
    }

    public static boolean isSource(String source) throws SourceNotValid {
        return source.matches("[a-zA-Z0-9]+");
    }

    public static boolean isDestination(String destination) throws DestinationNotValid {
        return destination.matches("[a-zA-Z0-9]+");
    }

    public static boolean isId(String id) throws IdNotValid {
        return id.matches("[a-zA-Z0-9]+");
    }

    public static boolean isNumber(String number) throws NumberNotValid {
        return number.matches("[0-9]+");
    }

}
