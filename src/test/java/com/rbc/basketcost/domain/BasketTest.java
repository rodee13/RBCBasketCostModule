package com.rbc.basketcost.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    @Test
    public void BasketPopulateTest() {

        final List<BasketItem> basketItems = new ArrayList<BasketItem>();

        final int id1 = 1;
        final BigDecimal requiredQty1 = BigDecimal.ONE;

        final int id2 = 1;
        final BigDecimal requiredQty2 = BigDecimal.TEN;

        final BasketItem basketItem1 = new BasketItem(id1, requiredQty1);
        final BasketItem basketItem2 = new BasketItem(id2, requiredQty2);

        basketItems.add(basketItem1);
        basketItems.add(basketItem2);

        final Basket basket = new Basket(basketItems);

        BasketItem bi1 = basket.getBasketItems().get(0);
        assertEquals(id1, bi1.getId());
        assertEquals(requiredQty1, bi1.getRequiredQuantity());

        BasketItem bi2 = basket.getBasketItems().get(1);
        assertEquals(id2, bi2.getId());
        assertEquals(requiredQty2, bi2.getRequiredQuantity());

    }

}
