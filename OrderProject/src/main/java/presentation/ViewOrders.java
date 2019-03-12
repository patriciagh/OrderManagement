package presentation;

import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Customer;
import model.Order;
//GHITUN PATRICIA ROXANA - 30227
public class ViewOrders extends JFrame{
	public ViewOrders() throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new GridLayout(1,1));
		this.setTitle("Afisare comenzi");		
		this.setSize(500, 400);
		String []coloane=Gui.getColoane(new Order());
	///	for(String s : coloane)
	//		System.out.println(s);		
		JTable tab=Gui.getTable(new Order());
		JScrollPane scroll = new JScrollPane(tab);
		panel.add(scroll);
		this.add(panel);
		this.setVisible(true);		
	}
	
}

