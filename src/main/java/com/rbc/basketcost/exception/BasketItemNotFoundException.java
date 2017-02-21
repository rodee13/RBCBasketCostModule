package com.rbc.basketcost.exception;

public class BasketItemNotFoundException extends RuntimeException {
	public BasketItemNotFoundException(String cause){
		super(cause);
	}
}
