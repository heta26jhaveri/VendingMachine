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

/**
 * Junit test class - provides some simple tests
 * @author jhaveri
 *
 */
public class VendingMachineTest{
	static VendingMachine vm;
	
	@BeforeClass
	public static void setUp(){
		vm = new VendingMachine();
	}

	@AfterClass
	public static void tearDown(){
		vm = null;
	}
	
	@Test
	public void testCheckPrice(){
		Item coke = Item.Coke;
		float cokePrice = vm.selectItemAndGetPrice(coke);
		Assert.assertEquals(cokePrice, coke.getPrice(), 0);
	}
	
	@Test 
	public void testChange(){
		Item coke = Item.Coke;
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
		Item coke = Item.Coke;
		vm.selectItemAndGetPrice(coke);
		vm.insertCoin(Coin.Quarter);
		vm.collectItemAndChange();
	}
}
