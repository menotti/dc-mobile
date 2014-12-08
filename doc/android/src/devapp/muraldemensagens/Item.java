package com.example.trocamsg;

public class Item {
	 
    private String nome;
    private String msg;
 
    public Item(String nome, String msg) {
        super();
        this.nome = nome;
        this.msg = msg;
    }

	public String getnome() {
		return nome;
	}
	public void setnome(String nome) {
		this.nome = nome;
	}

	public String getmsg() {
		return msg;
	}
	public void setmsg(String msg) {
		this.msg = msg;
	}
}