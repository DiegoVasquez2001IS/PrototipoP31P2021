package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Controlador.Facultad;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class FacultadDAO extends Conexion {

    private static final String sql_select = "SELECT codigo_facultad, nombre_facultad, estatus_facultad FROM facultades";
    private static final String sql_insert = "INSERT INTO facultades(codigo_facultad, nombre_facultad, estatus_facultad) VALUES(?,?,?)";
    private static final String sql_update = "UPDATE facultades SET codigo_facultad=?, nombre_facultad=?, estado_facultad=?";
    private static final String sql_delete = "";
    private static final String sql_query = "";

    public List<Facultad> select() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Facultad facultad = null;
        List<Facultad> facultades = new ArrayList<Facultad>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_select);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String codigo_facultad = rs.getString("codigo_facultad");
                String nombre_facultad = rs.getString("nombre_facultad");
                String estado_facultad = rs.getString("estatus_facultad");

                facultad = new Facultad();
                facultad.setCodigo_facultad(codigo_facultad);
                facultad.setNombre_facultad(nombre_facultad);
                facultad.setEstado_facultad(estado_facultad);

                facultades.add(facultad);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return facultades;
    }

    public int insert(Facultad facultad) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_insert);
            stmt.setString(1, facultad.getCodigo_facultad());
            stmt.setString(2, facultad.getNombre_facultad());
            stmt.setString(3, facultad.getEstado_facultad());

            rows = stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro exitoso: se registraron: " + String.valueOf(rows) + " filas");
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return rows;
    }

}
