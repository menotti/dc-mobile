package com.example.xcsbooks.model;

public class Cliente {
	private String username;
	private String senha;
	private String nome;
	private String cpf;
	private String email;
	private String telefone1;
	private String telefone2;
	private Endereco endereco;
	
	public Cliente(String username, String senha, String nome, String cpf,
			String email, String telefone1, String telefone2, Endereco endereco) {
		this.username = username;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.endereco = endereco;
	}

	public Cliente() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
