/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class ControladorTipoPrecio {
    
    static Conexion cn ;
    static ResultSet rs;
    
    public void AgregarTipoPrecio(TipoPrecio tipo){
        
    }
    public void EliminarTipoPrecio(int idTipoPrecio){
        
    }
    public void ModificarTipoPrecio( TipoPrecio cambios){
        
    }
    public TipoPrecio Buscar (int idTipoPrecio){
        TipoPrecio precio = new TipoPrecio();
        return precio;
        
    }
    public static ArrayList<TipoPrecio> ObtenerTodos() throws ErrorTienda{
        ArrayList<Object> precios = new ArrayList<Object>();
        cn = new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT * FROM TipoPrecio");
            while(rs.next()){
            precios.add(rs.getString(1));
            precios.add(rs.getString(2));
            precios.add(rs.getString(3));
            }
            
        } catch (Exception e) {
        }
        ArrayList<TipoPrecio> misPrecios = (ArrayList) precios;
        return misPrecios;
    }
}
