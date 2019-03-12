package model;
// GHITUN PATRICIA ROXANA - 30227
public class Customer implements Comparable<Customer> {
	private int idClient ;
	private String numeClient;
	private int varstaClient;
	private String adresaClient;
	public Customer(int id , String name , int age , String address)
	{
		this.idClient=id;
		this.numeClient=name;
		this.varstaClient=age;
		this.adresaClient=address;
	}
	public Customer() {
	}
	public int getId() {
		return this.idClient;
	}
	public void setId(int id) {
		this.idClient = id;
	}
	public String getNume() {
		return this.numeClient;
	}
	public void setNume(String name) {
		this.numeClient = name;
	}
	public int getVarsta() {
		return this.varstaClient;
	}
	public void setVarsta(int age) {
		this.varstaClient = age;
	}
	public String getAdresa() {
		return adresaClient;
	}
	public void setAdresa(String address) {
		this.adresaClient = address;
	}
	public String afisareClient()
	{
		return "Clientul  	"+this.idClient+" 	 "+this.numeClient +" 	 "+this.varstaClient+" 	 "+this.adresaClient;
	}

	public int compareTo(Customer o) {
		int idCust=o.getId();
		return this.getId()-idCust;
	}
	
}
