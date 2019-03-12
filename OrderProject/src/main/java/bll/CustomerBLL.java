package bll;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bll.validators.AgeValidator;
import bll.validators.NameValidator;
import bll.validators.Validator;
import dao.CustomerDAO;
import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class CustomerBLL
{
	private List<Validator<Customer>> validators;
	public CustomerBLL()
	{
		validators = new ArrayList<Validator<Customer>>();
		validators.add(new AgeValidator());
		validators.add(new NameValidator());
	}
	public void afisare() throws SQLException
	{
		CustomerDAO dao=new CustomerDAO();
		dao.afisareClienti();
	}
	public void inserare(Customer client) throws SQLException
	{		
		for(Validator<Customer> v : validators)
		{
			v.validate(client);
		}
		CustomerDAO dao=new CustomerDAO();
		dao.inserareClient(client);
	}
	public void update(Customer client,String optiune) throws SQLException
	{
		for(Validator<Customer> v : validators)
		{
			v.validate(client);
		}
		CustomerDAO dao=new CustomerDAO();
		dao.updateClient(client, optiune);
	}
	public void stergere(Customer client) throws SQLException
	{
		CustomerDAO dao=new CustomerDAO();
		dao.stergereClient(client);
	}
	public ArrayList<Customer> getCustomers() throws SQLException
	{
		CustomerDAO dao=new CustomerDAO();
		return dao.getCustomers();
	}
	public Customer cautareClient(int idClient) throws SQLException
	{
		CustomerDAO dao=new CustomerDAO();
		Customer gasit=dao.cautareClient(idClient);
		if(gasit==null)
		{
			System.out.println("Clientul nu a fost gasit ! ");
			return null;
		}		
		else return gasit;
	}
	
}
