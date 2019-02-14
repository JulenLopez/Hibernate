package beans;

import MVC.Model;
import base.Arma;
import base.Personaje;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelPersonaje extends JPanel implements ActionListener, ListSelectionListener, DocumentListener {
    public JBotonesCrud botonesCrud;
    public JLabel lblNombre;
    public JLabel lblDescripcion;
    public JLabel lblVida;
    public JLabel lblAtaque;
    public JTextField tfNombre;
    public JTextField tfDescripcion;
    public JTextField tfVida;
    public JTextField tfAtaque;
    public JButton btBorrarTodo;
    public DefaultListModel<Personaje> mPersonaje;
    public JPanelBusqueda panelBusqueda;
    public JPanelAnadirArma panelAnadirArma;
    public JLabel lblArma;
    public Personaje personajeSeleccionado;
    public boolean modificar;

    public JPanelPersonaje() {
        setLayout(null);

        botonesCrud = new JBotonesCrud();
        botonesCrud.btModificar.setLocation(10, 145);
        botonesCrud.btCancelar.setLocation(10, 44);
        botonesCrud.btGuardar.setLocation(10, 111);
        botonesCrud.setBounds(222, 87, 104, 231);
        add(botonesCrud);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(16, 24, 51, 14);
        add(lblNombre);

        lblDescripcion = new JLabel("Descripcion");
        lblDescripcion.setBounds(16, 92, 37, 14);
        add(lblDescripcion);

        lblVida = new JLabel("Vida");
        lblVida.setBounds(16, 60, 43, 14);
        add(lblVida);

        lblAtaque = new JLabel("Ataque");
        lblAtaque.setBounds(16, 126, 37, 14);
        add(lblAtaque);

        tfNombre = new JTextField();
        tfNombre.setBounds(101, 21, 104, 20);
        add(tfNombre);
        tfNombre.setColumns(10);

        tfDescripcion = new JTextField();
        tfDescripcion.setBounds(101, 92, 104, 20);
        add(tfDescripcion);
        tfDescripcion.setColumns(10);

        tfVida = new JTextField();
        tfVida.setBounds(101, 57, 104, 20);
        add(tfVida);

        tfAtaque = new JTextField();
        tfAtaque.setBounds(101, 123, 104, 20);
        add(tfAtaque);
        tfAtaque.setColumns(10);

        btBorrarTodo = new JButton("Borrar todo");
        btBorrarTodo.setBounds(383, 353, 121, 23);
        add(btBorrarTodo);

        panelBusqueda = new JPanelBusqueda();
        panelBusqueda.setBounds(353, 23, 151, 190);
        add(panelBusqueda);
        mPersonaje = new DefaultListModel<>();
        panelBusqueda.lista.setModel(mPersonaje);

        panelAnadirArma = new JPanelAnadirArma();
        panelAnadirArma.setBounds(16, 186, 198, 190);
        add(panelAnadirArma);

        lblArma = new JLabel("Armas");
        lblArma.setBounds(94, 161, 46, 14);
        add(lblArma);

        panelBusqueda.lista.addListSelectionListener(this);
        refrescarLista();
        botonesCrud.addListeners(this);
        modificar=false;

        panelBusqueda.tfBuscar.getDocument().addDocumentListener(this);
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

                if (!tfVida.getText().matches("[0-9]*")) {
                    JOptionPane.showMessageDialog(null,
                            "La vida tiene que ser un número",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tfVida.selectAll();
                    tfVida.requestFocus();
                    return;
                }

                if(tfDescripcion.equals(""))
                    tfDescripcion.setText("Vacio");

                if (!tfAtaque.getText().matches("[0-9]*")) {
                    JOptionPane.showMessageDialog(null,
                            "El rating tiene que ser un número",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tfAtaque.selectAll();
                    tfAtaque.requestFocus();
                    return;
                }

                if(!panelAnadirArma.modelLista.isEmpty()){
                    for(int i=0; i<panelAnadirArma.modelLista.getSize();i++){
                        personajeSeleccionado.setArmas((Arma) panelAnadirArma.modelLista.getElementAt(i));
                    }
                }

                if(!modificar)
                    model.guardar(recogerDatos());
                else
                    model.modificar(personajeSeleccionado);
                modificar=false;
                refrescarLista();
                limpiar();
                break;
            case CANCELAR:
                limpiar();
                break;
            case MODIFICAR:
                modificar=true;
                break;
            case BORRAR:
                model.eliminar(personajeSeleccionado);
                refrescarLista();
                limpiar();
                break;
        }
    }

    private void limpiar(){
        tfAtaque.setText("");
        tfDescripcion.setText("");
        tfVida.setText("");
        tfNombre.setText("");
    }

    public Personaje recogerDatos(){
        Personaje personaje = new Personaje();
        personaje.setNombre(tfNombre.getText());
        personaje.setAtaque(Integer.parseInt(tfAtaque.getText()));
        personaje.setVida(Integer.parseInt(tfVida.getText()));
        personaje.setDescripcion(tfDescripcion.getText());
        if(modificar){
            personaje.setId(personajeSeleccionado.getId());
            if(personajeSeleccionado.getArmas()!=null)
                personaje.setArmas(personajeSeleccionado.getArmas());
        }
        return personaje;
    }


    private void rellenarDatos(Personaje personaje){
        tfNombre.setText(personaje.getNombre());
        tfVida.setText(String.valueOf(personaje.getVida()));
        tfDescripcion.setText(personaje.getDescripcion());
        tfAtaque.setText(String.valueOf(personaje.getAtaque()));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Model model = new Model();
        if(panelBusqueda.lista.getSelectedIndex()==-1)
            return;
        personajeSeleccionado = (Personaje) panelBusqueda.lista.getSelectedValue();
        rellenarDatos(personajeSeleccionado);
        panelAnadirArma.modelLista.removeAllElements();
        panelAnadirArma.cbArmas.refrescar(model.getArmasLibres());
    }

    private void refrescarLista() {
        Model model = new Model();
        mPersonaje.removeAllElements();

        for (Personaje personaje : model.getPersonaje()) {
            mPersonaje.addElement(personaje);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        refrescarLista();
        for(int i =0; i<mPersonaje.getSize();i++){
            if (!mPersonaje.elementAt(i).getNombre().toLowerCase().contains(panelBusqueda.tfBuscar.getText().toLowerCase())) {
                mPersonaje.removeElementAt(i);
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        refrescarLista();
        for(int i =0; i<mPersonaje.getSize();i++){
            if (!mPersonaje.elementAt(i).getNombre().toLowerCase().contains(panelBusqueda.tfBuscar.getText().toLowerCase())) {
                mPersonaje.removeElementAt(i);
            }
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

}
