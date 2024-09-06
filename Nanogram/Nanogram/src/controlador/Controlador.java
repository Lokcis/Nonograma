package controlador;

import interfaz.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import mundo.LoadNono;
import mundo.Nonograma;

public class Controlador {

    private PanelGrilla panelGrilla;
    private PanelVidas panelVidas;
    private PanelTimer pnlTimer;
    private Nonograma juegoNonograma;
    private LoadNono loadNono;
    private int vidasRestantes;

    public Controlador() {

    }

    public void conectar(PanelGrilla panelGrilla, PanelVidas panelVidas, LoadNono loadNono, PanelTimer pnlTimer, Nonograma juegoNonograma) {
        this.panelGrilla = panelGrilla;
        this.panelVidas = panelVidas;
        this.pnlTimer = pnlTimer;
        this.loadNono = loadNono;
        this.juegoNonograma = juegoNonograma;
        this.vidasRestantes = 3;
    }

    public void verificarSolucion(int x, int y) {
        if (!juegoNonograma.verificarSolucion(x, y)) {
            restarVida();
        } else {
            if (juegoNonograma.verificarVictoria()) {
                mostrarMensajeVictoria();
            }
        }
    }

    private void restarVida() {
        vidasRestantes--;
        panelVidas.actualizarVidas(vidasRestantes);

        if (vidasRestantes <= 0) {
            pnlTimer.stopTimer();
            mostrarMensajePerdida();
            panelGrilla.setJuegoActivo(false);
        }
    }

    private void mostrarMensajeVictoria() {
        pnlTimer.stopTimer();
        String tiempo = pnlTimer.getTiempoTranscurrido();
        JOptionPane.showMessageDialog(null, "¡Felicidades! ¡Has ganado en " + tiempo + "!");
        panelGrilla.setJuegoActivo(false);
    }

    private void mostrarMensajePerdida() {
        pnlTimer.stopTimer();
        String tiempo = pnlTimer.getTiempoTranscurrido();
        JOptionPane.showMessageDialog(null, "¡Has perdido el juego! Tiempo: " + tiempo);
        panelGrilla.setJuegoActivo(false);
    }

    public void reiniciarJuego() {
        panelGrilla.reiniciarGrilla(); // Reiniciar la grilla
        panelVidas.reiniciarVidas(); // Reiniciar las vidas
        pnlTimer.reiniciarTimer(); // Reiniciar el temporizado
        vidasRestantes = 3; // Restablecer vidas restantes
        panelGrilla.setJuegoActivo(true); // Asegúrate de habilitar el juego
    }

    public void nivel(String nivel) {
        loadNono.cargarNivel(nivel);
        reiniciarJuego();

        // Actualizar las pistas
        InterfazApp app = (InterfazApp) SwingUtilities.getWindowAncestor(panelGrilla);
        app.actualizarPistas(loadNono);

    }

}
