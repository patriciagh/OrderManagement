package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Customer;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class ProductDAO {
	protected static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());
	public  void afisareProduse() throws SQLException 
	{
		Product produs = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM product";
		PreparedStatement afisare = null;
		ResultSet rez = null;
		int id=0;
		String nume="";
		double pret=0;
		int stoc=0;
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(	rez.next() ) 
			{
				id=rez.getInt("idProdus");
			    nume = rez.getString("numeProdus");
				pret = rez.getDouble("pretProdus");
				stoc = rez.getInt("stocProdus");				
				produs = new Product(id, nume, pret, stoc);
				System.out.println(produs.afisareProdus());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		con.close();
		afisare.close();
		rez.close();
	}		
	public  void inserareProdus(Product produs) throws SQLException 
	{
		Connection con = ConnectionFactory.getConnection();
		String query="INSERT INTO product (idProdus,numeProdus,pretProdus,stocProdus) VALUES (?,?,?,?)";
		PreparedStatement inserare = null;
		ResultSet rez=null;
		if(verificareProdus(produs)==1)
			System.out.println("Produs existent deja ! ");
		else {	
				try {
							inserare = con.prepareStatement(query, inserare.RETURN_GENERATED_KEYS);
							inserare.setInt(1, produs.getId());
							inserare.setString(2, produs.getNume());
							inserare.setDouble(3, produs.getPret());
							inserare.setInt(4, produs.getStoc());
							inserare.executeUpdate();
							rez = inserare.getGeneratedKeys();	
							System.out.println("S-a inserat produsul in baza de date ");
							System.out.println(produs.afisareProdus());
							
					} catch (SQLException e) 
					{
						System.out.println(e.getMessage());
					} 
			}
		con.close();
		inserare.close();
		rez.close();
	}
	public int verificareProdus(Product p) throws SQLException
	{
		int ok=0;
		for(Product prod : this.getProducts())		
			if(prod.getId()==p.getId())		
				ok=1;			
		return ok;
	}
	public ArrayList<Product> getProducts() throws SQLException
	{
			Connection con=ConnectionFactory.getConnection();
			ArrayList<Product> products=new ArrayList<Product>();// lista de produse		
			String query="SELECT * FROM product";
			PreparedStatement afisare = null;
			ResultSet rez = null;
			int id=0;
			String nume="";
			double pret=0;
			int stoc=0;
			try {
				afisare = con.prepareStatement(query);
				rez = afisare.executeQuery();
				while(	rez.next() ) 
				{
					id=rez.getInt("idProdus");
				    nume = rez.getString("numeProdus");
					pret = rez.getDouble("pretProdus");
					stoc = rez.getInt("stocProdus");				
					Product produs = new Product(id, nume, pret, stoc);
					products.add(produs);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			Collections.sort(products);
			con.close();
			afisare.close();
			rez.close();
			return products;
	}
	public void updateProdus(Product produs, String optiune) throws SQLException
	{
		Connection con= (Connection) ConnectionFactory.getConnection();
		PreparedStatement update = null;
		String queryNume = "UPDATE product SET numeProdus="+'"'+produs.getNume()+'"'+" WHERE idProdus="+produs.getId();
		String queryPret = "UPDATE product SET pretProdus="+'"'+produs.getPret()+'"'+" WHERE idProdus="+produs.getId();
		String queryStoc = "UPDATE product SET stocProdus="+'"'+produs.getStoc()+'"'+" WHERE idProdus="+produs.getId();
		if(verificareProdus(produs)==1)
		{
			try{
				if(optiune.equals("nume"))
				{
					update = (PreparedStatement) con.prepareStatement(queryNume);
					update.executeUpdate();
					System.out.println(produs.afisareProdus() + " a fost actualizat");
				}
				if(optiune.equals("pret"))
				{
					update = (PreparedStatement) con.prepareStatement(queryPret);
					update.executeUpdate();
					System.out.println("Pretul  : "+produs.afisareProdus() + " a fost actualizat");
				}
				if(optiune.equals("stoc"))
				{
					update = (PreparedStatement) con.prepareStatement(queryStoc);
					update.executeUpdate();
					System.out.println("Stocul : "+produs.afisareProdus() + " a fost actualizat");
				}
				
			}
			catch(SQLException e){
				System.out.println(e.getMessage());	}
		}else
		{
			System.out.println("Produsul nu se afla in baza de date ! ");
		}
		con.close();
		update.close();
	}
	
	public void stergereProdus(Product produs) throws SQLException
	{
		Connection con= (Connection) ConnectionFactory.getConnection();
		PreparedStatement update = null;
		String query = "DELETE FROM  product WHERE idProdus="+produs.getId();
		if(verificareProdus(produs)==1) {
			try{
					update = (PreparedStatement) con.prepareStatement(query);
					update.executeUpdate();
					System.out.println(produs.afisareProdus() + " a fost sters");
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
			}	
		}else System.out.println("Produsul respectiv nu se afla in baza de date !");
		con.close();
		update.close();
		
	}
	public Product cautareProdus(int idp) throws SQLException
	{
		Product produs = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM product WHERE idProdus="+idp;
		PreparedStatement afisare = null;
		ResultSet rez = null;
		int id=0;
		String nume="";
		double pret=0;
		int stoc=0;
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(	rez.next() ) 
			{
				id=rez.getInt("idProdus");
			    nume = rez.getString("numeProdus");
			    pret = rez.getDouble("pretProdus");
				stoc = rez.getInt("stocProdus");				
				produs = new Product(id, nume, pret, stoc);				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		con.close();
		afisare.close();
		rez.close();
		return produs;			
		
	}
}
	
	