/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_mcq.Server;

import java.io.IOException;
import online_mcq.Resgistration_Info;
import online_mcq.StudentLoginData;

/**
 *
 * @author SAIKAT
 */
public class LoginRequestHandler  {
    ServerInputOutput sio;
    LoginRequestHandler(ServerInputOutput sio) {
       this.sio=sio;
    }

    

    public void Action(Object obj) throws IOException {
        
        boolean isLogin=false;
        StudentLoginData sld = (StudentLoginData) obj;

        System.out.println("Before cheak in login username of entering :"+sld.getUsername());

        if (DatabaseAction.AlreadyRegisterList.isEmpty()) {
            sio.SentToClient("Login Failed.Please Register");
        } else {
            System.out.println("in LoginRequesthandler is Login cheak");
            for (Resgistration_Info reginfo : DatabaseAction.AlreadyRegisterList) {
                boolean Matching = reginfo.getUsername().equals(sld.getUsername()) && (reginfo.getPassword().equals(sld.getPassword()));
                System.out.println("Entering username: "+sld.getUsername());
                System.out.println("save cilo in registerlist a username: "+reginfo.getUsername());
                System.out.println("Entering password: "+sld.getPassword());
                System.out.println("save cilo in registerlist a password: "+reginfo.getPassword());
                System.out.println("bal");
                if (Matching) {
                     System.out.println("in matching");
                    isLogin=true;
                    
                    break;
                } 
                
            }
            
            if(isLogin)
            {
                  sio.SentToClient("Login Successful");
                 // Server.UserDataOutput.put(sld.getUsername(),WorkerThread.dos);
                  //Server.UserObjectOutput.put(sld.getUsername(),WorkerThread.oos);
                   System.out.println("Login successfull msg sent to client");        
                
            }
            else {
                    sio.SentToClient("Login Failed.Please Register");
                    System.out.println("Login failur");
                }
            
            
                   
                    
        }
    }

}
