/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestoras;

/**
 *
 * @author icastillo
 */
public class GestoraMain {
 
    /*
    
    */
    public void muestraMenuPrincipal(){
        System.out.println("Seleccione una opción del menú");
        System.out.println("0: Para salir");
        System.out.println("1: Para cambiar propietario de un regalo");
        System.out.println("2: Para insertar una nueva criaturita con dos nuevos regalos");
        System.out.println("3: Para insertar un nuevo regalo y asignar a criaturita existente");
        System.out.println("4: Para borrar una criaturita con todos sus regalos");
    }
    
    /*
    
    */
    public boolean validaOpcionMenuPrincipal(String opcion){
        boolean vale=true;
        int opcionParseada=-1;
        
        try{
            opcionParseada=Integer.parseInt(opcion);
        }catch(NumberFormatException error){
            vale=false;
        }
        if(vale){
            if(opcionParseada<0 || opcionParseada>4){
                vale=false;
            }
        }        
        return vale;
    }
    
    /*
    
    */
    public short parseStringToShort(String cadena){
        Short _short=null;
        
        try{
            _short=Short.parseShort(cadena);
        }catch(NumberFormatException error){}      
        return _short;
    }
    
    
}
