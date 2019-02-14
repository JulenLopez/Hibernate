package MVC;

import base.Arma;
import base.Personaje;
import beans.JEstado;
import beans.JPanelArmas;
import beans.JPanelPersonaje;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

public class Vista extends JFrame implements ChangeListener {
	public DefaultListModel<Personaje> modelPersonaje;
	public DefaultListModel<Arma> modelArma;
	public JPanel panel;
	public JMenuBar menuBar;
	public JToolBar toolBar;
	public JEstado estado;
	public JTabbedPane tabbedPane;
	public JPanelArmas panelArmas;
	public JPanelPersonaje panelPersonaje;
	public JMenu mnNewMenu;
	public JMenu mnOpciones;

	public Vista() {
		setSize(548,498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		menuBar = new JMenuBar();
		panel.add(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.SOUTH);
		
		estado = new JEstado();
		getContentPane().add(estado, BorderLayout.SOUTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelPersonaje = new JPanelPersonaje();
		panelPersonaje.botonesCrud.setLocation(222, 97);
		tabbedPane.addTab("Personaje", null, panelPersonaje, null);
		
		panelArmas = new JPanelArmas();
		tabbedPane.addTab("Arma", null, panelArmas, null);

		modelPersonaje = new DefaultListModel();
		modelArma = new DefaultListModel();

        tabbedPane.addChangeListener(this);

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		repaint();
	}

    @Override
    public void stateChanged(ChangeEvent e) {
        Model model = new Model();
        List<Arma> armas = model.getArma();
        panelPersonaje.panelAnadirArma.cbArmas.inicializar(armas);
        panelPersonaje.panelAnadirArma.cbArmas.refrescar(model.getArmasLibres());
    }
}
