package com.data.enums;

public enum OrderStatus {
	CREATED,  //order created
	PENDING,  //razorpay opened
	PLACED,  //payment success
    SHIPPED,
    DELIVERED,
    CANCELLED
}
