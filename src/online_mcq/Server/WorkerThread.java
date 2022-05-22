       
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online_mcq.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import online_mcq.QuestionSet;
import online_mcq.StudentLoginData;
import online_mcq.Resgistration_Info;
import online_mcq.StudentResult;


/**
 *
 * @author SAIKAT
 */
class WorkerThread implements Runnable{
    
   
  
    
     DataInputStream dis;
     DataOutputStream dos;
     ObjectInputStream ois;
     ObjectOutputStream oos;
     Socket ServerSideSocket;

    public WorkerThread(Socket s) throws SQLException {
        // RegisterList rl=new RegisterList();
       ServerSideSocket=s;
       
    }
    
   
    @Override
    public void run() {
       
        try {
            dis=new DataInputStream(ServerSideSocket.getInputStream());
             dos=new DataOutputStream(ServerSideSocket.getOutputStream());
             ois=new ObjectInputStream(ServerSideSocket.getInputStream());
              oos=new ObjectOutputStream(ServerSideSocket.getOutputStream());
              
             ServerInputOutput sio=new ServerInputOutput(dis, dos, ois, oos);
            
            
            while(true){
               DatabaseAction db=new DatabaseAction();
               DatabaseAction dba=new DatabaseAction(sio);
                
                 Object obj=ois.readObject();
             
                
                System.out.println("csts obj: "+obj);
               
                if(obj.equals(null))
                {
                    continue;
                }
                
                if(!(obj.equals(null)))
                {
                    System.out.println("yes obj is not null");
                    if(obj instanceof StudentLoginData)
                 {
                     System.out.println("in cheking for login");
                   StudentLoginData SLD=(StudentLoginData)obj;
                     System.out.println("before sending lrh Username: "+SLD.getUsername());
                   LoginRequestHandler LRH=new LoginRequestHandler(sio);
                   LRH.Action(SLD);
                 }
                    
                    
                 else if(obj instanceof Resgistration_Info)
                 {
                    
                   Resgistration_Info RIN=(Resgistration_Info)obj;
                   RegistrationRequestHandler RRH=new RegistrationRequestHandler(sio);
                   
                   RRH.Action(RIN);
                   
                 }
                 
                 
                 else if(obj instanceof StudentResult)  
               {
                   StudentResult sr=(StudentResult)obj;
                   DatabaseAction.ResultInsert(sr);
                   //marking apply here
               }
                 else if(obj instanceof QuestionSet)
                 {
                     QuestionSet QS=(QuestionSet)obj;
                     dba.QuestionInsert(QS);
                 }
                  
                 
                 
                 else if(obj instanceof String)
                 {
                    System.out.println("yes ob is string");
                   String msg=(String)obj;
                     System.out.println("msg is going to provideprofile; "+msg);
                 if(msg.charAt(0)=='S')
                 {
                     String uname=msg.substring(3);
                     System.out.println("msg is going to provideprofile; "+msg);
                     System.out.println("server profie provide kore: "+uname+"er jonno");
                    
                     dba.ProvideProfileOf(uname);
                     
                 }
                 else if(msg.charAt(0)=='E')
                 {
                     System.out.println("yes in E command");
                     String course=msg.substring(3);
                     if(course.equals("JAVA"))
                     {
                         System.out.println("QUESTION REQ ER AGA");
                         
                         dba.QuestionSendingFrom("javaquestion");
                         System.out.println("afer ");
                         
                         
                     }
                 }
                 else if(msg.charAt(0)=='R')
                 {
//                     String studentName=msg.substring(3);
//                     DatabaseAction.ProvideResultOf(studentName);
                     
                 }
                 else if(msg.charAt(0)=='A')
                 {
                     String str=msg.substring(2);
                     File file=new File("AdminInfo.txt");
                     if(!(file.exists())) file.createNewFile();
                     Scanner in=new Scanner(file);
                     String str1="";
                     while(in.hasNext())
                     {
                         str1=str1+in.nextLine();
                     }
                     if(str.equals(str1))
                     {
                        dos.writeUTF("Admin Login Successful");
                        dos.flush();
                     }
                     else
                     {
                        dos.writeUTF("!!Login Failed.Incorrect Information!!");
                        dos.flush();
                     }
                     
                 }
                 
                     
                 }
                    
              
            
                }
               
            
                 
            
      
            } 
               
        
        
    }   catch (IOException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
}
}
