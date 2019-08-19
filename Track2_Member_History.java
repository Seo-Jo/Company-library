import java.text.*;
import java.util.*;
class Track2_Member_History{  //각 회원당 대출정보 조회
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		D_5_Dept_DTO m_Dto = new D_5_Dept_DTO();
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		CommonUtil comutil = new CommonUtil();
		String member_Id = "";
		do{
			System.out.print("[대출회원조회]사번을 입력하시오 [0]돌아가기: ");
			member_Id = input.next();
			if(member_Id.equals("0")) return;
			arr = dao.BM_History(member_Id, "member");
			if(arr.size()!= 0){
				for(int i=0; i<arr.size(); i++){
					System.out.print(comutil.getCPad(arr.get(i).getBook_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getBook_Name(),25," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getAuthor(),20," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getPublisher(),10," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getMember_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getName(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getDept_name(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getRank_name(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getRent_Start_Date(),10," ") +"\t");
					if(arr.get(i).getRent_Return_Date() != null){
						System.out.print(comutil.getCPad(arr.get(i).getRent_Return_Date(),10," ") +"\n");
					}else{
						System.out.println("대출중");
					}
				}
			}else{
				System.out.println("대출 정보가 없습니다.");
			}
		}while(!member_Id.equals("0"));
	}
}