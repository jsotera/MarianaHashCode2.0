package es.mariaanasanz.hashcode2.base;

import es.mariaanasanz.hashcode2.ejercicio.Ejercicio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JuegoLaberinto extends Application {

    private Pane pane;
    private Scene scene;
    private Laberinto laberinto;
    private Jugador jugador;
    private Ejercicio ejercicio;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.ejercicio = new Ejercicio();

        int columnas = 15;
        int filas = 15;

        // Generar el laberinto y el jugador
        laberinto = new Laberinto(filas, columnas);
        jugador = new Jugador(laberinto.getEntrada());

        // Crear el panel y agregar el laberinto y el jugador
        pane = new Pane();
        pane.getChildren().add(laberinto);
        pane.getChildren().add(jugador);

        // Generar los botones de reshacer y rehacer

        Image image1 = new Image("deshacer.png");
        ImageView imageView1 = new ImageView(image1);
        Button button1 = new Button("Deshacer");
        button1.setGraphic(imageView1);
        pane.getChildren().add(button1);

        Image image2 = new Image("reahacer.png");
        ImageView imageView2 = new ImageView(image2);
        Button button2 = new Button("Rehacer");
        button2.setGraphic(imageView2);
        pane.getChildren().add(button2);

        button1.setLayoutX(columnas*Laberinto.anchoCelda+20);
        button1.setLayoutY(20);
        button2.setLayoutX(columnas*Laberinto.anchoCelda+20);
        button2.setLayoutY(80);

        button1.setOnAction(e -> {
            KeyCode movimiento = ejercicio.deshacerMovimiento();
            if(movimiento!=null) {
                jugador.mover(movimiento, laberinto, laberinto.getRecorridoLaberinto(), ejercicio);
            }
        });

        button2.setOnAction(e -> {
            KeyCode movimiento = ejercicio.rehacerMovimiento();
            if(movimiento!=null) {
                jugador.mover(movimiento, laberinto, laberinto.getRecorridoLaberinto(), ejercicio);
            }
        });

        // Crear la escena y agregar el panel
        scene = new Scene(pane);

        // Manejar los eventos de teclado
        scene.setOnKeyReleased(event -> {
            jugador.mover(event.getCode(), laberinto, laberinto.getRecorridoLaberinto(), ejercicio);
        });

        // Configurar el escenario
        stage.setScene(scene);
        stage.setTitle("Laberinto");
        stage.show();
    }
}