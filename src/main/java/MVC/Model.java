package MVC;

import base.Arma;
import base.Personaje;
import ejemplo.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public Model(){
        conectar();
    }

    public void conectar(){
        HibernateUtil.buildSessionFactory();
    }

    public void guardar(Personaje personaje){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(personaje);
        session.getTransaction().commit();
        session.close();
    }
    public void guardar(Arma arma){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(arma);
        session.getTransaction().commit();
        session.close();
    }


    public void modificar(Personaje personaje){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.update(personaje);

        for(Arma arma : personaje.getArmas()){
            arma.setPersonaje(personaje);
            session.update(arma);
        }

        session.getTransaction().commit();
        session.close();
    }
    public void modificar(Arma arma){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(arma);
        session.getTransaction().commit();
        session.close();
    }

    public void eliminar(Personaje personaje){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(personaje);
        for(Arma arma : personaje.getArmas()){
            arma.setPersonaje(null);
            session.update(arma);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void eliminar(Arma arma){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(arma);
        session.getTransaction().commit();
        session.close();
    }


    public List<Personaje> getPersonaje(){

        Session session = HibernateUtil.getCurrentSession();
        ArrayList<Personaje> personajes =(ArrayList<Personaje>) session.createQuery("FROM Personaje").list();
        session.close();
        return personajes;
    }
    public List<Arma> getArma(){
        Session session = HibernateUtil.getCurrentSession();
        ArrayList<Arma> armas =(ArrayList<Arma>) session.createQuery("FROM Arma").list();
        session.close();
        return armas;
    }

    public
    List<Arma> getArmasLibres(){
        Session session = HibernateUtil.getCurrentSession();
        ArrayList<Arma> armas = (ArrayList<Arma>) session.createQuery("FROM Arma a WHERE a.personaje IS NULL").list();
        session.close();
        return armas;
    }
}
