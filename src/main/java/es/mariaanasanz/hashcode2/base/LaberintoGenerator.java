package es.mariaanasanz.hashcode2.base;

import java.util.ArrayList;
import java.util.List;

public class LaberintoGenerator {
    private static final int NO_CAMINO = 0;
    private static final int CAMINO = 1;
    private static final int VIABLE = 2;

    private static int[][] laberinto;
    private static int filas;
    private static int columnas;
    private static int posicionXFinal;
    private static int posicionYFinal;
    private static List<Celda> recorridoLaberinto = new ArrayList<>();

    public static void main(String[] args) {
        posicionXFinal = 14;
        posicionYFinal = 14;
        generarRecorridoAleatorio(15,15,0,0,posicionXFinal,posicionYFinal);
        mostrarLaberinto();
        mostrarRecorrido();
    }

    public static List<Celda> generarRecorridoAleatorio(int columnas, int filas, int posicionInicialX, int posicionInicialY, int posicionFinalX, int posicionFinalY){
        posicionXFinal = posicionFinalX;
        posicionYFinal = posicionFinalY;
        laberinto = generarLaberinto(columnas,filas,posicionInicialX,posicionInicialY,posicionFinalX,posicionFinalY);
        //mostrarLaberinto();
        //mostrarRecorrido();
        return recorridoLaberinto;
    }

    public static void mostrarLaberinto(){
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                System.out.print(laberinto[i][j]);
            }
            System.out.println();
        }
    }

    public static void mostrarRecorrido(){
        for(Celda celda : recorridoLaberinto){
            System.out.println(celda.getColumna()+"-"+celda.getFila());
        }
    }

    public static int[][] generarLaberinto(int columnas, int filas, int posicionInicialX, int posicionInicialY, int posicionFinalX, int posicionFinalY) {
        LaberintoGenerator.columnas = columnas;
        LaberintoGenerator.filas = filas;

        laberinto = new int[filas][columnas];
        generarParedes();
        generarCamino(posicionInicialX, posicionInicialY, posicionFinalX, posicionFinalY);
        return laberinto;
    }

    private static void generarParedes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                laberinto[i][j] = NO_CAMINO;
            }
        }
    }

    private static void generarCamino(int posicionX, int posicionY, int posicionFinalX, int posicionFinalY) {
        laberinto[posicionY][posicionX] = CAMINO;
        recorridoLaberinto.add(new Celda(posicionY,posicionX));
        while(laberinto[posicionFinalY][posicionFinalX] != CAMINO){
            List<Celda> celdasAdyacentes = getCeldasAdyacentes(posicionX, posicionY);
            if(celdasAdyacentes.size()==0){
                break;
            }
            Celda irACelda = celdasAdyacentes.get((int) (Math.random()*celdasAdyacentes.size()));
            boolean esCeldaValida = false;
            if(irACelda.getColumna()==posicionFinalX && irACelda.getFila()==posicionFinalY){
                esCeldaValida = true;
            }
            if(!esCeldaValida){
                esCeldaValida = esViable(irACelda);
            }
            while(!esCeldaValida){
                // podria transformar el array y eliminar el elemento que no mola para optimizarlo
                int random = (int) (Math.random()*celdasAdyacentes.size());
                irACelda = celdasAdyacentes.get(random);
                if(irACelda.getColumna()==posicionFinalX && irACelda.getFila()==posicionFinalY){
                    esCeldaValida = true;
                } else {
                    esCeldaValida = esViable(irACelda);
                }
            }
            generarCamino(irACelda.getColumna(), irACelda.getFila(), posicionFinalX, posicionFinalY);
        }
    }

    public static List<Celda> getCeldasAdyacentes(int posicionX, int posicionY){
        List<Celda> celdasDisponibles = new ArrayList<>();
        if(posicionX<laberinto[posicionY].length-1 && laberinto[posicionY][posicionX+1]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY, posicionX+1));
        }
        if(posicionX>0 && laberinto[posicionY][posicionX-1]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY, posicionX-1));
        }
        if(posicionY<laberinto.length-1 && laberinto[posicionY+1][posicionX]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY+1, posicionX));
        }
        if(posicionY>0 && laberinto[posicionY-1][posicionX]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY-1, posicionX));
        }
        return celdasDisponibles;
    }

    public static boolean esViable(Celda celda){
        boolean esViable = false;
        if(celda==null){
            return esViable;
        }
        List<Celda> celdasAdyacentes = getCeldasAdyacentesDisponibles(celda);
        while(celdasAdyacentes.size()!=0){
            Celda auxCelda = celdasAdyacentes.get(0);
            List<Celda> masCeldas = getCeldasAdyacentesDisponibles(auxCelda);
            celdasAdyacentes.addAll(masCeldas);
            celdasAdyacentes.remove(auxCelda);
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (laberinto[i][j]==VIABLE) {
                    laberinto[i][j] = NO_CAMINO;
                    if(j==posicionXFinal && i==posicionYFinal){
                        esViable = true;
                    }
                }
            }
        }

        return esViable;
    }

    public static List<Celda> getCeldasAdyacentesDisponibles(Celda celda){
        return getCeldasAdyacentesDisponibles(celda.getColumna(), celda.getFila());
    }
    
    public static List<Celda> getCeldasAdyacentesDisponibles(int posicionX, int posicionY){
        List<Celda> celdasDisponibles = new ArrayList<>();
        if(posicionX<laberinto[posicionY].length-1 && laberinto[posicionY][posicionX+1]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY, posicionX+1));
            laberinto[posicionY][posicionX+1] = VIABLE;
        }
        if(posicionX>0 && laberinto[posicionY][posicionX-1]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY, posicionX-1));
            laberinto[posicionY][posicionX-1] = VIABLE;
        }
        if(posicionY<laberinto.length-1 && laberinto[posicionY+1][posicionX]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY+1, posicionX));
            laberinto[posicionY+1][posicionX] = VIABLE;
        }
        if(posicionY>0 && laberinto[posicionY-1][posicionX]==NO_CAMINO){
            celdasDisponibles.add(new Celda(posicionY-1, posicionX));
            laberinto[posicionY-1][posicionX] = VIABLE;
        }
        return celdasDisponibles;
    }
}
