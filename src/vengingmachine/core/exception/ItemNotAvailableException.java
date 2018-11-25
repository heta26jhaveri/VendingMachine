package vengingmachine.core.exception;

/**
 * Selected item not available or sold out exception
 * @author jhaverih
 *
 */
public class ItemNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = -6662170817178320600L;
	String message;

	public ItemNotAvailableException(String message){
		this.message = message;
	}
	
	@Override
	public String toString(){
		return this.message;
	}
}
