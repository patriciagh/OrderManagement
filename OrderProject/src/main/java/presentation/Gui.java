package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bll.CustomerBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Customer;
import model.Order;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class Gui extends JFrame{
	//optiuni
	private JLabel customersLabel=new JLabel("CLIENTI : ");
	private JLabel productsLabel=new JLabel("PRODUSE : ");
	private JLabel ordersLabel=new JLabel("COMENZI : ");
	// butoane customeri
	private JButton afisareClienti = new JButton("Afisare");
	private JButton inserareClient= new JButton("Inserare");
	private JButton stergereClient = new JButton("Stergere");
	private JButton updateClient = new JButton("Update");
	// butoane produse
	private JButton afisareProduse = new JButton("Afisare");
	private JButton inserareProdus= new JButton("Inserare");
	private JButton stergereProdus = new JButton("Stergere");
	private JButton updateProdus = new JButton("Update");
	//buton pentru comanda
	private JButton comanda=new JButton("Comanda noua");	
	private JButton afisareComenzi=new JButton("Afisare comenzi");
	public Gui()
	{		
		this.setSize(500,350);
		this.setTitle("Warehouse");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		customersLabel.setBounds(30, 0, 100, 30);
		afisareClienti.setBounds(20, 40, 100, 30);
		inserareClient.setBounds(20, 80, 100, 30);
		stergereClient.setBounds(20, 120, 100, 30);
		updateClient.setBounds(20, 160, 100, 30);
		
		afisareClienti.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{					
						try {
							ViewCustomers afisareClient = new ViewCustomers();
							System.out.println("Afisare clienti ");
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (IllegalArgumentException e1) {
							e1.printStackTrace();
						} catch (InvocationTargetException e1) {
							e1.printStackTrace();
						}
					}			
				});
		afisareProduse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{					
				try {
					ViewProducts afisareProdus = new ViewProducts();
					System.out.println("Afisare produse ");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}			
		});
		inserareClient.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						InsertCustomer inserareCustomer = new InsertCustomer();
						System.out.println("Inserare client ");
					}
			
				}
		);
		updateClient.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					UpdateCustomer updateCustomer = new UpdateCustomer();
					System.out.println("Update client ");
				}
		
			}
		);
		stergereClient.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeleteCustomer stergereCustomer = new DeleteCustomer();
				System.out.println("Stergere client ");
			}
	
		}
	);
		inserareProdus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				InsertProduct inserareProdus = new InsertProduct();
				System.out.println("Inserare produs ");
			}
	
		}
		);
		stergereProdus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				DeleteProduct stergereProd = new DeleteProduct();
				System.out.println("Stergere produs ");
			}
	
		}
		);
		updateProdus.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				UpdateProduct updateProd = new UpdateProduct();
				System.out.println("Update produs ");
			}
	
		}
	);
		productsLabel.setBounds(170,0,100,30);
		afisareProduse.setBounds(160,40,100,30);
		inserareProdus.setBounds(160,80,100,30);
		stergereProdus.setBounds(160,120,100,30);
		updateProdus.setBounds(160,160,100,30);
		
		ordersLabel.setBounds(330, 0, 100, 30);
		comanda.setBounds(290,40, 150,30);
		afisareComenzi.setBounds(290, 80,150,30);;
		comanda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				NewOrder comandaNoua = new NewOrder();
				System.out.println("Comanda noua ");
			}
	
		}
	   );
		afisareComenzi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					ViewOrders afisareOrders = new ViewOrders();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Afisare comenzi ");
			}
	
		}
	   );
		//Adaugare in frame - elemente
		this.add(afisareComenzi);
		this.add(comanda);
		this.add(ordersLabel);
		this.add(stergereProdus);
		this.add(updateProdus);
		this.add(inserareProdus);
		this.add(inserareClient);
		this.add(stergereClient);
		this.add(updateClient);
		this.add(productsLabel);
		this.add(afisareProduse);
		this.add(customersLabel);
		this.add(afisareClienti);
		this.setVisible(true);
	
	}
	public static int getClasa(Class c)
	{
		int clasa=-1;//0 - customer , 1-product , 2-orders
		if(c.getName().equals("model.Customer"))
			clasa=0;
		if(c.getName().equals("model.Product"))
			clasa=1;
		if(c.getName().equals("model.Order"))
			clasa=2;
		return clasa;
	}
	public static String[] getColoane(Object obj)
	{
		Field[] fields=obj.getClass().getDeclaredFields();
		int nr=0 , i=0;
		for(Field f : fields) nr++;
		String[] col = new String[nr];
		for(Field f : fields)
			{
				col[i]=new String(f.getName().toString());
				i++;
			}
		return col;
	}
	public static DefaultTableModel getRowsCustomers(DefaultTableModel model) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Object []rows;
		CustomerBLL bll=new CustomerBLL();
		ArrayList<Customer> customers=new ArrayList<Customer>();
		Method[] methods=bll.getClass().getDeclaredMethods();
		for(Method m : methods) 
			if(m.getName().equals("getCustomers"))
				customers=(ArrayList<Customer>) m.invoke(bll, null);
		for(Customer client : customers)
		{
			rows=new Object[]{
					client .getId(),
					client .getNume(),
					client .getVarsta(),
					client .getAdresa()
					};
			model.addRow(rows);
		}
		return model;
	}
	public static DefaultTableModel getRowsProducts(DefaultTableModel model) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Object []rows;
		ProductBLL bll=new ProductBLL();
		ArrayList<Product> products=new ArrayList<Product>();
		Method[] methods=bll.getClass().getDeclaredMethods();
		for(Method m : methods) 
			if(m.getName().equals("getProducts"))
				products=(ArrayList<Product>) m.invoke(bll, null);
		for(Product client : products)
		{
			rows=new Object[]{
					client .getId(),
					client .getNume(),
					client .getPret(),
					client .getStoc()
					};
			model.addRow(rows);
		}
		return model;
	}
	public static DefaultTableModel getRowsOrders(DefaultTableModel model) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Object []rows;
		OrderBLL bll=new OrderBLL();
		ArrayList<Order> orders=new ArrayList<Order>();
		Method[] methods=bll.getClass().getDeclaredMethods();
		for(Method m : methods) 
			if(m.getName().equals("getOrders"))
				orders=(ArrayList<Order>) m.invoke(bll, null);
		for(Order o : orders)
		{
			rows=new Object[]{
					o .getIdOrder(),
					o .getIdClient(),
					o .getIdProdus(),
					o .getTotal()
					};
			model.addRow(rows);
		}
		return model;
	}
	public static JTable creareTabelNull()
	{
		JTable tabel=tabel=new JTable()
		{
			 public boolean isCellEditable(int row, int column) 
			 {                
	                return false;               
	         };
		};
		return tabel;
	}
	
	public static JTable getTable(Object obj) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String []coloane=getColoane(obj);
		JTable tabel = null;
		DefaultTableModel model=new DefaultTableModel(coloane,0);
		
		if(getClasa(obj.getClass())==0)
		{   
			model=getRowsCustomers(model);
			tabel=creareTabelNull();
			tabel.setModel(model);
			return tabel;
		}
		if(getClasa(obj.getClass())==1)
		{   
			model=getRowsProducts(model);
			tabel=creareTabelNull();
			tabel.setModel(model);
			return tabel;
		}
		if(getClasa(obj.getClass())==2)
		{   
			model=getRowsOrders(model);
			tabel=creareTabelNull();
			tabel.setModel(model);
			return tabel;
		}
		return tabel;		
	}
	
}


