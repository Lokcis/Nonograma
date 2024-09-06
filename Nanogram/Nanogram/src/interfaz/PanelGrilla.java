package interfaz;

import java.awt.event.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import controlador.Controlador;

public class PanelGrilla extends JPanel {

    private Controlador controlador;
    private PanelTimer pnlTimer;
    private JLabel lblMundo; // Matriz de JLabels para cada celda del tablero
    private String[][] tableroUsuario; // Matriz que representa el estado del tablero del usuario
    private boolean juegoActivo;

    public PanelGrilla(Controlador controlador, PanelTimer pnlTimer) {
        setLayout(new GridLayout(10, 10));
        setBackground(Color.WHITE);

        lblMundo = new JLabel();
        tableroUsuario = new String[10][10]; // Inicializamos la matriz de usuario

        this.controlador = controlador;
        this.pnlTimer = pnlTimer;
        this.juegoActivo = true;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                lblMundo = new JLabel();
                lblMundo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                lblMundo.setHorizontalAlignment(JLabel.CENTER);
                lblMundo.setVerticalAlignment(JLabel.CENTER);
                lblMundo.setEnabled(true);
                lblMundo.addMouseListener(new LabelClicMouse(i, j, lblMundo, controlador, this, pnlTimer));
                tableroUsuario[i][j] = "";
                add(lblMundo);
            }
        }
    }

    public String[][] getTableroUsuario() {
        return tableroUsuario;
    }

    public void actualizarTableroUsuario(int x, int y, String valor) {
        tableroUsuario[x][y] = valor;
    }

    public void removeLabelClicMouse() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                lblMundo.removeMouseListener(lblMundo.getMouseListeners()[0]);
            }
        }
    }

    public void setJuegoActivo(boolean activo) {
        this.juegoActivo = activo;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public void reiniciarGrilla() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroUsuario[i][j] = ""; // Reinicia la matriz del usuario
                JLabel label = (JLabel) getComponent(i * 10 + j); // Obtiene el JLabel correspondiente
                label.setIcon(null); // Limpia los iconos visuales
            }
        }
        setJuegoActivo(true);
    }

    class LabelClicMouse extends MouseAdapter {

        private JLabel label;
        private Controlador ctrl;
        private int x, y;
        private ImageIcon imgBlock, imgXN;
        private PanelGrilla pnlMundo;
        private PanelTimer pnlTimer;
        private boolean timerStarted = false;

        public LabelClicMouse(int x, int y, JLabel label, Controlador ctrl, PanelGrilla pnlMundo, PanelTimer pnlTimer) {
            this.label = label;
            this.ctrl = ctrl;
            this.x = x;
            this.y = y;
            this.imgBlock = new ImageIcon(getClass().getResource("/resources/image/BTareas1.jpg"));
            this.imgXN = new ImageIcon(getClass().getResource("/resources/image/xn.jpg"));
            this.pnlMundo = pnlMundo;
            this.pnlTimer = pnlTimer;
        }

        @Override
        public void mouseClicked(MouseEvent evento) {

            if (!pnlMundo.isJuegoActivo()) {
                return; // No hacer nada si el juego no está activo
            }

            if (!timerStarted) {
                pnlTimer.startTimer();

                timerStarted = true;
            }

            if (evento.isMetaDown()) { // botón derecho
                pnlMundo.actualizarTableroUsuario(x, y, "0"); // Se asigna un '0'
                label.setIcon(imgBlock);
            } else { // botón izquierdo
                pnlMundo.actualizarTableroUsuario(x, y, "X"); // Se asigna una 'X'
                label.setIcon(imgXN);
            }

            // verificamos la solución
            ctrl.verificarSolucion(x, y);
        }
    }
}
