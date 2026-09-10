//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import com.guille.configuracion.FabricaAplicacion;
import com.guille.persistencia.ConexionBD;
import com.guille.vistas.Aplicacion;



void main() {

        Aplicacion aplicacion = FabricaAplicacion.crear(ConexionBD.Ambiente.DESARROLLO);
        aplicacion.ejecutar();

}


