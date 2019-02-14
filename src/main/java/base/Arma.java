package base;

import javax.persistence.*;

@Entity
@Table(name="Armas")
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name = "ataque")
    private int ataque;
    @Column(name = "durabilidad")
    private long durabilidad;


    public Arma(long id, String nombre, int ataque, long durabilidad) {
        this.id=id;
        this.nombre=nombre;
        this.ataque=ataque;
        this.durabilidad=durabilidad;
    }

    public Arma(){}

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getAtaque() {return ataque;}

    public void setAtaque(int ataque) {this.ataque = ataque;}

    public long getDurabilidad() {return durabilidad;}

    public void setDurabilidad(long durabilidad) {this.durabilidad = durabilidad;}

    @Override
    public String toString() {
        return  nombre;
    }

    @ManyToOne
    @JoinColumn(name = "id_personaje")
    private Personaje personaje;

    public void setPersonaje(Personaje personaje){
        this.personaje=personaje;
    }
    public Personaje getPersonaje(){return personaje;}

}
