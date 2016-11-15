import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class GeneralUtility {

  private String numeric = "\\d+";

  public boolean validateNumeric(String number) {

    if(number.matches(numeric))
      return true;
    else
      return false;

  }

  public Date convertStringToDate(String date) {

    Date aDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    try {
      aDate = (Date)dateFormat.parse(date);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return aDate;

  }

  public String convertDateToString(Date date) {

    String strFormat;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    strFormat =  dateFormat.format(date);

    return strFormat;

  }

  public String checkNumber(boolean matchesDesired) {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    Scanner in = new Scanner(System.in);
    String number;
    int index;
    System.out.print("Enter the number of the reservation: ");
    number = in.nextLine();

    while(!validateNumeric(number)) {
      System.out.print("Input is invalid. Please try again: ");
      number = in.nextLine();
    }
    if(matchesDesired = false) {
      while(((index = reader.checkNumber(number)) == -1)) {
        System.out.print("Number is invalid. Please try again: ");
        number = in.nextLine();
      }
    }

    return number;

  }

}
