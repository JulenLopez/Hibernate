package beans;

import MVC.Model;
import base.Arma;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelArmas extends JPanel implements ActionListener, ListSelectionListener {
	public Arma armaSeleccionada;
	public JBotonesCrud botonesCrud;
	public JLabel lblNombre;
	public JLabel lblAtaque;
	public JLabel lblDurabilidad;
	public JTextField tfNombre;
	public JTextField tfAtaque;
	public JTextField tfDurabilidad;
	public JScrollPane scrollPane;
	public JList<Arma> listArmas;
	public DefaultListModel<Arma> mArma;
	

	public JPanelArmas() {
		setLayout(null);
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.setBounds(10, 168, 210, 121);
		add(botonesCrud);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(21, 37, 58, 14);
		add(lblNombre);
		
		lblAtaque = new JLabel("Ataque");
		lblAtaque.setBounds(21, 77, 58, 14);
		add(lblAtaque);
		
		lblDurabilidad = new JLabel("Durabilidad");
		lblDurabilidad.setBounds(21, 113, 58, 14);
		add(lblDurabilidad);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(89, 34, 96, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfAtaque = new JTextField();
		tfAtaque.setBounds(89, 74, 96, 20);
		add(tfAtaque);
		tfAtaque.setColumns(10);
		
		tfDurabilidad = new JTextField();
		tfDurabilidad.setBounds(89, 110, 96, 20);
		add(tfDurabilidad);
		tfDurabilidad.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(269, 31, 161, 192);
		add(scrollPane);


		listArmas = new JList();
		scrollPane.setViewportView(listArmas);

        mArma = new DefaultListModel<>();
        listArmas.setModel(mArma);

        refrescarLista();
		botonesCrud.addListeners(this);

		listArmas.addListSelectionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Model model = new Model();
		switch (JBotonesCrud.Accion.valueOf(e.getActionCommand())){
			case NUEVO:
				limpiar();
				tfNombre.grabFocus();
				break;
			case GUARDAR:

                if (tfNombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "El campo nombre es obligatorio",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!tfAtaque.getText().matches("[0-9]*") || tfAtaque.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "El ataque tiene que ser un número",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tfAtaque.selectAll();
                    tfAtaque.requestFocus();
                    return;
                }

                if (!tfDurabilidad.getText().matches("[0-9]*") || tfDurabilidad.getText().equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "La durabilidad tiene que ser un número",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tfDurabilidad.selectAll();
                    tfDurabilidad.requestFocus();
                    return;
                }

				model.guardar(recogerDatos());
				limpiar();
				refrescarLista();
				break;
			case CANCELAR:
				limpiar();
				break;
			case MODIFICAR:
				model.modificar(armaSeleccionada);
				limpiar();
				refrescarLista();
				break;
			case BORRAR:
				model.eliminar(armaSeleccionada);
				limpiar();
				refrescarLista();
				break;
		}
	}

	private Arma recogerDatos() {
		Arma arma = new Arma();
		arma.setAtaque(Integer.parseInt(tfAtaque.getText()));
		arma.setDurabilidad(Long.parseLong(tfDurabilidad.getText()));
		arma.setNombre(tfNombre.getText());
		return arma;
	}

	private void limpiar(){
        tfNombre.setText("");
		tfAtaque.setText("");
		tfDurabilidad.setText("");
	}

	public void refrescarLista() {
        Model model = new Model();
	    mArma.removeAllElements();

        for (Arma arma : model.getArma()) {
            mArma.addElement(arma);
        }
    }



	private void rellenarDatos(Arma arma){
		tfNombre.setText(arma.getNombre());
		tfDurabilidad.setText(String.valueOf(arma.getDurabilidad()));
		tfAtaque.setText(String.valueOf(arma.getAtaque()));
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(listArmas.getSelectedIndex()==-1)
			return;
		armaSeleccionada = listArmas.getSelectedValue();
		rellenarDatos(armaSeleccionada);
	}
}
