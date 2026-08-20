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


    public static void tratarDatos(Connection cn,String data){
        //LEER DATOS DEL CSV y llamar a insertar
        int contadorLinea=1;
        try {
            FileReader fr=new FileReader(new File(data));
            BufferedReader br= new BufferedReader(fr);
            String linea="";
            //salto cabezera
            br.readLine();
            while((linea=br.readLine())!=null){
                String[] datos = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i = 0; i < datos.length; i++) {
                    if (datos[i] == null || datos[i].trim().isEmpty() || datos[i].trim().equals("\"\"")) {
                        datos[i] = null;
                    }
                }
                Date suscripcion = null;

                if (datos[10] != null) {
                    suscripcion = java.sql.Date.valueOf(datos[10]);
                }
                //ya está el cliente creado en principio
                Cliente cliente = new Cliente(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
                        datos[5],
                        datos[6],
                        datos[7],
                        datos[8],
                        datos[9],
                        suscripcion,
                        datos[11]
                );
                if(insertarCliente(cliente,contadorLinea,cn)){
                    contadorLinea++;
                }
                //dejo asi el if porque el log se enviaria dentro del metodo de insertarCliente entonces si no da true es que ha enviado el log y no tiene que contar la linea
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean insertarCliente(Cliente c,int linea,Connection cn){
        //si sale mal enviarLog desde aqui y asi paso la excepcion y todo
        //aqui ira la query el preparedStatement
        boolean insertado=false;
        String query="insert into clientes values (?,?,?,?,?,?,?,?,?,?,?,?)";
        //este temp es para que en caso de error yo vea directamente sustituidos los datos en el objeto
        String temp = "insert into clientes values(" + c.getId() + ",'" + c.getCliente_id() + "','" + c.getNombre() + "','" + c.getApellido() + "','" + c.getEmpresa() + "','" + c.getCiudad() + "','" + c.getPais() + "','" + c.getNtelefono() + "','" + c.getNtelefono2() + "','" + c.getEmail() + "'," + (c.getSuscripcion() != null ? "'" + c.getSuscripcion() + "'" : "NULL") + ",'" + c.getWeb() + "')";
        
        try {
            PreparedStatement pt=cn.prepareStatement(query);
            pt.setInt(1,c.getId());
            pt.setString(2, c.getCliente_id());
            pt.setString(3, c.getNombre());
            pt.setString(4, c.getApellido());
            pt.setString(5, c.getEmpresa());
            pt.setString(6, c.getCiudad());
            pt.setString(7, c.getPais());
            pt.setString(8, c.getNtelefono());
            pt.setString(9, c.getNtelefono2());
            pt.setString(10, c.getEmail());
            pt.setDate(11, c.getSuscripcion());
            pt.setString(12, c.getWeb());
            pt.executeUpdate();
            pt.close();
            insertado=true;

        } catch (SQLException e) {
            enviarLog(temp,linea,e);
        }


        return insertado;
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
