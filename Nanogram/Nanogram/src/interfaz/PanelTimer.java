package interfaz;

import java.awt.Color;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelTimer extends JPanel implements Runnable {

    // Atributos
    private JLabel lblTimer;
    private Thread threadTimer;
    private Timer timer;
    private boolean running;
    private int counter = 0;

    public PanelTimer() {
        setLayout(null);
        setBackground(Color.WHITE);
        lblTimer = new JLabel("Tiempo: 00:00:00");
        lblTimer.setFont(new Font("Arial", Font.BOLD, 14));
        lblTimer.setBounds(0, 0, 200, 30);
        add(lblTimer);
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                int hours = counter / 3600;
                int minutes = (counter % 3600) / 60;
                int seconds = counter % 60;
                lblTimer.setText(String.format("Tiempo: %02d:%02d:%02d", hours, minutes, seconds));
            }
        });
        
    }

    public void startTimer() {
        if (!timer.isRunning()) {  // Inicia el temporizador si no está ya corriendo
            timer.start();
        }
    }
    public void stopTimer() {
        timer.stop(); 
    }

    public void reiniciarTimer() {
        stopTimer(); // Detenemos el temporizador
        counter = 0; // Reiniciamos el contador
        lblTimer.setText("Tiempo: 00:00:00"); // Restablecemos la visualización
        startTimer();
    }

    public String getTiempoTranscurrido() {
        int hours = counter / 3600;
        int minutes = (counter % 3600) / 60;
        int seconds = counter % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000); // Pausa de 1 segundo
                counter++;
                int hours = counter / 3600;
                int minutes = (counter % 3600) / 60;
                int seconds = counter % 60;
                lblTimer.setText(String.format("Tiempo: %02d:%02d:%02d", hours, minutes, seconds));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
