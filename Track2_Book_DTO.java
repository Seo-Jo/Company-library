class Track2_Book_DTO{
	String rent_Id, member_Id, name, age, dept_name, rank_name, address, rent_Start_Date, rent_Return_Date = "";
	String book_Id, book_Name, author, publisher, status = "";

	Track2_Book_DTO(){}	
		
	Track2_Book_DTO(String book_Id, String book_Name, String author, String publisher, String member_Id, 
		String name, String dept_name, String rank_name, String rent_Start_Date, String rent_Return_Date){
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.author = author;
		this.publisher = publisher;
		this.member_Id = member_Id;
		this.name = name;
		this.dept_name = dept_name;
		this.rank_name = rank_name;
		this.rent_Start_Date = rent_Start_Date;
		this.rent_Return_Date = rent_Return_Date;
	}
		
	void setBook_Id(String book_Id){
		this.book_Id = book_Id;
	}
	void setName(String name){
		this.name = name;
	}
	void setBook_Name(String book_Name){
		this.book_Name = book_Name;
	}
	void setAuthor(String author){
		this.author = author;
	}
	void setPublisher(String publisher){
		this.publisher = publisher;
	}
	void setMember_Id(String member_Id){
		this.member_Id = member_Id;
	}
	void setRent_start_date(String rent_Start_Date){
		this.rent_Start_Date = rent_Start_Date;
	}
	void setRent_return_date(String rent_Return_Date){
		this.rent_Return_Date = rent_Return_Date;
	}
	
	Track2_Book_DTO(String rent_Id, String member_Id, String book_Id, String book_Name, String author, 
				String publisher, String rent_Start_Date, String rent_Return_Date, String status){
		this.rent_Id = rent_Id;
		this.member_Id = member_Id;
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.author = author;
		this.publisher = publisher;
		this.rent_Start_Date = rent_Start_Date;
		this.rent_Return_Date = rent_Return_Date;
		this.status = status;
	}		
	
	Track2_Book_DTO(String member_Id, String name, String book_Id, String book_Name, String author, 
			String publisher){
		this.member_Id = member_Id;
		this.name = name;
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.author = author;
		this.publisher = publisher;
	}		
	
	Track2_Book_DTO(String member_Id, String book_Id, String book_Name, String author, String publisher, 
		String rent_Start_Date, String rent_Return_Date, String Status){
		this.member_Id = member_Id;
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.author = author;
		this.publisher = publisher;
		this.rent_Start_Date = rent_Start_Date;
		this.rent_Return_Date = rent_Return_Date;
		this.status = status;
	}
	
	Track2_Book_DTO(String book_Id, String book_Name, String author, String publisher, String status){
		this.book_Id = book_Id;
		this.book_Name = book_Name;
		this.author = author;
		this.publisher = publisher;
		this.status = status;
	}

	String getRent_Id(){
		return rent_Id;
	}	
	String getBook_Id(){
		return book_Id;
	}
	String getBook_Name(){
		return book_Name;
	}
	String getAuthor(){
		return author;
	}
	String getPublisher(){
		return publisher;
	}
	String getStatus(){
		return status;
	}
	String getRent_Start_Date(){
		return rent_Start_Date;
	}	
	String getRent_Return_Date(){
		return rent_Return_Date;
	}
	
	String getMember_Id(){
		return member_Id;
	}
	String getName(){
		return name;
	}
	String getAge(){
		return age;
	}
	String getDept_name(){
		return dept_name;
	}
	String getRank_name(){
		return rank_name;
	}
	String getAddress(){
		return address;
	}
}