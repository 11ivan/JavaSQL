/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Mascotas;


/**
 *
 * @author icastillo
 */
public class Main {
    public static void main(String[] args){
        GestoraConsultas gestoraConsultas=new GestoraConsultas();
        
        try{
            gestoraConsultas.getGestoraConexion().connect();
            gestoraConsultas.routerXD();
            gestoraConsultas.getGestoraConexion().close();       
            System.out.println("Si ha llegado aqu� es por que est� bien. Fiuuu");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
