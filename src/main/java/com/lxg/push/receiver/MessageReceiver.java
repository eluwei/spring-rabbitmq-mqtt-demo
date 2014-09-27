package com.lxg.push.receiver;


public interface MessageReceiver {

	public void enqueue(String productName, String topic, String message, String phones);
	
}

