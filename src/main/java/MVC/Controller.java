package MVC;


import ui.Login;

import javax.swing.*;
import java.sql.SQLException;

public class Controller  {

    Vista vista;
    Model model;

    public Controller(Vista vista, Model model){
        this.vista=vista;
        this.model=model;

        iniciarSesion();
    }

    private void iniciarSesion() {

        boolean autenticado = false;
        Login login = new Login();
        int intentos = 1;

        do {
            login.mostrarDialogo();
            String usuario = login.getUsuario();
            String contrasena = login.getContrasena();

            autenticado = model.iniciarSesion(usuario, contrasena);
            if (!autenticado) {
                if (intentos > 2) {
                    mensajeError("Limite de intentos superados procediendo a cerrar el programa");
                    System.exit(0);
                }
                login.limpiarContrasena();
                login.setMensaje("Usuario/Contraseña incorrectos");
                intentos++;
                continue;
            }

        } while (!autenticado);
    }


    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }






}
