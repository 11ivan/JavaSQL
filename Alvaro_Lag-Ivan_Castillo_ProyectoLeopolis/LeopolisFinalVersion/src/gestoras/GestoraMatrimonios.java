/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import leopolis2.Matrimonios;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author icastillo
 */

    
    // -- RESTRICCIONES Y FALLOS A PROBAR PARA INTRODUCIR EN EL LOG --
/*
    · Un matrimonio que incluya un id de Ciudadane que no existe
    · Un matrimonio que incluya un ciudadane que ya esté casade
    · Un matrimonio en el que uno de los contrayentes haya fallecido
    · Un matrimonio con una fecha futura
    · Un divorcio para un matrimonio que no existe
    · Un divorcio con una fecha anterior al matrimonio
    · Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
*/

public class GestoraMatrimonios {
    
    private Query consultaGetMatrimonioVigenteCiudadane;
    private Query consultaGetMatrimonio;
    private Session sesion;
    private final String ordenConsulta1="from Matrimonios where IDConyuge1 = :idCiudadane1 and Fecha_fin is null or IDConyuge2 = :idCiudadane2 and Fecha_fin is null";
    private final String ordenConsulta2="from Matrimonios where ID=:idMatrimonio";
    
    public GestoraMatrimonios(Session sesion){
        this.sesion=sesion;
        preparaConsultas();
    }    
    
    /*
    Propósito: Prepara las consultas necesarias 
    Precondiciones: No hay
    Entradas: No hay
    Salidas: No hay
    Postcondiciones: Se han preparado las consultas necesarias
    */
    public void preparaConsultas(){       
        consultaGetMatrimonioVigenteCiudadane = sesion.createQuery(ordenConsulta1);      
        consultaGetMatrimonio = sesion.createQuery(ordenConsulta2);
    }
    
    /*
    Propósito: Inserta un nuevo matrimonio en la base de datos.
    Precondiciones: Un matrimonio que incluya un id de Ciudadane que no existe
                    Un matrimonio que incluya un ciudadane que ya esté casade
                    Un matrimonio en el que uno de los contrayentes haya fallecido
                    Un matrimonio con una fecha futura
    Entradas: Un objeto Matrimonio
    Salidas: No hay
    Postcondiciones:Se ha insertado un nuevo matrimonio 
    */
    public void insertMatrimonio(Matrimonios matrimonios){
        Transaction tran;     
        tran = this.sesion.beginTransaction();           
        this.sesion.save(matrimonios);
        tran.commit();
    }
    
    /*
    Propósito: Actualiza la fecha de finalización de un matrimonio.
    Precondiciones: Un divorcio para un matrimonio que no existe
                    Un divorcio con una fecha anterior al matrimonio
                    Un divorcio para un matrimonio que ya estaba disuelto (divorcio anterior).
    Entradas: Un objeto Matrimonio
    Salidas: No hay
    Postcondiciones:
    */
    public void actualizarMatrimonio (Matrimonios matrimonios){
        Transaction tran;       
        tran = this.sesion.beginTransaction();
        this.sesion.update (matrimonios);
        tran.commit();
    }
    
    /*
    Propósito: Dado un id de Ciudadane devuelve el matrimonio sin fecha de fin asociado en la base de datos
    Precondiciones: El Ciudadane existe
    Entradas: Un Byte que es el ID del ciudadane
    Salidas: Un objeto Matrimonios
    Postcondiciones: El objeto Matrimonios será null si no existe
    */
    public Matrimonios getMatrimonioVigenteCiudadane(Byte idCiudadane){
        Matrimonios matrimonio=null;
        List<Matrimonios> listaMatrimonio;
        consultaGetMatrimonioVigenteCiudadane.setParameter("idCiudadane1", idCiudadane);
        consultaGetMatrimonioVigenteCiudadane.setParameter("idCiudadane2", idCiudadane);
        listaMatrimonio=consultaGetMatrimonioVigenteCiudadane.list();
        if(!listaMatrimonio.isEmpty()){
            matrimonio=listaMatrimonio.get(0);
        }
        return matrimonio;
    }
    
    /*
    Propósito: Dado un id de Matrimonio devuelve el matrimonio asociado en la base de datos
    Precondiciones: No hay
    Entradas: Un Byte que es el ID del Matrimonio
    Salidas: Un objeto Matrimonios
    Postcondiciones: El objeto Matrimonios será null si no existe
    */
    public Matrimonios getMatrimonio(Byte idMatrimonio){
        Matrimonios matrimonio=null;
        List<Matrimonios> listaMatrimonio;
        consultaGetMatrimonio.setParameter("idMatrimonio", idMatrimonio);
        listaMatrimonio=consultaGetMatrimonio.list();
        if(!listaMatrimonio.isEmpty()){
            matrimonio=listaMatrimonio.get(0);
        }
        return matrimonio;
    }
    
    
    
}
