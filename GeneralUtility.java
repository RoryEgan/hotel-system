import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
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
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    try {
      aDate = (Date)dateFormat.parse(date);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return aDate;

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
    if(!matchesDesired) {
      while(((index = reader.checkNumber(number)) == -1)) {
        System.out.print("Number is invalid. Please try again: ");
        number = in.nextLine();
      }
    }


    return number;

  }

  public String getHotel() {

    HotelReader hreader = new HotelReader("l4Hotels.csv");
    ArrayList<Hotel> hotels = hreader.getHotelInfo();
    Scanner in = new Scanner(System.in);
    int input;

    System.out.print("Please select a hotel choice: ");
    for(int i = 0; i < hotels.size(); i++) {
      System.out.print("\n" + i + ". " + hotels.get(i).getType());
    }
    System.out.print("\nEnter your choice: ");
    input = in.nextInt();
    return hotels.get(input).getType();

  }

  public String getType() {

    Scanner in = new Scanner(System.in);
    int input;
    String type = "";

    System.out.print("Is this a Simple or Advanced purchase booking? \n1. Simple \n2. Advanced\nPlease enter: ");
    input = in.nextInt();
    if(input == 1) {
      type = "simple";
    }
    else if(input == 2) {
      type = "advanced";
    }
    else {
      System.out.println("Invalid input.");
    }
    return type;
  }

}
