package com.mustafa.hashdehash;

//Message POJO for facilitating automatic JSON conversion in the request handlers implemented in HashCOntroller.java
public class Message {
	private String message;
	
	public Message () {};
		
	public Message(String msg) {
		message = msg;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
