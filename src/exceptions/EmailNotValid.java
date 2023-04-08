package exceptions;

public class EmailNotValid extends Exception{
    public EmailNotValid() {
        super("Email is not valid!");
    }
}
