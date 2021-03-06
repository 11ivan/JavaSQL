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
import java.io.File;
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
 * @author alag
 */
public class GestoraDeGestoras {
    
    private GestoraMatrimonios gestoraMatrimonios;
    private ArrayList<Incidencia> listaIncidencias;
    private GestoraErrores gestoraErrores;
    private Session sesion;
    private Query consultaGetCiudadane;
    

    
    public GestoraDeGestoras() {
        //Abrimos la conexion con la base de datos LA ABRIMOS AQU� PORQUE LA NECESITO PARA ENVIARSELA A LA GESTORA DE MATRIMONIOS
        this.sesion = HibernateUtil.getSessionFactory().openSession();
        this.gestoraMatrimonios = new GestoraMatrimonios(sesion);
        this.listaIncidencias=new ArrayList<>();
        this.gestoraErrores=new GestoraErrores();  
        //Preparamos las consultas necesarias
        preparaConsultas();
    }
      
    /*
    Prop�sito: Prepara las consultas necesarias 
    Precondiciones: No hay
    Entradas: No hay
    Salidas: No hay
    Postcondiciones: Se han preparado las consultas necesarias
    */
    private void preparaConsultas(){             
        String ordenConsulta ="from Ciudadanes where ID=:idCiudadane";
        consultaGetCiudadane = sesion.createQuery(ordenConsulta);
    }
    
    /*
    Prop�sito:  
    Precondiciones: 
    Entradas:
    Salidas: 
    Postcondiciones: 
    */
    public void actualizaDB(ArrayList<Asiento> listaAsientos){    
        
        for(int i=0;i<listaAsientos.size();i++){
            switch(listaAsientos.get(i).getTipo()){
                
                case "Matrimonio":
                    //Gestionamos el Matrimonio
                    gestionaMatrimonio(listaAsientos.get(i));                  
                break;
                                
                case "Divorcio":      
                    //Gestionamos el Divorcio
                    gestionaDivorcio(listaAsientos.get(i));                                  
                break;
                
                case "Deceso":
                    
                break;
                
                case "Nacimiento":
                    
                break;

                
            }//Fin segun
        }//Fin para
        
        //Cerramos la conexion con la base de datos
        sesion.close();
        
        //Escribir las incidencias en LOG
        File archivoXML=new File("LOG.xml");
        gestoraErrores.guardarListaErrores(archivoXML, listaIncidencias);
    }
    
    /*
    Prop�sito: A?ade una incidencia a la lista de incidencias
    Precondiciones: No hay
    Entradas: Un entero que ser� el c�digo de error para identificar el motivo de la incidencia,
              Un objeto Asiento que ser� el que se a?ada a la incidencia
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
                motivoIncidencia="El Ciudadane ya est� casade";
            break;           
            case 2:
                motivoIncidencia="El Ciudadane ya ha fallecido";
            break;            
            case 3:
                motivoIncidencia="Fecha posterior a la actual";
            break;        
            case 4:
                motivoIncidencia="El Matrimonio no existe";
            break;     
            case 5:
                motivoIncidencia="Fecha anterior a la que se casaron";
            break;         
            case 6:
                motivoIncidencia="Ya estaban divorciados";
            break;          
            case 7:
                motivoIncidencia="El Ciudadane ya existe";
            break;                      
        }
        incidencia.setMotivo(motivoIncidencia);//a?adimos el motivo de la incidencia
        listaIncidencias.add(incidencia);//a?adimos la incidencia a la lista de incidencias
    }
    
    
    
    /*TESTEADO
    Prop�sito: Comprueba si un id de Ciudadane existe en la base de datos
    Precondiciones: No hay
    Entradas: Un entero que es el ID del ciudadane
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si el ciudadane existe y false sino
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
    
    
    /*
    Prop�sito: 
    Precondiciones: 
    Entradas: 
    Salidas: 
    Postcondiciones: 
    */
    public void gestionaMatrimonio(Asiento asiento){
        Ciudadanes ciudadanes1=null;
        Ciudadanes ciudadanes2=null;
        ciudadanes1=getCiudadane(asiento.getCiudadane().get(0).getID());
        ciudadanes2=getCiudadane(asiento.getCiudadane().get(1).getID());

        //Comprobar id's de los Ciudadane
        //Sino existen se a?ade a la lista de incidencias
        if(ciudadanes1==null || ciudadanes2==null){
            addIncidencia(0, asiento);
        }else if( gestoraMatrimonios.getMatrimonioVigenteCiudadane(Byte.parseByte(String.valueOf(ciudadanes1.getId()))) !=null ||
                  gestoraMatrimonios.getMatrimonioVigenteCiudadane(Byte.parseByte(String.valueOf(ciudadanes2.getId()))) !=null){
            //Comprobar si los Ciudadane ya est�n casade
            //Si ya est� casade se a?ade a la lista de incidencias
            addIncidencia(1, asiento);                     
        }else if(ciudadanesFallecido(ciudadanes1) || ciudadanesFallecido(ciudadanes2)){
            //Comprobar que no hayan fallecido
            //Si ya ha fallecido se a?ade a la lista de incidencias
            addIncidencia(2, asiento);
        }else if(compruebaFechaFutura(asiento.getFecha())){   
            //Comprobar si es fecha futura
            //Si aun no se han casado se a?ade a la lista de incidencias
            addIncidencia(3, asiento);
        }else{
            insertMatrimonio(asiento);
        }
    }
    
    /*
    Prop�sito: 
    Precondiciones: 
    Entradas: 
    Salidas: 
    Postcondiciones: 
    */
    public void gestionaDivorcio(Asiento asiento){
        Matrimonios matrimonio=null;
        matrimonio=gestoraMatrimonios.getMatrimonio(asiento.getMatrimonio());
        if(matrimonio==null){
            //Un divorcio para un matrimonio que no existe
            addIncidencia(4, asiento);
        }else if(!compruebaFechaFutura(asiento.getFecha(), matrimonio.getFechamatrimonio())){
            //Un divorcio con una fecha anterior al matrimonio
            addIncidencia(5, asiento);
        }else if(matrimonio.getFechafin()!=null){
            //Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
            addIncidencia(6, asiento);
        }else{
            //Si no se ha producido ninguna incidencia actualizamos la fecha de finalizaci�n del matrimonio
            actualizaFechaFinalizacionMatrimonio(matrimonio, asiento.getFecha());
        }   
    }

    /*
    Prop�sito: Actualiza la fecha de finalizacion de un matrimonio de la base de datos
    Precondiciones: El matrimonio existe y aun no tiene fecha de fin
    Entradas: Un objeto Matrimonios y una cadena que ser� la fecha en formato dd/MM/yyyy
    Salidas: No hay
    Postcondiciones: Se ha actualizado la fecha de finalizacion del matrimonio
    */
    public void actualizaFechaFinalizacionMatrimonio(Matrimonios matrimonio, String fechaFinalizacion){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = null;
        try {
            parsed = dateFormat.parse(fechaFinalizacion);
            matrimonio.setFechamatrimonio(new java.sql.Date(parsed.getTime()));
            gestoraMatrimonios.actualizarMatrimonio(matrimonio);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    
    /*TESTEADO
    Prop�sito: Dado un id devuelve el Ciudadane asociado en la base de datos
    Precondiciones: No hay
    Entradas: Un Byte que es el ID del ciudadane
    Salidas: Un Ciudadane
    Postcondiciones: El Ciudadane ser� null si no existe
    */
    public Ciudadanes getCiudadane(Byte idCiudadane){
        Ciudadanes ciudadane=null;
        //Query consulta;
        List<Ciudadanes> listaCiudadanes;
        //Session ses = HibernateUtil.getSessionFactory().openSession();//Mantener sesion abierta durante la actualizacion en vez de abrir y cerrar conexion en cada proceso
        //String ordenConsulta ="from Ciudadanes where ID=:idCiudadane";//A poder ser enviar sesion abierta para realizar inserciones y actualizaciones
        //consulta = sesion.createQuery(ordenConsulta);//Tener las consultas preparadas en Constructor en vez de prepararlas en cada proceso
        consultaGetCiudadane.setParameter("idCiudadane", idCiudadane);
        listaCiudadanes=consultaGetCiudadane.list();
        if(!listaCiudadanes.isEmpty()){
            ciudadane=listaCiudadanes.get(0);
        }
        //ses.close();        
        return ciudadane;
    }
    
    
    /*
    Prop�sito: Inserta un nuevo matrimonio en la base de datos.
    Precondiciones: Los id de Ciudadane deben existir
                    Los Ciudadane no deben estar casados
                    Los Ciudadane no deben estar fallecidos
                    La fecha del matrimonio debe ser menor o igual a la actual
    Entradas: Un objeto Asiento
    Salidas: No hay
    Postcondiciones:Se ha insertado un nuevo matrimonio 
    */
    public void insertMatrimonio(Asiento asiento){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = null;
        Matrimonios matrimonios=new Matrimonios();
        
        //Metodo para obtener �ltimo id e incrmentar en 1
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
    Prop�sito: Comprueba si una fecha es mayor a la actual
    Precondiciones: No hay
    Entradas: Una cadena que ser� la fecha en formato dd/MM/yyyy
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si la fecha es mayor a la actual y false sino
    */
    public boolean compruebaFechaFutura(String fechaCadena){
        boolean esMayor=false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = null;  
        Calendar fechaActual = Calendar.getInstance();  
        java.util.Date actual = new java.sql.Date(fechaActual.getTimeInMillis()); 
        int comparacion=0;
        
        try {
            parsed = dateFormat.parse(fechaCadena);           
            comparacion=actual.compareTo(parsed);//Comparamos la fecha actual con la parseada            
            //Si la fecha actual es anterior a la parseada
            if(comparacion==-1){
                esMayor=true;
            }           
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }             
        return esMayor;
    }
    
    /*
    Prop�sito: Comprueba si una fecha es mayor a la fecha del matrimonio
    Precondiciones: No hay
    Entradas: Una cadena que ser� la fecha en formato dd/MM/yyyy
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si la fecha es mayor a la fecha del matrimonio y false sino
    */
    public boolean compruebaFechaFutura(String fechaCadena, Date fechaMatrimonio){
        boolean esMayor=false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = null;  
        java.util.Date matrimonio = new java.sql.Date(fechaMatrimonio.getTime()); 
        int comparacion=0;
        
        try {
            parsed = dateFormat.parse(fechaCadena);           
            comparacion=matrimonio.compareTo(parsed);//Comparamos la fecha de inicio del matrimonio con la parseada            
            //Si la fecha actual es anterior a la parseada
            if(comparacion==-1){
                esMayor=true;
            }           
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }             
        return esMayor;
    }
    
    /*
    Prop�sito: Comprueba si un Ciudadanes ha fallecido
    Precondiciones: El ciudadane debe existir
    Entradas: Un objeto Ciudadanes
    Salidas: Un booleano
    Postcondiciones: El booleano ser� verdadero si el Ciudadanes ha fallecido y false sino
    */
    public boolean ciudadanesFallecido(Ciudadanes ciudadanes){
        boolean fallecido=false;
        fallecido = (ciudadanes.getFechamuerte()!=null) ? true : false;
        return fallecido;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

