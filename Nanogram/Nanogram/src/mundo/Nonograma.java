package mundo;

import interfaz.PanelGrilla;

/**
 *
 * @author asus
 */
public class Nonograma {
    private PanelGrilla panelGrilla;
    private LoadNono loadNono;
    private int vidasRestantes;
    
    public Nonograma(PanelGrilla panelGrilla, LoadNono loadNono) {
        this.panelGrilla = panelGrilla;
        this.loadNono = loadNono;
    }

    public boolean verificarSolucion(int x, int y) {
        String valorUsuario = panelGrilla.getTableroUsuario()[x][y];
        String valorSolucion = loadNono.getNono()[x][y];

        return valorUsuario.equals(valorSolucion);
    }

    public boolean verificarVictoria() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!panelGrilla.getTableroUsuario()[i][j].equals(loadNono.getNono()[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    
}