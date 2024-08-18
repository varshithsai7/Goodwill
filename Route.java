package train;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Station> stations;

    public Route(List<Station> stations) {
        this.stations = stations;
    }

    public int check(String stationName) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equalsIgnoreCase(stationName)) {
                return i;
            }
        }
        return -1;
    }

    public List<Station> getStations() {
        return stations;
    }

    public String give(int start, int end) {
        StringBuilder result = new StringBuilder();
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                result.append("-->").append(stations.get(i).getName());
            }
        } else {
            for (int i = start; i >= end; i--) {
                result.append("<--").append(stations.get(i).getName());
            }
        }
        return result.toString().trim();
    }

    public void display() {
        for (Station station : stations) {
            System.out.print(station.getName() + " <--> ");
        }
        System.out.println("terminates");
    }
}