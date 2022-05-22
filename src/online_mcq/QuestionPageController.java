/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_mcq;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import online_mcq.Server.QuestionDatabase;
import static online_mcq.Starting_code.stage;

/**
 * FXML Controller class
 *
 * @author SAIKAT
 */
public class QuestionPageController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private Label questionID;
    @FXML
    private Label option1;
    @FXML
    private Label option2;
    @FXML
    private Label option3;
    @FXML
    private Label option4;
    @FXML
    private CheckBox b1;
    @FXML
    private CheckBox b2;
    @FXML
    private CheckBox b4;
    @FXML
    private CheckBox b3;
    static ArrayList<QuestionDatabase> AllQuestion=new ArrayList<QuestionDatabase>();
     int i=-1;
    @FXML
    private Label resultlabel;
    static  int correct=0;
    static  int wrong=0;
    static  int AttemptNum=0;
    static int totalquestion=0;
    @FXML
    private Label questionNumber;
    @FXML
    private Label attempt;
    @FXML
    private Label timeUp;
    @FXML
    private AnchorPane viewResult;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            AllQuestion=(ArrayList<QuestionDatabase>) ClientConnection.ReadObject();
            totalquestion=AllQuestion.size();
            questionNumber.setText(totalquestion+"");
             getNextQuestion();
            
        } catch (IOException ex) {
            Logger.getLogger(QuestionPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
public void getNextQuestion()
{
    
   
             
           i++;
            questionID.setText(AllQuestion.get(i).getQID());
            question.setText(AllQuestion.get(i).getQuestion());
            option1.setText(AllQuestion.get(i).getOption1());
            option2.setText(AllQuestion.get(i).getOption2());
            
            option3.setText(AllQuestion.get(i).getOption3());
            option4.setText(AllQuestion.get(i).getOption4());
            attempt.setText(AttemptNum+"");
            
}

public void OptionTest(String selectedAns) throws IOException{
       
     attempt.setText(AttemptNum+"");
     if(AllQuestion.get(i).getCorrectAns().equals(selectedAns))
        {
            resultlabel.setText("Wow !!! Right Answer !!!");
            correct++;
        }
        else
        {
            resultlabel.setText("Answer was Wrong . Correct Answer: "+AllQuestion.get(i).getCorrectAns());
            wrong++;
        }
        if(i==totalquestion-1)
        {
            Parent root = FXMLLoader.load(getClass().getResource("ResultView.fxml"));
            viewResult.getChildren().add(root);
           StudentResult sr=new StudentResult(User_LoginController.loginer,AfterLoginController.course,totalquestion+"",AttemptNum+"",correct+"",wrong+"",correct+"","5");
           ClientConnection.SentObject(sr);
        }
}




    @FXML
    private void NextAction(ActionEvent event) {
            
              getNextQuestion();
              b1.setDisable(false);
              b2.setDisable(false);
              b3.setDisable(false);
              b4.setDisable(false);
              b1.setIndeterminate(false);
             
    }

    @FXML
    private void option1Action(ActionEvent event) throws IOException {
        b2.setDisable(true);
        b3.setDisable(true);
        b4.setDisable(true);
        AttemptNum++;
        
        String SA=option1.getText();
        OptionTest(SA);
        
    }

    @FXML
    private void option2Action(ActionEvent event) throws IOException {
        b1.setDisable(true);
        b3.setDisable(true);
        b4.setDisable(true);
        AttemptNum++;
       
        String SA=option2.getText();
        OptionTest(SA);
    }

    @FXML
    private void option4Action(ActionEvent event) throws IOException {
        b2.setDisable(true);
        b3.setDisable(true);
        b1.setDisable(true);
        AttemptNum++;
       
        String SA=option3.getText();
        OptionTest(SA);
    }

    @FXML
    private void option3Action(ActionEvent event) throws IOException {
         b1.setDisable(true);
        b2.setDisable(true);
        b4.setDisable(true);
        AttemptNum++;
       
        
        String SA=option4.getText();
        OptionTest(SA);
    }

    @FXML
    private void homeAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AfterLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
