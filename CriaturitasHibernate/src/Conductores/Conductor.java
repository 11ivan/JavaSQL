package Conductores;

import Gestoras.ManejadorCriaturitas;

public class Conductor {
    public static void main(String[] args) {
        //final byte id = 6, otro = 15;
        //final String nombre = "Violeta";
        ManejadorCriaturitas mc = new ManejadorCriaturitas();
//        mc.borrar(otro);
//        mc.crearCriaturita(nombre,id);
        mc.listaCriaturitas(mc.getCriaturitas());
//        mc.cambiarNombre(nombre, id);
        //System.out.println("\n---------------------------\n"+mc.cadenaCriaturita(mc.recuperar(id)));
    }
}
