package com.example.xcsbooks.model;

public class Administrador {
	private String username;
	private String senha;
	
	public Administrador(String username, String senha) {
		this.username = username;
		this.senha = senha;
	}

	public Administrador() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
