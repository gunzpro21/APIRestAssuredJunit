package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFacilities {
	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
		String strDate = formatter.format(date);
		return strDate;
	}
	public static void main(String[] args) {
	}
}
