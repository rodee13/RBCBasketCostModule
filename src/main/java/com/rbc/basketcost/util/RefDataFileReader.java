package com.rbc.basketcost.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rbc.refdata.Item;
import com.rbc.refdata.enums.UnitType;


public class RefDataFileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefDataFileReader.class);

	public static Map<Integer, Item> loadItemRefDataIntoMap(String fileName) {
		Map<Integer, Item> itemMap = new HashMap<>();
		List<String> itemList = FileReader.getListofLinesFromFile(fileName);
		String itemArr[];
		for (String itemLine : itemList) {
			itemArr = itemLine.split("\\|");
			int itemId = Integer.parseInt((itemArr[0]));
			itemMap.put(itemId, new Item(itemId, itemArr[1], FileReader.getBigDecimalFromString(itemArr[2]),
									FileReader.getBigDecimalFromString(itemArr[3]), UnitType.valueOf(itemArr[4])));

		}

		return itemMap;
	}


}
