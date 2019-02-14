package beans;

import javax.swing.*;
import java.awt.*;

public class JEstado extends JPanel {
	public JLabel lbEstado;

	/**
	 * Create the panel.
	 */
	public JEstado() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lbEstado = new JLabel("");
		add(lbEstado);

	}

	public void setMensajeConfirmacion(String mensaje) {
		lbEstado.setForeground(Color.BLACK);
		lbEstado.setText(mensaje);
	}
	
	public void setMensajeError(String mensaje) {
		lbEstado.setForeground(Color.RED);
		lbEstado.setText(mensaje);
	}
	
}
