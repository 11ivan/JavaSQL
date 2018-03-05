/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import generated.Asiento;
import leopolis2.Ciudadanes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alag
 */
public class GestoraDecesos {
    
    private Session sesion;
    
    public GestoraDecesos(Session sesion){
        this.sesion=sesion;
        
    } 
    /**
    * Interfaz:
    * Este método actualiza la fecha de fallecimiento de un ciudadane
    * Precondiciones: El ciudadano no puede estar muerto ya y tiene que tener un ID existente.
    * Entradas: un ciudadano
    * Salidas: nada
    * Postcondiciones: nada
    * Restricciones: nada
    */
    public void actualizarFechaMuerte(Ciudadanes c){
        Transaction tran;
        
        tran = this.sesion.beginTransaction();
        this.sesion.update(c);
        tran.commit();
        
    }
    
    
    
}
