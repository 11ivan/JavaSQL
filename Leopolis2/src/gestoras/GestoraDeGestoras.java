/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import generated.Asiento;
import generated.Ciudadane;
import generated.Errores;
import generated.Incidencia;
import java.util.ArrayList;
import java.util.List;
import leopolis2.Ciudadanes;
import leopolis2.Matrimonios;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author icastillo
 */
public class GestoraDeGestoras {
    
    private GestoraMatrimonios gestoraMatrimonios;
    private ArrayList<Incidencia> listaIncidencias;
    private GestoraErrores gestoraErrores;

    
    public GestoraDeGestoras() {
        this.gestoraMatrimonios = new GestoraMatrimonios();
        this.listaIncidencias=new ArrayList<>();
        this.gestoraErrores=new GestoraErrores();
    }
    
    
    public void actualizaDB(ArrayList<Asiento> listaAsientos){
                  
        for(int i=0;i<listaAsientos.size();i++){
            switch(listaAsientos.get(i).getTipo()){
                
                case "Matrimonio":
                    //Se inserta un nuevo matrimonio
                    
                    //Comprobar id's de los Ciudadane
                    //Sino existen se a?ade a la lista de incidencias
                    if(getCiudadane(listaAsientos.get(i).getCiudadane().get(0).getID())!=null ||
                       getCiudadane(listaAsientos.get(i).getCiudadane().get(1).getID())!=null){
                        addIncidencia(0, listaAsientos.get(i));
                    }                    
                        
                    //Comprobar si los Ciudadane ya están casade
                        //Si ya está casade se a?ade a la lista de incidencias
                        addIncidencia(1, listaAsientos.get(i));
                        
                    //Comprobar que no hayan fallecido
                        //Si ya ha fallecido se a?ade a la lista de incidencias
                        addIncidencia(2, listaAsientos.get(i));
                        
                    //Comprobar si es fecha futura
                        //Si aun no se han casado se a?ade a la lista de incidencias
                        addIncidencia(3, listaAsientos.get(i));
                        
                    //Si no se ha producido ninguna incidencia insertamos el matrimonio
                    insertMatrimonio(listaAsientos.get(i));
                    
                break;
                                
                case "Divorcio":
                    //Se actualiza la fecha de finalización del matrimonio                    
                    
                    //Un divorcio para un matrimonio que no existe
                    addIncidencia(4, listaAsientos.get(i));
                    
                    //Un divorcio con una fecha anterior al matrimonio
                    addIncidencia(5, listaAsientos.get(i));
                    
                    //Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
                    addIncidencia(6, listaAsientos.get(i));
                    
                    //Si no se ha producido ninguna incidencia actualizamos la fecha de finalización del matrimonio
                    
                break;
                
                case "Deceso":
                    
                break;
                
                case "Nacimiento":
                    
                break;

                
            }//Fin segun
        }//Fin para
        
        //Escribir las incidencias en LOG
        //gestoraErrores.guardarListaErrores(archivoXML, listaIncidencias);
    }
    
    /*
    Propósito: A?ade una incidencia a la lista de incidencias
    Precondiciones: No hay
    Entradas: Un entero que será el código de error para identificar el motivo de la incidencia,
              Un objeto Asiento que será el que se a?ada a la incidencia
    Salidas: No hay
    Postcondiciones: Se ha a?adido una incidencia a la lista de incidenacias
    */
    public void addIncidencia(int codError, Asiento asiento){
        Incidencia incidencia=new Incidencia();
        String motivoIncidencia="";
        incidencia.setAsiento(asiento);//A?adimos el asiento a la incidencia
        incidencia.setFecha("Fecha Actual");//!!Ponemos la fecha actual
        
        switch(codError){//Ponemos motivo de incidencia segun el codigo de error
            case 0:
                motivoIncidencia="El Ciudadane no existe";
            break;           
            case 1:
                motivoIncidencia="El Ciudadane ya está casade";
            break;           
            case 2:
                motivoIncidencia="El Ciudadane ha fallecido";
            break;            
            case 3:
                motivoIncidencia="Fecha posterior a la actual";
            break;        
            case 4:
                motivoIncidencia="El Matrimonio no existe";
            break;     
            case 5:
                motivoIncidencia="Fecha anterior a la actual";
            break;         
            case 6:
                motivoIncidencia="Ya estaban divorciados";
            break;          
            case 7:
                //motivoIncidencia="El Ciudadane no existe";
            break;           
            case 8:
                //motivoIncidencia="El Ciudadane no existe";
            break;
            case 9:
                //motivoIncidencia="El Ciudadane no existe";
            break;           
            case 10:
                //motivoIncidencia="El Ciudadane no existe";
            break;
            case 11:
                //motivoIncidencia="El Ciudadane no existe";
            break;          
            case 12:
                //motivoIncidencia="El Ciudadane no existe";
            break;
        }
        incidencia.setMotivo(motivoIncidencia);//a?adimos el motivo de la incidencia
        listaIncidencias.add(incidencia);//a?adimos la incidencia a la lista de incidencias
    }
    
    /*TESTEADO
    Propósito: Comprueba si un id de Ciudadane existe en la base de datos
    Precondiciones: No hay
    Entradas: Un entero que es el ID del ciudadane
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si el ciudadane existe y false sino
    */
    /*public boolean compruebaIdCiudadane(Byte idCiudadane){
        boolean existe=true;
        Query consulta;
        List<Ciudadane> listaCiudadanes;
        Session ses = HibernateUtil.getSessionFactory().openSession();//Mantener sesion abierta durante la actualizacion en vez de abrir y cerrar conexion en cada proceso
        String ordenConsulta ="from Ciudadanes where ID=:idCiudadane";//A poder ser enviar sesion abierta para realizar inserciones y actualizaciones
        consulta = ses.createQuery(ordenConsulta);//Tener las consultas preparadas en Constructor en vez de prepararlas en cada proceso
        consulta.setParameter("idCiudadane", idCiudadane);
        listaCiudadanes=consulta.list();
        if(listaCiudadanes.isEmpty()){
            existe=false;
        }
        ses.close();
        
        return existe;
    }*/
    
    /*TESTEADO
    Propósito: Dado un id devuelve el Ciudadane asociado en la base de datos
    Precondiciones: No hay
    Entradas: Un Byte que es el ID del ciudadane
    Salidas: Un Ciudadane
    Postcondiciones: El Ciudadane será null si no existe
    */
    public Ciudadanes getCiudadane(Byte idCiudadane){
        Ciudadanes ciudadane=null;
        Query consulta;
        List<Ciudadanes> listaCiudadanes;
        Session ses = HibernateUtil.getSessionFactory().openSession();//Mantener sesion abierta durante la actualizacion en vez de abrir y cerrar conexion en cada proceso
        String ordenConsulta ="from Ciudadanes where ID=:idCiudadane";//A poder ser enviar sesion abierta para realizar inserciones y actualizaciones
        consulta = ses.createQuery(ordenConsulta);//Tener las consultas preparadas en Constructor en vez de prepararlas en cada proceso
        consulta.setParameter("idCiudadane", idCiudadane);
        listaCiudadanes=consulta.list();
        if(listaCiudadanes.isEmpty()){
            ciudadane=listaCiudadanes.get(0);
        }
        ses.close();
        
        return ciudadane;
    }
    /*
    Propósito: 
    Precondiciones: 
    Entradas: 
    Salidas: 
    Postcondiciones: 
    */
    public void insertMatrimonio(Asiento asiento){
        Matrimonios matrimonios=new Matrimonios();
        //Ciudadanes ciudadanes1=new Ciudadanes();
        
        //matrimonios.setId();  Obtener último id e incrmentar en 1
        
        matrimonios.setIDConyuge1(getCiudadane(asiento.getCiudadane().get(0).getID()));//Obtener Ciudadanes mediante consulta
        matrimonios.setIDConyuge2(getCiudadane(asiento.getCiudadane().get(1).getID()));
        
        gestoraMatrimonios.insertMatrimonio(matrimonios);
    }
    
}
