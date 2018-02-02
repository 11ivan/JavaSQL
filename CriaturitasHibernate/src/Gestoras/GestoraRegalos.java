/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestoras;

import Clases.Regalos;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author icastillo
 */
public class GestoraRegalos {
    
        public void crearCriaturita (String nombre, short id){
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        Regalos nene = new Regalos();
        //nene.setNombre(nombre);
        nene.setId(id);
		// Al ejecutar el método save el objeto se convierte en persistente
        ses.save(nene);
        tran.commit();
        ses.close();
    }
    public void cambiarNombre (String nombre, short id){
        Regalos nene;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        nene = new Regalos (id);
        //nene.setNombre (nombre);
        ses.update (nene);
        tran.commit();
        ses.close();
    }
    public void borrar (short id){
        Regalos nene;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        nene = new Regalos (id);
        ses.delete (nene);
        tran.commit();
        ses.close();
    }
    public Regalos recuperar (short id){
        Regalos nene;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        nene = (Regalos)ses.get(Regalos.class, id);
        ses.close();
        return nene;
    }
    public List<Regalos> getCriaturitas(){
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
    public void listaCriaturitas (List<Regalos> lista){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        for (Regalos actual:lista){
            // El objeto es detached y con esto pasa a persistent
            ses.update(actual);
            System.out.println(cadenaCriaturita(actual));
        }
        ses.close();
    }
    public String cadenaCriaturita (Regalos c){
        String cad="";
        //cad = "ID: "+c.getId() + "  Nombre: " + c.getNombre();
        return cad;
    }
    
}
