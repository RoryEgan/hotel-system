import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
public class CancellationWriter {

  private String fileName;
  private ArrayList<Reservation> reservationInfo;
  private ReservationReader reader = new ReservationReader("ReservationInfo.csv");

  CancellationWriter(String fileName) {

    this.fileName = fileName;
    reservationInfo = reader.readIn(fileName);

  }

  public String getFileName() {

    return fileName;

  }

  public ArrayList<Reservation> getReservationInfo() {

    return reservationInfo;

  }

  public void cancelReservation(String number, String cancelDate) {

    try {

      File reservations = new File("ReservationInfo.csv");
      File cancellations = new File(fileName);
      File tmp = File.createTempFile("tmp", "");

      Scanner fileIn = new Scanner(reservations);

      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(cancellations, true));
      BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tmp, true));

      ArrayList<String> info = new ArrayList<String>();
      ArrayList<String> resInfo = new ArrayList<String>();
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
        else if(!lineSplit[0].equals(number)) {
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
      bufferedWriter.close();
      tempWriter.close();
      System.out.println("\nCancellation completed.");
    }
    catch(FileNotFoundException e) {
      System.out.println("Error: File could not be found.");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
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
