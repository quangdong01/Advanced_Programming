/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.ConnectDB;

// tất cả các thư viện cần thiết đã được tự động import theo recommend
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;



/**
 *
 * @author admin
 */
public class ConnectDb {
   public Connection cnt;
   public void connectToDatabase(String server, String user, String password, String database, int port)
   {
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setServerName(server);
       ds.setUser(user);
       ds.setPassword(password);
       ds.setDatabaseName(database);
       ds.setPortNumber(port);
       ds.setEncrypt(false);
        try 
        {
            cnt = ds.getConnection();
            System.out.println("Connect successfully to Vehicle and Driver Management !!");
        } 
        catch (SQLServerException ex) 
        {
            Logger.getLogger(ConnectDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   }
}
