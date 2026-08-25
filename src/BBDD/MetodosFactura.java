package BBDD;

import java.io.*;
import java.sql.*;

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

    public static int insertarFacturas(Connection c,String archivo){
        String query="insert into facturas (ruc,id_cliente,tipo_registro,tipo_comprobante,fecha_emision,metodo_pago,numero_comprobante,iva_porcentaje,parte_iva,total,imputa_iva,imputa_ire,imputa_irp,imputar)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psta = c.prepareStatement(query);
            psta.setInt(1,21);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 1;
    }


    private static boolean existeCliente(Connection c,int idcliente){
        String query="select * from clientes where id="+idcliente+";";
        boolean existe=false;
        try {
            Statement sta=c.createStatement();
            ResultSet rs = sta.executeQuery(query);
            if(rs.next())
                existe=true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return existe;
    }


}
