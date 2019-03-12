package bll;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDAO;
import dao.ProductDAO;
import model.Customer;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class ProductBLL
{
	public ProductBLL()
	{
		
	}
	public void afisare() throws SQLException
	{
		ProductDAO dao=new ProductDAO();
		dao.afisareProduse();
	}
	public void inserare(Product produs) throws SQLException
	{	
		ProductDAO dao=new ProductDAO();
		dao.inserareProdus(produs);
	}
	public void update(Product produs,String optiune) throws SQLException
	{		
		ProductDAO dao=new ProductDAO();
		dao.updateProdus(produs, optiune);
	}
	public void stergere(Product produs) throws SQLException
	{
		ProductDAO dao=new ProductDAO();
		dao.stergereProdus(produs);
	}
	public ArrayList<Product> getProducts() throws SQLException
	{
		ProductDAO dao=new ProductDAO();
		return dao.getProducts();
	}
	public Product cautareProdus(int idProdus) throws SQLException
	{
		ProductDAO dao=new ProductDAO();
		Product gasit=dao.cautareProdus(idProdus);
		if(gasit==null)
		{
			System.out.println("Produsul nu a fost gasit ! ");
			return null;
		}		
		else return gasit;
	}
}

