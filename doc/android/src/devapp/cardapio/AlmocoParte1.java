package com.example.cardapioonline;

public class Almoco {
	private String principal;
	private String guarnicao;
	private String arroz;
	private String feijao;
	private String saladas;
	private String sobremesa;
	private String bebida;
	
	public Almoco(){
		super();
	}
	
	public Almoco(String principal, String guarnicao, 
			String arroz, String feijao, 
			String saladas, String sobremesa, 
			String bebida){
		
		this.principal = principal;
		this.guarnicao = guarnicao;
		this.arroz = arroz;
		this.feijao = feijao;
		this.saladas = saladas;
		this.sobremesa = sobremesa;
		this.setBebida(bebida);
	}
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getGuarnicao() {
		return guarnicao;
	}
	public void setGuarnicao(String guarnicao) {
		this.guarnicao = guarnicao;
	}
	public String getArroz() {
		return arroz;
	}
	...