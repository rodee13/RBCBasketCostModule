package com.rbc.refdata;

import java.math.BigDecimal;

import com.rbc.refdata.enums.UnitType;

public class Item {

    public final static String DEFAULT_CURRENCY = "GBP";

    private final int id;
    private final String itemName;
    private final BigDecimal costPerUnit;
    private final BigDecimal unitQuantity;
    private final UnitType unitType;
    private final String currency;

    public Item(int id, String itemName, BigDecimal costPerUnit, BigDecimal unitQuantity, UnitType unitType) {
        this.id = id;
        this.itemName = itemName;
        this.costPerUnit = costPerUnit;
        this.unitQuantity = unitQuantity;
        this.unitType = unitType;
        this.currency = DEFAULT_CURRENCY;
    }
    
    public Item(int id, String itemName, BigDecimal costPerUnit, BigDecimal unitQuantity, UnitType unitType, String currency) {
        this.id = id;
        this.itemName = itemName;
        this.costPerUnit = costPerUnit;
        this.unitQuantity = unitQuantity;
        this.unitType = unitType;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public BigDecimal getUnitQuantity() {
        return unitQuantity;
    }

    public UnitType getUnitType() {
        return unitType;
    }
    
    public String getCurrency() {
        return currency;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", costPerUnit=" + costPerUnit + ", unitQuantity="
				+ unitQuantity + ", unitType=" + unitType + ", currency=" + currency + "]";
	}

    
}