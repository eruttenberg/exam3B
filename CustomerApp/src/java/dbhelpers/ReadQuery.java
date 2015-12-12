
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


public class ReadQuery {
    
    private Connection conn;
    private ResultSet results;
    
    public ReadQuery() {
    
    Properties props = new Properties();
    InputStream instr = getClass().getResourceAsStream("dbConnection.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String password = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void doRead() {
        try {
            String query = "Select * from CUSTOMERS ORDER BY custID ASC";
            
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public String getHTMLtable() {
        String table ="<table>";
              table +="<tr> <th> CustID </th>";
              table += "<th> First Name </th>";
              table += "<th> Last Name </th>";
              table += "<th> Address 1 </th>";
              table += "<th> Address 2 </th>";
              table += "<th> City </th>";
              table += "<th> State </th>";
              table += "<th> Zip </th>";
              table += "<th> E-mail Address </th>";
              table += "<th> Age </th> </tr>";
        
        try {
            while(this.results.next()) {
                
                customers customers = new customers();
                customers.setCustID(this.results.getInt("custID"));
                customers.setFirstName(this.results.getString("firstName"));
                customers.setLastName(this.results.getString("lastName"));
                customers.setAddr1(this.results.getString("addr1"));
                customers.setAddr2(this.results.getString("addr2"));
                customers.setCity(this.results.getString("city"));
                customers.setState(this.results.getString("state"));
                customers.setZip(this.results.getString("zip"));
                customers.setEmailAddr(this.results.getString("emailAddr"));
                customers.setAge(this.results.getInt("age"));
                
                table += "<tr>";
                table += "<td>";
                table += customers.getCustID();                  
                table += "</td>";
                
                table += "<td>";
                table += customers.getFirstName();          
                table += "</td>";
               
                table += "<td>";
                table += customers.getLastName();            
                table += "</td>";
               
                table += "<td>";
                table += customers.getAddr1();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getAddr2();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getCity();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getState();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getZip();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getEmailAddr();          
                table += "</td>";
                
                table += "<td>";
                table += customers.getAge();        
                table += "</td>";
                
                table+="<td>";
                table+="<a href=update?custID=" + customers.getCustID() + "> Update </a>" + "<a href=delete?custID=" + customers.getCustID() +"> Delete </a>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table += "</table>";
        
                return table;
                
        
        
    }
    
}
