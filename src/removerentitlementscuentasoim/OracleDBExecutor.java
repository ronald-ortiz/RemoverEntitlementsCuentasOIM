/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removerentitlementscuentasoim;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBExecutor {

    private Connection connection;

    // Constructor que recibe una conexión
    public OracleDBExecutor(Connection connection) {
        this.connection = connection;
    }

    // Método para ejecutar una consulta SQL y devolver el ResultSet
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return resultSet;
    }

    // Método para ejecutar una actualización SQL (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query) {
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la actualización: " + e.getMessage());
        }
        return result;
    }

}
