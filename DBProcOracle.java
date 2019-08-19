import java.rmi.RemoteException;
import java.sql.*;
public class DBProcOracle{
	DBConnectionOracle common = new DBConnectionOracle();
	
	// ����(insert), ����(delete), ����(update)
   public int executeDB(String sQuery){
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		int result = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);	
			result = ps.executeUpdate();
			
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
			common.close(con, ps, rs);
			}catch(Exception e){
			System.out.println("close Exception: " + e.getMessage());
			}
		}
		return result;
	}
   
   // �ϳ��� data�� ��ȸ �Ҷ�
   // return String 
   public String executeGetString(String sQuery){
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		String result = null;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);		
			rs = ps.executeQuery();
			while(rs.next()){
				result = rs.getString(1);	
			}
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
			common.close(con, ps, rs);
			}catch(Exception e){
			System.out.println("close Exception: " + e.getMessage());
			}
        }
		return result;
	}
   
   // return String[]
   public String[] executeGetArray1(String sQuery){
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		String[] result = null;
		int rc2 = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery);		
			rs = ps.executeQuery();
 
			ResultSetMetaData rsmd = rs.getMetaData();
 
			int cols = rsmd.getColumnCount(); //�÷��� �Ѱ���

			result = new String[cols];
	
			while(rs.next()){
				for (int i = 1; i <= cols; i++) {
					result[i-1] = rs.getString(i);	
				}
				rc2++;
			}
	
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
			common.close(con, ps, rs);
			}catch(Exception e){
			System.out.println("close Exception: " + e.getMessage());
			}
      }
		if(rc2 == 0) return null;
		else return result;
	}
	
   // return String[][]
   public String[][] executeGetArray2(String sQuery){
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
		String[][] result = null;
		int rc2 = 0;
		try{
			con = common.getConnection();
			ps  = con.prepareStatement(sQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);			
			rs = ps.executeQuery();
 
			ResultSetMetaData rsmd = rs.getMetaData();
 
			int cols = rsmd.getColumnCount(); //�÷��� �Ѱ���
 			rc2 = 0;

			rs.last(); // ������ �ͼ�
			int rowCount = rs.getRow(); // ī��Ʈ����
			rs.beforeFirst(); // �ٽ� �Ǿ�����

			result = new String[rowCount][cols];
	
			while(rs.next()){
				for (int i = 1; i <= cols; i++) {
					result[rc2][i-1] = rs.getString(i);	
				}
				rc2++;
			}
	
		}catch(RemoteException me){
			System.out.println("RemoteException: " + me.getMessage());
		}catch(SQLException se){
			System.out.println("SQLException: " + se.getMessage());
		}catch(Exception e){
			System.out.println("Exception: " + e.getMessage());
		}finally{
			try{
			common.close(con, ps, rs);
			}catch(Exception e){
			System.out.println("close Exception: " + e.getMessage());
			}
      }
		if(rc2 == 0) return null;
		else return result;
	}	
}	














