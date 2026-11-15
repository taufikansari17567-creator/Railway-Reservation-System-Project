public class Ticket {

    int pnr;
    Passenger passenger;
    Train train;
    boolean cancelled = false;

    Ticket(int pnr, Passenger passenger, Train train) {

        this.pnr = pnr;
        this.passenger = passenger;
        this.train = train;
    }

    String getTicketDetails() {

        return "\n========== RAILWAY TICKET ==========\n"
            + "PNR Number: " + pnr + "\n"
            + "Passenger Name: " + passenger.name + "\n"
            + "Age: " + passenger.age + "\n"
            + "Gender: " + passenger.gender + "\n"
            + "Train Number: " + train.trainNo + "\n"
            + "Train Name: " + train.trainName + "\n"
            + "Source: " + train.source + "\n"
            + "Destination: " + train.destination + "\n"
            + "Status: " + (cancelled ? "CANCELLED" : "CONFIRMED")
            + "\n====================================\n";
    }
}