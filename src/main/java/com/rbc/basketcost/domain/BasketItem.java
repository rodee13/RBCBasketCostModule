package com.rbc.basketcost.domain;

import java.math.BigDecimal;

public class BasketItem {

    private final int id;
    private final BigDecimal requiredQuantity;

    public BasketItem(int id, BigDecimal requiredQuantity) {
        this.id = id;
        this.requiredQuantity = requiredQuantity;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
                ", requiredQuantity=" + requiredQuantity +
                '}';
    }

}
