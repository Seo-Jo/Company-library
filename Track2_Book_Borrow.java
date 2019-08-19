import java.text.*;
import java.util.*;
class Track2_Book_Borrow{ 
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		CommonUtil comutil = new CommonUtil();
		Track2_Book_DTO dto = new Track2_Book_DTO();
		D_5_Dept_DTO m_Dto = new D_5_Dept_DTO();
		String rent_Start_Date = "";
		String book_Id, status_Value, borrow_YN, member_Id, date_Input, check_YN = "";
		int gubun = 0;
		do{
			System.out.print("[도서대여][1]대여 [2]대여취소 [0]뒤로가기: ");
			gubun = input.nextInt();
			if(gubun == 1){
				do{
					System.out.print("[도서대여]도서일련번호 입력 ([0]뒤로가기): ");
					book_Id = input.next();
					if(book_Id.equals("0")) return;
					arr = dao.book_Borrow(book_Id);
					if(arr.size() == 0){
						System.out.println("일련번호가 없거나 대출불가 도서입니다.");
					}
				}while(arr.size() == 0);
				if(arr.size() != 0){
					System.out.print(comutil.getCPad(arr.get(0).getBook_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(0).getBook_Name(),30," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(0).getAuthor(),20," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(0).getPublisher(),10," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(0).getStatus(),5," ") +"\n");
				}
				
				System.out.print("도서를 대출하시겠습니까[y/n](돌아가기:0) :");
				borrow_YN = input.next();
				if(borrow_YN.equals("0")) return;	
				if(borrow_YN.equals("y") || borrow_YN.equals("Y") || borrow_YN.equals("ㅛ")){
					do{
						System.out.print("[도서대여]사번을 입력하시오(돌아가기:0 ): ");
							member_Id = input.next();
							if(member_Id.equals("0")) return;
							m_Dto = dao.executeQuery_Id(member_Id);
							if(m_Dto == null) System.out.println("사번을 정확히 입력하십시오.");
					}while(m_Dto == null);
					do{
						System.out.print("[대출일자] [1]수기 [2]오늘(자동기입) [0]돌아가기 : ");
						date_Input = input.next();
						if(date_Input.equals("0")) return;
					}while(!date_Input.equals("1") && !date_Input.equals("2"));	
						if(date_Input.equals("1")){
							boolean checkD = true;
							do{
								System.out.print("[대출일자] 년월일(yyyy-mm-dd)? ");
								rent_Start_Date = input.next();
								checkD = comutil.checkDate(rent_Start_Date);
								System.out.println("checkD" +checkD);
								if(!checkD){
									System.out.println("유효하지 않은 날짜 형식입니다. yyyy-mm-dd으로 입력하시기 바랍니다.");
								}
							}while(checkD == false);
						}else if(date_Input.equals("2")){
							rent_Start_Date = comutil.getToday();
						}
						int result_Insert = dao.insert_BorrowInfo(dao.getRent_Id(), member_Id, book_Id, rent_Start_Date);
						int result_Status = dao.update_Status(book_Id, "borrow");
						if(result_Insert == 1 && result_Status == 1){
							System.out.println("대출이 완료되었습니다." +result_Insert+" | (status: "+result_Status+ ")");
						}else{
							System.out.println("대출실패. 다시 시도해주십시오." +result_Insert+" | (status: "+result_Status+ ")");
						}
				}		
			}else if(gubun == 2){
				do{
					System.out.print("[도서취소]취소할 도서일련번호 입력 ([0]뒤로가기): ");
					book_Id = input.next();
					if(book_Id.equals("0")) return;
					dto = dao.cancle_BCheck(book_Id);
					if(dto == null)	System.out.println("최소할 도서의 일련번호를 정확히 입력하십시오.");
				}while(dto == null);
				dto = dao.cancle_BCheck(book_Id);				
				System.out.print(comutil.getCPad(dto.getMember_Id(),5," ") +"\t");
				System.out.print(comutil.getCPad(dto.getName(),20," ") +"\t");
				System.out.print(comutil.getCPad(dto.getBook_Id(),10," ") +"\t");
				System.out.print(comutil.getCPad(dto.getBook_Name(),10," ") +"\t");
				System.out.print(comutil.getCPad(dto.getPublisher(),10," ") +"\n");
			
				System.out.print("해당 내용이 본인의 정보와 일치합니까[y/n] : ");
				check_YN = input.next();
					if(check_YN.equals("y") || check_YN.equals("Y") || check_YN.equals("ㅛ")){
					int result_Delete = dao.book_Cancle(book_Id);
					int result_Status = dao.update_Status(book_Id, "return");
					if(result_Delete == 1 && result_Delete == 1){
						System.out.println("대여가 취소되었습니다." +result_Delete+ " | (status: " +result_Status+ ")");
					}else{
						System.out.println("대여취소가 실패하였습니다." +result_Delete+ " | (status: " +result_Status+ ")");
					}
				}
			}
		}while(gubun != 0);
	}
}