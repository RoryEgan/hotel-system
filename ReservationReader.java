import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class ReservationReader {

  private String fileName;
  private ArrayList<Reservation> reservationInfo;

  ReservationReader(String fileName) {

    this.fileName = fileName;
    reservationInfo = readIn(fileName);

  }

  public String getFileName() {

    return fileName;

  }

  public ArrayList<Reservation> getReservationInfo() {

    return reservationInfo;

  }

  private ArrayList<Reservation> readIn(String fileName) {

    ArrayList<Reservation> info = new ArrayList<Reservation>();

    try {

      File reservationFile = new File(fileName);
      Scanner fileIn = new Scanner(reservationFile);

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        info.add(new Reservation(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5], lineSplit[6], lineSplit[7]));
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return info;

  }

  public void cancelReservation(String number, String cancelDate) {

    try {

      File reservations = new File(fileName);
      File cancellations = new File("CancellationInfo.csv");
      File tmp = File.createTempFile("tmp", "");


      Scanner fileIn = new Scanner(reservations);

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(cancellations, true));
      BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tmp, true));

      String [] lineSplit;

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        if(!lineSplit[0].equals(number)) {
          tempWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[6]);
          tempWriter.newLine();
        }
        else {
          bufferedWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[7]+"," + cancelDate);
          bufferedWriter.newLine();
        }
      }
      tempWriter.close();
      bufferedWriter.close();
      if(reservations.delete())
      tmp.renameTo(reservations);
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void purgeReservations(int number) {

    try {

      File reservations = new File(fileName);
      File tmp = File.createTempFile("tmp", "");

      Scanner fileIn = new Scanner(reservations);
      BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tmp, true));
      String [] lineSplit;
      int index = 1;

      while(fileIn.hasNext() && index < reservationInfo.size()) {
        lineSplit = fileIn.nextLine().split(",");
        if(lineSplit[0] != reservationInfo.get(index).getNumber()) {
          tempWriter.write(lineSplit[0]+"," + lineSplit[1]+"," + lineSplit[2]+"," + lineSplit[3]+"," + lineSplit[4]+"," + lineSplit[5]+"," + lineSplit[6]+"," + lineSplit[7]);
          tempWriter.newLine();
          index++;
        }
      }
      tempWriter.close();
      if(reservations.delete())
      tmp.renameTo(reservations);
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  public String getDate(String number) {

    String date="";

    for(int i = 0; i < reservationInfo.size(); i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        date = reservationInfo.get(i).getDate();
      }
    }

    return date;
  }

  public String checkType(String number) {

    String type="";

    for(int i = 0; i < reservationInfo.size(); i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        type = reservationInfo.get(i).getType();
      }
    }

    return type;
  }

  public int checkNumber(String number) {

    int position = -1;
    for(int i = 0; i < reservationInfo.size() && position == -1; i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        position = i;
      }
    }
    return position;
  }
}
