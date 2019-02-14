package ejemplo;

import MVC.Controller;
import MVC.Model;
import MVC.Vista;

public class Principal {

    static Vista vista;
    static Model model;
    static Controller controller;

    public static void main(String[] args){
        vista = new Vista();
        model = new Model();
        controller = new Controller(vista,model);




        //Lineas para guardar objetos en la BBDD

        HibernateUtil.closeSessionFactory();
    }
}
