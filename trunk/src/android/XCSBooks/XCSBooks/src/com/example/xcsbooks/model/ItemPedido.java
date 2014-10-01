package com.example.xcsbooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemPedido implements Parcelable {
	private int quantidade;
	private Produto produto;
	private Dinheiro totalItem;
	
	public ItemPedido() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemPedido(int quantidade, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
	}
	public ItemPedido(int quantidade, Produto produto, Dinheiro totalItem) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
		this.totalItem = totalItem;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Dinheiro getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Dinheiro totalItem) {
		this.totalItem = totalItem;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(getQuantidade());
		dest.writeInt(getProduto().getCodigo());
		dest.writeString(((LivroNovo)getProduto()).getPreco().toString());
		dest.writeString(((LivroNovo)getProduto()).getIsbn());
		dest.writeString(((LivroNovo)getProduto()).getTitulo());
		dest.writeString(((LivroNovo)getProduto()).getAutor());
		dest.writeString(((LivroNovo)getProduto()).getEditora());
		
		dest.writeString(getTotalItem().toString());
		
	}
	
	public static final Parcelable.Creator<ItemPedido> CREATOR = new Parcelable.Creator<ItemPedido>() {

		@Override
		public ItemPedido createFromParcel(Parcel source) {
			return new ItemPedido(source);
		}

		@Override
		public ItemPedido[] newArray(int size) {
			return new ItemPedido[size];
		}
	};
	
	protected ItemPedido(Parcel in){
		setQuantidade(in.readInt());
		setProduto(new LivroNovo(in.readInt(),0,new Dinheiro(in.readString()),
				in.readString(),in.readString(),in.readString(),in.readString()));
		setTotalItem(new Dinheiro(in.readString()));
	}
	
}
