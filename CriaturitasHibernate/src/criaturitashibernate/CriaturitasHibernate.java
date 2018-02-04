/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criaturitashibernate;

import Clases.Criaturitas;
import Clases.Regalos;
import Gestoras.GestoraMain;
import Gestoras.GestoraRegalos;
import Gestoras.ManejadorCriaturitas;
import exceptions.ExcepcionRegalos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Collation;

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
        Regalos regalo;
        Criaturitas criaturita;
        GestoraRegalos gestoraRegalos=new GestoraRegalos();
        int tipoSalida=0;//El tipo de salida es 1 cuando se decide salir por voluntad del usuario, 2 cuando se decide salir porque todo es correcto y 0 si no se puede salir
        String respuesta="";
        Collection<Regalos> coleccionRegalos=null;
        
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
            
            regalo=null;
            criaturita=null;
            
            switch(opcionElegida){
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
                case "1"://CAMBIAR PROPIETARIO DE UN REGALO
                            //MOSTRAR REGALOS LEER Y VALIDAR IDREGALO
                            do{
                                System.out.println("Introduzca el ID del regalo que quiere cambiar de propietario\n");
                                gestoraRegalos.muestraListaRegalos();
                                try {
                                    idRegalo=br.readLine();
                                    idRegaloParseado=gestoraMain.parseStringToShort(idRegalo);
                                    if(!idRegaloParseado.equals(-1)){
                                        regalo = gestoraRegalos.getRegalo(idRegaloParseado);
                                    }
                                    if(regalo==null){
                                        do{
                                            System.out.println("No se encontro un Regalo con ese id \n ?Quiere volver a introducirlo? S/N");
                                            respuesta=br.readLine();
                                        }while(gestoraMain.validaSN(respuesta));//metodo
                                        if(respuesta.toLowerCase().charAt(0)=='n'){
                                            tipoSalida=1;
                                        }
                                    }else{
                                        tipoSalida=2;
                                    }                                    
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }while(tipoSalida==0);
                            //Si no quiere salir
                            if(tipoSalida==2){
                                tipoSalida=0;
                                //MOSTRAR CRIATURITAS LEER Y VALIDAR IDCRIATURITA
                                do{
                                    System.out.println("Introduzca el ID del propietario al que quiere asignar el regalo\n");
                                    manejadorCriaturitas.listaCriaturitas(manejadorCriaturitas.getCriaturitas());
                                    try {
                                        idCriaturita=br.readLine();
                                        idCriaturitaParseado=gestoraMain.parseStringToShort(idCriaturita);
                                        if(!idCriaturitaParseado.equals(-1)){
                                            criaturita=manejadorCriaturitas.recuperar(idCriaturitaParseado);
                                        }
                                        if(criaturita==null){
                                            do{
                                                System.out.println("No se encontro una Criaturita con ese id \n ?Quiere volver a introducirlo? S/N");
                                                respuesta=br.readLine();
                                            }while(gestoraMain.validaSN(respuesta));//metodo

                                            if(respuesta.toLowerCase().charAt(0)=='n'){
                                                tipoSalida=1;
                                            }
                                        }//fin si no se encontro la Criaturita
                                        else{
                                            tipoSalida=2;
                                        }      
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }while(tipoSalida==0);

                                //Si el motivo de salida fue correcto
                                if(tipoSalida==2){
                                    if(regalo.getGoesTo()==null || !regalo.getGoesTo().equals(criaturita)){//Comprobar si el regalo ya está asignado a la Criaturita antes de actualizar
                                        regalo.setGoesTo(criaturita);
                                        //regalo.setSuperficie(null);
                                        gestoraRegalos.actualizarRegaloCriaturita(regalo);
                                    }else{
                                        System.out.println("El regalo ya está asignado a esa Criaturita");
                                    }
                                }
                            }//Fin si no queria salir
                            
                            tipoSalida=0;
                break;
                
                
                case "2"://INSERTAR NUEVA CRIATURITA CON DOS NUEVOS REGALOS
                        
                            //LEER Y VALIDAR NOMBRE CRIATURITA
                    
                            //CONSULTAR ID DE LA ULTIMA CRIATURITA INTRODUCIDA
                    
                            //ASIGNAR ID AUMENTADO EN 1 A LA CRIATURITA
                    
                            //INSERTAR CRIATURITA
                    
                            //LEER Y VALIDAR DATOS REGALOS
                            
                            //INSERTAR REGALOS
                break;
                
                
                case "3"://INSERTAR NUEVO REGALO Y ASIGNAR A CRIATURITA EXISTENTE
                                //LEER Y VALIDAR DATOS DEL REGALO
                                regalo=new Regalos();
                                
                                //Denominacion, Edad Minima, Precio Obligatorios
                                do{
                                    System.out.println("Introduzca el nombre del regalo");
                                    try{
                                        regalo.setDenominacion(br.readLine());
                                    }catch(ExcepcionRegalos error){
                                        tipoSalida=0;
                                        System.out.println(error);
                                    }catch(IOException e){
                                        System.out.println(e.getMessage());
                                    }                                           
                                }while(tipoSalida==0);
                                
                                System.out.println("Introduzca la edad mínima");
                                
                                System.out.println("Introduzca el precio");
                                
                                
                                //Alto, Ancho, Largo Opcionales (Pero si pone uno de ellos debe ponerlos todos)
                    
                                //MOSTRAR CRIATURITAS LEER Y VALIDAR IDCRIATURITA
                                do{
                                    System.out.println("Introduzca el ID de la Criaturita que quiere eliminar\n");
                                    manejadorCriaturitas.listaCriaturitas(manejadorCriaturitas.getCriaturitas());
                                    try {
                                        idCriaturita=br.readLine();
                                        idCriaturitaParseado=gestoraMain.parseStringToShort(idCriaturita);
                                        if(idCriaturitaParseado!=null){
                                            criaturita=manejadorCriaturitas.recuperar(idCriaturitaParseado);
                                        }
                                        if(criaturita==null){
                                            do{
                                                System.out.println("No se encontro una Criaturita con ese id \n ?Quiere volver a introducirlo? S/N");
                                                respuesta=br.readLine();
                                            }while(gestoraMain.validaSN(respuesta));//metodo

                                            if(respuesta.toLowerCase().charAt(0)=='n'){
                                                tipoSalida=1;
                                            }
                                        }//fin si no se encontro la Criaturita
                                        else{
                                            tipoSalida=2;
                                        }      
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }while(tipoSalida==0);
                                if(tipoSalida==2){


                                }
                                tipoSalida=0;
                break;
                
                   
                case "4"://BORRAR UNA CRIATURITA Y TODOS SUS REGALOS 
                            //MOSTRAR CRIATURITAS LEER Y VALIDAR IDCRIATURITA
                            do{
                                System.out.println("Introduzca el ID de la Criaturita que quiere eliminar\n");
                                manejadorCriaturitas.listaCriaturitas(manejadorCriaturitas.getCriaturitas());
                                try {
                                    idCriaturita=br.readLine();
                                    idCriaturitaParseado=gestoraMain.parseStringToShort(idCriaturita);
                                    if(idCriaturitaParseado!=null){
                                        criaturita=manejadorCriaturitas.recuperar(idCriaturitaParseado);
                                    }
                                    if(criaturita==null){
                                        do{
                                            System.out.println("No se encontro una Criaturita con ese id \n ?Quiere volver a introducirlo? S/N");
                                            respuesta=br.readLine();
                                        }while(gestoraMain.validaSN(respuesta));//metodo
                                        
                                        if(respuesta.toLowerCase().charAt(0)=='n'){
                                            tipoSalida=1;
                                        }
                                    }//fin si no se encontro la Criaturita
                                    else{
                                        tipoSalida=2;
                                    }      
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }while(tipoSalida==0);
                            if(tipoSalida==2){
                                //Se eliminará la criaturita y sus regalos
                                coleccionRegalos=criaturita.getRegalosCollection();
                                if(coleccionRegalos!=null && coleccionRegalos.size()>0){
                                    for(Regalos regaloTemp:coleccionRegalos){                                 
                                        
                                        gestoraRegalos.borrarRegalo(regaloTemp.getId());
                                    }
                                }
                                manejadorCriaturitas.borrar(criaturita.getId());
                            }
                            tipoSalida=0;
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
