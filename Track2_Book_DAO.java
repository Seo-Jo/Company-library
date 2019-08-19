import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import java.text.*;
class Track2_Book_DAO{
	DBConnectionOracle common = new DBConnectionOracle();
	Connection          con      = null;
	ResultSet	    	rs	     = null;
	PreparedStatement   ps       = null;

	ArrayList<Track2_Book_DTO> BM_History(String search, String gubun){
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		String query =  " select a.book_id, b.book_name, b.author, b.publisher, a.member_id, c.name, "+
						" d.dept_name, e.rank_name, "+
						" to_char(a.rent_start_date, 'yyyy-mm-dd') as rent_start_date, "+
						" to_char(a.Rent_return_date, 'yyyy-mm-dd') as rent_return_date "+
						" from A05_TRACK2_BOOK_RENT a, track2_book b, A05_track2_member c, "+
						" A05_track2_dept_desc d, A05_track2_rank_desc e "+
						" where a.book_id = b.book_id "+
						" and a.member_id = c.member_id "+
						" and d.dept_no = c.dept_no "+
						" and e.rank_no = c.rank_no ";
						
		// System.out.println(query);
		if(gubun.equals("book")){
			query += " and b.book_id = '"+search+"' ";
		}else if(gubun.equals("member")){
			query += " and c.member_id = '"+search+"' ";
		}		

		Track2_Book_DTO dto = null;
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String book_Id = rs.getString(1); 
				String book_Name = rs.getString(2); 
				String author = rs.getString(3); 
				String publisher = rs.getString(4); 
				String member_Id = rs.getString(5);
				String name = rs.getString(6); 
				String dept_name = rs.getString(7); 
				String rank_name = rs.getString(8); 
				String rent_Start_Date = rs.getString(9); 
				String rent_Return_Date = rs.getString(10); 
				dto = new Track2_Book_DTO(book_Id, book_Name, author, publisher, member_Id, name, dept_name, rank_name, rent_Start_Date, rent_Return_Date);
				arr.add(dto);
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException book_Search: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException book_Search: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception book_Search: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close() Exception book_Search: " +e.getMessage());
			}
		}
		return arr;
	}
			
	ArrayList<Track2_Book_DTO> book_Borrow(String id){
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		String query =  " SELECT BOOK_ID, BOOK_NAME, author, publisher,  "+
						" decode(status, 'y', '[대출가능]', '[대출불가]') as status  "+
						" from TRACK2_BOOK "+
						" where substr(book_id,0,2) = '05' "+
						" and status = 'y' "+
						" and book_id = '"+id+"' ";
		//System.out.print(query);		
		Track2_Book_DTO dto = null;				
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String book_Id = rs.getString(1); 
				String book_Name = rs.getString(2); 
				String author = rs.getString(3); 
				String publisher = rs.getString(4); 
				String status = rs.getString(5); 
				dto = new Track2_Book_DTO(book_Id, book_Name, author, publisher, status);
				arr.add(dto);
			}
		}catch(RemoteException re){
			System.out.print("RemoteException book_Search: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException book_Search: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception book_Search: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close() Exception book_Search: " +e.getMessage());
			}
		}
		return arr;
	}	
	
	ArrayList<Track2_Book_DTO> book_Return(String id, String gubun){
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		String query =  " SELECT b.rent_id, b.member_id, b.BOOK_ID, a.BOOK_NAME, a.author, a.publisher, "+
						" b.RENT_START_DATE, b.RENT_RETURN_DATE, "+
						" decode(a.status, 'y', '[대출가능]', '[대출불가]') as status "+
						" from TRACK2_BOOK a, A05_TRACK2_BOOK_RENT b "+
						" where a.BOOK_ID = b.BOOK_ID "+
						" and RENT_RETURN_DATE is null ";

		if(gubun.equals("member")){
			query += " and b.Member_Id = '"+id+"' ";
		}else if(gubun.equals("book")){
			query += " and b.BOOK_ID = '"+id+"' ";
		}
		//System.out.print(query);		
		Track2_Book_DTO dto = null;				
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String member_Id = rs.getString(1); 
				String book_Id = rs.getString(2); 
				String book_Name = rs.getString(3); 
				String author = rs.getString(4); 
				String publisher = rs.getString(5); 
				String rent_Start_Date = rs.getString(6); 
				String rent_Return_Date = rs.getString(7); 
				String status = rs.getString(8); 
				dto = new Track2_Book_DTO(member_Id, book_Id, book_Name, author, publisher, rent_Start_Date, rent_Return_Date, status);
				arr.add(dto);
			}
		}catch(RemoteException re){
			System.out.print("RemoteException book_Search: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException book_Search: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception book_Search: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close() Exception book_Search: " +e.getMessage());
			}
		}
		return arr;
	}


	String getRent_Id(){
		String query = " select max(rent_Id) from A05_TRACK2_BOOK_RENT ";
		int maxId = 0;
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				maxId = rs.getInt(1);
			}
		}catch(RemoteException re){
			System.out.print("RemoteException creAuto_Id: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException creAuto_Id: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception creAuto_Id: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.print("close() 오류" +e.getMessage());
			}
		}
		if(maxId == 0) maxId = 1001;
		else maxId += 1;
		return Integer.toString(maxId);
	}
	
	int book_Cancle(String book_Id){ 
		String query = " delete a05_track2_book_rent where book_Id = '"+book_Id+"' "+
						"and RENT_RETURN_DATE IS NULL ";
		int result = 0;
		//System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		}catch(RemoteException re){
			System.out.print("RemoteException deleteDB : " +re.getMessage());
		}catch(SQLDataException se){
			System.out.print("SQLDataException deleteDB : " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception deleteDB: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.print("close ()" +e.getMessage());
			}
		}
		return result;	
	}
	
	int update_Status(String book_Id, String gubun){ 
		String query = " update TRACK2_BOOK ";
		int result = 0;
		if(gubun.equals("borrow")){
			query +=  " set status = 'n' where book_id = '"+book_Id+"'";
		}else if(gubun.equals("return")){
			query +=  " set status = 'y' where book_id = '"+book_Id+"'";
		}
		//System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		}catch(RemoteException re){
			System.out.print("RemoteException deleteDB : " +re.getMessage());
		}catch(SQLDataException se){
			System.out.print("SQLDataException deleteDB : " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception deleteDB: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.print("close ()" +e.getMessage());
			}
		}
		return result;	
	}
	
	int update_ReturnInfo(String member_Id, String book_Id, String rent_Return_Date){ 
		String query = " update a05_track2_book_rent "+
						" set rent_Return_Date = null "+
						" where member_id = '"+member_Id+"' "+
						" and book_id = '"+book_Id+"' "+
						" and rent_Return_Date = '"+rent_Return_Date+"' ";
		int result = 0;
		//System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		}catch(RemoteException re){
			System.out.print("RemoteException deleteDB : " +re.getMessage());
		}catch(SQLDataException se){
			System.out.print("SQLDataException deleteDB : " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception deleteDB: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.print("close ()" +e.getMessage());
			}
		}
		return result;		
	}	
	
	int update_ReturnInfo(String rent_Id, String member_Id, String book_Id, String rent_Return_Date){ 
		String query = " update A05_TRACK2_BOOK_RENT "+
						" set rent_Return_Date = '"+rent_Return_Date+"' "+
						" where RENT_RETURN_DATE is null "+
						" and book_Id = '"+book_Id+"'";
		int result = 0;
		//System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		}catch(RemoteException re){
			System.out.print("RemoteException deleteDB : " +re.getMessage());
		}catch(SQLDataException se){
			System.out.print("SQLDataException deleteDB : " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception deleteDB: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.print("close ()" +e.getMessage());
			}
		}
		return result;		
	}		
	
	int insert_BorrowInfo(String rent_Id, String member_Id, String book_Id, String rent_Start_Date){ 
		String query = " insert into A05_TRACK2_BOOK_RENT(rent_Id, member_Id, book_Id, rent_Start_Date) "+
					   " values('"+rent_Id+"', '"+member_Id+"', '"+book_Id+"', '"+rent_Start_Date+"')";
		int result = 0;
		//System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			result = ps.executeUpdate();
			
		}catch(RemoteException re){
			System.out.print("RemoteException deleteDB : " +re.getMessage());
		}catch(SQLDataException se){
			System.out.print("SQLDataException deleteDB : " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception deleteDB: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps);
			}catch(Exception e){
				System.out.print("close ()" +e.getMessage());
			}
		}
		return result;		
	}	
	
	ArrayList<Track2_Book_DTO> book_Search(String search, String gubun){
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		String query = " select BOOK_ID, BOOK_NAME, AUTHOR, PUBLISHER, "+
						" decode(status, 'y', '[대출가능]', '[대출불가]') "+
						" as status from track2_book "+
						" where substr(book_id,0,2) = '05'";
								
		if(gubun.equals("title")){
			query += " and BOOK_NAME like '%"+search+"%'";  //제목으로 찾기
		}else if(gubun.equals("author")){
			query += " and AUTHOR like '%"+search+"%'";  //저자로 찾기
		}else if(gubun.equals("publisher")){
			query += " and PUBLISHER like '%"+search+"%'";  //출판사로 찾기
		}else if(gubun.equals("all")){
			query += "";
		}
		//System.out.println(query);
		Track2_Book_DTO dto =  null;
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String book_Id = rs.getString(1); 
				String book_Name = rs.getString(2); 
				String author = rs.getString(3); 
				String publisher = rs.getString(4); 
				String status = rs.getString(5); 
				dto = new Track2_Book_DTO(book_Id, book_Name, author, publisher, status);
				arr.add(dto);
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException book_Search: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException book_Search: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception book_Search: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close() Exception book_Search: " +e.getMessage());
			}
		}
		return arr;
	}

	Track2_Book_DTO check_ReturnBook(String member_Id, String book_Id, String rent_Return_Date){
		String query = "select a.book_id, b.book_name, b.author, b.publisher, a.member_id, c.name, "+
						" to_char(a.rent_start_date, 'yyyy-mm-dd') as rent_start_date, to_char(a.rent_return_date , 'yyyy-mm-dd') as rent_return_date "+
						" from a05_track2_book_rent a, track2_book b, a05_track2_member c "+
						" where a.book_id = b.book_id "+
						" and a.member_id = c.member_id "+
						" and a.member_id = '"+member_Id+"' "+
						" and a.book_id = '"+book_Id+"' "+
						" and a.rent_return_date = '"+rent_Return_Date+"' ";
		Track2_Book_DTO dto = null;
		// System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				dto = new Track2_Book_DTO();
				dto.setBook_Id(rs.getString(1)); 
				dto.setBook_Name(rs.getString(2)); 
				dto.setAuthor(rs.getString(3)); 
				dto.setPublisher(rs.getString(4)); 
				dto.setMember_Id(rs.getString(5)); 
				dto.setName(rs.getString(6)); 
				dto.setRent_start_date(rs.getString(7)); 
				dto.setRent_return_date(rs.getString(8)); 
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException executeQuery: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException executeQuery: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception executeQuery: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close Exception executeQuery: " +e.getMessage());
			}
		}
		return dto;
	}		
	
	Track2_Book_DTO cancle_BCheck(String search){
		String query = " SELECT a.member_id, c.name, a.BOOK_ID, substr(b.book_name, 0,6) as book_name, b.author, "+
						" b.publisher, a.RENT_RETURN_DATE "+
						" from A05_TRACK2_BOOK_RENT a, track2_book b, a05_track2_member c "+
						" where a.BOOK_ID = b.BOOK_ID "+
						" and a.member_id = c.member_id "+
						" and substr(a.book_id,0,2) = '05' "+
						" and a.RENT_RETURN_DATE IS NULL "+
						" and a.BOOK_ID = '"+search+"' ";
		Track2_Book_DTO dto = null;
		// System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				String member_Id = rs.getString(1); 
				String name = rs.getString(2); 
				String book_Id = rs.getString(3); 
				String book_Name = rs.getString(4); 
				String author = rs.getString(5); 
				String publisher = rs.getString(6); 
				dto = new Track2_Book_DTO(member_Id, name, book_Id, book_Name, author, publisher);
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException executeQuery: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException executeQuery: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception executeQuery: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close Exception executeQuery: " +e.getMessage());
			}
		}
		return dto;
	}	
	
	D_5_Dept_DTO executeQuery_Id(String search){
		String query = " select a.member_id, a.name, a.age, b.dept_name, c.rank_name, a.address, to_char(a.reg_date, 'yyyy-mm-dd') "+
						" from A05_track2_member a, A05_track2_dept_desc b, A05_track2_rank_desc c "+
						" where a.dept_no = b.dept_no "+
						" and a.rank_no = c.rank_no "+ 
						" and member_id = '"+search+"'"; 
		D_5_Dept_DTO dto = null;
		// System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2); 
				int age = rs.getInt(3);
				String dept_name = rs.getString(4); 
				String rank_name = rs.getString(5);
				String address = rs.getString(6);
				String reg_date = rs.getString(7);
				dto = new D_5_Dept_DTO(id, name, age, dept_name, rank_name, address, reg_date);
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException executeQuery: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException executeQuery: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception executeQuery: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close Exception executeQuery: " +e.getMessage());
			}
		}
		return dto;
	}	
	
	ArrayList<D_5_Dept_DTO> executeQuery(String search, String gubun){
		ArrayList<D_5_Dept_DTO> arr = new ArrayList<D_5_Dept_DTO>();
		String query = " select a.member_id, a.name, a.age, b.dept_name, c.rank_name, a.address, to_char(a.reg_date, 'yyyy-mm-dd') "+
						" from A05_track2_member a, A05_track2_dept_desc b, A05_track2_rank_desc c "+
						" where a.dept_no = b.dept_no "+
						" and a.rank_no = c.rank_no ";
		
		search = "%"+search+"%";
		if(gubun.equals("id")){
			query += " and member_id like ? order by member_id"; 
		}else if(gubun.equals("name")){
			query += " and name like ? order by member_id"; 
		}else if(gubun.equals("dept_name")){
			query += " and dept_name like ? order by member_id"; 
		}else if(gubun.equals("rank_name")){
			query += " and rank_name like ? order by member_id"; 
		}else if(gubun.equals("all")){
			query += " order by member_id"; 
		}
		// System.out.print(query);
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			if(!gubun.equals("all")) ps.setString(1, search);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2); 
				int age = rs.getInt(3);
				String dept_name = rs.getString(4); 
				String rank_name = rs.getString(5);
				String address = rs.getString(6);
				String reg_date = rs.getString(7);
				D_5_Dept_DTO dto = new D_5_Dept_DTO(id, name, age, dept_name, rank_name, address, reg_date);
				arr.add(dto);
			}
			
		}catch(RemoteException re){
			System.out.print("RemoteException executeQuery: " +re.getMessage());
		}catch(SQLException se){
			System.out.print("SQLException executeQuery: " +se.getMessage());
		}catch(Exception e){
			System.out.print("Exception executeQuery: " +e.getMessage());
		}finally{
			try{
				common.close(con,ps,rs);
			}catch(Exception e){
				System.out.print("close Exception executeQuery: " +e.getMessage());
			}
		}
		return arr;
	}	
	
	Track2_Book_DTO bookId_Search(String search){
		Connection          con      = null;
		ResultSet	    	rs	     = null;
		PreparedStatement   ps       = null;
	
		String query = " select BOOK_ID, BOOK_NAME, AUTHOR, PUBLISHER, "+
						" decode(status, 'y', '[대출가능]', '[대출불가]') "+
						" as status from track2_book "+
						" where substr(book_id,0,2) = '05' "+ 
						" and BOOK_ID = '"+search+"'"; //book_Id로 검색.
		Track2_Book_DTO dto = null;	
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()){
				String book_Id = rs.getString(1); 
				String book_Name = rs.getString(2); 
				String author = rs.getString(3); 
				String publisher = rs.getString(4); 
				String status = rs.getString(5); 
				dto = new Track2_Book_DTO(book_Id, book_Name, author, publisher, status);
			}
		}catch(RemoteException re){
			System.out.println("RemoteException executeGetDto: " +re.getMessage());
		}catch(SQLDataException se){
			System.out.println("SQLDataException executeGetDto: " +se.getMessage());
		}catch(Exception e){
			System.out.println("Exception executeGetDto: " +e.getMessage());
		}finally{
			try{
				common.close(con, ps, rs);
			}catch(Exception e){
				System.out.println("close() Exception executeGetDto: " +e.getMessage());
			}
		}
		return dto;
	}
}