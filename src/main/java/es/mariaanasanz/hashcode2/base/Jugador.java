package es.mariaanasanz.hashcode2.base;

import es.mariaanasanz.hashcode2.ejercicio.Ejercicio;
import es.mariaanasanz.hashcode2.ejercicio.Posicion;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;

public class Jugador extends Pane {
    private int anchoCelda = 30;
    private int altoCelda = 30;
    private Rectangle rectangulo;
    private Celda celdaActual;
    private Celda celdaInicial;
    private int posX;
    private int posY;

    public Jugador(Celda celdaActual) {
        this.celdaActual = celdaActual;
        this.celdaInicial = celdaActual;
        this.posX = celdaActual.getColumna()*anchoCelda +1;
        this.posY = celdaActual.getFila()*altoCelda +1;
        this.rectangulo = new Rectangle(celdaActual.getColumna() * anchoCelda + 2, celdaActual.getFila() * altoCelda + 2,
                anchoCelda-4, altoCelda-4);
        this.rectangulo.setFill(Color.BLUE);
        getChildren().add(this.rectangulo);
    }

    public void mover(KeyCode direccion, Laberinto laberinto, List<Celda> recorridoLaberinto, Ejercicio ejercicio) {
        Celda vecino = null;
        switch(direccion) {
            case W:
                if (!celdaActual.tienePared(Laberinto.arriba) && celdaActual.getFila() > 0) {
                    vecino = laberinto.getCeldas()[celdaActual.getFila() - 1][celdaActual.getColumna()];
                }
                break;
            case S:
                if (!celdaActual.tienePared(Laberinto.abajo) && celdaActual.getFila() < laberinto.getFilas() - 1) {
                    vecino = laberinto.getCeldas()[celdaActual.getFila() + 1][celdaActual.getColumna()];
                }
                break;
            case A:
                if (!celdaActual.tienePared(Laberinto.izquierda) && celdaActual.getColumna() > 0) {
                    vecino = laberinto.getCeldas()[celdaActual.getFila()][celdaActual.getColumna() - 1];
                }
                break;
            case D:
                if (!celdaActual.tienePared(Laberinto.derecha) && celdaActual.getColumna() < laberinto.getColumnas() - 1) {
                    vecino = laberinto.getCeldas()[celdaActual.getFila()][celdaActual.getColumna() + 1];
                }
                break;
        }

        if (vecino != null) {
            Posicion posicion = new Posicion(vecino.getColumna(), vecino.getFila());
            ejercicio.anadirMovimiento(direccion, posicion);
            if(ejercicio.puedoIrHacia(posicion)) {
                posX = (vecino.getColumna() - celdaInicial.getColumna()) * anchoCelda;
                posY = (vecino.getFila() - celdaInicial.getFila()) * altoCelda;
                celdaActual = vecino;
                TranslateTransition tt = new TranslateTransition(Duration.millis(100), rectangulo);
                tt.setToX(posX);
                tt.setToY(posY);
                tt.play();
            }
        }

        if (celdaActual == laberinto.getSalida()) {
            Label mensaje = new Label("Enhorabuena!");
            mensaje.setFont(new Font("Arial", 30));
            mensaje.setTextFill(Color.GREEN);
            mensaje.setAlignment(Pos.CENTER);
            mensaje.setLayoutX(anchoCelda);
            mensaje.setLayoutY(altoCelda * laberinto.getFilas() / 2);

            getChildren().remove(0, getChildren().size());
            getChildren().add(mensaje);
        }
    }
}
