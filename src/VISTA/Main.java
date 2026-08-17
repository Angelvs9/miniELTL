import BBDD.ConfigLoader;
import BBDD.gestorConexion;

import static BBDD.Metodos.crearBBDD;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //variable del variable_resolution de config
    String bd = ConfigLoader.get().getProperty("sql.schema");

    gestorConexion g=new gestorConexion();
    crearBBDD(g.getConexion(),bd);

    g.cerrar();


}
