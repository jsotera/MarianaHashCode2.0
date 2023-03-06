package es.mariaanasanz.hashcode2.base;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Laberinto extends Pane {

    public final static String arriba = "ARRIBA";
    public final static String abajo = "ABAJO";
    public final static String derecha = "DERECHA";
    public final static String izquierda = "IZQUIERDA";
    public static final int anchoCelda = 30;
    public static final int altoCelda = 30;

    private int filas;
    private int columnas;
    private Celda[][] celdas;
    private Celda entrada;
    private Celda salida;
    private List<Celda> recorridoLaberinto = new ArrayList<>();

    public Laberinto(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Celda[filas][columnas];
        generarLaberinto();
        generarRecorrido();
        dibujarLaberinto();
        generarRecorridosFalsos();
    }

    private void generarLaberinto() {
        // Inicializar todas las celdas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda(i, j);
            }
        }

        // Seleccionar una celda de inicio aleatoria
        int filaInicio = (int) (Math.random() * filas);
        int columnaInicio = 0;
        entrada = celdas[filaInicio][columnaInicio];

        // Seleccionar una celda de salida aleatoria
        int filaSalida = (int) (Math.random() * filas);
        int columnaSalida = columnas - 1;
        salida = celdas[filaSalida][columnaSalida];

        // Marcar la celda de inicio como visitada y eliminarla de la lista
        entrada.setVisitada(true);
    }

    private void generarRecorridosFalsos(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = celdas[i][j];
                if(!celda.estaEnRecorrido(recorridoLaberinto)){
                    //List<Celda> celdasAdyacentes = getCeldasAdyacentes(celda);
                    eliminarCaminoEnCeldaAdyacente(celda);
                }
            }
        }
    }

    public void eliminarCaminoEnCeldaAdyacente(Celda celda){
        List<Celda> celdasAdyacentes = new ArrayList<>();
        int random = (int) (Math.random()*4);
        if(random ==0 && celda.getColumna()<celdas[celda.getFila()].length-1){
            Celda celdaAdyacente = celdas[celda.getFila()][celda.getColumna()+1];
            Rectangle pared = celda.getPared(derecha);
            if(pared!=null) {
                getChildren().remove(pared);
                celda.removePared(derecha);
            }
            pared = celdaAdyacente.getPared(izquierda);
            if(pared!=null) {
                getChildren().remove(pared);
                celdaAdyacente.removePared(izquierda);
            }
        }
        if(random ==1 && celda.getColumna()>0){
            Celda celdaAdyacente = celdas[celda.getFila()][celda.getColumna()-1];
            Rectangle pared = celda.getPared(izquierda);
            if(pared!=null) {
                getChildren().remove(pared);
                celda.removePared(izquierda);
            }
            pared = celdaAdyacente.getPared(derecha);
            if(pared!=null) {
                getChildren().remove(pared);
                celdaAdyacente.removePared(derecha);
            }
        }
        if(random ==2 && celda.getFila()<celdas.length-1){
            Celda celdaAdyacente = celdas[celda.getFila()+1][celda.getColumna()];
            Rectangle pared = celda.getPared(abajo);
            if(pared!=null) {
                getChildren().remove(pared);
                celda.removePared(abajo);
            }
            pared = celdaAdyacente.getPared(arriba);
            if(pared!=null) {
                getChildren().remove(pared);
                celdaAdyacente.removePared(arriba);
            }
        }
        if(random ==3 && celda.getFila()>0){
            Celda celdaAdyacente = celdas[celda.getFila()-1][celda.getColumna()];
            Rectangle pared = celda.getPared(arriba);
            if(pared!=null) {
                getChildren().remove(pared);
                celda.removePared(arriba);
            }
            pared = celdaAdyacente.getPared(abajo);
            if(pared!=null) {
                getChildren().remove(pared);
                celdaAdyacente.removePared(abajo);
            }
        }
    }

    private void generarRecorrido(){
        recorridoLaberinto = LaberintoGenerator.generarRecorridoAleatorio(columnas, filas, entrada.getColumna(), entrada.getFila(), salida.getColumna(), salida.getFila());
    }

    private void dibujarLaberinto() {
        // Dibujar las celdas y las paredes
        // mostrarRecorrido();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = celdas[i][j];
                //Rectangle rectangulo = new Rectangle(j * anchoCelda, i * altoCelda, anchoCelda, altoCelda);
                //rectangulo.setFill(Color.WHITE);
                //rectangulo.setStroke(Color.WHITE);
                //getChildren().add(rectangulo);

                if (celda.tieneParedArriba(recorridoLaberinto)) {
                    Rectangle pared = new Rectangle(j*anchoCelda, i*altoCelda, anchoCelda, 2);
                    pared.setFill(Color.BLACK);
                    getChildren().add(pared);
                    celda.addPared(arriba, pared);
                }

                if (celda.tieneParedIzquierda(recorridoLaberinto)) {
                    Rectangle pared = new Rectangle(j * anchoCelda, i * altoCelda, 2, altoCelda);
                    pared.setFill(Color.BLACK);
                    getChildren().add(pared);
                    celda.addPared(izquierda, pared);
                }

                if (celda.tieneParedAbajo(recorridoLaberinto)) {
                    Rectangle pared = new Rectangle(j * anchoCelda, (i+1) * altoCelda - 2, anchoCelda, 2);
                    pared.setFill(Color.BLACK);
                    getChildren().add(pared);
                    celda.addPared(abajo, pared);
                }

                if (celda.tieneParedDerecha(recorridoLaberinto)) {
                    Rectangle pared = new Rectangle(((j+1) * anchoCelda)-2, i * altoCelda, 2, altoCelda);
                    pared.setFill(Color.BLACK);
                    getChildren().add(pared);
                    celda.addPared(derecha, pared);
                }
            }
        }

        // Dibujar la entrada y la salida
        Rectangle entradaRect = new Rectangle(0+2, entrada.getFila() * altoCelda + 2, anchoCelda -4 , altoCelda -4);
        entradaRect.setFill(Color.GREEN);
        getChildren().add(entradaRect);

        Rectangle salidaRect = new Rectangle((columnas - 1) * anchoCelda+2, salida.getFila() * altoCelda +2, anchoCelda -4,altoCelda -4);
        salidaRect.setFill(Color.RED);
        getChildren().add(salidaRect);
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Celda getEntrada() {
        return entrada;
    }

    public Celda getSalida() {
        return salida;
    }

    public List<Celda> getRecorridoLaberinto() {
        return recorridoLaberinto;
    }

    public void mostrarRecorrido(){
        for(Celda celda : recorridoLaberinto){
            System.out.println(celda.getColumna()+"-"+celda.getFila());
        }
    }

    public void setRecorridoLaberinto(List<Celda> recorridoLaberinto) {
        this.recorridoLaberinto = recorridoLaberinto;
    }
}
