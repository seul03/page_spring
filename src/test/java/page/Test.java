package page;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private String user = "scott";
   private String password = "pass";
   
   @org.junit.Test
   public void test() throws ClassNotFoundException {
      Class.forName(driver);
      try(Connection conn = DriverManager.getConnection(url, user, password)){
         
      } catch (Exception e) {
         System.out.println("!!!!!");
      }
   }
}