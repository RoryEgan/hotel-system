import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class AnalyticGenerator {

  public void generateAnalytics(String hotel, Date startDate, String interval, int numIntervals) {

    DateUtility dateutility = new DateUtility();
    ReservationReader reader = new ReservationReader("ReservationInfo.csv");
    String stringDate = dateutility.convertDateToString(startDate);
    Date endDate;

    String fileName = hotel + "-" + stringDate + "-" + interval + "-" + numIntervals + ".csv";
    File analytics = createFile(fileName);

    System.out.println("Created analytics file: " + analytics.getName());

    Calendar cal = Calendar.getInstance();
    cal.setTime(startDate);

    if(interval.equals("daily")) {
      cal.add(Calendar.DATE, numIntervals);
      endDate = cal.getTime();
    }
    else if(interval.equals("monthly")) {
      cal.add(Calendar.MONTH, numIntervals);
      endDate = cal.getTime();
    }
    else {
      cal.add(Calendar.WEEK_OF_YEAR, numIntervals);
      endDate = cal.getTime();
    }

    outputInfo(hotel, startDate, endDate, analytics);

  }

  public void outputInfo(String hotel, Date startDate, Date endDate, File analytics) {

    try {

      File reservations = new File("ReservationInfo.csv");
      Date date;
      DateUtility dateutility = new DateUtility();

      Scanner fileIn = new Scanner(reservations);

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(analytics, true));

      ArrayList<String> info = new ArrayList<String>();
      String [] lineSplit;

      while(fileIn.hasNext()) {

        lineSplit = fileIn.nextLine().split(",");

        if(lineSplit[1].equals(hotel)) {
          date = dateutility.convertStringToDate(lineSplit[4]);
          if(date.before(endDate) && date.after(startDate)) {
            for(int i = 0; i < lineSplit.length; i++) {
              info.add(lineSplit[i]);
            }
            for(int z = 0; z < info.size(); z++) {
              bufferedWriter.write(info.get(z) + ",");
            }
          }
          bufferedWriter.newLine();
          info.clear();
        }
      }
      bufferedWriter.flush();
      bufferedWriter.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


  private File createFile(String fileName) {

    try {

      File analytics = new File(fileName);
      analytics.createNewFile();
      return analytics;
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
