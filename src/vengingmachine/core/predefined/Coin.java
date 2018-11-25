package vengingmachine.core.predefined;

/**
 * The enum defines coins with their denominations supported by the vending machine
 * @author jhaverih
 *
 */
public enum Coin{
	Penny(0.1F), Nickel(0.5F), Dime(0.10F), Quarter(0.25F), Loonie(1), Toonie(2);
	
	float denomination;

	private Coin(float denomination){
		this.denomination = denomination;
	}

	public float getDenomination(){
		return denomination;
	}

	public void setDenomination(float denomination){
		this.denomination = denomination;
	}
}
