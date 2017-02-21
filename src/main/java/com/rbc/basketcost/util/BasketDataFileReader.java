package com.rbc.basketcost.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rbc.basketcost.domain.Basket;
import com.rbc.basketcost.domain.BasketItem;

public class BasketDataFileReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasketDataFileReader.class);

	public static Basket getBasketFromFile(String fileName) {
		Basket basket = new Basket();
		List<String> basketItemList = FileReader.getListofLinesFromFile(fileName);
		String basketItemArr[];
		for (String itemLine : basketItemList) {
			basketItemArr = itemLine.split("\\|");
			int itemId = Integer.parseInt((basketItemArr[0]));
			basket.addItem(new BasketItem(itemId, FileReader.getBigDecimalFromString(basketItemArr[1])));
		}

		return basket;
	}

}
