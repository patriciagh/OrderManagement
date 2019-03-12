package presentation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bll.CustomerBLL;
import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class InsertCustomer extends JFrame{
	
	private JLabel idLabel=new JLabel("ID");
	private JLabel numeLabel=new JLabel("NUME");
	private JLabel varstaLabel=new JLabel("VARSTA");
	private JLabel adresaLabel=new JLabel("ADRESA");
	private JLabel label=new JLabel("");
	
	private JTextField id=new JTextField();
	private JTextField nume=new JTextField();
	private JTextField varsta=new JTextField();
	private JTextField adresa=new JTextField();
	
	private int idClient=0;
	private String numeClient="";
	private int varstaClient=0;
	private String adresaClient="";
	
	private JButton salvare=new JButton("Adaugare");
	CustomerBLL bll=new CustomerBLL();
	public InsertCustomer() 
	{		
		this.setLayout(null)	;	
		this.setSize(400,450);
		this.setTitle("Inserare client ");
		this.setLocationRelativeTo(null);
		idLabel.setBounds(30, 0, 100, 30);
		id.setBounds(20, 30, 200, 30);
		numeLabel.setBounds(30, 60, 100, 30);
		nume.setBounds(20, 90, 200, 30);
		varstaLabel.setBounds(30, 120, 100, 30);
		varsta.setBounds(20, 150, 200, 30);
		adresaLabel.setBounds(30, 180, 100, 30);
		adresa.setBounds(20, 210, 200, 30);
		salvare.setBounds(40, 260, 100, 30);
		label.setBounds(20,300,300,30);
		salvare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(id.getText().equals("")==false && nume.getText().equals("")==false && varsta.getText().equals("")==false && adresa.getText().equals("")==false)
					{					
						idClient=Integer.parseInt(id.getText());
						numeClient=new String(nume.getText());
						varstaClient=Integer.parseInt(varsta.getText());
						adresaClient=new String(adresa.getText());
						Customer cautat;
						try {
							cautat = bll.cautareClient(idClient);
							if(cautat==null)
							{
								Customer nou=new Customer(idClient,numeClient,varstaClient,adresaClient);
								
									bll.inserare(nou);
									label.setText(nou.afisareClient()+" a fost inserat");
								
							}else label.setText("Client existent ! ");
						} catch (SQLException e2) {
							e2.printStackTrace();
						}						
					}else label.setText("Date incorecte ! ");
			}			
		});	
		this.add(label);
		this.add(salvare);
		this.add(adresa);
		this.add(adresaLabel);
		this.add(varsta);
		this.add(varstaLabel);
		this.add(nume);
		this.add(numeLabel);
		this.add(idLabel);
		this.add(id);
		this.setVisible(true);
	}
}
