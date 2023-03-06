package es.mariaanasanz.hashcode2.base;

import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.List;

public class Celda {
    private int fila;
    private int columna;
    private boolean visitada;
    private boolean tiene4parecedes;
    private HashMap<String, Rectangle> paredes;

    public Celda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.visitada = false;
        this.tiene4parecedes = true;
        this.paredes = new HashMap<>();
    }

    public boolean isVisitada() {
        return this.visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public boolean tieneParedArriba(List<Celda> recorridoLaberinto) {
        boolean tienePared = true;
        int posicionRecorrido = -1;
        for(Celda celda : recorridoLaberinto){
            if(celda.getFila()==this.getFila() && celda.getColumna()==this.getColumna()){
                posicionRecorrido = recorridoLaberinto.indexOf(celda);
                break;
            }
        }
        if(posicionRecorrido==0){
            // si es la primera celda del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        } else if (posicionRecorrido>0 && posicionRecorrido<recorridoLaberinto.size()-1){
            // si es una celda intermedia del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==1 && orientacion.getColumna()==0){
                tienePared = false;
            }
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        } else if (posicionRecorrido>=0 && posicionRecorrido==recorridoLaberinto.size()-1){
            // si es la ultima celda del recorrido
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            Celda orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        }
        return tienePared;
    }

    public boolean tieneParedIzquierda(List<Celda> recorridoLaberinto) {
        boolean tienePared = true;
        int posicionRecorrido = -1;
        for(Celda celda : recorridoLaberinto){
            if(celda.getFila()==this.getFila() && celda.getColumna()==this.getColumna()){
                posicionRecorrido = recorridoLaberinto.indexOf(celda);
                break;
            }
        }
        if(posicionRecorrido==0){
            // si es la primera celda del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==1){
                tienePared = false;
            }
        } else if (posicionRecorrido>0 && posicionRecorrido<recorridoLaberinto.size()-1){
            // si es una celda intermedia del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==1){
                tienePared = false;
            }
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==1){
                tienePared = false;
            }
        } else if (posicionRecorrido>=0 && posicionRecorrido==recorridoLaberinto.size()-1){
            // si es la ultima celda del recorrido
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            Celda orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==1){
                tienePared = false;
            }
        }
        return tienePared;
    }

    public boolean tieneParedAbajo(List<Celda> recorridoLaberinto) {
        boolean tienePared = true;
        int posicionRecorrido = -1;
        for(Celda celda : recorridoLaberinto){
            if(celda.getFila()==this.getFila() && celda.getColumna()==this.getColumna()){
                posicionRecorrido = recorridoLaberinto.indexOf(celda);
                break;
            }
        }
        if(posicionRecorrido==0){
            // si es la primera celda del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==-1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        } else if (posicionRecorrido>0 && posicionRecorrido<recorridoLaberinto.size()-1){
            // si es una celda intermedia del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==-1 && orientacion.getColumna()==0){
                tienePared = false;
            }
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==-1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        } else if (posicionRecorrido>=0 && posicionRecorrido==recorridoLaberinto.size()-1){
            // si es la ultima celda del recorrido
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            Celda orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==-1 && orientacion.getColumna()==0){
                tienePared = false;
            }
        }
        return tienePared;
    }

    public boolean tieneParedDerecha(List<Celda> recorridoLaberinto) {
        boolean tienePared = true;
        int posicionRecorrido = -1;
        for(Celda celda : recorridoLaberinto){
            if(celda.getFila()==this.getFila() && celda.getColumna()==this.getColumna()){
                posicionRecorrido = recorridoLaberinto.indexOf(celda);
                break;
            }
        }
        if(posicionRecorrido==0){
            // si es la primera celda del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==-1){
                tienePared = false;
            }
        } else if (posicionRecorrido>0 && posicionRecorrido<recorridoLaberinto.size()-1){
            // si es una celda intermedia del recorrido
            Celda siguienteCelda = recorridoLaberinto.get(posicionRecorrido+1);
            Celda orientacion = new Celda(this.getFila()-siguienteCelda.getFila(), this.getColumna()-siguienteCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==-1){
                tienePared = false;
            }
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==-1){
                tienePared = false;
            }
        } else if (posicionRecorrido>=0 && posicionRecorrido==recorridoLaberinto.size()-1){
            // si es la ultima celda del recorrido
            Celda anteriorCelda = recorridoLaberinto.get(posicionRecorrido-1);
            Celda orientacion = new Celda(this.getFila()-anteriorCelda.getFila(), this.getColumna()-anteriorCelda.getColumna());
            //el if es el que varia para cada orientacion
            if(orientacion.getFila()==0 && orientacion.getColumna()==-1){
                tienePared = false;
            }
        }
        return tienePared;
    }

    public boolean estaEnRecorrido(List<Celda> recorrido){
        boolean estaEnRecorrido = false;
        for(Celda celda : recorrido){
            if(celda.getColumna()==this.getColumna() && celda.getFila()==this.getFila()){
                estaEnRecorrido = true;
                break;
            }
        }
        return estaEnRecorrido;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isTiene4parecedes() {
        return tiene4parecedes;
    }

    public void setTiene4parecedes(boolean tiene4parecedes) {
        this.tiene4parecedes = tiene4parecedes;
    }

    public HashMap<String, Rectangle> getParedes() {
        return paredes;
    }

    public void setParedes(HashMap<String, Rectangle> paredes) {
        this.paredes = paredes;
    }

    public void addPared(String posicion, Rectangle pared){
        paredes.put(posicion, pared);
    }

    public boolean tienePared(String posicion){
        return paredes.get(posicion)!=null;
    }

    public Rectangle getPared(String posicion){
        return paredes.get(posicion);
    }

    public void removePared(String posicion){
        paredes.remove(posicion);
    }
}
