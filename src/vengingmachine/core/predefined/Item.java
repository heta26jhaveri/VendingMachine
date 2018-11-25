package vengingmachine.core.predefined;

/**
 * The enum defines items and its price.
 * @author jhaverih	
 */
public enum Item{
	Coke(1), Lays(1.5F), Candy(0.75F);
	
	float price;

	private Item(float price){
		this.price = price;
	}

	public float getPrice(){
		return price;
	}

	public void setPrice(float price){
		this.price = price;
	}
}
