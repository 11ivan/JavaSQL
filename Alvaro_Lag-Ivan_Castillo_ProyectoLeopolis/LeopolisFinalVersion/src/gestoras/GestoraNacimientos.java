/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import java.util.List;
import leopolis2.Ciudadanes;
import leopolis2.Pastas;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alag
 */
public class GestoraNacimientos {
    
    private Session sesion;
    private Query consultaGetPasta;
    
    private final String ordenConsulta="from Pastas where NombrePasta=:Nombre";
    
    public GestoraNacimientos(Session sesion){
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
        consultaGetPasta = sesion.createQuery(ordenConsulta);      
        
    }
    /**
    * Interfaz:
    * Este método inserta un nacimiento
    * Precondiciones: El nuevo ciudadane no puede tener un ID ya existente, no puede tener un tipo de pasta
    *                 favorita inexistente, no puede tener una fecha de nacimiento futura, no puede tener
    *                 un padre o madre con ID inexistente.
    * Entradas: un ciudadano
    * Salidas: nada
    * Postcondiciones: nada
    * Restricciones: nada
    */
    public void insertarNacimiento(Ciudadanes c){
        Transaction tran;
        tran = this.sesion.beginTransaction();
        this.sesion.save(c);
        tran.commit();
        
    }
    
    /**
     * Comentario:
     * Este método comprueba si existe un tipo de pasta
     * Precondiciones: nada
     * Entradas: cadena nombre de pasta
     * Salidas: booleano
     * Postcondiciones: true si existe en la base de datos, false si no
    */
    
    public boolean compruebaPastaFavoritaExistente(String nombrePasta){
        List<Pastas> listaPastas;
        
        boolean existe = false;
        consultaGetPasta.setParameter("Nombre", nombrePasta);
        listaPastas=consultaGetPasta.list();
        if(!listaPastas.isEmpty()){
            existe = true;
        }
        
        return existe;
    }
    
}
