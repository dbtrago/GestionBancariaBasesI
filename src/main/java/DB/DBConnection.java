/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/***** DBConnection.java *****/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {  
   private static Connection connection;
   
   public static String url = "jdbc:mysql://localhost:3360/bank?allowPublicKeyRetrieval=true&useSSL=false";
   private static String user = "root";//Username of database  
  private static String password = "root";//Password of database

   
   public static Connection getConnection() throws SQLException{
       try {
           connection = DriverManager.getConnection(url, user, password);
           if (connection != null) {
               System.out.println("Conexión a base de datos " + url + " ... Ok");
               return  connection;
           }
       } catch (Exception e) {
           System.out.println(e);
       }
       return connection;
   }  
 }   