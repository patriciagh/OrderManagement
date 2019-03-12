package bll.validators;

import model.*;
//GHITUN PATRICIA ROXANA - 30227
public class NameValidator implements Validator<Customer> 
{
	public void validate(Customer client) 
	{
		if(client.getNume()==null)
		{
			System.out.println("Numele este introdus gresit !");
		}
	}	
	
}
