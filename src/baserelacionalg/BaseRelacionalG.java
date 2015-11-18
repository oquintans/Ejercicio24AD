/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalg;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalG {

    Connection conn;
    CallableStatement cS;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseRelacionalG brG = new BaseRelacionalG();
        brG.connection();
        brG.procedure("p2");

    }

    public void connection() {
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion Establecida.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void procedure(String a) {
        try {
            cS = conn.prepareCall("{? = call prezo_produto (?)}");
            cS.setString(2, a);
            cS.registerOutParameter(1, Types.INTEGER);
            cS.execute();
            System.out.println("Cod: " + a);
            System.out.println("Prezo: " + cS.getInt(1));
            cS.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
