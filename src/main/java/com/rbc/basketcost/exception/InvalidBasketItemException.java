package com.rbc.basketcost.exception;

public class InvalidBasketItemException extends RuntimeException {
	public InvalidBasketItemException(String cause){
		super(cause);
	}
}
