package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Customer;
import model.Order;
//GHITUN PATRICIA ROXANA - 30227
public class OrderDAO {
	protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
	public  void afisareComenzi() throws SQLException 
	{
		Order com = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM comanda";
		PreparedStatement afisare = null;
		ResultSet rez = null;
		int ido=0;
		int idc=0;
		int idp=0;
		double tot=0;
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(rez.next()) 
			{
				ido=rez.getInt("idComanda");
				idc=rez.getInt("idClient");
				idp=rez.getInt("idProdus");
				tot=rez.getDouble("total");
				com=new Order(ido,idc,idp,tot);
				System.out.println(com.afisareComanda());
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	
	}	
	
	public  void inserareComanda(Order comanda) throws SQLException 
	{
		Connection con = ConnectionFactory.getConnection();
		String query="INSERT INTO comanda (idComanda,idClient,idProdus,total) VALUES (?,?,?,?)";
		PreparedStatement inserare = null;
		ResultSet rez=null;
		try {
						inserare = con.prepareStatement(query, inserare.RETURN_GENERATED_KEYS);
						inserare.setInt(1, comanda.getIdOrder());
						inserare.setInt(2, comanda.getIdClient());
						inserare.setInt(3, comanda.getIdProdus());
						inserare.setDouble(4, comanda.getTotal());
						inserare.executeUpdate();
						rez = inserare.getGeneratedKeys();				
						System.out.println("S-a inserat comanda in baza de date ");
						System.out.println(comanda.afisareComanda());
						
				} catch (SQLException e) 
				{
					System.out.println(e.getMessage());
				} 
		
	}
	
	public  ArrayList<Order> getOrders() throws SQLException
	{
		Order com = null;
		Connection con = ConnectionFactory.getConnection();
		String query="SELECT * FROM comanda";
		PreparedStatement afisare = null;
		ResultSet rez = null;
		ArrayList<Order> orders=new ArrayList<Order>();
		int ido=0;
		int idc=0;
		int idp=0;
		double tot=0;
		try {
			afisare = con.prepareStatement(query);
			rez = afisare.executeQuery();
			while(rez.next()) 
			{
				ido=rez.getInt("idComanda");
				idc=rez.getInt("idClient");				;
				idp=rez.getInt("idProdus");
				tot=rez.getDouble("total");
				com=new Order(ido,idc,idp,tot);
				orders.add(com);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Collections.sort(orders);
		return orders;
	}	
	public  int verificareComanda(int comanda) throws SQLException
	{
		int ok=0;
		for(Order o : getOrders())		
			if(o.getIdOrder()==comanda)		
				ok=1;			
		return ok;
	}
	
}
