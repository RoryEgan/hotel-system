import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class CheckOutWriter {

  private String fileName;

  CheckOutWriter(String fileName) {

    this.fileName = fileName;

  }

  public String getFileName() {

    return fileName;

  }

  public void write(String number, String currentDate) {

    try {

      File checkins = new File("CheckIns.csv");
      File checkouts = new File(fileName);
      Scanner fileIn = new Scanner(checkins);
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(checkouts, true));

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        if(lineSplit[0].equals(number)) {
          bufferedWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[7]+"," + lineSplit[8]+"," + currentDate);
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
}
