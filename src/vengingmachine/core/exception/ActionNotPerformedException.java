package vengingmachine.core.exception;

public class ActionNotPerformedException extends RuntimeException{

	private static final long serialVersionUID = 5110777559353200081L;
	
	String message;

	public ActionNotPerformedException(String message){
		this.message = message;
	}
}
