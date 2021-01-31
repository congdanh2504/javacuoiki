package Fun;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
	java.util.Date date;
	public ConvertDate(String date) throws ParseException  {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.date = dateFormat.parse(date);
	}
	public java.sql.Date convert(){
		return new java.sql.Date(this.date.getTime());
	}
}
