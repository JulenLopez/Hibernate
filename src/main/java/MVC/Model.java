package MVC;

import base.Arma;
import base.Personaje;
import base.User;
import ejemplo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Model {

    public Model(){
        conectar();
    }

    public void conectar(){
        HibernateUtil.buildSessionFactory();
    }

    public boolean iniciarSesion(String nombre, String contrasena){

        Session session = HibernateUtil.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.nombre = :nombre AND u.contrasena = :contrasena");
        query.setParameter("nombre",nombre);
        query.setParameter("contrasena",contrasena);
        User user = query.uniqueResult();
        if(user != null) {
            session.close();
            return true;
        }
        session.close();
        return false;
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

    public Personaje eliminar(Personaje personaje){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(personaje);
        for(Arma arma : personaje.getArmas()){
            arma.setPersonaje(null);
            session.update(arma);
        }
        session.getTransaction().commit();
        session.close();

        return personaje;
    }

    public void eliminar(Arma arma){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(arma);
        session.getTransaction().commit();
        session.close();
    }

    public void borrarTodo(){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table Armas").executeUpdate();
        session.createSQLQuery("truncate table personajes").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deshacerBorrado(Personaje personaje){
        guardar(personaje);
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