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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        boolean error;
        
        for(int i=0;i<listaAsientos.size();i++){
            error=false;
            switch(listaAsientos.get(i).getTipo()){
                
                case "Matrimonio":
                    //Se inserta un nuevo matrimonio
                    
                    //Comprobar id's de los Ciudadane
                    //Sino existen se a?ade a la lista de incidencias
                    if(getCiudadane(listaAsientos.get(i).getCiudadane().get(0).getID())!=null ||
                       getCiudadane(listaAsientos.get(i).getCiudadane().get(1).getID())!=null){
                        addIncidencia(0, listaAsientos.get(i));
                        error=true;
                    }                    
                        
                    //Comprobar si los Ciudadane ya están casade
                    //Si ya está casade se a?ade a la lista de incidencias
                        addIncidencia(1, listaAsientos.get(i));
                        
                    //Comprobar que no hayan fallecido
                    //Si ya ha fallecido se a?ade a la lista de incidencias
                        addIncidencia(2, listaAsientos.get(i));
                        
                    //Comprobar si es fecha futura
                    //Si aun no se han casado se a?ade a la lista de incidencias
                    if(!compruebaFechaFutura(listaAsientos.get(i).getFecha())){                      
                        addIncidencia(3, listaAsientos.get(i));
                        error=true;
                    }
                    
                    //Si no se ha producido ninguna incidencia insertamos el matrimonio
                    if(!error){
                        insertMatrimonio(listaAsientos.get(i));
                    }
                    
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
    Propósito: Inserta un nuevo matrimonio en la base de datos.
    Precondiciones: Los id de Ciudadane deben existir
                    Los Ciudadane no deben estar casados
                    Los Ciudadane no deben estar fallecidos
                    La fecha del matrimonio debe ser menor o igual a la actual
    Entradas: Un objeto Asiento
    Salidas: No hay
    Postcondiciones:Se ha insertado un nuevo matrimonio 
    */
    public void insertMatrimonio(Asiento asiento){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = null;
        Matrimonios matrimonios=new Matrimonios();
        
        //Metodo para obtener último id e incrmentar en 1
        //matrimonios.setId();  
        try {
            parsed = dateFormat.parse(asiento.getFecha());
            matrimonios.setFechamatrimonio(new java.sql.Date(parsed.getTime()));
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        matrimonios.setIDConyuge1(getCiudadane(asiento.getCiudadane().get(0).getID()));//Obtener Ciudadanes mediante consulta
        matrimonios.setIDConyuge2(getCiudadane(asiento.getCiudadane().get(1).getID()));
        
        gestoraMatrimonios.insertMatrimonio(matrimonios);
    }
    
    /*
    Propósito: Comprueba si una fecha es mayor a la actual
    Precondiciones: No hay
    Entradas: Una cadena que será la fecha en formato dd-MM-yyyy
    Salidas: Un booleano
    Postcondiciones: El booleano será verdadero si la fecha es menor o igual a la actual y false sino
    */
    public boolean compruebaFechaFutura(String fechaCadena){
        boolean vale=true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = null;  
        Calendar fechaActual = Calendar.getInstance();  
        java.util.Date actual = new java.sql.Date(fechaActual.getTimeInMillis()); 
        int comparacion=0;
        
        try {
            parsed = dateFormat.parse(fechaCadena);           
            comparacion=actual.compareTo(parsed);//Comparamos la fecha actual con la parseada
            
            //Si la fecha actual no es anterior o igual a la parseada
            if(comparacion==-1){
                vale=false;
            }
            
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }             
        return vale;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

