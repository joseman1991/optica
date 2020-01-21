
package controladorMovimientos;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.DataSet;
import modelo.MovimientoDAO;
import modelo.Responsables;
import vistaMovimiento.Responsable;
import vistaMovimiento.Ventas;

/**
 *
 * @author Usuario
 */
public class ControladorHistorialVentas {
   
    private final Ventas ventas;

    public ControladorHistorialVentas(Ventas ventas) {
        this.ventas = ventas;
          frameModal = JOptionPane.getFrameForComponent(ventas);
    }
    private Responsable vista_resposable;
    private Ctrl_responsable controlador_responsable;
    private Responsables responsable;
    
    public void inicializar(){
        SwingUtilities.invokeLater(() -> {
            eventos();
            ventas.setVisible(true);
        });
    }
    
    
    private void eventos(){
         ventas.bucsResponsable.addActionListener((ActionEvent e) -> {
            InicializarVistaResponsable();
            controlador_responsable = new Ctrl_responsable(vista_resposable);
            controlador_responsable.tipo = 'C';
            controlador_responsable.IniciarVistaResponsable();
        });
    }
    
     private final Frame frameModal;
     private void InicializarVistaResponsable() {
        vista_resposable = new Responsable(frameModal, true);
        //vista_resposable = new Responsable(this.vistaEgreso, true);
        vista_resposable.setLocationRelativeTo(frameModal);
        vista_resposable.setResizable(false);
        vista_resposable.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                responsable = controlador_responsable.getResponsable();
                if (responsable != null) {
                    ventas.responsable.setText(responsable.getRazonsocial());
                    MovimientoDAO mdao=new MovimientoDAO();
                    try {
                        DataSet dt=mdao.obtenerVenta(responsable.getIdentificacion());
                        ventas.TablaDetalle.setModel(dt);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorHistorialVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("nulo");
                    ventas.responsable.setText("");
                }
            }
        });
    }
}
