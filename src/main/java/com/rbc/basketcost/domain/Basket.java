package com.rbc.basketcost.domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<BasketItem> basketItems;

    public Basket() {
        this.basketItems = new ArrayList<BasketItem>();
    }

    public Basket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void addItem(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

}
