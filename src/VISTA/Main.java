import BBDD.ConfigLoader;
import BBDD.gestorConexion;

import static BBDD.MetodosCliente.*;
import static BBDD.MetodosFactura.*;


private static int contarLineasCSV(String data) {
    int nclientes = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(data))) {
        br.readLine(); // saltar cabecera
        while (br.readLine() != null) {
            nclientes++;
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return nclientes;
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //variable del variable_resolution de config
    String tablaClientes = ConfigLoader.get().getProperty("sql.customerTable");
    String tablaFacturas = ConfigLoader.get().getProperty("sql.BillTable");

    gestorConexion g=new gestorConexion();
    crearTablaClientes(g.getConexion(),tablaClientes);
    crearTablaFacturas(g.getConexion(),tablaFacturas);



    //instertamos los clientes
    System.out.println("======================================================================\n");
    //tratar datos es el que lo inserta tambien
    System.out.println("insertamos clientes:\n");
    System.out.println("===== customers-1000.csv =====\n");
    int totalClientes=contarLineasCSV(ConfigLoader.get().getProperty("csv.path.customers"));
    System.out.println("el fichero csv cuenta con: " + totalClientes + " en total");
    int clientesInsertados=tratarDatos(g.getConexion(),ConfigLoader.get().getProperty("csv.path.customers"));

    if (totalClientes>clientesInsertados)
        System.out.println("no se han insertado todos los clientes correctamente, se insertaron solo " +clientesInsertados+ ", revisar el log");
    else
        System.out.println("se insertaron todos lo clientes adecuadamnete");
    System.out.println("======================================================================\n");
    System.out.println("seguimos con la inserción de las facturas");






    System.out.println("======================================================================\n");
    g.cerrar();


}
