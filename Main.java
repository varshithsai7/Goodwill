package train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Route route1 = new Route(createRoute1Stations()); // Blue Line
        Route route2 = new Route(createRoute2Stations()); // Red Line
        Route route3 = new Route(createRoute3Stations()); // Green Line

        System.out.println("Available Routes:");
        route1.display();
        route2.display();
        route3.display();

        System.out.print("Enter Start Station: ");
        String startStation = normalizeInput(scanner.nextLine());
        System.out.print("Enter Destination Station: ");
        String destinationStation = normalizeInput(scanner.nextLine());

        int startIndex = findRouteIndex(route1, route2, route3, startStation);
        int endIndex = findRouteIndex(route1, route2, route3, destinationStation);

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("Invalid station name entered. Please try again.");
            return;
        }

        String routeDescription = calculateRoute(route1, route2, route3, startStation, destinationStation);
        System.out.println("Route: " + routeDescription);

        int cost = calculateCost(routeDescription);
        System.out.println("Your cost: " + cost);
    }

    private static String normalizeInput(String input) {
        return input.trim().replace("_", " ");
    }

    private static int findRouteIndex(Route route1, Route route2, Route route3, String station) {
        int index = route1.check(station);
        if (index == -1) {
            index = route2.check(station);
            if (index == -1) {
                index = route3.check(station);
            }
        }
        return index;
    }

    private static List<Station> createRoute1Stations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Nagole"));
        stations.add(new Station("Uppal"));
        stations.add(new Station("Stadium"));
        stations.add(new Station("NGRI"));
        stations.add(new Station("Habsiguda"));
        stations.add(new Station("Tarnaka"));
        stations.add(new Station("Mettuguda"));
        stations.add(new Station("Secunderabad-East"));
        stations.add(new Station("Parade-Grounds"));
        stations.add(new Station("Paradise"));
        stations.add(new Station("Rasoolpura"));
        stations.add(new Station("Prakash Nagar"));
        stations.add(new Station("Begumpet"));
        stations.add(new Station("Ameerpet"));
        stations.add(new Station("Madhura Nagar"));
        stations.add(new Station("Yousufguda"));
        stations.add(new Station("Jubilee Hills Road No 5"));
        stations.add(new Station("Jubilee Hills Check Post"));
        stations.add(new Station("Peddamma Temple"));
        stations.add(new Station("Madhapur"));
        stations.add(new Station("Durgam Cheruvu"));
        stations.add(new Station("HITEC City"));
        stations.add(new Station("Raidurg"));
        return stations;
    }

    private static List<Station> createRoute2Stations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Miyapur"));
        stations.add(new Station("JNTU College"));
        stations.add(new Station("KPHB Colony"));
        stations.add(new Station("Kukatpally"));
        stations.add(new Station("Balanagar"));
        stations.add(new Station("Moosapet"));
        stations.add(new Station("Bharatnagar"));
        stations.add(new Station("Erragadda"));
        stations.add(new Station("ESI Hospital"));
        stations.add(new Station("SR Nagar"));
        stations.add(new Station("Ameerpet"));
        stations.add(new Station("Punjagutta"));
        stations.add(new Station("Irrum Manzil"));
        stations.add(new Station("Khairatabad"));
        stations.add(new Station("Lakdi Ka Pul"));
        stations.add(new Station("Assembly"));
        stations.add(new Station("Nampally"));
        stations.add(new Station("Gandhi Bhavan"));
        stations.add(new Station("Osmania Medical College"));
        stations.add(new Station("MGBS"));
        stations.add(new Station("Malakpet"));
        stations.add(new Station("New Market"));
        stations.add(new Station("Musarambagh"));
        stations.add(new Station("Dilsukhnagar"));
        stations.add(new Station("Chaitanyapuri"));
        stations.add(new Station("Victoria Memorial"));
        stations.add(new Station("LB Nagar"));
        return stations;
    }

    private static List<Station> createRoute3Stations() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Raidurg"));
        stations.add(new Station("HITEC City"));
        stations.add(new Station("Durgam Cheruvu"));
        stations.add(new Station("Madhapur"));
        stations.add(new Station("Peddamma Temple"));
        stations.add(new Station("Jubilee Hills Check Post"));
        stations.add(new Station("Jubilee Hills Road No 5"));
        stations.add(new Station("Yousufguda"));
        stations.add(new Station("Madhura Nagar"));
        stations.add(new Station("Ameerpet"));
        stations.add(new Station("Begumpet"));
        stations.add(new Station("Prakash Nagar"));
        stations.add(new Station("Rasoolpura"));
        stations.add(new Station("Paradise"));
        stations.add(new Station("Secunderabad-East"));
        stations.add(new Station("Mettuguda"));
        stations.add(new Station("Tarnaka"));
        stations.add(new Station("Habsiguda"));
        stations.add(new Station("NGRI"));
        stations.add(new Station("Stadium"));
        stations.add(new Station("Uppal"));
        stations.add(new Station("Nagole"));
        return stations;
    }

    private static String calculateRoute(Route route1, Route route2, Route route3, String startStation, String endStation) {
        StringBuilder result = new StringBuilder();
        int startIndex, endIndex;

        startIndex = route1.check(startStation);
        endIndex = route1.check(endStation);
        if (startIndex != -1 && endIndex != -1) {
            result.append(route1.give(startIndex, endIndex));
            return result.toString();
        }

        startIndex = route2.check(startStation);
        endIndex = route2.check(endStation);
        if (startIndex != -1 && endIndex != -1) {
            result.append(route2.give(startIndex, endIndex));
            return result.toString();
        }

        startIndex = route3.check(startStation);
        endIndex = route3.check(endStation);
        if (startIndex != -1 && endIndex != -1) {
            result.append(route3.give(startIndex, endIndex));
            return result.toString();
        }

        String[] junctions = {"Ameerpet", "Secunderabad-East", "MGBS"};
        for (String junction : junctions) {
            int junctionIndex = route1.check(junction);
            if (junctionIndex != -1) {
                if (route2.check(endStation) != -1) {
                    result.append(route1.give(startIndex, junctionIndex)).append(route2.give(route2.check(junction), endIndex));
                    return result.toString();
                }
                if (route3.check(endStation) != -1) {
                    result.append(route1.give(startIndex, junctionIndex)).append(route3.give(route3.check(junction), endIndex));
                    return result.toString();
                }
            }

            junctionIndex = route2.check(junction);
            if (junctionIndex != -1) {
                if (route1.check(endStation) != -1) {
                    result.append(route2.give(startIndex, junctionIndex)).append(route1.give(route1.check(junction), endIndex));
                    return result.toString();
                }
                if (route3.check(endStation) != -1) {
                    result.append(route2.give(startIndex, junctionIndex)).append(route3.give(route3.check(junction), endIndex));
                    return result.toString();
                }
            }

            junctionIndex = route3.check(junction);
            if (junctionIndex != -1) {
                if (route1.check(endStation) != -1) {
                    result.append(route3.give(startIndex, junctionIndex)).append(route1.give(route1.check(junction), endIndex));
                    return result.toString();
                }
                if (route2.check(endStation) != -1) {
                    result.append(route3.give(startIndex, junctionIndex)).append(route2.give(route2.check(junction), endIndex));
                    return result.toString();
                }
            }
        }

        return "Route not available for the selected stations.";
    }

    private static int calculateCost(String routeDescription) {
        int numberOfStations = routeDescription.split("-->").length - 1;
        return numberOfStations * 5;
    }
}