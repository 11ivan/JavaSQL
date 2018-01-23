/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateexample;

import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author icastillo
 */
public class HibernateExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
     SessionFactory instancia = HibernateUtil.buildSessionFactory();
        Session session = instancia.openSession();
        Query q = session.createQuery("from BIClientes");
 
        Iterator<BIClientes> it = q.iterate();
        BIClientes e;
        
        while (it.hasNext()) {
            e = it.next();
            System.out.println(e.getNombre()+ " " + e.getDireccion());
        }
 
        session.close();
    }   
        
}
