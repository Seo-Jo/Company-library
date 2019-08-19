import java.util.*;
import java.text.*;
class Track2_Book_BookSearch{
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		Track2_Book_DTO dto = new Track2_Book_DTO();
		CommonUtil comutil = new CommonUtil();
		String sb_Choice, search = "";
		
		do{
			System.out.print("[도서조회][1]제목 [2]저자 [3]출판사 [4]전체검색 [0]뒤로가기 : ");
			sb_Choice = input.next();
			if(sb_Choice.equals("0")) return;
			if(sb_Choice.equals("1")){
				System.out.print("[도서조회]제 목 ([0]뒤로가기): ");
				search = input.next();
				if(search.equals("0")) return;
				arr = dao.book_Search(search, "title");
			}else if(sb_Choice.equals("2")){
				System.out.print("[도서조회]저 자 ([0]뒤로가기): ");
				search = input.next();
				if(search.equals("0")) return;
				arr = dao.book_Search(search, "author");
			}else if(sb_Choice.equals("3")){
				System.out.print("[도서조회]출판사 ([0]뒤로가기): ");
				search = input.next();
				if(search.equals("0")) return;
				arr = dao.book_Search(search, "publisher");
			}else if(sb_Choice.equals("4")){
				arr = dao.book_Search("", "all");
			}
			if(arr.size() != 0){
				for(int i=0; i<arr.size(); i++){			
					System.out.print(comutil.getCPad(arr.get(i).getBook_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getBook_Name(),30," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getAuthor(),20," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getPublisher(),20," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getStatus(),10," ") +"\n");
				}
			}else if(sb_Choice.equals("1") || sb_Choice.equals("2") || sb_Choice.equals("3") || sb_Choice.equals("4") || arr.size() != 0){
				System.out.print("검색 내용이 없습니다.");
			}
		}while(!sb_Choice.equals("1") || !sb_Choice.equals("2") || !sb_Choice.equals("3") || !sb_Choice.equals("4") || arr.size() == 0);
	}
}	