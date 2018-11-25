package vengingmachine.core.service;

/**
 * A parameterized generic class with 2 parameters
 * @author jhaverih
 *
 * @param <P1>
 * @param <P2>
 */
public class Bucket<P1, P2>{
	P1 parameter1;
	P2 parameter2;
	public Bucket(P1 parameter1, P2 parameter2){
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}
}
