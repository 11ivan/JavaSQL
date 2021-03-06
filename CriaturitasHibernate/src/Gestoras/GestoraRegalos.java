/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestoras;

import Clases.Regalos;
import Util.HibernateUtil;
import exceptions.ExcepcionRegalos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author icastillo
 */
public class GestoraRegalos {
    
        public void crearRegalo (Regalos regalo){
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();    
        regalo.setId(getSiguienteID());
        
        // Al ejecutar el método save el objeto se convierte en persistente
        ses.save(regalo);
        tran.commit();
        ses.close();
    }
        
    public void actualizarRegaloCriaturita (Regalos regalo){
        //Regalos regaloActualizado;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        //regaloActualizado = new Regalos (regalo.getId());     
        //regaloActualizado.setGoesTo(regalo.getGoesTo());
        //try {
            //regaloActualizado.setAlto(regalo.getAlto());
            //regaloActualizado.setAncho(regalo.getAncho());
            //regaloActualizado.setDenominacion(regalo.getDenominacion());
        /*} catch (ExcepcionRegalos ex) {
            Logger.getLogger(GestoraRegalos.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //regaloActualizado.setEdadMinima(regalo.getEdadMinima());
        //regaloActualizado.setLargo(regalo.getLargo());
        //regaloActualizado.setPrecio(regalo.getPrecio());
        //regaloActualizado.setTipo(regalo.getTipo());
        //regaloActualizado.setSuperficie(null);
        ses.update (regalo);
        tran.commit();
        ses.close();
    }
    
    public Short getSiguienteID(){
        Short id=-1;
        Query consulta;
        List<Regalos> listaRegalos;
        // No necesitamos datos de la conexion porque ya están definidos en el hibernate.cfg.xml
        Session ses = HibernateUtil.getSessionFactory().openSession();
        String ordenConsulta ="from Regalos";
        consulta = ses.createQuery(ordenConsulta);
        listaRegalos=consulta.list();
        id=listaRegalos.get(listaRegalos.size()-1).getId();
        ses.close();
        id++;
        return id;
    }
    
    public void borrarRegalo (short id){
        Regalos regalo;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        regalo = new Regalos (id);
        ses.delete (regalo);
        tran.commit();
        ses.close();
    }
    
    public void borrarRegalo (Regalos regalo){
        //Regalos regalo;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        //regalo = new Regalos (id);
        ses.delete (regalo);
        tran.commit();
        ses.close();
    }
    
    public Regalos getRegalo (short id){
        Regalos regalo;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        regalo = (Regalos)ses.get(Regalos.class, id);
        ses.close();
        return regalo;
    }
    
    public List<Regalos> getListaRegalos(){
        Query consulta;
        List<Regalos> todasCria;
        // No necesitamos datos de la conexion porque ya están definidos en el hibernate.cfg.xml
        Session ses = HibernateUtil.getSessionFactory().openSession();
        String ordenConsulta ="from Regalos";
        consulta = ses.createQuery(ordenConsulta);
        todasCria=consulta.list();
        ses.close();
        return todasCria;
    }
    
    public void muestraListaRegalos (){
        List<Regalos> lista=this.getListaRegalos();
        Session ses = HibernateUtil.getSessionFactory().openSession();
        for (Regalos actual:lista){
            // El objeto es detached y con esto pasa a persistent
            ses.update(actual);
            System.out.println(cadenaRegalo(actual));
        }
        ses.close();
    }
    
    public String cadenaRegalo (Regalos c){
        String cad="";
        String nombrePropietario="";//max = (a > b) ? a : b;
        //Short idCriaturitaRecogido=c.getGoesTo().getId();
        //nombrePropietario = (idCriaturitaRecogido==null) ? "No tiene" : c.getGoesTo().getNombre();
        nombrePropietario = (c.getGoesTo()==null) ? "No tiene" : c.getGoesTo().getNombre();
        cad = "ID: "+c.getId() + "  Denominacion: " + c.getDenominacion() + "   Propietario: " +  nombrePropietario ;
        return cad;
    }
    
}
