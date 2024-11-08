package ph.devcon.flag.core.component.exception;

public class InvalidRequestException extends RuntimeException{ 
    
    public InvalidRequestException(final String msg){
        super(msg);
    }
}