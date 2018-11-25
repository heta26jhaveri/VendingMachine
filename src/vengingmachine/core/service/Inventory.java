package vengingmachine.core.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Class defines the inventory (item and cash here) and its quantity and provides supported actions
 * @author jhaverih
 *
 * @param <T>
 */
public class Inventory<T>{
	
	Map<T, Integer> inventory = new HashMap<T, Integer>();
	
	public void add(T item){
		Integer quantity = 0;
		if(inventory.containsKey(item)){
			quantity = inventory.get(item);
		}
		inventory.put(item, quantity +1);
	}
	
	public boolean deduct(T item){
		if(hasItem(item)){
			inventory.put(item, inventory.get(item)-1 );
			return true;
		}
		return false;
	}
	
	public boolean hasItem(T item){
		return (inventory.containsKey(item) && inventory.get(item)>0);
	}
	
	public void clear(){
		inventory.clear();
	}
	
	public void put(T item, Integer quantity){
		inventory.put(item, quantity);
	}
}
