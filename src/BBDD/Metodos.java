package BBDD;

import MODELO.Cliente;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //LEER DATOS DEL CSV PARA INSERTAR
        String query = "insert into clientes values(?,?,?,?,?,?,?,?,?,?,?,?)";
        int Errores=0;
        int contadorLinea=0;
        String linea="";
        try {
            FileReader fr=new FileReader(new File(data));
            BufferedReader br=new BufferedReader(fr);
            br.readLine();
            while((linea=br.readLine())!=null){

                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

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
                    System.out.println("Linea " + contadorLinea + " -> fecha cruda: [" + datos[10].trim() + "]");
                    suscripcion = Date.valueOf(datos[10].trim());
                }
                else {
                    System.out.println("Linea " + contadorLinea + " -> FECHA VACIA, se inserta como NULL");
                }
                String web = datos[11].trim();
                Cliente cliente = new Cliente(id, customerId, nombre, apellido, empresa, ciudad, pais, telefono1, telefono2, email, suscripcion, web);


                PreparedStatement pst = cn.prepareStatement(query);
                pst.setInt(1, cliente.getId());
                pst.setString(2, cliente.getCliente_id());
                //el nombre en ese ejercicio es not null
                if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
                    pst.setNull(3, java.sql.Types.VARCHAR);
                } else {
                    pst.setString(3, cliente.getNombre());
                }
                pst.setString(4, cliente.getApellido());
                pst.setString(5, cliente.getEmpresa());
                pst.setString(6, cliente.getCiudad());
                pst.setString(7, cliente.getPais());
                pst.setString(8, cliente.getNtelefono());
                pst.setString(9, cliente.getNtelefono2());
                pst.setString(10, cliente.getEmail());
                if (cliente.getSuscripcion() == null) {
                    pst.setNull(11, java.sql.Types.DATE);
                } else {
                    pst.setDate(11, cliente.getSuscripcion());
                }
                pst.setString(12, cliente.getWeb());


                pst.executeUpdate();
                contadorLinea++;
                pst.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            Errores++;
            enviarLog(query,contadorLinea,e);
            System.out.println("entra");
        }

    }

    public static void enviarLog(String query,int contadorLinea,SQLException e){


        String fecha = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String hora = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));

        String detalleError = "--------------------------------------\nHora: " + hora + "\nQuery: " + query + "\nLinea: " + contadorLinea + "\nMotivo: " + e.getMessage() + "\n--------------------------------------\n";

        String nombreLog = ConfigLoader.get().getProperty("temp.path") + "_" + fecha + ".log";
        File f = new File(nombreLog);

        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(detalleError);
        } catch (IOException ioEx) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ioEx);
        }
    }



}
