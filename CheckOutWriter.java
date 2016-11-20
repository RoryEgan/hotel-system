import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * Class to write a reservation in the checkins file to the checkouts file.
 */
public class CheckOutWriter {

  private String fileName;

  CheckOutWriter(String fileName) {

    this.fileName = fileName;

  }

  public String getFileName() {

    return fileName;

  }

  /**
   * Method to write to the checkouts file
   * @param  number of reservation to be written
   * @param  current date of checkout
   */
  public void write(String number, String currentDate) {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");

    try {

      File checkins = new File("CheckIns.csv");
      File checkouts = new File(fileName);
      Scanner fileIn = new Scanner(checkins);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(checkouts, true));

      ArrayList<String> info = new ArrayList<String>();
      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");

        if(lineSplit[0].equals(number)) {
          for(int i = 0; i < lineSplit.length; i++) {
            info.add(lineSplit[i]);
          }
          for(int z = 0; z < info.size(); z++) {
            bufferedWriter.write(info.get(z) + ",");
          }
          bufferedWriter.newLine();
          info.clear();
        }
      }
      bufferedWriter.close();
      System.out.println("\nCheckout saved.");

      double bill = reader.getBill(number);
      if(reader.checkType(number).equals("advanced")) {
        bill = reader.applyDiscount(number);
      }
      System.out.println("\nBill is: " + bill);
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
