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
		
		System.out.print("[�����ݳ�][1]�ݳ� [2]�ݳ���� [0]�ڷΰ���:");
		return_Choice = input.next();
		if(return_Choice.equals("0")){
			return;
		}else if(return_Choice.equals("1")){
			do{
				System.out.print("[�����ݳ�]����� �Է��Ͻÿ�(���ư���:0): ");
				member_Id = input.next();
				if(member_Id.equals("0")) return;
				arr = dao.book_Return(member_Id, "member");
				if(arr.size() == 0) System.out.println("�����Ͻ� å�� �����ϴ�.");
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
					System.out.print("[�����ݳ�]�ݳ��� ������ �Ϸù�ȣ �Է� ([0]�ڷΰ���): ");
					book_Id = input.next();
					if(book_Id.equals("0")) return;
					arr = dao.book_Return(book_Id, "book");
					if(arr.size() == 0){
						System.out.println("������ �Ϸù�ȣ�� ��Ȯ�� �Է��Ͻʽÿ�.");
					}
				}while(arr.size() == 0);
			
				System.out.print("������ �ݳ��Ͻðڽ��ϱ�[y/n](���ư���:0) : ");
				borrow_YN = input.next();
				if(borrow_YN.equals("y") || borrow_YN.equals("Y") || borrow_YN.equals("��")){
					System.out.print("[�ݳ�����][1]���� [2]����(�ڵ�����) [0]���ư��� : ");
					date_Input = input.next();
					if(date_Input.equals("0")) return;
					if(date_Input.equals("1")){
						boolean checkD = true;
						do{
							System.out.print("[��������] �����(yyyy-mm-dd)? ");
							rent_Return_Date = input.next();
							checkD = comutil.checkDate(rent_Return_Date);
							System.out.println("checkD" +checkD);
							if(!checkD){
								System.out.println("��ȿ���� ���� ��¥ �����Դϴ�. yyyy-mm-dd���� �Է��Ͻñ� �ٶ��ϴ�.");
							}
						}while(checkD == false);
					}else if(date_Input.equals("2")){
						rent_Return_Date = comutil.getToday();
					}
					int result_Insert = dao.update_ReturnInfo(dao.getRent_Id(), member_Id, book_Id, rent_Return_Date);
					int result_Status = dao.update_Status(book_Id, "return");
					if(result_Insert == 1 && result_Status == 1){
						System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�." +result_Insert+" | (status: "+result_Status+ ")");
					}else{
						System.out.println("�ݳ�����. �ٽ� �õ����ֽʽÿ�." +result_Insert+" | (status: "+result_Status+ ")");
					}
				}
			}
		}else if(return_Choice.equals("2")){
			System.out.print("[�ݳ����]��� �Է� (0:���ư���): ");
			member_Id = input.next();
			if(member_Id.equals("0")) return;
			
			System.out.print("[�ݳ����]å�� �Ϸù�ȣ �Է� (0:���ư���): ");
			book_Id = input.next();
			if(book_Id.equals("0")) return;
			
			System.out.print("[�ݳ����]�ݳ����� [1]���� [2]����(�ڵ��Է�) [0]���ư���: ");
			date_Input = input.next();
			if(date_Input.equals("0")) return;
			if(date_Input.equals("1")){
				boolean checkD = true;
				do{
					System.out.print("[��������] �����(yyyy-mm-dd)? ");
					rent_Return_Date = input.next();
					checkD = comutil.checkDate(rent_Return_Date);
					System.out.println("checkD" +checkD);
					if(!checkD){
						System.out.println("��ȿ���� ���� ��¥ �����Դϴ�. yyyy-mm-dd���� �Է��Ͻñ� �ٶ��ϴ�.");
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
			System.out.print("��� ������ �½��ϱ�[y/n] (0:���ư���): ");
			checkGetYN = input.next();
			if(checkGetYN.equals("0")) return;
			if(checkGetYN.equals("y") || checkGetYN.equals("Y") || checkGetYN.equals("��")){
				int result_Insert = dao.update_ReturnInfo(member_Id, book_Id, rent_Return_Date);
				int result_Status = dao.update_Status(book_Id, "borrow");
				if(result_Insert == 1 && result_Status == 1){
					System.out.println("��Ұ� �Ϸ�Ǿ����ϴ�." +result_Insert+" | (status: "+result_Status+ ")");
				}else{
					System.out.println("��ҽ���. �ٽ� �õ����ֽʽÿ�." +result_Insert+" | (status: "+result_Status+ ")");
				}					
			}
		}
	}
}