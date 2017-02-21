package com.rbc.basketcost.services.impl;

import com.rbc.basketcost.domain.Basket;
import com.rbc.basketcost.domain.BasketItem;
import com.rbc.basketcost.exception.BasketItemNotFoundException;
import com.rbc.basketcost.exception.InvalidBasketItemException;
import com.rbc.basketcost.exception.InvalidItemException;
import com.rbc.basketcost.services.ifaces.BasketService;
import com.rbc.basketcost.util.BasketDataFileReader;
import com.rbc.refdata.Item;
import com.rbc.refdata.enums.UnitType;
import com.rbc.refdata.ifaces.ItemService;
import com.rbc.refdata.impl.ItemServiceFromCacheImpl;
import com.rbc.refdata.util.ItemType;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class BasketServiceImplTest {

    
    private final ItemService itemServiceMock = Mockito.mock(ItemService.class);
    private final BasketService basketServiceMock = new BasketServiceImpl(itemServiceMock);

 
    @Test
    public void checkBucketCostFileTest() {
        final BasketService basketServiceUsingFileAndCache = new BasketServiceImpl(new ItemServiceFromCacheImpl("ItemsRefDataTest.txt"));
        BigDecimal totalCost = null;
		totalCost = basketServiceUsingFileAndCache.getTotalBasketCost(BasketDataFileReader.getBasketFromFile("BasketItemDataTest.txt"));
        assertEquals(new BigDecimal("38.47"),totalCost);

    }  
    
    @Test
    public void checkBucketCostCacheTest() {
        final BasketService basketServiceUsingCache = new BasketServiceImpl(new ItemServiceFromCacheImpl("ItemsRefDataTest.txt"));

        final int bananaId = ItemType.BANANA.getId();
        final BigDecimal bananaQty = BigDecimal.TEN;

        final int orangeId = ItemType.ORANGE.getId();
        final BigDecimal orangeQty = new BigDecimal("150");

        final int appleId = ItemType.APPLE.getId();
        final BigDecimal appleQty =  new BigDecimal("500");

        Basket basket = new Basket();
        basket.addItem(new BasketItem(bananaId, bananaQty));
        basket.addItem(new BasketItem(orangeId, orangeQty));
        basket.addItem(new BasketItem(appleId, appleQty));

        BigDecimal totalCost = null;
		totalCost = basketServiceUsingCache.getTotalBasketCost(basket);

        assertEquals(new BigDecimal("300.16"),totalCost);

    }
    
    @Test
    public void checkBucketCostMockTest() {

        final int lemonId = 1;
        final BigDecimal lemonQty = new BigDecimal(20);
        final BigDecimal lemonPrice = new BigDecimal("1.50");
        final BigDecimal lemonUnitQty = BigDecimal.ONE;
        final Item itemLemon = new Item(lemonId, "LEMONS", lemonPrice, lemonUnitQty, UnitType.UNIT);

        final int orangeId = 2;
        final BigDecimal orangeQty = new BigDecimal("50");
        final BigDecimal orangePrice = new BigDecimal("1.60");
        final BigDecimal orangeUnitQty = new BigDecimal("100");
        final Item itemOrange = new Item(orangeId, "ORANGES", orangePrice, orangeUnitQty, UnitType.GRAM);

        final int bananaId = 3;
        final BigDecimal bananaQty =  new BigDecimal("100");
        final BigDecimal bananaPrice = new BigDecimal("2.95");
        final BigDecimal bananaUnitQty = new BigDecimal("100");
        final Item itemBanana = new Item(bananaId, "BANANAS", bananaPrice, bananaUnitQty, UnitType.GRAM);

        when(itemServiceMock.getItem(lemonId)).thenReturn(itemLemon);
        when(itemServiceMock.getItem(orangeId)).thenReturn(itemOrange);
        when(itemServiceMock.getItem(bananaId)).thenReturn(itemBanana);

        Basket basket = new Basket();
        basket.addItem(new BasketItem(lemonId, lemonQty));
        basket.addItem(new BasketItem(orangeId, orangeQty));
        basket.addItem(new BasketItem(bananaId, bananaQty));

        BigDecimal totalCost = basketServiceMock.getTotalBasketCost(basket);

        assertEquals(new BigDecimal("33.75"),totalCost);

    }    

    @Test(expected=BasketItemNotFoundException.class)
    public void checkBasketItemNotFoundExceptionIsThrownTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(999, BigDecimal.ONE)); // invalid item id.


        basketServiceMock.getTotalBasketCost(basket);


    }

    @Test(expected=InvalidBasketItemException.class)
    public void checkInvalidBasketItemExceptionIsThrownWhenNullItemTest() {

        Basket basket = new Basket();
        basket.addItem(null);               // Null item.

        basketServiceMock.getTotalBasketCost(basket);


    }

    @Test(expected=InvalidBasketItemException.class)
    public void checkInvalidBasketItemExceptionIsThrownWhenQtyNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, new BigDecimal("-1"))); // Negative Quantity.

        basketServiceMock.getTotalBasketCost(basket);


    }

    @Test(expected=InvalidItemException.class)
    public void checkInvalidItemExceptionIsThrownWhenQtyNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, BigDecimal.ONE));
        final BigDecimal price = new BigDecimal("1.50");

        final Item item = new Item(1, "TEST", price, new BigDecimal("-1"), UnitType.UNIT); // Negative Qty
        when(itemServiceMock.getItem(1)).thenReturn(item);

        basketServiceMock.getTotalBasketCost(basket);

    }

    @Test(expected=InvalidItemException.class)
    public void checkInvalidItemExceptionIsThrownWhenPriceNegativeTest() {

        Basket basket = new Basket();
        basket.addItem(new BasketItem(1, BigDecimal.ONE));
        final BigDecimal price = new BigDecimal("-1.50");

        final Item item = new Item(1, "TEST", price, BigDecimal.ONE, UnitType.UNIT); // Negative Price
        when(itemServiceMock.getItem(1)).thenReturn(item);

        basketServiceMock.getTotalBasketCost(basket);


    }

}
