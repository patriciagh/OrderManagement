package model;
//GHITUN PATRICIA ROXANA - 30227
public class Product implements Comparable<Product>{
	private int idProdus;
	private String numeProdus;
	private double pretProdus;
	private int stocProdus;
	public Product(int id, String n, double p, int c)
	{
		this.idProdus=id;
		this.numeProdus=n;
		this.pretProdus=p;
		this.stocProdus=c;
	}
	public Product() {
	}
	public int getId() {
		return this.idProdus;
	}
	public void setId(int idp) {
		this.idProdus = idp;
	}
	public String getNume() {
		return this.numeProdus;
	}
	public void setNume(String name) {
		this.numeProdus = name;
	}
	public double getPret() {
		return this.pretProdus;
	}
	public void setPret(double price) {
		this.pretProdus = price;
	}
	public int getStoc() {
		return this.stocProdus;
	}
	public void setStoc(int cant) {
		this.stocProdus = cant;
	}
	public String afisareProdus()
	{
		return "Produsul "+this.idProdus+"	 "+this.numeProdus+" 	"+this.pretProdus+" 	"+this.stocProdus;
	}
	public int compareTo(Product o) {
		int idp=o.getId();
		return this.getId()-idp;
	}
	
}
