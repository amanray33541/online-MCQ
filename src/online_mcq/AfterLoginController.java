/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_mcq;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import static online_mcq.Starting_code.stage;

/**
 * FXML Controller class
 *
 * @author SAIKAT
 */
public class AfterLoginController implements Initializable {

    @FXML
    private AnchorPane profilepageCenter;
    static String course;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ProfileButtonAction(ActionEvent event) throws IOException, InterruptedException {
        String str="S"+"##"+User_LoginController.loginer;
        String strobj=new String(str);
        ClientConnection.SentObject(strobj);
        Thread.sleep(500);
         Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        
         Scene scene = new Scene(root);
        
         stage.setScene(scene);
         stage.show();
        
        
        
        
    }

    @FXML
    private void CppAction(ActionEvent event) {
        course="C++";
        profilepageCenter.getChildren().clear();
    }

    @FXML
    private void JavaAction(ActionEvent event) {
        course="JAVA";
        profilepageCenter.getChildren().clear();
    }

    @FXML
    private void startExamAction(ActionEvent event) throws IOException {
        profilepageCenter.getChildren().clear();
        String com="E"+"##"+course;
        if(course!=null)
        {
        ClientConnection.SentObject(com);
        Parent root = FXMLLoader.load(getClass().getResource("QuestionPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        }
        
        
    }
    

    @FXML
    private void LogoutAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
}
