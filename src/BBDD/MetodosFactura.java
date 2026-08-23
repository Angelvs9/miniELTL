package BBDD;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodosFactura {

    public static boolean crearTablaFacturas(Connection cn, String BD){
        boolean resultado=true;
        String linea="";
        String consulta="";


        File f= new File(BD);
        try {
            BufferedReader br= new BufferedReader(new FileReader(f));
            Statement stmt=cn.createStatement();
            while((linea=br.readLine())!=null){

                //si no está vacia ni es comentario lo añade
                if (linea!=" " && !linea.startsWith("--"))
                    consulta+=linea;


                if (consulta.endsWith(";")) {
                    stmt.execute(consulta);
                    consulta="";
                    //se supone que si ya has ejecutado 1 consulta el resto es igual
                    resultado=true;
                }
            }
            br.close();
            stmt.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
}
