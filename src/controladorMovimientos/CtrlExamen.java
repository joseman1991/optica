/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorMovimientos;

import jTextFieldAutoCOmplete.Element;
import jTextFieldAutoCOmplete.Predictor;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.AnchoColumna;
import modelo.ArticuloDAO;
import modelo.Articulos;
import modelo.ConexionPSQL;
import modelo.DataSet;
import modelo.Detallemovimiento;
import modelo.Empresa;
import modelo.ExamenDAO;
import modelo.ExamenVisual;

import modelo.ModelCellEditable;
import modelo.MovimientoDAO;
import modelo.Responsables;
import modelo.Tipomovimiento;
import modelo.TipomovimientoDAO;
import modelo.Usuarios;
import net.sf.jasperreports.view.JasperViewer;
import vistaMovimiento.Articulo;
import vistaMovimiento.Examen;
import vistaMovimiento.Responsable;

public class CtrlExamen {

    private final List<Detallemovimiento> listaDetelle;

    private final ModelCellEditable dt;
    private double totalMovimiento;

    private Empresa empresa;

    private Tipomovimiento tipoMov;

    private Usuarios usuario;
    private final Examen vistaEgreso;

    private final ExamenDAO citasDAO;

    private Responsable vista_resposable;
    private Ctrl_responsable controlador_responsable;
    private Responsables responsable;

    private Articulo vista_producto;
    private ControladorArticulo ctrl_producto;
    private Articulos producto;
    private Articulos producto2;
    private Articulos producto3;
    public List<Tipomovimiento> listaTipoMov;

    private Date fechaMovimiento;

    private int fila;

    private final MovimientoDAO moviDAO;
    private final TipomovimientoDAO tipoMoviDAO;

    private final Frame frameModal;

    private List<Articulos> listaArticulos;

    private Predictor predictor;
    private Element elemento;
    private MouseAdapter eventoClick;

    private double IVA;
    private double iva;

    private final ArticuloDAO articuloDAO;

    private JasperViewer jv;

    public CtrlExamen(Examen vistaEgreso) {
        this.citasDAO = new ExamenDAO();
        this.vistaEgreso = vistaEgreso;
        this.vistaEgreso.setResizable(false);


        moviDAO = new MovimientoDAO();
        tipoMoviDAO = new TipomovimientoDAO();

        listaDetelle = new ArrayList<>();
        frameModal = JOptionPane.getFrameForComponent(vistaEgreso);

        dt = new ModelCellEditable();
        dt.isCellEditable(0, 0);
        this.vistaEgreso.TablaDetalle.setModel(dt);
        articuloDAO = new ArticuloDAO();

        eventos();
        this.vistaEgreso.TablaDetalle.setRowHeight(25);
        this.vistaEgreso.TablaDetalle.setGridColor(Color.black);
        this.vistaEgreso.TablaDetalle.setShowGrid(true);

        int medida[] = {10, 10, 300, 40, 40, 40, 40};
        AnchoColumna ac = new AnchoColumna(this.vistaEgreso.TablaDetalle, medida);
        conexionPSQL = new ConexionPSQL();
    }

    public void IniciarEgreso() {
        vistaEgreso.datePicker.getJFormattedTextField().setText(formatearFecha(new java.util.Date()));
        try {

            modelo.Citas ci = new modelo.Citas();
            ci.setIdentificacion("");
            DataSet dat = citasDAO.obtenerTabla(ci);
            vistaEgreso.TablaDetalle.setModel(dat);

            tipoMoviDAO.obtnerListaDetalleMov("E");
            listaTipoMov = tipoMoviDAO.listaTipoMov;

            solonumeros(vistaEgreso.edad);
            solonumeros(vistaEgreso.oicyl);
            solonumeros(vistaEgreso.odcyl);
            solonumeros(vistaEgreso.oieje);
            solonumeros(vistaEgreso.odeje);
            solonumeros(vistaEgreso.telefono);
            solonumeros(vistaEgreso.addalt);
            solonumeros(vistaEgreso.adddp);
            
            if (listaTipoMov.size() > 0) {
                tipoMov = listaTipoMov.get(0);
            }
            IVA = articuloDAO.obtenerIVA();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(vistaEgreso, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        vistaEgreso.setVisible(true);
    }

    private void eventos() {
        vistaEgreso.bucsResponsable.addActionListener((ActionEvent e) -> {
            InicializarVistaResponsable();
            controlador_responsable = new Ctrl_responsable(vista_resposable);
            controlador_responsable.tipo = 'C';
            controlador_responsable.IniciarVistaResponsable();
        });

        vistaEgreso.datePanel.addActionListener((ActionEvent e) -> {
            java.util.Date date = (java.util.Date) vistaEgreso.datePicker.getModel().getValue();
            String fechaFormat = formatearFecha(date);
            vistaEgreso.datePicker.getJFormattedTextField().setText(fechaFormat);
            vistaEgreso.datePicker.getModel().setSelected(true);
        });

        vistaEgreso.registrar.addActionListener((ActionEvent e) -> {
            if (vistaEgreso.responsable.getText().equals("")) {
                JOptionPane.showMessageDialog(vistaEgreso, "Selecciona un beneficiario", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    java.util.Date fa = new java.util.Date();
                    fa = new java.util.Date(formatearFecha(fa));
                    java.util.Date fs = (java.util.Date) vistaEgreso.datePicker.getModel().getValue();
                    fs = new java.util.Date(formatearFecha(fs));
                    if (fs.after(fa)) {
                        JOptionPane.showMessageDialog(vistaEgreso, "La fecha no debe ser mayor a la fecha actual", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    ExamenVisual examenVisual = new ExamenVisual();
                    int addalt = 0;
                    int adddp = 0;
                    int oieje = 0;
                    int odeje = 0;
                    int oicyl = 0;
                    int odcyl = 0;
                    int edad = 0;

                    if (!vistaEgreso.edad.getText().isEmpty()) {
                        edad = Integer.parseInt(vistaEgreso.edad.getText());
                    } else {
                        JOptionPane.showMessageDialog(vistaEgreso, "Ingresa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (vistaEgreso.direccion.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(vistaEgreso, "Ingresa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (vistaEgreso.tipoluna.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(vistaEgreso, "Ingresa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (vistaEgreso.telefono.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(vistaEgreso, "Ingresa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!vistaEgreso.addalt.getText().isEmpty()) {
                        addalt = Integer.parseInt(vistaEgreso.addalt.getText());
                    }
                    if (!vistaEgreso.adddp.getText().isEmpty()) {
                        adddp = Integer.parseInt(vistaEgreso.adddp.getText());
                    }
                    if (!vistaEgreso.oieje.getText().isEmpty()) {
                        oieje = Integer.parseInt(vistaEgreso.oieje.getText());
                    }
                    if (!vistaEgreso.odeje.getText().isEmpty()) {
                        odeje = Integer.parseInt(vistaEgreso.odeje.getText());
                    }
                    if (!vistaEgreso.oicyl.getText().isEmpty()) {
                        oicyl = Integer.parseInt(vistaEgreso.oicyl.getText());
                    }
                    if (!vistaEgreso.odcyl.getText().isEmpty()) {
                        odcyl = Integer.parseInt(vistaEgreso.odcyl.getText());
                    }

                    examenVisual.setAddalt(addalt);
                    examenVisual.setAdddp(adddp);
                    examenVisual.setOdcyl(odcyl);
                    examenVisual.setOdeje(odeje);
                    examenVisual.setOicyl(oicyl);
                    examenVisual.setOieje(oieje);

                    examenVisual.setCelular(vistaEgreso.telefono.getText());
                    examenVisual.setCodtiporesponsable(responsable.getCodtiporesposanble());
                    examenVisual.setIdentificacion(responsable.getIdentificacion());
                    examenVisual.setDireccion(vistaEgreso.direccion.getText());
                    examenVisual.setEdad(edad);
                    examenVisual.setTipoluna(vistaEgreso.tipoluna.getText());
                    examenVisual.setFecha(fechaMovimiento);

                    citasDAO.insertatCita(examenVisual);
                    JOptionPane.showMessageDialog(vistaEgreso, "Examen registrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    obtenerFilas();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrlExamen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        vistaEgreso.cancelar.addActionListener((ActionEvent e) -> {
            vistaEgreso.dispose();
        });

    }

    public void solonumeros(JTextField j) {
        j.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evk) {
                if (!Character.isDigit(evk.getKeyChar())) {
                    evk.consume();
                }
            }

        });

    }

    private void obtenerFilas() throws SQLException {
        if (responsable != null) {
            modelo.Citas c = new modelo.Citas();
            c.setIdentificacion(responsable.getIdentificacion());
            DataSet dta = citasDAO.obtenerTabla(c);
            vistaEgreso.TablaDetalle.setModel(dta);
        }
    }

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
                    try {
                        vistaEgreso.responsable.setText(responsable.getRazonsocial());
                        obtenerFilas();
                    } catch (SQLException ex) {
                        Logger.getLogger(CtrlExamen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("nulo");
                    vistaEgreso.responsable.setText("");
                }
            }
        });
    }

    private String formatearFecha(java.util.Date fecha) {
        String fechaFormat;
        if (fecha != null) {
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fechaFormat = formateador.format(fecha);
            this.fechaMovimiento = new Date(fecha.getTime());
        } else {
            fechaFormat = formatearFecha(fechaMovimiento);
        }
        return fechaFormat;
    }

    private double stock;

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void setListaArticulos(List<Articulos> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    private final ConexionPSQL conexionPSQL;

}
