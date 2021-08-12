package com.payday.dto;

import java.time.LocalDateTime;

public class GenericCommentDto {

    private String fromUser;
    private String message;
    private LocalDateTime timestamp;
    
	public GenericCommentDto(String fromUser, String message, LocalDateTime timestamp) {
		super();
		this.fromUser = fromUser;
		this.message = message;
		this.timestamp = timestamp;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
