package exceptions;

public class PasswordNotValid extends Exception{
    public PasswordNotValid() {
        super("Password is not valid! It must containt small, capital letters and numbers!");
    }

}
