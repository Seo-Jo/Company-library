import java.text.*;
import java.util.*;
public class CommonUtil{

	//���ó�¥
	String getToday(){
		SimpleDateFormat getdate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = getdate.format(date);
		// Calendar cal = Calendar.getInstance();
		// String rent_Start_Date = today.format(cal.getTime());
		return today;
	}
	
	
	// ��¥���� �˻�
	boolean checkDate(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy-MM-dd");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}	
	// ������ �ڸ��� ��ŭ ä��� ����ä���
    public static String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// ������ �ڸ��� ��ŭ ä��� ����ä���
    public static String getCPad(String str, int size, String strFillText) {
        int intPadPos = 0;
        for(int i = (str.getBytes()).length; i < size; i++) {
            if(intPadPos == 0) {
                str += strFillText;
                intPadPos = 1;
            } else {
                str = strFillText + str;
                intPadPos = 0;
            }
        }
        return str;
    }
	// ������ �ڸ��� ��ŭ ä��� ������ä���
    public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
    }
}
