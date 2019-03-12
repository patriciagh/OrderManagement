package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bll.CustomerBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Customer;
import model.Order;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class NewOrder extends JFrame {
	private JLabel idComanda=new JLabel("Id comanda : ");
	private JLabel comandaLabel=new JLabel(" ");//eroare comanda
	private JLabel idClient=new JLabel("Id client : ");
	private JLabel clientLabel=new JLabel("");//eroare client
	private JLabel idProdus=new JLabel("Id produs : ");
	private JLabel produsLabel=new JLabel("");//eroare client
	private JLabel cantProdus=new JLabel("Cantitate : ");
	private JLabel cantLabel=new JLabel("");
	private JLabel dateComanda=new JLabel("");
	private JTextField comandaText = new JTextField();// COMANDA TF
	private JTextField clientText = new JTextField();
	private JTextField produsText = new JTextField();
	private JTextField cantText = new JTextField();	

	private int idComandaInt=0;
	private int idClientInt=0;
	private int idProdusInt=0;
	private int cantInt=0;
	private JButton verificare = new JButton("Verificare date");
	private JButton salvare = new JButton("Salvare");
	OrderBLL oBLL=new OrderBLL();
	ProductBLL pBLL=new ProductBLL();
	CustomerBLL cBLL= new CustomerBLL();
	Product produs=null;
	Customer client=null;
	Order comanda=null;
	public NewOrder()
	{
		this.setSize(500,500);
		this.setTitle("Comanda noua");
		this.setLayout(null);			
		this.setLocationRelativeTo(null);
		idComanda.setBounds(20, 0, 100, 30);// ID COMANDA
		comandaText.setBounds(20,30,150,30);//comanda tf
		idClient.setBounds(20,60,100,30);
		clientText.setBounds(20,90,150,30);
		comandaLabel.setBounds(200,60,200,30);
		clientLabel.setBounds(200,90,200,30);
		idProdus.setBounds(20,120,100,30);
		produsText.setBounds(20,150,150,30);
		produsLabel.setBounds(200,180,200,30);
		cantProdus.setBounds(20,180,100,30);
		cantText.setBounds(20,210,150,30);
		cantLabel.setBounds(200,210,200,30);
		verificare.setBounds(200,30,150,30);
		salvare.setBounds(200,150,150,30);
		dateComanda.setBounds(20,240,1000,30);
		
		verificare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(comandaText.getText().trim().equals("")==false)
				{
					idComandaInt=Integer.parseInt(comandaText.getText().trim());
					try {
						if(oBLL.cautare(idComandaInt)==1) 
						{
							comandaLabel.setText("Comanda existenta !");
						}else comandaLabel.setText("Comanda - ok");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else comandaLabel.setText("Id-ul comenzii nu este corect");
				
				if(clientText.getText().trim().equals("")==false)
				{
					idClientInt=Integer.parseInt(clientText.getText().trim());
					try {
						if(cBLL.cautareClient(idClientInt)==null)
						{
							clientLabel.setText("Clientul nu a fost gasit!");
						}else
						{
							client=cBLL.cautareClient(idClientInt);
							clientLabel.setText(client.afisareClient());
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else clientLabel.setText("Id-ul clientului introdus gresit");
			}			
		});
		
		salvare.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{		
				if(produsText.getText().trim().equals("")==false)
				{
					idProdusInt=Integer.parseInt(produsText.getText().trim());
					try {
						if(pBLL.cautareProdus(idProdusInt)==null) 
						{
							produsLabel.setText("Produs inexistent !");
						}else
						{
							produs=pBLL.cautareProdus(idProdusInt);
							produsLabel.setText(produs.afisareProdus());
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else produsLabel.setText("Id-ul produsului nu este corect");
				
				
				if(cantText.getText().trim().equals("")==false)
				{
					cantInt=Integer.parseInt(cantText.getText().trim());
					if(cantInt<=produs.getStoc())
					{
						double total=cantInt*produs.getPret();
						produs.setStoc(produs.getStoc()-cantInt);
						cantLabel.setText("Noul stoc :"+produs.getStoc()+"  total:"+new DecimalFormat("#.##").format(total));
						try {
							System.out.println(idComandaInt);
							System.out.println(idClientInt);
							System.out.println(idProdusInt);
							System.out.println(total);
							comanda=new Order(idComandaInt,idClientInt,idProdusInt,total);
							System.out.println(comanda.afisareComanda());
							pBLL.update(produs, "stoc");
							oBLL.inserare(comanda);
							factura(comanda,client,produs,cantInt);
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}else cantLabel.setText("Stoc insuficient !");
					
				}else cantLabel.setText("Cantitate gresita");
			}			
		});
	
		this.add(dateComanda);
		this.add(salvare);
		this.add(cantLabel);
		this.add(cantText);
		this.add(cantProdus);
		this.add(idProdus);
		this.add(produsLabel);
		this.add(produsText);
		this.add(verificare);
		this.add(clientLabel);
		this.add(comandaLabel);
		this.add(idComanda);
		this.add(comandaText);
		this.add(clientText);
		this.add(idClient);
		this.setVisible(true);
	}
	
	public static void factura(Order comanda , Customer client,Product produs , int cant)
	{
		System.out.println("Fac o factura");
		File file=new File("C:\\Users\\patri\\Documents\\Facultate\\PT2018\\PT2018_30227_Ghitun_Patricia\\PT2018_30227_Ghitun_Patricia_Assignment_3\\tema3_tp\\Facturi\\factura"+comanda.getIdOrder()+".txt");
		try(FileWriter fileWrite = new FileWriter(file, true);
			    BufferedWriter buffer = new BufferedWriter(fileWrite);
			    PrintWriter p = new PrintWriter(buffer))
				{
				    p.println("FACTURA cu id-ul : "+comanda.getIdOrder());
				    p.println("Date comanda : ");
				    p.println();
				    p.println("Comanda cu numarul : "+comanda.getIdOrder());
				    p.println("Clientul : "+client.getId());
				    p.println("    "+"Nume client : "+client.getNume());
				    p.println("    "+"Varsta : "+client.getVarsta());
				    p.println("    "+"Adresa : "+client.getAdresa());	
				    p.println();
				    p.println("A cumparat produsul : "+produs.getId());
				    p.println("    "+"Nume produs : "+produs.getNume());
				    p.println("    "+"Pret produs : "+produs.getPret());
				    p.println();
				    p.println("Cantitate cumparata : "+cant);
				    p.println("Total de plata : "+comanda.getTotal());
				}
			 catch (IOException e) {
			   System.out.println(e.getMessage());
			}
				
	}
}
