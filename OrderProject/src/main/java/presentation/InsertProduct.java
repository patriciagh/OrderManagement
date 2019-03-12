package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.ProductBLL;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class InsertProduct extends JFrame {
		
	private JLabel idLabel=new JLabel("ID");
	private JLabel numeLabel=new JLabel("NUME");
	private JLabel pretLabel=new JLabel("PRET");
	private JLabel stocLabel=new JLabel("STOC");
	private JLabel label=new JLabel("");
	
	private JTextField id=new JTextField();
	private JTextField nume=new JTextField();
	private JTextField pret=new JTextField();
	private JTextField stoc=new JTextField();
	
	private int idProdus=0;
	private String numeProdus="";
	private double pretProdus=0;
	private int stocProdus=0;
	
	private JButton salvare=new JButton("Adaugare");
	ProductBLL bll=new ProductBLL();
	public InsertProduct() 
	{		
		this.setLayout(null)	;	
		this.setSize(400,450);
		this.setTitle("Inserare produs ");
		this.setLocationRelativeTo(null);
		idLabel.setBounds(30, 0, 100, 30);
		id.setBounds(20, 30, 200, 30);
		numeLabel.setBounds(30, 60, 100, 30);
		nume.setBounds(20, 90, 200, 30);
		pretLabel.setBounds(30, 120, 100, 30);
		pret.setBounds(20, 150, 200, 30);
		stocLabel.setBounds(30, 180, 100, 30);
		stoc.setBounds(20, 210, 200, 30);
		salvare.setBounds(40, 260, 100, 30);
		label.setBounds(20,300,300,30);
		salvare.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e) 
			{	
				if(id.getText().equals("")==false && nume.getText().equals("")==false && pret.getText().equals("")==false && stoc.getText().equals("")==false)	
				{
					idProdus=Integer.parseInt(id.getText());
					numeProdus=new String(nume.getText());
					pretProdus=Double.parseDouble(pret.getText());
					stocProdus=Integer.parseInt(stoc.getText());
					try {
						Product cautat=bll.cautareProdus(idProdus);
						if(cautat==null)
						{
							Product nou=new Product(idProdus,numeProdus,pretProdus,stocProdus);
							bll.inserare(nou);
							label.setText(nou.afisareProdus()+" a fost inserat");
						}else label.setText("Produs existent ! ");
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}else label.setText("Date incorecte ! ");
			}			
		});	
		this.add(label);
		this.add(salvare);
		this.add(stoc);
		this.add(stocLabel);
		this.add(pret);
		this.add(pretLabel);
		this.add(nume);
		this.add(numeLabel);
		this.add(idLabel);
		this.add(id);
		this.setVisible(true);
	}
}	
