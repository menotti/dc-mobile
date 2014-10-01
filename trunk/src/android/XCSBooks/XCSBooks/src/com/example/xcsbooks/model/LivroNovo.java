package com.example.xcsbooks.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LivroNovo extends Produto implements Parcelable {
	protected String isbn;
	protected String titulo;
	protected String autor;
	protected String editora;
	protected String usado;

	public LivroNovo() {
		super();
	}
	public LivroNovo(int codigo, int quantidade, Dinheiro preco, String isbn,
			String titulo, String autor, String editora) {
		super(codigo, quantidade, preco);
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
	}
	public LivroNovo(int codigo, int quantidade, Dinheiro preco, String isbn,
			String titulo, String autor, String editora, String estado) {
		super(codigo, quantidade, preco);
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.usado = estado;
	}
	public LivroNovo(LivroNovo livro) {
		super(livro.getCodigo(), livro.getQuantidade(), livro.getPreco());
		this.isbn = livro.getIsbn();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.editora = livro.getEditora();
	}
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn=isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo=titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor=autor;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora=editora;
	}
	public String getUsado() {
		return usado;
	}
	public void setUsado(String usado) {
		this.usado = usado;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(getCodigo());
		dest.writeInt(getQuantidade());
		dest.writeString(getPreco().toString());
		dest.writeString(getIsbn());
		dest.writeString(getTitulo());
		dest.writeString(getAutor());
		dest.writeString(getEditora());
		dest.writeString(getUsado());
	}

	public static final Parcelable.Creator<LivroNovo> CREATOR = new Parcelable.Creator<LivroNovo>() {

		@Override
		public LivroNovo createFromParcel(Parcel source) {
			return new LivroNovo(source);
		}

		@Override
		public LivroNovo[] newArray(int size) {
			return new LivroNovo[size];
		}
	};
	
	private LivroNovo(Parcel in){
		setCodigo(in.readInt());
		setQuantidade(in.readInt());
		setPreco(new Dinheiro(in.readString()));
		setIsbn(in.readString());
		setTitulo(in.readString());
		setAutor(in.readString());
		setEditora(in.readString());
		setUsado(in.readString());
	}
	
}
