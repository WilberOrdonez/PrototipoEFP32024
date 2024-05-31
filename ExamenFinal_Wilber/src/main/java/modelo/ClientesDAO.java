package modelo;

import controlador.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de interactuar con la tabla de clientes en la base de datos
 */
public class ClientesDAO {

    private static final String SQL_SELECT = "SELECT pkid, nombre, idTipo, apellido, nit, telefono, direccion, correo, estatus FROM cliente";
    private static final String SQL_INSERT = "INSERT INTO cliente(pkid, nombre, idTipo, apellido, nit, telefono, direccion, correo, estatus) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, idTipo=?, apellido=?, nit=?, telefono=?, direccion=?, correo=?, estatus=? WHERE pkid = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE pkid=?";
    private static final String SQL_QUERY = "SELECT pkid, nombre, idTipo, apellido, nit, telefono, direccion, correo, estatus FROM cliente WHERE pkid = ?";

    public List<Clientes> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes cliente = null;
        List<Clientes> clientes = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String pkid = rs.getString("pkid");
                String nombre = rs.getString("nombre");
                String idTipo = rs.getString("idTipo");
                String apellido = rs.getString("apellido");
                String nit = rs.getString("nit");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                String estatus = rs.getString("estatus");

                cliente = new Clientes();
                cliente.setPkid(pkid);
                cliente.setNombre(nombre);
                cliente.setIdTipo(idTipo);
                cliente.setApellido(apellido);
                cliente.setNit(nit);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);
                cliente.setCorreo(correo);
                cliente.setEstatus(estatus);

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return clientes;
    }

    public int insert(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getPkid());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getIdTipo());
            stmt.setString(4, cliente.getApellido());
            stmt.setString(5, cliente.getNit());
            stmt.setString(6, cliente.getTelefono());
            stmt.setString(7, cliente.getDireccion());
            stmt.setString(8, cliente.getCorreo());
            stmt.setString(9, cliente.getEstatus());
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getIdTipo());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getNit());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getCorreo());
            stmt.setString(8, cliente.getEstatus());
            stmt.setString(9, cliente.getPkid());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, cliente.getPkid());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public Clientes query(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query: " + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, cliente.getPkid());
            rs = stmt.executeQuery();
            if (rs.next()) {
                String pkid = rs.getString("pkid");
                String nombre = rs.getString("nombre");
                String idTipo = rs.getString("idTipo");
                String apellido = rs.getString("apellido");
                String nit = rs.getString("nit");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                String estatus = rs.getString("estatus");

                cliente = new Clientes();
                cliente.setPkid(pkid);
                cliente.setNombre(nombre);
                cliente.setIdTipo(idTipo);
                cliente.setApellido(apellido);
                cliente.setNit(nit);
                cliente.setTelefono(telefono);
                cliente.setDireccion(direccion);
                cliente.setCorreo(correo);
                cliente.setEstatus(estatus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
}
