package core.exceptions;

public class ExtractFieldValueException extends RuntimeException{
 public ExtractFieldValueException(String message){
     super(message);
 }
 public ExtractFieldValueException(IllegalAccessException exception){
     super(exception);
 }
}
