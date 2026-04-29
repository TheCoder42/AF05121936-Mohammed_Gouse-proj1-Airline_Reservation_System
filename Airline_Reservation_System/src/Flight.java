public class Flight {
    int id;
    String name, source, destination;
    int seats;
    double price;

    public Flight(String name, String source, String destination, int seats, double price) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.price = price;
    }
}