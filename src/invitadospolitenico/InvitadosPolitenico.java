/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package invitadospolitenico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author politecnico
 */
public class InvitadosPolitenico {

    public static void main(String[] args) {
        try {
            
            Connection conexion = ConexionBD.conectar();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Selecciona una opción:");
                System.out.println("1. Crear registro");
                System.out.println("2. Modificar registro");
                System.out.println("3. Eliminar registro");
                System.out.println("4. Consultar registros");
                System.out.println("5. Salir");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearRegistro(conexion, scanner);
                        break;
                    case 2:
                        modificarRegistro(conexion, scanner);
                        break;
                    case 3:
                        eliminarRegistro(conexion, scanner);
                        break;
                    case 4:
                        consultarRegistros(conexion);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (SQLException e) {
             System.out.println("Mensaje de error: " + e.getMessage());
        }
    }

    private static void crearRegistro(Connection conexion, Scanner scanner) throws SQLException {
        
        while (true) {
            
            scanner.nextLine();
            
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine(); // Lee la línea completa al presionar Enter

            System.out.print("Correo: ");
            String correo = scanner.nextLine();


            String insertQuery = "INSERT INTO Invitados (nombre, correo) VALUES (?, ?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(insertQuery);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, correo);
            preparedStatement.executeUpdate();
            
            System.out.println("Registro creado con éxito.");
            System.out.println("---------------------------------");
            System.out.print("¿Desea crear otro registro? (S/N): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("S")) {
                break; // Sale del bucle si la respuesta no es "S".
            }
        
        }
        System.out.println("---------------------------------");
        
    }

    private static void modificarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("ID del registro a modificar: ");
        int id = scanner.nextInt();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.next();
        System.out.print("Nuevo correo: ");
        String nuevoCorreo = scanner.next();

        String updateQuery = "UPDATE Invitados SET nombre = ?, correo = ? WHERE id = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(updateQuery);
        preparedStatement.setString(1, nuevoNombre);
        preparedStatement.setString(2, nuevoCorreo);
        preparedStatement.setInt(3, id);
        int filasActualizadas = preparedStatement.executeUpdate();

        if (filasActualizadas > 0) {
            System.out.println("Registro modificado con éxito.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }
    }

    private static void eliminarRegistro(Connection conexion, Scanner scanner) throws SQLException {
        System.out.print("ID del registro a eliminar: ");
        int id = scanner.nextInt();

        String deleteQuery = "DELETE FROM Invitados WHERE id = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, id);
        int filasEliminadas = preparedStatement.executeUpdate();

        if (filasEliminadas > 0) {
            System.out.println("Registro eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún registro con el ID proporcionado.");
        }
    }

    private static void consultarRegistros(Connection conexion) throws SQLException {
        String selectQuery = "SELECT * FROM Invitados";
        PreparedStatement preparedStatement = conexion.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nombre = resultSet.getString("nombre");
            String correo = resultSet.getString("correo");

            System.out.println("ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo);
        }
    }
    
}
