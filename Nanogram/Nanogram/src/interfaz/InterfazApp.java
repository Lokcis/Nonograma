package interfaz;

import controlador.Controlador;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import mundo.LoadNono;
import mundo.Nonograma;

/**
 *
 * @author gonza
 */
public class InterfazApp extends JFrame {

    // Relaciones
    private PanelGrilla pnlGrilla;
    private PanelPistasHorizontal pnlPistasHorizontal;
    private PanelPistasVertical pnlPistasVertical;
    private Nonograma juegoNonograma;
    private PanelVidas pnlVidas;
    private PanelTimer pnlTimer;
    private PanelBotones pnlBotones;
    private Nonograma nono;
    private Controlador ctrl;
    private ImageIcon icon;
    private Image image;

    // Atributos   
    private JMenuBar mbrOpciones; // crear JMenuItem para las opciones
    private LoadNono loadNono;

    //constructor
    public InterfazApp(Controlador ctrl) {

        //Propiedades de la interfaz o frame
        setTitle("Nonograma");
        getContentPane().setLayout(null);  //Tipo de layout, en este caso null
        setSize(575, 750);  //Tamaño del Frame
        setLocationRelativeTo(null);  //Centra el Frame
        setResizable(false);  //No permite cambiar el tamaño del frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa cuando se le da a la X
        getContentPane().setBackground(Color.WHITE);

        //Imagen a la barra de tareas
        icon = new ImageIcon(getClass().getResource("/resources/image/BTareas2.jpg"));
        image = icon.getImage();
        setIconImage(image);

        //Enlaza el controlador
        this.ctrl = ctrl;

        // Instancia controles de menu
        mbrOpciones = new JMenuBar();

        // Instancia los paneles   
        pnlTimer = new PanelTimer();
        pnlTimer.setBounds(410, 50, 126, 30);
        add(pnlTimer);

        pnlGrilla = new PanelGrilla(ctrl, pnlTimer);
        pnlGrilla.setBounds(105, 200, 440, 440);
        add(pnlGrilla);

        loadNono = new LoadNono(); //Instancia de LoadNono
        loadNono.readNono("data/nonos/nono0.in");

        pnlPistasHorizontal = new PanelPistasHorizontal(loadNono);
        pnlPistasHorizontal.setBounds(106, 117, 439, 80);
        add(pnlPistasHorizontal);

        pnlPistasVertical = new PanelPistasVertical(loadNono);
        pnlPistasVertical.setBounds(15, 202, 88, 439);
        add(pnlPistasVertical);

        pnlVidas = new PanelVidas();
        pnlVidas.setBounds(40, 30, 224, 64);
        add(pnlVidas);
        nono = new Nonograma(pnlGrilla, loadNono);

        pnlBotones = new PanelBotones(ctrl, pnlGrilla, pnlVidas, pnlTimer);
        pnlBotones.setBounds(20, 653, 500, 64);
        add(pnlBotones);

        ctrl.conectar(pnlGrilla, pnlVidas, loadNono, pnlTimer, nono);

    }

    public void actualizarPistas(LoadNono loadNono) {
        pnlPistasHorizontal.actualizarPistas(loadNono);
        pnlPistasVertical.actualizarPistas(loadNono);
    }

    public static void main(String args[]) {
        InterfazApp frmMain = new InterfazApp(new Controlador());
        frmMain.setVisible(true);

    }

    //ME GUSTA EL CHIMBO
        
}
