package vengingmachine.core.exception;

/**
 * Not entire amount paid exception
 * @author jhaverih
 *
 */
public class NotFullAmountPaidException extends RuntimeException{

	private static final long serialVersionUID = 5738325950888380101L;
	String message;
	double remainingAmount;
	public NotFullAmountPaidException(String message, double remainingAmount){
		this.message = message;
		this.remainingAmount = remainingAmount;
	}
}
