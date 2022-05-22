/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_mcq.Server;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


public class Server_HomepageController implements Initializable {

    /**
     * Initializes the controller class.
     */
     static Connection databaseConnection;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         try {
             databaseConnection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/serverdatabase","root","");
             // TODO
         } catch (SQLException ex) {
             Logger.getLogger(Server_HomepageController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
}
