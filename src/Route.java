import java.util.ArrayList;
import java.util.Random;

public class Route {
    private ArrayList<Place> nodes;

    private int id;

    private  double distance = 0;

    public Route(int id, ArrayList<Place> places) {
        this.id = id;
        nodes = new ArrayList<>(places.size());
        randomPath(new ArrayList<>(places));
        sumDistance();
    }

    // sum distance between all cities
    private void sumDistance() {
        Place start;
        Place stop;


        for (int i = 0; i < nodes.size() - 1; i++) {
            start = nodes.get(i);
            stop = nodes.get(i + 1);
            distance += calculateDistance(start, stop);
        }

        //back to home
        start = nodes.get(nodes.size()-1); // last city
        stop = nodes.get(0); // fist city
        distance += calculateDistance(start, stop);

    }

    // calculate distance between two places
    private double calculateDistance(Place start, Place stop) {
        return Math.sqrt(Math.pow((stop.getX() - start.getX()), 2) + Math.pow((stop.getY() - start.getY()), 2));
    }

    // generate path
    private void randomPath(ArrayList<Place> places) {
        Random random = new Random();
        while (!places.isEmpty()) {
            nodes.add(places.remove(random.nextInt(places.size())));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Route ");
        builder.append(id);
        builder.append(" { ");
        for (Place p : nodes) {
            builder.append(p.getIndex());
            builder.append(" ");
        }
        builder.append("} ");
        builder.append("overall distance ");
        builder.append(distance);
        return builder.toString();
    }

    public ArrayList<Place> getNodes() {
        return nodes;
    }

    public int getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }
}
