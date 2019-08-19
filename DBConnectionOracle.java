import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
class DBConnectionOracle{
	Connection getConnection() throws RemoteException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	 	
		} catch (Exception ex) {
			System.out.println("ex=========SQLException: " + ex.getMessage());
		}	
		Connection con = null;
		try{
			String DB_URL = "jdbc:oracle:thin:@admin-PC04:1521:ORCL";
			String DB_USER = "scott";
			String DB_PASSWORD= "tiger";
			
			// String DB_URL = "jdbc:oracle:thin:@admin-PC0:1521:ORCL";
			// String DB_USER = "hr";
			// String DB_PASSWORD= "1234";
			
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);	
			if(con == null){
				System.out.println(" oracle db 연결실패===========");	
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
		} 
		return con;
	}	

	public void close(Connection con, PreparedStatement ps){
		try {
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }		
	public void close(Connection con, PreparedStatement ps, ResultSet result){
		try {
			if(result != null)	result.close();
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
			//정보를 얻은 후에 연결을 종료 // 항상 접속을 했으면 끊는다.
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }	
}	