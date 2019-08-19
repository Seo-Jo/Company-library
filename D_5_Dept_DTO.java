class D_5_Dept_DTO{
	String member_id, name, address, reg_date, dept_no, rank_no, dept_name, rank_name;
	int age;
	
	D_5_Dept_DTO(){}	
	
	D_5_Dept_DTO(String member_id, String name, int age, String dept_no, String rank_no, String address, String reg_date){
		this.member_id = member_id;
		this.name = name;
		this.age = age;
		this.dept_no = dept_no;
		this.rank_no = rank_no;
		this.address = address;
		this.reg_date = reg_date;
	}
	
	void setDetp_no(String dept_no){
		this.dept_no = dept_no;
	}
	void setDept_name(String dept_name){
		this.dept_name = dept_name;
	}
	void setRank_no(String rank_no){
		this.rank_no = rank_no;
	}
	void setRank_name(String rank_name){
		this.rank_name = rank_name;
	}
	
	String getmember_id(){
		return member_id;
	}
	String getName(){
		return name;
	}
	int getAge(){
		return age;
	}
	String getDept_no(){
		return dept_no;
	}
	String getAddress(){
		return address;
	}
	String getReg_date(){
		return reg_date;
	}
	String getDept_name(){
		return dept_no;
	}
	String getRank_name(){
		return rank_no;
	}	
}