import java.util.ArrayList;

public class Reservation {

    ArrayList<Ticket> tickets = new ArrayList<>();
    int pnrCounter = 1001;

    Ticket bookTicket(Passenger p, Train t) {

        if (t.bookSeat()) {

            Ticket ticket = new Ticket(
                pnrCounter++, p, t
            );

            tickets.add(ticket);
            return ticket;
        }

        return null;
    }

    boolean cancelTicket(int pnr) {

        for (Ticket t : tickets) {

            if (t.pnr == pnr && !t.cancelled) {

                t.cancelled = true;
                t.train.cancelSeat();
                return true;
            }
        }

        return false;
    }
}