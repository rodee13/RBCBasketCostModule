package com.rbc.basketcost.domain;

import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class BasketItemTest {

    @Test
    public void BasketItemPopulateTest() {

        final int id = 1;
        final BigDecimal requiredQty = BigDecimal.ONE;
        final BasketItem basketItem = new BasketItem(id, requiredQty);

        assertEquals(id, basketItem.getId());
        assertEquals(requiredQty, basketItem.getRequiredQuantity());

    }

}
