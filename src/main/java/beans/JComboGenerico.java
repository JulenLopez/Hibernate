package beans;

import javax.swing.*;
import java.util.List;

public class JComboGenerico<T> extends JComboBox<T>{
	
	//Atributos
	private List<T> datos;
	
	//Constructor
	public JComboGenerico() {
		super(); //heredo todo
	}
	
	//Metodos
	public void inicializar(List<T> datos) {
		this.datos = datos;
		listar();
	}
	
	public void refrescar(List<T> armasLibres) {
		limpiar();
		this.datos=armasLibres;
		listar();
	}
	
	public void listar() {
		if(datos == null)
			return;
		for(T dato : datos)
			addItem(dato);
	}
	
	public void limpiar() {
		removeAllItems();
	}
	
	public T getArmaSeleccionada() {
		T dato = (T) getSelectedItem();
		return dato;
	}
}
