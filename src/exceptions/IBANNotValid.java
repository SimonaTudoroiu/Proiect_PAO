package exceptions;

public class IBANNotValid extends Exception{
    public IBANNotValid() {
        super("IBAN is not valid!");
    }

}
