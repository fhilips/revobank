package com.revobank.dto.response;

public class MessageResponseDTO {

    private String message;

	public MessageResponseDTO(String message) {
		super();
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}    
    
}