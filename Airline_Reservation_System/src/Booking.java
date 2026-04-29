public class Booking {
    int bookingId;
    int passengerId;
    int flightId;

    public Booking(int passengerId, int flightId) {
        this.passengerId = passengerId;
        this.flightId = flightId;
    }
}
