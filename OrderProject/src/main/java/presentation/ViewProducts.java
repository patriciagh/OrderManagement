package presentation;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Customer;
import model.Product;
//GHITUN PATRICIA ROXANA - 30227
public class ViewProducts extends JFrame{
	public ViewProducts() throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new GridLayout(1,1));
		this.setTitle("Afisare produse");
		this.setSize(500, 400);
		String []coloane=Gui.getColoane(new Product());		
		JTable tab=Gui.getTable(new Product());
		JScrollPane scroll = new JScrollPane(tab);
		panel.add(scroll);
		this.add(panel);
		this.setVisible(true);	
	}
	
}
