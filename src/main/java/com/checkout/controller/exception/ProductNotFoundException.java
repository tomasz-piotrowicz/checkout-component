package com.checkout.controller.exception;

public class ProductNotFoundException extends Exception {
    private static final String INFO = "No such product";

    public ProductNotFoundException() {
        super(INFO);
    }
}
