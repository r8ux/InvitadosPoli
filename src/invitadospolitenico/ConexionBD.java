/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package invitadospolitenico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://172.18.0.2:3306/Politecnico_invitados"; // Cambia la dirección IP si es diferente
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "politecnico";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión a la base de datos exitosa.");
            System.out.println("---------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la BD, Detalle :" + e.getMessage());
        }
        return conexion;
    }
}

