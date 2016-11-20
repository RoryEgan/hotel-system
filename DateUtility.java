import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/**
 * A class to carry out a range of actions on dates
 */
public class DateUtility {

  /**
   * A method to convert a given string to a date
   * @param  date string in date format
   * @return      date that has been created from string
   */
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

  /**
   * A method to return the position in the week of a date.
   * @param  date date that the day needs to be found from
   * @return      int of position in the week
   */
  public int getDayOfWeek(Date date) {

    int day;
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    day = cal.get(Calendar.DAY_OF_WEEK);

    return day;
  }

  /**
   * A method to check if a specific date is before the current time.
   * @param  date date to be checked
   * @return      boolean that depends on the result of the checking
   */
  public boolean checkIfBefore(Date date) {

    Date currentDate = Calendar.getInstance().getTime();

    if(date.before(currentDate))
    return true;

    else return false;
  }

  /**
   * A method to validate user input to make sure it matches the desired date format.
   * @param  date string to be checked
   * @return      boolean dependent on result of checking
   */
  public boolean validateDate(String date) {

    String pattern = "([0-9]{2})-([0-9]{2})-([0-9]{4})";

    if(date.matches(pattern))
    return true;
    else return false;
  }

  /**
   * A method to convert a given date to a string.
   * @param  date date to be converted
   * @return      string that has been converted from date
   */

  public String convertDateToString(Date date) {

    String strFormat;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    strFormat =  dateFormat.format(date);

    return strFormat;

  }
}
