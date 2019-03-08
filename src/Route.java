import java.util.*;

public class Route {
    private ArrayList<Place> nodes;

    private Rucksack rucksack;

    private int id;

    private double distance = 0;

    private double time = 0;

    private double vMax;

    private double vMin;

    private double speed;

    public Route(int id, ArrayList<Place> places, double vMax, double vMin, double capacity) {
        this.id = id;
        this.vMax = vMax;
        this.vMin = vMin;
        rucksack = new Rucksack(capacity);
        nodes = new ArrayList<>(places.size());
        randomPath(new ArrayList<>(places));
        sumDistance();
    }

    // sum distance between all cities
    private void sumDistance() {
        Place start;
        Place stop;

        double temp;


        for (int i = 0; i < nodes.size() - 1; i++) {

            // current city
            start = nodes.get(i);

            // collect items from city
            chooseItemProfit(start.getItems());

            // calculate speed after collecting items in city
            speed = vMax - rucksack.getTotalWeight() * ((vMax - vMin)/rucksack.getCapacity());

            // city to go
            stop = nodes.get(i + 1);

            temp = calculateDistance(start, stop);

            distance += temp;

            // calculate time between two cities
            time += temp / speed;

        }

        //back to home
        // the last city
        start = nodes.get(nodes.size() - 1); // last city

        // calculate speed after collecting items in city
        speed = vMax - rucksack.getTotalWeight() * ((vMax - vMin)/rucksack.getCapacity());

        // colect items from last city
        chooseItemProfit(start.getItems());

        stop = nodes.get(0); // fist city

        temp = calculateDistance(start, stop);
        distance += temp;

        time += temp / speed;

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

    // choose the most profitable item
    private void chooseItemProfit(ArrayList<Item> items) {

        // sort items descending profit
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getProfit() - o2.getProfit();
            }
        });

        // add items to rucksack if total weight is less then capacity
        for (Item item : items) {
            if (rucksack.getTotalWeight() + item.getWeight() <= rucksack.getCapacity()) {
                rucksack.addItem(item);
            }
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
        builder.append("} \n");
        builder.append("overall distance ");
        builder.append(distance);
        builder.append("\noverall time ");
        builder.append(time);
        builder.append("\npicked items ");
        builder.append(rucksack.getItems().size());

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
