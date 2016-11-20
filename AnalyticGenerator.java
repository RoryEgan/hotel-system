import java.util.Date;
public class AnalyticGenerator {

  private Hotel hotel;

  AnalyticGenerator() {

  }

  public void generateAnalytics(String hotel, Date startDate, String interval, int numIntervals) {

    ReservationReader reader = new ReservationReader("ReservationInfo.csv");

    //reader.analytics()
  }
}
