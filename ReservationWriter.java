import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class ReservationWriter {

  private String fileName;

  ReservationWriter(String fileName) {

    this.fileName = fileName;

  }

  public String getFileName() {

    return fileName;

  }

  public void write(String number, String hotel, String type, String name, String nights, String rooms, String deposit, String currentDate) {

    try {

      File myFile = new File(fileName);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));

      bufferedWriter.write(number+"," + hotel+"," + type+"," + name+"," + nights+"," + rooms+"," + deposit+"," + currentDate);
      bufferedWriter.newLine();
      bufferedWriter.close();
      System.out.println("Info saved.");
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
