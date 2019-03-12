package presentation;

import java.awt.GridLayout;
//GHITUN PATRICIA ROXANA - 30227
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import model.Customer;
public class ViewCustomers extends JFrame{
	public ViewCustomers() throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new GridLayout(1,1));
		this.setTitle("Afisare clienti");		
		this.setSize(500, 400);
		String []coloane=Gui.getColoane(new Customer());		
		JTable tab=Gui.getTable(new Customer());
		JScrollPane scroll = new JScrollPane(tab);
		panel.add(scroll);
		this.add(panel);
		this.setVisible(true);		
	}
	
}
