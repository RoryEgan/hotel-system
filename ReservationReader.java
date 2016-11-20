import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

/**
 * A Class to read in from the reservtions file
 */
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

/**
 * Method to read in from the file
 * @param  fileName string of the file name to be read in
 * @return          arraylist of reservations that have been read in
 */
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

/**
 * Method to purge reservations from the fle that are over a month old
 * @param number number of the reservaton to be purged
 */
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
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }

  }

/**
 * Method to apppy a 5% discount to advanced purchases
 * @param  number number of reservation to be discounted
 * @return        double of the new amount to be charged
 */
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

/**
 * Method to get the date of a particular reservation
 * @param  number number of the reservation the date is needed from
 * @return        string of the date
 */
  public String getDate(String number) {

    String date="";

    for(int i = 0; i < reservationInfo.size(); i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        date = reservationInfo.get(i).getDate();
      }
    }

    return date;
  }

/**
 * Method to check the type of a given reservation.
 * @param  number number of the reservaton to be checked
 * @return        string of the type the reservation is
 */
  public String checkType(String number) {

    String type="";

    for(int i = 0; i < reservationInfo.size(); i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        type = reservationInfo.get(i).getType();
      }
    }

    return type;
  }

/**
 * Method to check a position within the file
 * @param  number number of reservation to be checked
 * @return        int of the posiition
 */
  public int checkNumber(String number) {

    int position = -1;
    for(int i = 0; i < reservationInfo.size() && position == -1; i++) {
      if(reservationInfo.get(i).getNumber().equals(number)) {
        position = i;
      }
    }
    return position;
  }

/**
 * Method to get the final cost of a reservation
 * @param  number number to get the cost of
 * @return        final cost of the desired reservation
 */
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
