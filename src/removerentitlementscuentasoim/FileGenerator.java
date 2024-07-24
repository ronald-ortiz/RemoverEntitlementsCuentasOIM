/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removerentitlementscuentasoim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileGenerator {

    // Método que recibe una lista de Strings y genera un archivo txt
    public void generateFile(List<String> dataList, String fileName) {
        // Crear un objeto BufferedWriter para escribir en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Recorrer cada elemento de la lista
            for (String data : dataList) {
                // Escribir cada elemento en el archivo y saltar a la siguiente línea
                writer.write(data);
                writer.newLine();
            }
            System.out.println("Archivo generado exitosamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
        }
    }

}
