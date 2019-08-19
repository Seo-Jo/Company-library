import java.text.*;
import java.util.*;
class Track2_Book_History{ //�� å�� ����/�ݳ� ���� 
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		Track2_Book_DTO dto = new Track2_Book_DTO();
		ArrayList<Track2_Book_DTO> arr = new ArrayList<Track2_Book_DTO>();
		CommonUtil comutil = new CommonUtil();
		String book_Id = "";
		do{
			System.out.print("[���⵵����ȸ]�����Ϸù�ȣ�� �Է��Ͻÿ� [0]���ư���: ");
			book_Id = input.next();
			if(book_Id.equals("0")) return;
			arr = dao.BM_History(book_Id, "book");
			if(arr.size()!= 0){
				for(int i=0; i<arr.size(); i++){
					System.out.print(comutil.getCPad(arr.get(i).getBook_Id(),5," ") +"\t");
					System.out.print(comutil.getCPad(arr.get(i).getBook_Name(),20," ") +"\t");
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
						System.out.println("������");
					}
				}
			}else{
				System.out.println("���� ������ �����ϴ�.");
			}
		}while(!book_Id.equals("0"));
	}
}