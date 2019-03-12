package bll.validators;

import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class AgeValidator implements Validator<Customer> 
{
	public void validate(Customer client) 
	{
		if(client.getVarsta() < 18)
		{
			throw new IllegalArgumentException("Varsta este prea mica !");
		}
		
	}
}
