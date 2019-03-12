package presentation;

import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import bll.*;
import model.*;
//GHITUN PATRICIA ROXANA - 30227
public class UpdateProduct extends JFrame {
	private JLabel idProduct=new JLabel("Introduceti id-ul produsului : ");
	private JTextField id=new JTextField();	
	private JRadioButton nume = new JRadioButton("Nume");
	private JRadioButton pret = new JRadioButton("Pret");
	private JRadioButton stoc = new JRadioButton("Stoc");		
	private JLabel label=new JLabel("");
	private JLabel actualizat=new JLabel();
	private JButton salvare=new JButton("Salvare");
	private JButton cautare=new JButton("Cautare");
	private JTextField atribut=new JTextField();
	private ButtonGroup group = new ButtonGroup();
	private int idText=0;
	ProductBLL bll=new ProductBLL();
	Product cautat=null;
	public UpdateProduct() 
	{		
		this.setLayout(null);	
		this.setSize(450,450);
		this.setTitle("Update Produs");
		this.setLocationRelativeTo(null);
		
		 idProduct.setBounds(20, 0, 250, 30);
		 id.setBounds(20, 30, 200, 30);//tf
		 label.setBounds(20, 100, 250, 30);
		 cautare.setBounds(20, 70, 100, 30);
		 nume.setBounds(20, 130, 100,30);
		 pret.setBounds(20, 160, 100,30);
		 stoc.setBounds(20, 190, 100,30);
		 atribut.setBounds(20,230,200,30);//tf
		 salvare.setBounds(20, 280, 100,30);
		 actualizat.setBounds(20,310,250,30);
		 group.add(pret);
		  group.add(nume);
		 group.add(stoc);
	
		 cautare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{	
					if(id.getText().equals("")==false)
					{
						idText=Integer.parseInt(id.getText());								
						try {
							cautat=bll.cautareProdus(idText);
							if(cautat!=null)							
									label.setText(cautat.afisareProdus());															
							else label.setText("Produsul nu a fost gasit");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else label.setText("Date gresite !");
					
				}			
			});
		 
		 salvare.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{		
					if(getRadioNume()==true)											
						actualizareNume();
					
					if(getRadioPret()==true)					
						actualizarePret();
					
					if(getRadioStoc()==true)					
						actualizareStoc();					
					
				}			
			});
			
		 this.add(actualizat);
		 this.add(atribut);
		 this.add(salvare);
		 this.add(cautare);
		 this.add(label);
		 this.add(nume);
		 this.add(pret);
		 this.add(stoc);
		 this.add(id);
		 this.add(idProduct);
		this.setVisible(true);
	}	
	
	public boolean getRadioNume()
	{			return nume.isSelected();		}
	public boolean getRadioPret()
	{			return pret.isSelected();		}
	public boolean getRadioStoc()
	{			return stoc.isSelected();		}
	
	public void actualizareNume()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			String numeNou=atribut.getText().trim();
			cautat.setNume(numeNou);
			try {
				bll.update(cautat, "nume");
				actualizat.setText("Produsul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizat numele produsului "+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Numele introdus este incorect");
	}
	
	public void actualizarePret()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			double pretNou=Double.parseDouble(atribut.getText().trim());					
			cautat.setPret(pretNou);
			try {
				bll.update(cautat, "pret");
				actualizat.setText("Produsul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizat pretul produsului"+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Pretul introdus este incorect");
	}
	
	public void actualizareStoc()
	{
		if(atribut.getText().trim().equals("")==false)
		{
			int stocNou=Integer.parseInt(atribut.getText().trim());					
			cautat.setStoc(stocNou);
			try {
				bll.update(cautat, "stoc");
				actualizat.setText("Produsul "+cautat.getId()+"a fost actualizat.");
				System.out.println("A fost actualizat stocul produsului"+cautat.getId());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else actualizat.setText("Stocul introdus este incorect");
	}
	
}
