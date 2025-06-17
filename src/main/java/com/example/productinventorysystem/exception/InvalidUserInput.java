package com.example.productinventorysystem.exception;

public class InvalidUserInput extends RuntimeException{
	public InvalidUserInput(String msg) {
		super(msg);
	}
}
