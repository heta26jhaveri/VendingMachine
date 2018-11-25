package vengingmachine.core.exception;

/**
 * No sufficient change available exception
 * @author jhaverih
 *
 */
public class InsufficientChangeException extends RuntimeException{
	
	private static final long serialVersionUID = 8054300545522341194L;
	String message;

	public InsufficientChangeException(String message){
		this.message = message;
	}
	
	@Override
	public String toString(){
		return this.message;
	}
}
