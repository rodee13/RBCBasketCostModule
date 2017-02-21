package com.rbc.refdata.domain;

import org.junit.Test;

import com.rbc.refdata.Item;
import com.rbc.refdata.enums.UnitType;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class ItemTest {

    @Test
    public void checkItemWithCurrency() {

        final int id = 1;
        final String itemName = "TEST";
        final BigDecimal costPerUnit = BigDecimal.ONE;
        final BigDecimal unitQuantity = BigDecimal.TEN;
        final UnitType unitType = UnitType.UNIT;
        final String currency = "USD";

        Item item = new Item(id, itemName, costPerUnit, unitQuantity, unitType, currency);

        assertEquals(id, item.getId());
        assertEquals(itemName, item.getItemName());
        assertEquals(costPerUnit, item.getCostPerUnit());
        assertEquals(unitQuantity, item.getUnitQuantity());
        assertEquals(unitType, item.getUnitType());
        assertEquals(currency, item.getCurrency());
        


    }
    
    @Test
    public void checkItemWithoutCurrency() {

        final int id = 1;
        final String itemName = "TEST";
        final BigDecimal costPerUnit = BigDecimal.ONE;
        final BigDecimal unitQuantity = BigDecimal.TEN;
        final UnitType unitType = UnitType.UNIT;
        final String currency = Item.DEFAULT_CURRENCY;

        Item item = new Item(id, itemName, costPerUnit, unitQuantity, unitType);

        assertEquals(id, item.getId());
        assertEquals(itemName, item.getItemName());
        assertEquals(costPerUnit, item.getCostPerUnit());
        assertEquals(unitQuantity, item.getUnitQuantity());
        assertEquals(unitType, item.getUnitType());
        assertEquals(currency, item.getCurrency());
        


    }

}
