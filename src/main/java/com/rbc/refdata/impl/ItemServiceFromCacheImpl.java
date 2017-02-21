package com.rbc.refdata.impl;

import java.util.Map;

import com.rbc.basketcost.util.RefDataFileReader;
import com.rbc.refdata.Item;
import com.rbc.refdata.ifaces.ItemService;


//ItemService implementation loading items into cache from a text file.
// There could be different types of implementation depending on the agreed architecture 
// The cache could be a distributed cache
public class ItemServiceFromCacheImpl implements ItemService {

	
	private final Map<Integer, Item> itemCacheMap;
	

	public ItemServiceFromCacheImpl(String fileName){
		itemCacheMap = RefDataFileReader.loadItemRefDataIntoMap(fileName);
	}
	
    public Item getItem(int id) {
    	
    	return itemCacheMap.get(id);
	}

}
