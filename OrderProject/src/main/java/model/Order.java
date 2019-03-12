package model;
//GHITUN PATRICIA ROXANA - 30227
public class Order implements Comparable<Order>{
	private int idComanda;
	private int idClient;
	private int idProdus;
	private double total;
	public Order(int io , int ic,int ip,double t)
	{
		this.idComanda=io;
		this.idClient=ic;
		this.idProdus=ip;
		this.total=t;
	}
	public Order() {
	}
	public int getIdProdus() {
		return idProdus;
	}
	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}
	public int getIdOrder() {
		return idComanda;
	}
	public void setIdOrder(int idOrder) {
		this.idComanda = idOrder;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String afisareComanda()
	{
		return "Comanda  "+this.idComanda+"  client:"+this.idClient +"  produs :"+this.idProdus+"  total: "+this.total;
	}
	@Override
	public int compareTo(Order o) {
		int idComanda=o.getIdOrder();
		return this.getIdOrder()-idComanda;
	}

}
