package vengingmachine.core.service;

import java.util.ArrayList;
import java.util.List;

import vengingmachine.core.exception.ActionNotPerformedException;
import vengingmachine.core.exception.InsufficientChangeException;
import vengingmachine.core.exception.ItemNotAvailableException;
import vengingmachine.core.exception.NotFullAmountPaidException;
import vengingmachine.core.predefined.Coin;
import vengingmachine.core.predefined.Item;

/**
 * The vending machine class initializes a vending machine with each 
 * items with qty 5 and all coins supported with qty 5
 * It also provides methods to support user actions to operate machine
 * 
 * @author jhaverih
 *
 */
public class VendingMachine{
	Inventory<Coin> coinInventory;
	Inventory<Item> itemInventory;
	Item currentItem;
	float currentBalance;
	float totalSales;
	boolean itemCollected;
	
	public VendingMachine(){
		coinInventory = new Inventory<Coin>();
		itemInventory = new Inventory<Item>();
		for(Coin coin : Coin.values()){
			coinInventory.put(coin, 5);
		}
		
		for(Item item : Item.values()){
			itemInventory.put(item, 5);
		}
	}
	
	/** 
	 *  reset the vending machine
	 */
	public void reset(){
		coinInventory.clear();
		itemInventory.clear();
		currentItem = null;
		currentBalance = 0;
		totalSales=0;
		itemCollected = false;
	}
	
	public float selectItemAndGetPrice(Item item){
		if(itemInventory.hasItem(item)){
			currentItem = item;
			return item.getPrice();
		}
		throw new ItemNotAvailableException("Selected item is not available. Please choose other item");
	}
	
	public void insertCoin(Coin coin){
		coinInventory.add(coin);
		currentBalance+=coin.getDenomination();
	}
	
	public Bucket<Item, List<Coin>> collectItemAndChange(){
		if(isFullAmountPaid()){
			List<Coin> changeCoins = this.getChange(currentBalance - currentItem.getPrice());
			this.processCollection(changeCoins);
			Bucket<Item, List<Coin>> bucket = new Bucket<Item, List<Coin>>(currentItem, changeCoins);
			reset();
			return bucket;
		}
		throw new NotFullAmountPaidException("", currentItem.getPrice()-currentBalance);
	}
	
	public void processCollection(List<Coin> changeCoins){
		totalSales = totalSales + currentItem.getPrice();
		itemInventory.deduct(currentItem);
		changeCoins.forEach(coin -> coinInventory.deduct(coin));
	}
	
	public boolean isFullAmountPaid(){
		return currentBalance>=currentItem.getPrice();
	}
	
	public void resetSelection(){
		currentItem = null;
		currentBalance = 0;
	}
	
	public void cancelSelection(){
		if(!itemCollected){
			refund();
		}else{
			throw new ActionNotPerformedException(
					"Action not performed.");
		}
	}
	
	private List<Coin> refund(){
		if(currentBalance>0){
			List<Coin> coins = this.getChange(currentBalance);
			for(Coin coin : coins){
				coinInventory.deduct(coin);
			}
			resetSelection();
		}
		return null;
	}
	
	public List<Coin> getChange(float amount){
		List<Coin> coins = new ArrayList<>();
		while(amount>0){
			if(amount>= Coin.Toonie.getDenomination() && coinInventory.hasItem(Coin.Toonie)){
				coins.add(Coin.Toonie);
				amount-= Coin.Toonie.getDenomination() ;
			}else if(amount>=Coin.Loonie.getDenomination() && coinInventory.hasItem(Coin.Loonie)){
				coins.add(Coin.Loonie);
				amount-=Coin.Loonie.getDenomination();
			}else if(amount>=Coin.Quarter.getDenomination() && coinInventory.hasItem(Coin.Quarter)){
				coins.add(Coin.Quarter);
				amount-=Coin.Quarter.getDenomination();
			}else if(amount>=Coin.Dime.getDenomination() && coinInventory.hasItem(Coin.Dime)){
				coins.add(Coin.Dime);
				amount-=Coin.Dime.getDenomination();
			}else if(amount>=Coin.Nickel.getDenomination() && coinInventory.hasItem(Coin.Nickel)){
				coins.add(Coin.Nickel);
				amount-=Coin.Nickel.getDenomination();
			}else if(amount>=Coin.Penny.getDenomination() && coinInventory.hasItem(Coin.Penny)){
				coins.add(Coin.Penny);
				amount-=Coin.Penny.getDenomination();
			}else{
				throw new InsufficientChangeException("No sufficient coins available");
			}
		}
		return coins;
	}
}
