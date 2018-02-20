/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author icastillo
 */
public class GestoraMatrimonios {
    
    //RESTRICCIONES Y FALLOS A PROBAR PARA INTRODUCIR EN EL LOG
/*
Un matrimonio que incluya un id de Ciudadane que no existe
∑
Un matrimonio que incluya un ciudadane que ya estÈ casade
∑
Un matrimonio en el que uno de los contrayentes haya fallecido
∑
Un matrimonio con una fecha futura
∑
Un divorcio para un matrimonio que no existe
*/
    
    /*
    PropÛsito: Inserta un nuevo matrimonio en la base de datos.
    Precondiciones:
    Entradas:
    Salidas:
    Postcondiciones:
    */
    
    
    
    
    
    
    /*  public void crearRegalo (Regalos regalo){
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();    
        regalo.setId(getSiguienteID());
        
        // Al ejecutar el m√©todo save el objeto se convierte en persistente
        ses.save(regalo);
        tran.commit();
        ses.close();
    }
        
    public void actualizarRegaloCriaturita (Regalos regalo){
        //Regalos regaloActualizado;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        ses.update (regalo);
        tran.commit();
        ses.close();
    }
    
    public Short getSiguienteID(){
        Short id=-1;
        Query consulta;
        List<Regalos> listaRegalos;
        // No necesitamos datos de la conexion porque ya est√°n definidos en el hibernate.cfg.xml
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
        // No necesitamos datos de la conexion porque ya est√°n definidos en el hibernate.cfg.xml
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
        nombrePropietario = (c.getGoesTo()==null) ? "No tiene" : c.getGoesTo().getNombre();
        cad = "ID: "+c.getId() + "  Denominacion: " + c.getDenominacion() + "   Propietario: " +  nombrePropietario ;
        return cad;
    }
    
    */
    
    
}
