/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;

/**
 *
 * @author JOSE-MA
 */
public class CitasDAO extends ConexionPSQL {

    public int insertatCita(Citas citas) throws SQLException {
        abrirConexion();
        sentencia = conexion.prepareStatement("INSERT INTO citas(observacion, codtiporesponsable, identificacion, fecha)VALUES (?, ?, ?, ?)");
        int i = 1;
        sentencia.setString(i++, citas.getObservacion());
        sentencia.setString(i++, citas.getCodtiporesponsable());
        sentencia.setString(i++, citas.getIdentificacion());
        sentencia.setDate(i++, citas.getFecha());
        int res = sentencia.executeUpdate();
        cerrarConexion();
        return res;
    }

    public DataSet obtenerTabla(Citas citas) throws SQLException {
        DataSet dt;
        abrirConexion();
        sentencia = conexion.prepareStatement("SELECT idcitas, observacion, fecha FROM citas where identificacion=?");
        int i = 1;
        sentencia.setString(i++, citas.getIdentificacion());
        resultado = sentencia.executeQuery();
        dt = new DataSet();
        dt.load(resultado);
        cerrarConexion();
        return dt;
    }

}
