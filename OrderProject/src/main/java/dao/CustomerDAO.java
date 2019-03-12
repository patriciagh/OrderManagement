package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import com.mysql.fabric.xmlrpc.Client;

import connection.ConnectionFactory;
import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class CustomerDAO {
	protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	public  void afisareClienti() throws SQLException 
	{
		Customer client = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM customer";
		PreparedStatement afisare = null;
		ResultSet rez = null;
		int id=0;
		String nume="";
		int varsta=0;
		String adresa="";
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(	rez.next() ) 
			{
				id=rez.getInt("idClient");
			    nume = rez.getString("numeClient");
				varsta = rez.getInt("varstaClient");
				adresa = rez.getString("adresaClient");				
				client = new Customer(id, nume, varsta, adresa);
				System.out.println("Afisare client : ");
				System.out.println(client.afisareClient());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}	
	
	public  void inserareClient(Customer client) throws SQLException 
	{
		Connection con = ConnectionFactory.getConnection();
		String query="INSERT INTO customer (idClient,numeClient,varstaClient,adresaClient) VALUES (?,?,?,?)";
		PreparedStatement inserare = null;
		ResultSet rez=null;
		if(verificareClient(client)==1) 
			System.out.println("Clientul se afla deja in baza de date");
		else
		{
				try {
						inserare = con.prepareStatement(query, inserare.RETURN_GENERATED_KEYS);
						inserare.setInt(1, client.getId());
						inserare.setString(2, client.getNume());
						inserare.setInt(3, client.getVarsta());
						inserare.setString(4, client.getAdresa());
						inserare.executeUpdate();
						rez = inserare.getGeneratedKeys();				
						if (rez.next()) 
						{
							System.out.println("S-a inserat clientul in baza de date ");
							System.out.println(client.afisareClient());
						}
				} catch (SQLException e) 
				{
					System.out.println(e.getMessage());
				} 
		}
		con.close();
		inserare.close();
		rez.close();
	}
	
	public  void updateClient(Customer client, String optiune) throws SQLException
	{
		Connection con= (Connection) ConnectionFactory.getConnection();
		PreparedStatement update = null;
		String queryNume = "UPDATE customer SET numeClient="+'"'+client.getNume()+'"'+" WHERE idClient="+client.getId();
		String queryVarsta = "UPDATE customer SET varstaClient="+'"'+client.getVarsta()+'"'+" WHERE idClient="+client.getId();
		String queryAdresa = "UPDATE customer SET adresaClient="+'"'+client.getAdresa()+'"'+" WHERE idClient="+client.getId();
		if(verificareClient(client)==1)
		{
			try{
				if(optiune.equals("nume"))
				{
					update = (PreparedStatement) con.prepareStatement(queryNume);
					update.executeUpdate();
					System.out.println("Numele clientului : "+client.afisareClient() + " a fost actualizat");
				}
				if(optiune.equals("varsta"))
				{
					update = (PreparedStatement) con.prepareStatement(queryVarsta);
					update.executeUpdate();
					System.out.println("Varsta clientului : "+client.afisareClient() + " a fost actualizata");
				}
				if(optiune.equals("adresa"))
				{
					update = (PreparedStatement) con.prepareStatement(queryAdresa);
					update.executeUpdate();
					System.out.println("Adresa clientului : "+client.afisareClient() + " a fost actualizata");
				}
				
			}
			catch(SQLException e){
				System.out.println(e.getMessage());	}
		}else
		{
			System.out.println("Clientul nu se afla in baza de date ! ");
		}
		con.close();
		update.close();
	}
	
	public  void stergereClient(Customer client) throws SQLException
	{
		Connection con= (Connection) ConnectionFactory.getConnection();
		PreparedStatement update = null;
		String query = "DELETE FROM  customer WHERE idClient="+client.getId();
		if(verificareClient(client)==1) {
			try{
					update = (PreparedStatement) con.prepareStatement(query);
					update.executeUpdate();
					System.out.println(client.afisareClient() + " a fost sters");
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
			}	
		}else System.out.println("Clientul respectiv nu se afla in baza de date !");
		con.close();
		update.close();
	}
	
	public  ArrayList<Customer> getCustomers() throws SQLException
	{
			Connection con=ConnectionFactory.getConnection();
			ArrayList<Customer> customers=new ArrayList<Customer>();// lista de clienti afisati		
			String query="SELECT * FROM customer";
			PreparedStatement afisare = null;
			ResultSet rez = null;
			int id=0;
			String nume="";
			int varsta=0;
			String adresa="";
			try {
				afisare = con.prepareStatement(query);
				rez = afisare.executeQuery();
				while(	rez.next() ) 
				{
					id=rez.getInt("idClient");
				    nume = rez.getString("numeClient");
					varsta = rez.getInt("varstaClient");
					adresa = rez.getString("adresaClient");				
					Customer client = new Customer(id, nume, varsta, adresa);
					customers.add(client);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			Collections.sort(customers);
			con.close();
			afisare.close();
			rez.close();
			return customers;
	}	
	public  int verificareClient(Customer client) throws SQLException
	{
		int ok=0;
		for(Customer customer : getCustomers())		
			if(customer.getId()==client.getId())		
				ok=1;			
		return ok;
	}
	public Customer cautareClient(int idc) throws SQLException
	{
		Customer client = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM customer WHERE idClient="+idc;
		PreparedStatement afisare = null;
		ResultSet rez = null;
		int id=0;
		String nume="";
		int varsta=0;
		String adresa="";
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(	rez.next() ) 
			{
				id=rez.getInt("idClient");
			    nume = rez.getString("numeClient");
				varsta = rez.getInt("varstaClient");
				adresa = rez.getString("adresaClient");				
				client = new Customer(id, nume, varsta, adresa);				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		con.close();
		afisare.close();
		rez.close();
		return client;			
		
	}

}
