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

      ArrayList<String> resInfo = new ArrayList<String>();
      String [] lineSplit;

      while(fileIn.hasNext()) {

        lineSplit = fileIn.nextLine().split(",");

        if(!lineSplit[0].equals(number)) {
          for(int j = 0; j < lineSplit.length; j++) {
            resInfo.add(lineSplit[j]);
          }
          for(int k = 0; k < resInfo.size(); k++) {
            tempWriter.write(resInfo.get(k) + ",");
          }
          tempWriter.newLine();
          resInfo.clear();
        }
      }
      if(checkins.delete())
      tmp.renameTo(checkins);
      tempWriter.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
