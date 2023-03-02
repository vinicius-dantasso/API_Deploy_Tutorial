package br.edu.ufersa.todoApp.api.dto;

import java.util.UUID;

public class UserDTO {
	String email;
	UUID uuid;
	
	public String getEmail() {
		return email;
	}
	public UUID getUuid() {
		return uuid;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
