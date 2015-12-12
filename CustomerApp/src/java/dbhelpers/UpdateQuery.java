package dbhelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.customers;

public class UpdateQuery {
    
    private Connection conn;
    
    public UpdateQuery() {
      
    Properties props = new Properties();
    InputStream instr = getClass().getResourceAsStream("dbConnection.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String password = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
    public void doUpdate (customers customers) {
        
        try {
            String query = "UPDATE customers SET firstName = ?, lastName = ?, addr1 = ?, addr2 = ?, city = ?, state = ?, zip = ?, emailAddr = ?, age= ? WHERE custID = ?";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, customers.getFirstName());
            ps.setString(2, customers.getLastName());
            ps.setString(3, customers.getAddr1());
            ps.setString(4, customers.getAddr2());
            ps.setString(5, customers.getCity());
            ps.setString(6, customers.getState());
            ps.setString(7, customers.getZip());
            ps.setString(8, customers.getEmailAddr());
            ps.setInt(9, customers.getAge());
            ps.setInt (10, customers.getCustID());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

   