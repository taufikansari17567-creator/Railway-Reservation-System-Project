public class Train {
    int trainNo;
    String trainName;
    String source;
    String destination;
    int totalSeats;
    int availableSeats;

    Train( int trainNo, String trainName, String source, String destination, int totalSeats ){

        this.trainNo = trainNo;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    boolean bookSeat(){
        if( availableSeats > 0 ){
            availableSeats--;
            return true;
        }
        return false;
    }

    void cancelSeat(){
        if ( availableSeats < totalSeats ) {
            availableSeats++;
        }
    }

    public String toString(){
        return trainNo + " - " + trainName;
    }
}