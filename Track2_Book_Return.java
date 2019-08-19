import java.text.*;
import java.util.*;
class Track2_Book_Return{ 
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		ArrayList<Track2_Book_DTO> arr = null;
		CommonUtil comutil = new CommonUtil();
		Track2_Book_DTO dto =  null;
		String rent_Return_Date = "";
		String book_Id, borrow_YN, return_Choice, member_Id, date_Input, checkGetYN = "";
		
		System.out.print("[도서반납][1]반납 [2]반납취소 [0]뒤로가기:");
		return_Choice = input.next();
		if(return_Choice.equals("0")){
			return;
		}else if(return_Choice.equals("1")){
			do{
				System.out.print("[도서반납]사번을 입력하시오(돌아가기:0): ");
				member_Id = input.next();
				if(member_Id.equals("0")) return;
				arr = dao.book_Return(member_Id, "member");
				if(arr.size() == 0) System.out.println("대출하신 책이 없습니다.");
			}while(arr.size() == 0);
			if(arr.size() != 0){	
				arr = dao.book_Return(member_Id, "member");
				for(int i=0; i<arr.size(); i++){
					System.out.print(comutil.getCPad(arr.get(i).getMember_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getBook_Id(),10," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getBook_Name(),10," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getAuthor(),25," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getPublisher(),20," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getRent_Start_Date(),5," ") +"\t");			
					System.out.print(comutil.getCPad(arr.get(i).getStatus(),5," ") +"\n");
				}
				do{
					System.out.print("[도서반납]반납할 도서의 일련번호 입력 ([0]뒤로가기): ");
					book_Id = input.next();
					if(book_Id.equals("0")) return;
					arr = dao.book_Return(book_Id, "book");
					if(arr.size() == 0){
						System.out.println("도서의 일련번호를 정확히 입력하십시오.");
					}
				}while(arr.size() == 0);
			
				System.out.print("도서를 반납하시겠습니까[y/n](돌아가기:0) : ");
				borrow_YN = input.next();
				if(borrow_YN.equals("y") || borrow_YN.equals("Y") || borrow_YN.equals("ㅛ")){
					System.out.print("[반납일자][1]수기 [2]오늘(자동기입) [0]돌아가기 : ");
					date_Input = input.next();
					if(date_Input.equals("0")) return;
					if(date_Input.equals("1")){
						boolean checkD = true;
						do{
							System.out.print("[대출일자] 년월일(yyyy-mm-dd)? ");
							rent_Return_Date = input.next();
							checkD = comutil.checkDate(rent_Return_Date);
							System.out.println("checkD" +checkD);
							if(!checkD){
								System.out.println("유효하지 않은 날짜 형식입니다. yyyy-mm-dd으로 입력하시기 바랍니다.");
							}
						}while(checkD == false);
					}else if(date_Input.equals("2")){
						rent_Return_Date = comutil.getToday();
					}
					int result_Insert = dao.update_ReturnInfo(dao.getRent_Id(), member_Id, book_Id, rent_Return_Date);
					int result_Status = dao.update_Status(book_Id, "return");
					if(result_Insert == 1 && result_Status == 1){
						System.out.println("반납이 완료되었습니다." +result_Insert+" | (status: "+result_Status+ ")");
					}else{
						System.out.println("반납실패. 다시 시도해주십시오." +result_Insert+" | (status: "+result_Status+ ")");
					}
				}
			}
		}else if(return_Choice.equals("2")){
			System.out.print("[반납취소]사번 입력 (0:돌아가기): ");
			member_Id = input.next();
			if(member_Id.equals("0")) return;
			
			System.out.print("[반납취소]책의 일련번호 입력 (0:돌아가기): ");
			book_Id = input.next();
			if(book_Id.equals("0")) return;
			
			System.out.print("[반납취소]반납일자 [1]수기 [2]오늘(자동입력) [0]돌아가기: ");
			date_Input = input.next();
			if(date_Input.equals("0")) return;
			if(date_Input.equals("1")){
				boolean checkD = true;
				do{
					System.out.print("[대출일자] 년월일(yyyy-mm-dd)? ");
					rent_Return_Date = input.next();
					checkD = comutil.checkDate(rent_Return_Date);
					System.out.println("checkD" +checkD);
					if(!checkD){
						System.out.println("유효하지 않은 날짜 형식입니다. yyyy-mm-dd으로 입력하시기 바랍니다.");
					}
				}while(checkD == false);
			}else if(date_Input.equals("2")){
				rent_Return_Date = comutil.getToday();
			}				
			dto = dao.check_ReturnBook(member_Id, book_Id, rent_Return_Date);
			System.out.print(dto.getBook_Id() +"\t");
			System.out.print(dto.getBook_Name() +"\t");
			System.out.print(dto.getAuthor() +"\t");
			System.out.print(dto.getPublisher() +"\t");
			System.out.print(dto.getMember_Id() +"\t");
			System.out.print(dto.getName() +"\t");
			System.out.print(dto.getRent_Start_Date() +"\t");
			System.out.print(dto.getRent_Return_Date() +"\n");
			System.out.print("상기 내용이 맞습니까[y/n] (0:돌아가기): ");
			checkGetYN = input.next();
			if(checkGetYN.equals("0")) return;
			if(checkGetYN.equals("y") || checkGetYN.equals("Y") || checkGetYN.equals("ㅛ")){
				int result_Insert = dao.update_ReturnInfo(member_Id, book_Id, rent_Return_Date);
				int result_Status = dao.update_Status(book_Id, "borrow");
				if(result_Insert == 1 && result_Status == 1){
					System.out.println("취소가 완료되었습니다." +result_Insert+" | (status: "+result_Status+ ")");
				}else{
					System.out.println("취소실패. 다시 시도해주십시오." +result_Insert+" | (status: "+result_Status+ ")");
				}					
			}
		}
	}
}