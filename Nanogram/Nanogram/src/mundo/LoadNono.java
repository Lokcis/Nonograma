package mundo;

import interfaz.InterfazApp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoadNono {

    private String rows[][], cols[][];
    private String nono[][];

    public LoadNono() {
        rows = new String[5][];
        cols = new String[10][];
        nono = new String[10][];
    }

    public String[][] getRows() {
        return rows;
    }

    public String[][] getCols() {
        return cols;
    }

    public String[][] getNono() {
        return nono;
    }

    public void readNono(String fileIn) {
        BufferedReader buffer = null;
        String line;

        try {
            buffer = new BufferedReader(new FileReader(fileIn));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < 5; i++) { // Lee pistas de las filas                
                line = buffer.readLine();
                rows[i] = line.split(" ");
                for (int j = 0; j < rows[i].length; j++) { // Recorre cada elemento de la fila
                    if (rows[i][j].equals("0")) { // Verifica si el valor es "0"
                        rows[i][j] = " "; // Reemplaza "0" por un espacio
                    }
                }
            }
            for (int i = 0; i < 10; i++) { // Lee pistas de las columnas
                line = buffer.readLine();
                cols[i] = line.split(" "); // Inicializa cols[i] antes del bucle
                for (int j = 0; j < cols[i].length; j++) { // Recorre cada elemento de la columna
                    if (cols[i][j].equals("0")) { // Verifica si el valor es "0"
                        cols[i][j] = " "; // Reemplaza "0" por un espacio
                    }
                }
            }
            for (int i = 0; i < 10; i++) { // Lee el nonograma
                line = buffer.readLine();
                nono[i] = line.split(" ");
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarNivel(String nivel) {
        readNono("data/nonos/" + nivel + ".in");
        String mensaje = "Estás jugando el nivel " + (nivel.equals("nono0") ? "1" : "2");
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
