package vengingmachine.core.service;

import java.awt.List;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

import vengingmachine.core.exception.NotFullAmountPaidException;
import vengingmachine.core.predefined.Coin;
import vengingmachine.core.predefined.Item;

public class VendingMachineTest{
	static VendingMachine vm;
	
	@BeforeClass
	public static void setUp(){
		System.out.println("Setting up vending machine");
		vm = new VendingMachine();
	}

	@AfterClass
	public static void tearDown(){
		System.out.println("Tear down vending machine");
		vm = null;
	}
	
	@Test
	public void testCheckPrice(){
		System.out.println("Check Item Price");
		Item coke = Item.Coke;
		float cokePrice = vm.selectItemAndGetPrice(coke);
		Assert.assertEquals(cokePrice, coke.getPrice(), 0);
	}
	
	@Test 
	public void testChange(){
		System.out.println("Check change coins");
		Item coke = Item.Coke;
		float cokePrice = vm.selectItemAndGetPrice(coke);
		vm.insertCoin(Coin.Toonie);
		float inserted = Coin.Toonie.getDenomination();
		float remaining = inserted - coke.getPrice();

		Collection<Coin> coins = vm.getChange(remaining);
		float change = 0;
		for(Coin coin : coins){
			change+=coin.getDenomination();
		}
		
		Assert.assertEquals(change, remaining, 0);
	}
	
	@Test (expected = NotFullAmountPaidException.class)
	public void testNotFullPaidException(){
		System.out.println("Not full paid");
		Item coke = Item.Coke;
		float cokePrice = vm.selectItemAndGetPrice(coke);
		vm.insertCoin(Coin.Quarter);
		vm.collectItemAndChange();
	}
}
