package com.hackerrank.eshopping.product.dashboard.repositpry;

public class CustomErrorType extends RuntimeException  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
