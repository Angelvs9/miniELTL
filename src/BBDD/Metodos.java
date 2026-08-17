package BBDD;

import MODELO.Cliente;

import java.io.*;
import java.sql.*;
import java.sql.Date;
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


    public static void insertardelCSV(Connection cn,String data){
        //LEER DATOS DEL CSV
        int contador=0;
        String Errores="";
        String linea="";
        String consulta="";
        try {
            FileReader fr=new FileReader(new File(data));
            BufferedReader br=new BufferedReader(fr);
            br.readLine();
            while((linea=br.readLine())!=null){
                //separador , y además si hay algo vacio lo rellena con ""
                String[] datos = linea.split(",",-1);
                boolean rellenado=true;

                for (int i=0;i<=datos.length;i++){
                    //en la bd hay 12 campos
                    if(datos[i].equals("")){
                        Errores+="el campo numero:"+i+"esta vacio\n";
                    }
                }
                int id = Integer.parseInt(datos[0].trim());
                String customerId = datos[1].trim();
                String nombre = datos[2].trim();
                String apellido = datos[3].trim();
                String empresa = datos[4].trim();
                String ciudad = datos[5].trim();
                String pais = datos[6].trim();
                String telefono1 = datos[7].trim();
                String telefono2 = datos[8].trim();
                String email = datos[9].trim();
                Date suscripcion = null;
                if (!datos[10].trim().isEmpty()) {
                    suscripcion = Date.valueOf(datos[10].trim());
                }
                String web = datos[11].trim();
                Cliente tmp = new Cliente(id, customerId, nombre, apellido, empresa, ciudad, pais, telefono1, telefono2, email, suscripcion, web);
                String query = "insert into clientes values(?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement pst = cn.prepareStatement(query);
                pst.setInt(1, tmp.getId());
                pst.setString(2, tmp.getCliente_id());
                pst.setString(3, tmp.getNombre());
                pst.setString(4, tmp.getApellido());
                pst.setString(5, tmp.getEmpresa());
                pst.setString(6, tmp.getCiudad());
                pst.setString(7, tmp.getPais());
                pst.setString(8, tmp.getNtelefono());
                pst.setString(9, tmp.getNtelefono2());
                pst.setString(10, tmp.getEmail());
                if (tmp.getSuscripcion() == null) {
                    pst.setNull(11, java.sql.Types.DATE);
                } else {
                    pst.setDate(11, tmp.getSuscripcion());
                }

                pst.setString(12, tmp.getWeb());

                pst.executeUpdate();
                contador++;
                pst.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
