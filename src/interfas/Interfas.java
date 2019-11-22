/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfas;

import Forms.NuevoLibro;
import Forms.Principal;
import Modelo.Conexion;
import Modelo.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class Interfas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("SELECT * FROM `librodiario` ", c.getConexion());
        try {
            if (rs.next()) {
                Principal f = new Principal();
                f.setVisible(true);
                f.setLocationRelativeTo(null);
            } else {

                NuevoLibro nv = new NuevoLibro();
                nv.setVisible(true);
                nv.setLocationRelativeTo(null);
                

            }
            rs.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
