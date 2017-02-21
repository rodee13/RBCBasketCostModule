package com.rbc.refdata.domain;

import org.junit.Test;

import com.rbc.refdata.Item;
import com.rbc.refdata.enums.UnitType;
import com.rbc.refdata.ifaces.ItemService;
import com.rbc.refdata.impl.ItemServiceFromCacheImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class ItemFileReaderTest {

	ItemService itemService = new ItemServiceFromCacheImpl("ItemFileReaderTest.txt");
	
    @Test
    public void checkItemFromFile() {

        final int id = 1;
        final String itemName = "TEST";
        final BigDecimal costPerUnit = new BigDecimal(1.00).setScale(2, RoundingMode.DOWN);
        final BigDecimal unitQuantity = BigDecimal.TEN.setScale(2, RoundingMode.DOWN);
        final UnitType unitType = UnitType.UNIT;

        Item item = itemService.getItem(id);

        assertEquals(id, item.getId());
        assertEquals(itemName, item.getItemName());
        assertEquals(costPerUnit, item.getCostPerUnit());
        assertEquals(unitQuantity, item.getUnitQuantity());
        assertEquals(unitType, item.getUnitType());

        final int id2 = 2;
        final String itemName2 = "TEST2";
        final BigDecimal costPerUnit2 = new BigDecimal(2.00).setScale(2, RoundingMode.DOWN);
        final BigDecimal unitQuantity2 = new BigDecimal(20.00).setScale(2, RoundingMode.DOWN);
        final UnitType unitType2 = UnitType.GRAM;

        Item item2 = itemService.getItem(id2);

        assertEquals(id2, item2.getId());
        assertEquals(itemName2, item2.getItemName());
        assertEquals(costPerUnit2, item2.getCostPerUnit());
        assertEquals(unitQuantity2, item2.getUnitQuantity());
        assertEquals(unitType2, item2.getUnitType());

    }
    


}
