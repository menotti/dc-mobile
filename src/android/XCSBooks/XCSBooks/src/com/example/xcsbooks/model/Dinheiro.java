package com.example.xcsbooks.model;

import java.math.BigDecimal;

public class Dinheiro {
	public BigDecimal valor;
	
	public Dinheiro(double val){
		valor = new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public Dinheiro(String val){
		if(val.contains("R$")){
			String sub = val.substring(3);
			valor = new BigDecimal(sub);
		} else {
			valor = new BigDecimal(val);
		}
	}
	
	public Dinheiro(BigDecimal bd) {
		valor = bd;
	}
	
	public BigDecimal soma(Dinheiro outroValor){
		return valor.add(outroValor.valor);
	}
	
	public BigDecimal sub(Dinheiro outroValor){
		return valor.subtract(outroValor.valor);
	}
	
	public BigDecimal div(Dinheiro outroValor){
		return valor.divide(outroValor.valor, 2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal div(int a){
		return valor.divide(new BigDecimal(a), 2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal mult(Dinheiro outroValor){
		return valor.multiply(outroValor.valor).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public BigDecimal mult(int a){
		return valor.multiply(new BigDecimal(a)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public String toString() {
		return "R$ " + valor.setScale(2).toString();
	}
	
	public double toDouble(){
		return valor.doubleValue();
	}
}
