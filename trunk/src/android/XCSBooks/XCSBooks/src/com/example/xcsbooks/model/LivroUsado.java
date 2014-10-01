package com.example.xcsbooks.model;

public class LivroUsado extends LivroNovo {
	private String estado;
	private int porcentagemVenda;
	private String datahoraCadastro;
	
	public LivroUsado() {
		super();
	}

	public LivroUsado(int codigo, int quantidade, Dinheiro preco, String isbn,
			String titulo, String autor, String editora, String estado,
			int porcentagemVenda, String datahoraCadastro) {
		super(codigo, quantidade, preco, isbn, titulo, autor, editora);
		this.estado = estado;
		this.porcentagemVenda = porcentagemVenda;
		this.datahoraCadastro = datahoraCadastro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPorcentagemVenda() {
		return porcentagemVenda;
	}

	public void setPorcentagemVenda(int porcentagemVenda) {
		this.porcentagemVenda = porcentagemVenda;
	}

	public String getDatahoraCadastro() {
		return datahoraCadastro;
	}

	public void setDatahoraCadastro(String datahoraCadastro) {
		this.datahoraCadastro = datahoraCadastro;
	}


	
}
