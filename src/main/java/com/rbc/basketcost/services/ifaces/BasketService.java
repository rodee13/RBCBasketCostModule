package com.rbc.basketcost.services.ifaces;

import com.rbc.basketcost.domain.Basket;
import com.rbc.basketcost.exception.BasketItemNotFoundException;

import java.math.BigDecimal;

public interface BasketService {

    BigDecimal getTotalBasketCost(Basket basket) throws BasketItemNotFoundException;

}
