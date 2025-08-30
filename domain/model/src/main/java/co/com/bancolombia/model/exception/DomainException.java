package co.com.bancolombia.model.exception;


import co.com.bancolombia.model.config.Messages;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public static DomainException emptyField(String fieldName) {
        return new DomainException(Messages.EMPTY_FIELD);
    }

    public static DomainException invalidEmail(){
        return new DomainException(Messages.INVALID_USER_EMAIL);
    }

    public static DomainException invalidBaseSalaryMount() {
        return new DomainException(Messages.INVALID_BASE_SALARY_MOUNT);
    }

    public static DomainException invalidBaseSalaryFormat() {
        return new DomainException(Messages.INVALID_BASE_SALARY_FORMAT);
    }

    public static DomainException duplicateEmail(){
       return new DomainException(Messages.DUPLICATE_USER_EMAIL);
    }
}
