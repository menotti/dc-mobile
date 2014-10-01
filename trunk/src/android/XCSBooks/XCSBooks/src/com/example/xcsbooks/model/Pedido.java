package com.example.xcsbooks.model;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Pedido implements Parcelable {
	private int id;
	private String datahora;
	private String estado;
	private Dinheiro total;
	private List<Produto> produtos; 
	private List<ItemPedido> itens;
	
	
	public Pedido() {
		super();
	}
	
	public Pedido(int id, String datahora, String estado, Dinheiro total) {
		super();
		this.id = id;
		this.datahora = datahora;
		this.estado = estado;
		this.total = total;
		this.produtos = new ArrayList<Produto>();
		this.itens = new ArrayList<ItemPedido>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatahora() {
		return datahora;
	}
	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Dinheiro getTotal() {
		return total;
	}
	public void setTotal(Dinheiro total) {
		this.total = total;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getId());
		dest.writeString(getDatahora());
		dest.writeString(getEstado());
		dest.writeString(getTotal().toString());
		dest.writeList(getItens());
	}
	
	public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>() {

		@Override
		public Pedido createFromParcel(Parcel source) {
			try {
				return new Pedido(source);
			} catch (BadParcelableException e){
				return null;
			}
		}

		@Override
		public Pedido[] newArray(int size) {
			return new Pedido[size];
		}
	};
	
	private Pedido(Parcel in){
		setId(in.readInt());
		setDatahora(in.readString());
		setEstado(in.readString());
		setTotal(new Dinheiro(in.readString()));
		itens = new ArrayList<ItemPedido>();
		in.readList(itens, ItemPedido.class.getClassLoader());
	}
	
}
