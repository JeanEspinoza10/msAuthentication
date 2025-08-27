package co.com.bancolombia.model.exception;


public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public static DomainException emptyField(String fieldName) {
        return new DomainException("The field '" + fieldName + "' cannot be empty");
    }

    public static DomainException invalidEmail(){
        return new DomainException("Email format invalid");
    }
    public static DomainException invalidBaseSalaryMount() {
        return new DomainException("The field 'baseSalary' must be between 0 and 15,000,000");
    }
    public static DomainException invalidBaseSalaryFormat() {
        return new DomainException("The field 'baseSalary' must be a valid number");
    }
    public static DomainException duplicateEmail(){
       return new DomainException("Can't create with Email");
    }
}
