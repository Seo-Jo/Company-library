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
			System.out.print("[������ȸ][1]���� [2]���� [3]���ǻ� [4]��ü�˻� [0]�ڷΰ��� : ");
			sb_Choice = input.next();
			if(sb_Choice.equals("0")) return;
			if(sb_Choice.equals("1")){
				System.out.print("[������ȸ]�� �� ([0]�ڷΰ���): ");
				search = input.next();
				if(search.equals("0")) return;
				arr = dao.book_Search(search, "title");
			}else if(sb_Choice.equals("2")){
				System.out.print("[������ȸ]�� �� ([0]�ڷΰ���): ");
				search = input.next();
				if(search.equals("0")) return;
				arr = dao.book_Search(search, "author");
			}else if(sb_Choice.equals("3")){
				System.out.print("[������ȸ]���ǻ� ([0]�ڷΰ���): ");
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
				System.out.print("�˻� ������ �����ϴ�.");
			}
		}while(!sb_Choice.equals("1") || !sb_Choice.equals("2") || !sb_Choice.equals("3") || !sb_Choice.equals("4") || arr.size() == 0);
	}
}	