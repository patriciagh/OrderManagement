package presentation;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.CustomerBLL;
import model.Customer;
//GHITUN PATRICIA ROXANA - 30227
public class DeleteCustomer extends JFrame{

	private JLabel idLabel=new JLabel("ID : ");	
	private JLabel label=new JLabel();
	private JLabel sters=new JLabel();
	private JTextField id=new JTextField();	
	
	private JButton cautare=new JButton("Cautare client");
	private int idClient=0;
	private JButton stergere=new JButton("Stergere");
	
	CustomerBLL bll=new CustomerBLL();
	Customer cautat=null;
	public DeleteCustomer() 
	{		
		this.setLayout(null)	;	
		this.setSize(400,300);
		this.setTitle("Stergere client ");
		this.setLocationRelativeTo(null);
		//Font font=new Font("Mongolian Baiti", Font.BOLD, 17);
		idLabel.setBounds(20, 10, 250, 30);
		id.setBounds(60,10,150,30);
		cautare.setBounds(20,60,200,30);
		label.setBounds(20,90,200,30);
		stergere.setBounds(20, 130, 100, 30);
		sters.setBounds(20,160,200,30);
		cautare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(id.getText().trim().equals("")==false)
				{
					idClient=Integer.parseInt(id.getText());								
					try {
						cautat=bll.cautareClient(idClient);
						if(cautat!=null)							
								label.setText(cautat.afisareClient());															
						else label.setText("Clientul nu se afla in baza de date");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else label.setText("Id-ul introdus nu este corect");
			}			
		});
		stergere.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				try {
					sters.setText("Clientul "+cautat.getId()+" a fost sters !");
					bll.stergere(cautat);					
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}
			}			
		});
		this.add(sters);
		this.add(stergere);
		this.add(cautare);
		this.add(id);
		this.add(idLabel);
		this.add(label);
		this.setVisible(true);
	}
}
