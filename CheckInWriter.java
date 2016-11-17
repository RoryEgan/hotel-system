import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class CheckInWriter {

  private String fileName;

  CheckInWriter(String fileName) {

    this.fileName = fileName;

  }

  public String getFileName() {

    return fileName;

  }

  public void write(String number, String currentDate) {

    try {

      File reservations = new File("ReservationInfo.csv");
      File checkins = new File(fileName);
      Scanner fileIn = new Scanner(reservations);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(checkins, true));

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        if(lineSplit[0].equals(number)) {
          bufferedWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[7]+"," + currentDate);
          bufferedWriter.newLine();
          System.out.println("Info saved.");
        }
      }
      bufferedWriter.close();
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

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
