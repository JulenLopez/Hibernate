package base;

import javax.persistence.*;

@Entity
@Table(name="Usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="nombre")
    String nombre;
    @Column(name="contrasena")
    String contrasena;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;}
}
