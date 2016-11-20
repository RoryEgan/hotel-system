import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * Class to write a reservation to the checkins file.
 */
public class CheckInWriter {

  private String fileName;

  CheckInWriter(String fileName) {

    this.fileName = fileName;

  }

  public String getFileName() {

    return fileName;

  }

/**
 * Method to write a particular reservation to the checkin file
 * @param  number of reservation to be written
 * @param  current date of checkin
 */

  public void write(String number, String currentDate) {

    try {

      File reservations = new File("ReservationInfo.csv");
      File checkins = new File(fileName);
      Scanner fileIn = new Scanner(reservations);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(checkins, true));

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
      System.out.println("\nCheckin saved.");
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

/**
 * Method to delete a line from the checkins.
 * @param number number of reservation to be deleted
 */
  public void deleteLine(String number) {

    try {

      File checkins = new File(fileName);
      File tmp = File.createTempFile("tmp", "");


      Scanner fileIn = new Scanner(checkins);

      BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tmp, true));

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        if(!lineSplit[0].equals(number)) {
          tempWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[7]+"," + lineSplit[8]);
          tempWriter.newLine();
        }
      }
      tempWriter.close();
      if(checkins.delete())
      tmp.renameTo(checkins);
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
