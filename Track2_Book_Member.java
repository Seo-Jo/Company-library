import java.util.*;
public class Track2_Book_Member{ 
	String search = "";
	int search_Num = 0;
	void proc(){
		Scanner input = new Scanner(System.in);
		Track2_Book_DAO dao = new Track2_Book_DAO();
		D_5_Dept_DTO dto = new D_5_Dept_DTO();
		ArrayList<D_5_Dept_DTO> array = null;
		do{
			System.out.print("[ȸ����ȸ] [1]ID [2]�̸� [3]�μ��� [4]������ [5]���� [0]���ư���: ");
			search_Num = input.nextInt();
			if(search_Num == 0) return;
			if(search_Num == 1){
				System.out.print("[ȸ����ȸ] I D ? ");
				search = input.next();
				array = dao.executeQuery(search, "id");
			}else if(search_Num == 2){
				System.out.print("[ȸ����ȸ] �� �� ? ");
				search = input.next();
				array = dao.executeQuery(search, "name");
			}else if(search_Num == 3){
				System.out.print("[ȸ����ȸ] �μ��� ? ");
				search = input.next();
				array = dao.executeQuery(search, "dept_name");
			}else if(search_Num == 4){
				System.out.print("[ȸ����ȸ] ������ ? ");
				search = input.next();
				array = dao.executeQuery(search, "rank_name");
			}else if(search_Num == 5){
				array = dao.executeQuery(search, "all");
			}
			if(array.size() == 0){
				System.out.println("�˻��� ������ �����ϴ�.");
			}
			for(int i=0; i<array.size(); i++){			
				System.out.print(array.get(i).getmember_id() +"\t");
				System.out.print(array.get(i).getName() +"\t");
				System.out.print(array.get(i).getAge() +"\t");
				System.out.print(array.get(i).getDept_name() +"\t");
				System.out.print(array.get(i).getRank_name() +"\t");
				System.out.print(array.get(i).getAddress() +"\t");
				System.out.print(array.get(i).getReg_date() +"\n");
			}
		}while(search_Num != 0);
	}
}