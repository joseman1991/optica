/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;

public class ExamenDAO extends ConexionPSQL {

    public int insertatCita(ExamenVisual citas) throws SQLException {
        abrirConexion();
        sentencia = conexion.prepareStatement("INSERT INTO examenvisual(codtiporesponsable, identificacion, edad, "
                + " direccion,celular, tipoluna, odeje, oieje, odcyl, oicyl, adddp, addalt, fecha)\n"
                + "    VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?);");
        int i = 1;
        sentencia.setString(i++, citas.getCodtiporesponsable());
        sentencia.setString(i++, citas.getIdentificacion());
        sentencia.setInt(i++, citas.getEdad());
        sentencia.setString(i++, citas.getDireccion());
        sentencia.setString(i++, citas.getCelular());
        sentencia.setString(i++, citas.getTipoluna());

        sentencia.setInt(i++, citas.getOdeje());
        sentencia.setInt(i++, citas.getOieje());
        sentencia.setInt(i++, citas.getOdcyl());
        sentencia.setInt(i++, citas.getOicyl());
        sentencia.setInt(i++, citas.getAdddp());
        sentencia.setInt(i++, citas.getAddalt());
        sentencia.setDate(i++, citas.getFecha());
        int res = sentencia.executeUpdate();
        cerrarConexion();
        return res;
    }

    public DataSet obtenerTabla(Citas citas) throws SQLException {
        DataSet dt;
        abrirConexion();
        sentencia = conexion.prepareStatement("SELECT * FROM examenvisual where identificacion=?");
        int i = 1;
        sentencia.setString(i++, citas.getIdentificacion());
        resultado = sentencia.executeQuery();
        dt = new DataSet();
        dt.load(resultado);
        cerrarConexion();
        return dt;
    }

}
