package BBDD;

import MODELO.Factura;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        int nlinea=1;
        int insertados=0;
        String query="insert into facturas (ruc,id_cliente,tipo_registro,tipo_comprobante,fecha_emision,metodo_pago,numero_comprobante,iva_porcentaje,parte_iva,total,imputa_iva,imputa_ire,imputa_irp,imputar)" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            File f= new File(archivo);
            BufferedReader br= new BufferedReader(new FileReader(f));
            String linea="";
            br.readLine();
            PreparedStatement psta = c.prepareStatement(query);

            while((linea=br.readLine())!=null){
                String [] fac=linea.split(";",-1);
                //este hace el borrado el "" y los cambia a null
                for (int i = 0; i < fac.length; i++) {
                    if (fac[i] == null || fac[i].trim().isEmpty() || fac[i].trim().equals("\"\"")) {
                        fac[i] = null;
                    }
                }
                if(existeCliente(c,Integer.parseInt(fac[1]))){
                    Factura factura = new Factura(
                            fac[0],                                             // RUC
                            Integer.parseInt(fac[1]),                           // idCliente
                            fac[2],                                             // Tipo de Registro
                            fac[3],                                             // Tipo de Comprobante
                            fac[4],                                             // Fecha de Emision
                            fac[5],                                             // Metodo pago
                            Integer.parseInt(fac[6]),                           // Numero de Comprobante
                            Double.parseDouble(fac[7].replace("%", "").replace(",", ".")), // IVA_PORCENTAJE
                            Double.parseDouble(fac[8].replace(".", "").replace(",", ".")), // PARTE_IVA
                            Double.parseDouble(fac[9].replace(".", "").replace(",", ".")), // Total
                            fac[10],                                            // Imputa IVA
                            fac[11],                                            // Imputa IRE
                            fac[12],                                            // Imputa IRP
                            fac[13]                                             // Imputar
                    );

                    //relleno lo la query
                    psta.setString(1, factura.getRuc());
                    psta.setInt(2, factura.getIdCliente());
                    psta.setString(3, factura.getTipoRegistro());
                    psta.setString(4, factura.getTipoComprobante());
                    psta.setString(5, factura.getFechaEmision());
                    psta.setString(6, factura.getMetodoPago());
                    psta.setInt(7, factura.getNcomprobante());
                    psta.setDouble(8, factura.getIvaPorcentaje());
                    psta.setDouble(9, factura.getParteIva());
                    psta.setDouble(10, factura.getTotal());
                    psta.setString(11, factura.getImputaIVA());
                    psta.setString(12, factura.getImputaIRE());
                    psta.setString(13, factura.getImputaIRP());
                    psta.setString(14, factura.getImputar());
                    if(psta.executeUpdate()==1) {
                        insertados++;
                    }


                }
                nlinea++;
            }//fin while



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return insertados;
    }


    private static boolean existeCliente(Connection c,int idcliente){
        String query="select * from clientes where id='" +idcliente+ "';";
        System.out.println(query);
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

    public static void enviarLog(String query,int contadorLinea,SQLException e){


        String fecha = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String hora = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        String nombreLog = ConfigLoader.get().getProperty("temp.path") + "_" + fecha + ".log";
        File f = new File(nombreLog);

        if (!f.exists()) {
            try (FileWriter fwTitulo = new FileWriter(f, true)) {
                fwTitulo.write("===== customers-1000.csv =====\n");
            } catch (IOException ioEx) {
                Logger.getLogger(MetodosCliente.class.getName()).log(Level.SEVERE, null, ioEx);
            }
        }

        String detalleError = "--------------------------------------\nHora: " + hora + "\nQuery: " + query + "\nLinea: " + contadorLinea + "\nMotivo: " + e.getMessage() + "\n--------------------------------------\n";

        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(detalleError);
        } catch (IOException ioEx) {
            Logger.getLogger(MetodosCliente.class.getName()).log(Level.SEVERE, null, ioEx);
        }
    }



}
