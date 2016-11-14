import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class HotelReader {

  private String fileName;
  private ArrayList<Hotel> hotels;

  HotelReader(String fileName) {

    this.fileName = fileName;
    hotels = readIn(fileName);

  }

  public String getFileName() {

    return fileName;

  }

  public ArrayList<Hotel> getHotelInfo() {

    return hotels;

  }

  private ArrayList<Hotel> readIn(String fileName) {

    ArrayList<Hotel> info = new ArrayList<Hotel>();

    try {

      File userFile = new File(fileName);
      Scanner fileIn = new Scanner(userFile);

      File checker = new File("checkerfile.csv");
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(checker, true));

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        bufferedWriter.write(lineSplit[0]);
        info.add(new Hotel(lineSplit[0]));
      }
    }
    catch(IOException e) {
      System.out.println("Error: File could not be found.");
    }

    return info;

  }
}
