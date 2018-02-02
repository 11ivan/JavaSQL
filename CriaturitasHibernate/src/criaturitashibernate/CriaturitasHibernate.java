/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturitashibernate;

import Clases.Criaturitas;
import Clases.Regalos;
import Gestoras.GestoraMain;
import Gestoras.ManejadorCriaturitas;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author icastillo

Para hacer esta tarea puedes crear las clases manejadoras y de utilidad que consideres necesarias.

    PSEUDOCODIGO

    INICIO       
        MOSTRAR MENU LEER Y VALIDAR OPCION
        MIENTRAS OPCION ELEGIDA SEA DISTINTA DE SALIR      
            SEGUN OPCION ELEGIDA      
                CASO 1: CAMBIAR PROPIETARIO DE UN REGALO       
                CASO 2: INSERTAR NUEVA CRIATURITA CON DOS NUEVOS REGALOS        
                CASO 3: INSERTAR NUEVO REGALO Y ASIGNAR A CRIATURITA EXISTENTE       
                CASO 4: BORRAR UNA CRIATURITA Y TODOS SUS REGALOS            
            FIN SEGUN            
            VOLVER A MOSTRAR MENU LEER Y VALIDAR OPCION            
        FIN MIENTRAS      
    FIN

*/

public class CriaturitasHibernate {

    public static void main(String[] args) {

        //Variables
        GestoraMain gestoraMain=new GestoraMain();
        String opcionElegida="";
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        ManejadorCriaturitas manejadorCriaturitas=new ManejadorCriaturitas();
        String idCriaturita="";
        Short idCriaturitaParseado=null;
        String idRegalo="";
        Short idRegaloParseado=null;
        Regalos regalo=null;
        Criaturitas criaturita=null;
        
        //MOSTRAR MENU LEER Y VALIDAR OPCION
        do{
            gestoraMain.muestraMenuPrincipal();
            try {
                opcionElegida=br.readLine();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }while(!gestoraMain.validaOpcionMenuPrincipal(opcionElegida));
        
        //MIENTRAS OPCION ELEGIDA SEA DISTINTA DE SALIR
        while(!opcionElegida.equals("0")){
            
            switch(opcionElegida){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                
                
                case "1"://CAMBIAR PROPIETARIO DE UN REGALO
                            //MOSTRAR REGALOS LEER Y VALIDAR IDREGALO
                            do{
                                System.out.println("Introduzca el ID del regalo que quiere cambiar de propietario\n");
                                manejadorCriaturitas.listaCriaturitas(manejadorCriaturitas.getCriaturitas());//es regalos
                                try {
                                    idRegalo=br.readLine();
                                    idRegaloParseado=gestoraMain.parseStringToShort(idRegalo);/////////////////////
                                    //regalo = manejadorCriaturitas.recuperar(idCriaturitaParseado);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }while(idRegaloParseado==null /*|| regalo==null */);//es con regalos////////////
                            
                            //MOSTRAR CRIATURITAS LEER Y VALIDAR IDCRIATURITA
                            do{
                                System.out.println("Introduzca el ID del propietario al que quiere asignar el regalo\n");
                                manejadorCriaturitas.listaCriaturitas(manejadorCriaturitas.getCriaturitas());
                                try {
                                    idCriaturita=br.readLine();
                                    idCriaturitaParseado=gestoraMain.parseStringToShort(idCriaturita);
                                    if(idCriaturitaParseado!=null){
                                        criaturita=manejadorCriaturitas.recuperar(idCriaturitaParseado);
                                    }
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }while(criaturita==null);

                break;
                
                
                case "2"://INSERTAR NUEVA CRIATURITA CON DOS NUEVOS REGALOS
                    
                    
                break;
                
                
                case "3"://INSERTAR NUEVO REGALO Y ASIGNAR A CRIATURITA EXISTENTE
                    
                    
                break;
                
                   
                case "4"://BORRAR UNA CRIATURITA Y TODOS SUS REGALOS 
                   
                    
                break;
            }//FIN SEGUN
            
            //VOLVER A MOSTRAR MENU LEER Y VALIDAR OPCION
            do{
                gestoraMain.muestraMenuPrincipal();
                try {
                    opcionElegida=br.readLine();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }while(!gestoraMain.validaOpcionMenuPrincipal(opcionElegida));
        }//FIN MIENTRAS      
        
    }//FIN
}
