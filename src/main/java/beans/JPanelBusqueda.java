package beans;

import javax.swing.*;
import java.awt.*;

public class JPanelBusqueda extends JPanel {
	public JTextField tfBuscar;
	public JScrollPane scrollPane;
	public JList lista;

	public JPanelBusqueda() {
		setLayout(new BorderLayout(0, 0));
		
		tfBuscar = new JTextField();
		add(tfBuscar, BorderLayout.SOUTH);
		tfBuscar.setColumns(10);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		lista = new JList();
		scrollPane.setViewportView(lista);

	}
}
