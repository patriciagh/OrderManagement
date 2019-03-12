package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.*;
import model.*;
//GHITUN PATRICIA ROXANA - 30227
public class OrderBLL 
{
	public OrderBLL()
	{
		
	}
	public void afisare() throws SQLException
	{
		OrderDAO dao=new OrderDAO();
		dao.afisareComenzi();
	}
	public void inserare(Order or) throws SQLException
	{	
		OrderDAO dao=new OrderDAO();
		dao.inserareComanda(or);
	}
	public int cautare(int id) throws SQLException
	{
		OrderDAO dao=new OrderDAO();
		return dao.verificareComanda(id);
	}
	public ArrayList<Order> getOrders() throws SQLException
	{
		OrderDAO dao=new OrderDAO();
		return dao.getOrders();
	}
}
