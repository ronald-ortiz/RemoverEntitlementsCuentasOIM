/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removerentitlementscuentasoim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {

    // Variables de conexión
    private static final String DB_URL = "jdbc:oracle:thin:@//v3tvorta.mh.gob.sv:1521/oimp_db.mh.gob.sv";
    private static final String DB_USER = "PRD_OIM";
    private static final String DB_PASSWORD = "oracle12c";

    // Método para establecer la conexión
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Cargar el driver JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Establecer la conexión
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Conexión establecida.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC de Oracle: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión: " + e.getMessage());
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
