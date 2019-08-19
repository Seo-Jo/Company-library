import java.util.*;
class Track2_Book_Main{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int gubun = 0;
		do{
			System.out.print("[JSL도서관] [1]회원조회 [2]도서조회 [3]도서대여 [4]도서반납 "
							+"\n"+"[5]대출도서조회 [6]대출회원조회 [0]나가기 :");
			gubun = input.nextInt();
			if(gubun == 0){		  
				return;
			}else if(gubun == 1){
				Track2_Book_Member member = new Track2_Book_Member();
				member.proc();
			}else if(gubun == 2){ 
				Track2_Book_BookSearch book_Search = new Track2_Book_BookSearch();
				book_Search.proc();
			}else if(gubun == 3){ 
				Track2_Book_Borrow book_Borrow = new Track2_Book_Borrow();
				book_Borrow.proc();
			}else if(gubun == 4){ 
				Track2_Book_Return book_Return = new Track2_Book_Return();
				book_Return.proc();
			}else if(gubun == 5){ 
				Track2_Book_History book_History = new Track2_Book_History();
				book_History.proc();
			}else if(gubun == 6){ 
				Track2_Member_History member_History = new Track2_Member_History();
				member_History.proc();
			}
		}while(gubun != 0);
	}
}			
