package es.mariaanasanz.hashcode2.ejercicio;

import javafx.scene.input.KeyCode;

public class Ejercicio {

    public Ejercicio(){
        // TODO: debemos inicializar todos los elementos que necesitemos para resolver este ejercicio, ¿que vamos a necesitar?
    }

    public void anadirMovimiento(KeyCode movimiento, Posicion posicion){
        // TODO: debemos almacenar de manera ordenada los movimientos del laberinto que vamos recorriendo, ¿como podemos hacerlo?
        // TODO: Adicionalmente, debemos tener en cuenta en que posiciones hemos estado,
        //  ya que no se pueden volver a recorrer las casillas que se han recorrido (al menos hasta que se deshaga un movimiento)
    }

    public KeyCode deshacerMovimiento(){
        // TODO: debemos deshacer un movimiento en el laberinto y DEVOLVER hacia donde tenemos que deshacer el movimiento
        return null;
    }

    public KeyCode rehacerMovimiento() {
        // TODO: debemos rehacer un movimiento en el laberinto que previamente hemos deshecho y DEVOLVER hacia donde tenemos que rehacer el movimiento
        return null;
    }

    public boolean puedoIrHacia(Posicion posicion){
        // TODO: Debemos validar si la posicion hacia la que queremos ir nunca la hemos recorrido
        return true;
    }
}