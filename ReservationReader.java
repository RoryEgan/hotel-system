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

  public ArrayList<Reservation> readIn(String fileName) {

    ArrayList<Reservation> info = new ArrayList<Reservation>();

    try {

      File reservationFile = new File(fileName);
      Scanner fileIn = new Scanner(reservationFile);

      String [] lineSplit;
      ArrayList<String> roomTypes = new ArrayList<String>();

      while(fileIn.hasNext()) {
        lineSplit = fileIn.nextLine().split(",");
        for(int i = 10; i < lineSplit.length; i++) {
          roomTypes.add(lineSplit[i]);
        }
        info.add(new Reservation(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5], lineSplit[6], lineSplit[7], lineSplit[8], roomTypes));
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

  public void purgeReservation(String number) {

    try {

      File reservations = new File("ReservationInfo.csv");
      File tmp = File.createTempFile("tmp", "");

      Scanner fileIn = new Scanner(reservations);

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
      if(reservations.delete())
      tmp.renameTo(reservations);
      tempWriter.close();
      System.out.println("\nSystem is up to date.");
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

  public double applyDiscount(String number) {

    int position = -1;
    double cost = 0;
    for(int i = 0; i < reservationInfo.size() && position == -1; i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        cost = Double.parseDouble(reservationInfo.get(i).getCost());
        cost = cost * 0.95;
      }
    }
    return cost;

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

  public double getBill(String number) {

    int position = -1;
    double bill = 0;
    for(int i = 0; i < reservationInfo.size() && position == -1; i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        bill = Double.parseDouble(reservationInfo.get(i).getCost()) - Double.parseDouble(reservationInfo.get(i).getDeposit());
      }
    }
    return bill;

  }
}
