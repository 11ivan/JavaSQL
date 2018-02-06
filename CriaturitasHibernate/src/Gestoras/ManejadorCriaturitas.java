package Gestoras;

import Clases.Criaturitas;
import Util.HibernateUtil;
import java.util.*;
import org.hibernate.*;

public class ManejadorCriaturitas {
    
    public void crearCriaturita (String nombre, short id){
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        Criaturitas nene = new Criaturitas();
        nene.setNombre(nombre);
        nene.setId(id);
        // Al ejecutar el método save el objeto se convierte en persistente
        ses.save(nene);
        tran.commit();
        ses.close();
    }
    
    public void cambiarNombre (String nombre, short id){
        Criaturitas nene;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        nene = new Criaturitas (id);
        nene.setNombre (nombre);
        ses.update (nene);
        tran.commit();
        ses.close();
    }
    
    public void borrar (short id){
        Criaturitas nene;
        Transaction tran;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        tran = ses.beginTransaction();
        nene = new Criaturitas (id);
        ses.delete (nene);
        tran.commit();
        ses.close();
    }
    
    public Criaturitas recuperar (short id){
        Criaturitas nene;
        Session ses = HibernateUtil.getSessionFactory().openSession();
        nene = (Criaturitas)ses.get(Criaturitas.class, id);
        ses.close();
        return nene;
    }
    
    public List<Criaturitas> getCriaturitas(){
        Query consulta;
        List<Criaturitas> todasCria;
        // No necesitamos datos de la conexion porque ya están definidos en el hibernate.cfg.xml
        Session ses = HibernateUtil.getSessionFactory().openSession();
        String ordenConsulta ="from Criaturitas";
        consulta = ses.createQuery(ordenConsulta);
        todasCria=consulta.list();
        ses.close();
        return todasCria;

    }
    
    public void listaCriaturitas (List<Criaturitas> lista){
        Session ses = HibernateUtil.getSessionFactory().openSession();
        System.out.println("ID\tNombre");
        for (Criaturitas actual:lista){
            // El objeto es detached y con esto pasa a persistent
            ses.update(actual);       
            System.out.println(cadenaCriaturita(actual));
        }
        ses.close();
    }
    
    public String cadenaCriaturita (Criaturitas c){
        String cad;
        cad = c.getId() +"\t"+ c.getNombre();
        return cad;
    }

}
