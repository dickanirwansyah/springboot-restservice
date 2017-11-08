package command.service.exception;

public class HandlingAlreadyException extends RuntimeException{

    public HandlingAlreadyException(final String pesan){
        super(pesan);
    }
}
