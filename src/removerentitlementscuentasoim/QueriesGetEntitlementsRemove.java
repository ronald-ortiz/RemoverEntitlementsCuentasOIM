/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removerentitlementscuentasoim;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueriesGetEntitlementsRemove {

    public String getusrKeyOIM(Connection connection, String connectorName, String identity) {
        String usrKey = "";

        String sqlQuery = "select usr_key from usr where usr_login = '"+identity+"'";

        // Crear una instancia de OracleDBExecutor con la conexión
        OracleDBExecutor executor = new OracleDBExecutor(connection);

        ResultSet resultSet = executor.executeQuery(sqlQuery);

        try {
            while (resultSet != null && resultSet.next()) {
                // Procesar los resultados de la consulta
                usrKey = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al procesar los resultados: " + e.getMessage());
        } finally {
            // Cerrar el ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }

        return usrKey;
    }
    
    
    public String getEntAssingKey(Connection connection, String usrKey, String entitlement){
       String entAssingKey = "";
       
        String sqlQuery = "select ea.ent_assign_key from ent_assign ea inner join ent_list el on ea.ent_list_key = el.ent_list_key where ea.usr_key = "+usrKey+" and el.ent_display_name = '"+entitlement+"'";

        // Crear una instancia de OracleDBExecutor con la conexión
        OracleDBExecutor executor = new OracleDBExecutor(connection);

        ResultSet resultSet = executor.executeQuery(sqlQuery);

        try {
            while (resultSet != null && resultSet.next()) {
                // Procesar los resultados de la consulta
                entAssingKey = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al procesar los resultados: " + e.getMessage());
        } finally {
            // Cerrar el ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
       
       return entAssingKey;
    }

}
