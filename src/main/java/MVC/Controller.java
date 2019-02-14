package MVC;


public class Controller  {

    Vista vista;
    Model model;

    public Controller(Vista vista, Model model){
        this.vista=vista;
        this.model=model;
    }









    /*private void refrescarLista() {

        vista.mJuegos.removeAllElements();
        try {
            for (Juego juego : modelo.getJuegos()) {
                vista.mJuegos.addElement(juego);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            Util.mensajeError("No se pueden cargar los datos de la Base de Datos");
        }
    }*/


}
