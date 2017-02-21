package com.rbc.basketcost.exception;

public class InvalidItemException extends RuntimeException {
	public InvalidItemException(String cause){
		super(cause);
	}
}
