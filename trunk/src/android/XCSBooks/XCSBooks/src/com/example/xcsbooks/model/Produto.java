package com.example.xcsbooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {
	protected int codigo;
	protected int quantidade;
	protected Dinheiro preco;
	
	public Produto(int codigo, int quantidade, Dinheiro preco) {
		super();
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Produto() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Dinheiro getPreco() {
		return preco;
	}

	public void setPreco(Dinheiro preco) {
		this.preco = preco;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(codigo);
		dest.writeInt(quantidade);
		dest.writeString(preco.toString());
	}
	
	public static final Parcelable.Creator<Produto> CREATOR = new Parcelable.Creator<Produto>() {

		@Override
		public Produto createFromParcel(Parcel source) {
			return new Produto(source);
		}

		@Override
		public Produto[] newArray(int size) {
			return new Produto[size];
		}
	};
	
	protected Produto(Parcel in){
		setCodigo(in.readInt());
		setQuantidade(in.readInt());
		setPreco(new Dinheiro(in.readString()));
	}
	
}
