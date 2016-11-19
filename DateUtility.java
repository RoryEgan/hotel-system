import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class DateUtility {

  public Date convertStringToDate(String date) {

    Date aDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    try {
      aDate = (Date)dateFormat.parse(date);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return aDate;

  }

  public int getDayOfWeek(Date date) {

    int day;
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    day = cal.get(Calendar.DAY_OF_WEEK);

    return day;
  }

  public boolean checkIfBefore(Date date) {

    Date currentDate = Calendar.getInstance().getTime();

    if(date.before(currentDate))
    return true;

    else return false;
  }

  public boolean validateDate(String date) {

    String pattern = "([0-9]{2})-([0-9]{2})-([0-9]{4})";

    if(date.matches(pattern))
    return true;
    else return false;
  }

  public String convertDateToString(Date date) {

    String strFormat;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    strFormat =  dateFormat.format(date);

    return strFormat;

  }
}
