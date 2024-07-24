/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removerentitlementscuentasoim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RemoverEntitlementsCuentasOIM {

    private final static String URL_FILE_ENTITLEMENT          = "C:\\Users\\momar\\OneDrive\\Documentos\\ListadoEntitlementsRemoverADEXT\\ListadoCuentasRemoverEntitlements.txt";

    private final static String URL_FILE_ENTITLEMENT_FOUND    = "C:\\Users\\momar\\OneDrive\\Documentos\\ListadoEntitlementsRemoverADEXT\\EntitlementFound.txt";
    
    private final static String URL_FILE_ENTITLEMENT_NOTFOUND = "C:\\Users\\momar\\OneDrive\\Documentos\\ListadoEntitlementsRemoverADEXT\\EntitlementNotFound.txt";
    
    private final static String CONNECTOR_NAME = "ADEXTY";

    public static void main(String[] args) {

        BufferedReader lector = null;

        try {
            // Crear un FileReader para el archivo
            FileReader fileReader = new FileReader(URL_FILE_ENTITLEMENT);

            // Crear un BufferedReader para una lectura eficiente
            lector = new BufferedReader(fileReader);

            //Instancia objeto para la conexion a base de datos OIM
            OracleDBConnection orclDB = new OracleDBConnection();

            // Establecer la conexión
            Connection connection = orclDB.getConnection();

            // Leer cada línea del archivo
            String linea;
            
            List<String> listadoEntitlementsNoencontrados = new ArrayList<>();
            
            List<String> listadoEntitlementsEncontrados   = new ArrayList<>();
            
            while ((linea = lector.readLine()) != null) {

                String[] segmentosLineaArchivo = linea.split("@");

                String identidad = segmentosLineaArchivo[0];
                String entitlement = segmentosLineaArchivo[1];
                
                // Aquí puedes realizar operaciones con la base de datos
                QueriesGetEntitlementsRemove qu = new QueriesGetEntitlementsRemove();
                
                String oiuKey = qu.getusrKeyOIM(connection, CONNECTOR_NAME, identidad);
                
                //System.out.println("usr_key -> "+oiuKey+", correspondiente para la identidad: "+identidad+" del conector: "+CONNECTOR_NAME);
                
                String entitlementAssignKey = qu.getEntAssingKey(connection, oiuKey, entitlement);
                
                if(entitlementAssignKey.isEmpty()){
                 listadoEntitlementsNoencontrados.add(linea);
                }else{
                 String sentenciaRemoverEntitlement = "DELETE FROM ent_assign where ent_assign_key = "+entitlementAssignKey+"; --"+linea;
                 listadoEntitlementsEncontrados.add(sentenciaRemoverEntitlement);
                }
                
                
            } //end while
            
            // Cerrar la conexión
            orclDB.closeConnection(connection);
            
            System.out.println("Total de items en listado de cuentas que no fue posible encontrar sus entitlements: "+listadoEntitlementsNoencontrados.size());
            
            System.out.println("Se procede a generar archivos...");
            
            FileGenerator fg = new FileGenerator();
            
            fg.generateFile(listadoEntitlementsEncontrados, URL_FILE_ENTITLEMENT_FOUND);
            
            fg.generateFile(listadoEntitlementsNoencontrados, URL_FILE_ENTITLEMENT_NOTFOUND);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el BufferedReader en el bloque finally
            try {
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
