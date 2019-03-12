package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import bll.CustomerBLL;
import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class UpdateCustomer extends JFrame{
	private JLabel idCustomer=new JLabel("Introduceti id-ul clientului : ");
	private JTextField id=new JTextField();	
	private JRadioButton nume = new JRadioButton("Nume");
	private JRadioButton varsta = new JRadioButton("Varsta");
	private JRadioButton adr = new JRadioButton("Adresa");		
	private JLabel label=new JLabel("");
	private JLabel actualizat=new JLabel();
	private JButton salvare=new JButton("Salvare");
	private JButton cautare=new JButton("Cautare");
	private JTextField atribut=new JTextField();
	private ButtonGroup group = new ButtonGroup();
	private int idText=0;
	CustomerBLL bll=new CustomerBLL();
	Customer cautat=null;
	public UpdateCustomer() 
	{		
		this.setLayout(null);	
		this.setSize(450,450);
		this.setTitle("Update client");
		this.setLocationRelativeTo(null);
		
		 idCustomer.setBounds(20, 0, 250, 30);
		 id.setBounds(20, 30, 200, 30);//tf
		 label.setBounds(20, 100, 250, 30);
		 cautare.setBounds(20, 70, 100, 30);
		 nume.setBounds(20, 130, 100,30);
		 varsta.setBounds(20, 160, 100,30);
		 adr.setBounds(20, 190, 100,30);
		 atribut.setBounds(20,230,200,30);//tf
		 salvare.setBounds(20, 280, 100,30);
		 actualizat.setBounds(20,310,250,30);
		 group.add(adr);
		  group.add(nume);
		 group.add(varsta);
	
		 cautare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					idText=Integer.parseInt(id.getText());								
					try {
						cautat=bll.cautareClient(idText);
						if(cautat!=null)							
								label.setText(cautat.afisareClient());															
						else label.setText("Clientul nu a fost gasit");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}			
			});
		 salvare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(getRadioNume()==true)											
						actualizareNume();
					
					if(getRadioVarsta()==true)					
						actualizareVarsta();
					
					if(getRadioAdresa()==true)					
						actualizareAdresa();					
					
				}			
			});
		 this.add(actualizat);
		 this.add(atribut);
		 this.add(salvare);
		 this.add(cautare);
		 this.add(label);
		 this.add(nume);
		 this.add(varsta);
		 this.add(adr);
		 this.add(id);
		 this.add(idCustomer);
		this.setVisible(true);
	}	
	
	public boolean getRadioNume()
	{			return nume.isSelected();		}
	public boolean getRadioVarsta()
	{			return varsta.isSelected();		}
	public boolean getRadioAdresa()
	{			return adr.isSelected();		}
	
	public void actualizareNume()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			String numeNou=atribut.getText().trim();
			cautat.setNume(numeNou);
			try {
				bll.update(cautat, "nume");
				actualizat.setText("Clientul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizat numele clientului "+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Numele introdus este incorect");
	}
	
	public void actualizareVarsta()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			int varstaNoua=Integer.parseInt(atribut.getText().trim());					
			cautat.setVarsta(varstaNoua);
			try {
				bll.update(cautat, "varsta");
				actualizat.setText("Clientul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizata varsta clientului"+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Varsta introdusa este incorecta");
	}
	
	public void actualizareAdresa()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			String adrNoua=atribut.getText().trim();					
			cautat.setAdresa(adrNoua);
			try {
				bll.update(cautat, "adresa");
				actualizat.setText("Clientul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizata adresa clientului"+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Adresa introdusa este incorecta");
	}
	
}
