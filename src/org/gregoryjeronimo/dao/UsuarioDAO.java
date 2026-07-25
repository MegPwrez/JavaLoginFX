package org.gregoryjeronimo.dao;

import org.gregoryjeronimo.model.Usuario;
import org.gregoryjeronimo.util.Conexion;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;

// Data Access Object (DAO) -> MySQL usando Conexion
public class UsuarioDAO {    
    
    // Método para Iniciar Sesión con Depuración
    public Usuario iniciarSesion(String username, String passwordHash) {            
        Usuario usuario = null;
        String sql = "{call sp_iniciar_sesion(?,?)}";
        
        System.out.println("========== DEPURACIÓN LOGIN ==========");
        System.out.println("Usuario recibido desde la UI: [" + username + "]");
        System.out.println("Password recibida desde la UI: [" + passwordHash + "]");
        
        try (Connection conexion = Conexion.getInstancia().conectar()) {
            
            if (conexion != null) {
                System.out.println(" Conexión a la Base de Datos EXITOSA.");
            } else {
                System.err.println(" La conexión retornó NULL.");
                return null;
            }

            try (CallableStatement consulta = conexion.prepareCall(sql)) {
                consulta.setString(1, username);
                consulta.setString(2, passwordHash);
                
                try (ResultSet tablaResultado = consulta.executeQuery()) {
                    if (tablaResultado.next()) {
                        usuario = new Usuario();
                        usuario.setId(tablaResultado.getInt(1));
                        usuario.setUsername(tablaResultado.getString(2));
                        usuario.setRol(tablaResultado.getString(3));
                        System.out.println(" ¡Usuario encontrado en MySQL! Rol: " + usuario.getRol());
                    } else {
                        System.err.println(" MySQL ejecutó el Stored Procedure pero NO encontró coincidencia con esos datos.");
                    }
                }
            }
            
        } catch (SQLException e) {
            System.err.println(" Error de SQL/Conexión: " + e.getMessage());                   
        }
        
        System.out.println("=====================================");
        return usuario;
    }
    
    // Método para Registrar Usuario
    public boolean registrarUsuario(String username, String passwordHash, String rol) {
        String sql = "{call sp_registrar_usuario(?,?,?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consulta = conexion.prepareCall(sql)) {
            
            consulta.setString(1, username);
            consulta.setString(2, passwordHash);
            consulta.setString(3, rol.toLowerCase());
            
            int filasAfectadas = consulta.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
