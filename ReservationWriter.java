import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * Class to write a reservation to the file
 */
public class ReservationWriter {

  private String fileName;

  ReservationWriter(String fileName) {

    this.fileName = fileName;

  }

/**
 * Method to handle the writing of the reservation.
 * @param toBeWritten arraylist of strings that contains the reservation info
 */
  public void write(ArrayList<String> toBeWritten) {

    try {

      File myFile = new File(fileName);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));

      for(int i = 0; i < toBeWritten.size(); i++) {
        bufferedWriter.write(toBeWritten.get(i) + ",");
      }
      System.out.println("\nReservation created.");
      bufferedWriter.newLine();
      bufferedWriter.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
