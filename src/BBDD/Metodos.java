package BBDD;

import MODELO.Cliente;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Metodos {

    public static boolean crearBBDD(Connection cn, String BD){
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


    public static void insertardelCSV(Connection cn,String BD,String data){
        //LEER DATOS DEL CSV

        String Errores="";
        String linea="";
        String consulta="";
        try {
            FileReader fr=new FileReader(new File(data));
            BufferedReader br=new BufferedReader(fr);
            while((linea=br.readLine())!=null){
                //separador , y además si hay algo vacio lo rellena con ""
                String[] datos = linea.split(",",-1);
                boolean rellenado=true;

                for (int i=0;i<datos.length;i++){
                    //en la bd hay 12 campos
                    if(datos[i]==""){
                        Errores+="el campo numero:"+i+"esta vacio\n";

                    }
                }



                Cliente tmp=new Cliente();


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return errores;
    }

}
