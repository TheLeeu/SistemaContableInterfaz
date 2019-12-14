package Modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private final String base = "sistema_contable";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base+"";
    private Connection con = null;
    public ResultSet rs = null;
    
    

    public Connection getConexion() {

        
            if (this.con == null) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
                    
                    
                } catch (SQLException e) {
                    System.err.println(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        
        return con;
    }

    //Devuelve un result set con todos los datos de la consulta que hallamos requerido
    public ResultSet Consulta(String query, Connection conex) {
        //IMPORTANTE CERRAR RESULT SET LUEGO DE USAR ESTA FUNCION
        try {

            //creamos un estado de conexion
            Statement st = conex.createStatement();
            //Con dicho estado ejectuamos un query y capturamos los resultados en rs
            ResultSet res = st.executeQuery(query);

            /*
             this.rs = res;
            
             rs.close();
             conex.close();
             */
            return res;

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void Ejecutar(String query) { //Ejecuta una query (que no sea consulta) con el comando ingresado

        try {
            //Nueva conexion
            Conexion cone = new Conexion();

            //creamos un estado de conexion
            Statement st = cone.getConexion().createStatement();

            //Guardamos los datos con la query requerida
            st.execute(query);

            st.close();
            cone.close();

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void close() {

        try {
            this.con.close();
            this.con = null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
