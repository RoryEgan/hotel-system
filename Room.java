/**
 * Class to define a room.
 */
public class Room {


  private double[] rates = new double[7];

  Room(double[] roomCosts) {

    rates = roomCosts;
  }

  public double[] getRates() {

    return rates;

  }

}
