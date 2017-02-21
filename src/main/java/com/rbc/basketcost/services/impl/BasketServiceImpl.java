package com.rbc.basketcost.services.impl;

import com.rbc.basketcost.domain.Basket;
import com.rbc.basketcost.domain.BasketItem;
import com.rbc.basketcost.exception.InvalidBasketItemException;
import com.rbc.basketcost.exception.BasketItemNotFoundException;
import com.rbc.basketcost.exception.InvalidItemException;
import com.rbc.basketcost.services.ifaces.BasketService;
import com.rbc.refdata.Item;
import com.rbc.refdata.ifaces.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BasketServiceImpl implements BasketService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BasketServiceImpl.class);

    private final ItemService itemService;

    public BasketServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public BigDecimal getTotalBasketCost(Basket basket) {

        if (basket == null) {
            throw new IllegalArgumentException("No basket has been supplied");
        }

        List<BasketItem> basketItems = basket.getBasketItems();

        if (basketItems == null || basketItems.size() == 0) {
            throw new IllegalArgumentException("No items have been supplied in your basket");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (BasketItem basketItem : basketItems) {
            validateBasketItem(basketItem);
            Item item = itemService.getItem(basketItem.getId());
            if (item == null) {
                throw new BasketItemNotFoundException("Product Id: " + basketItem.getId() + " not found");
            }
            BigDecimal itemPrice = getItemPrice(item, basketItem);
            totalCost = totalCost.add(itemPrice);
        }

        LOGGER.info("Items processed: " + basketItems.size() + ", total cost: " + totalCost);

        return totalCost;

    }

    private void validateBasketItem(BasketItem basketItem) {

        if (basketItem == null) {
            throw new InvalidBasketItemException("Basket item is null");
        }

        if (basketItem.getRequiredQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidBasketItemException("Required quantity less than or equal to zero");
        }

    }

    private BigDecimal getItemPrice(Item item, BasketItem basketItem) {


        if (item.getUnitQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidItemException("Required unit quantity less than or equal to zero");
        }

        if (item.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidItemException("Required cost per unit less than or equal to zero");
        }

        BigDecimal itemPrice = item.getCostPerUnit().multiply(basketItem.getRequiredQuantity()).divide(item.getUnitQuantity(), RoundingMode.CEILING);

        LOGGER.info("Item being processed: " + item.getItemName() +
                    ", Quantity required: " + basketItem.getRequiredQuantity() +
                    ", Unit Price: " + item.getCostPerUnit() +
                    ", Unit Quantity: " + item.getUnitQuantity() +
                    ", Sold: " + item.getUnitType().getName() +
                    ", Item Price: <" + itemPrice + ">");

        return itemPrice.setScale(2, RoundingMode.DOWN);

    }

}