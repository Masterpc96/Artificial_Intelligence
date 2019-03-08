import java.util.ArrayList;

public class Rucksack {
    private double totalWeight;
    private ArrayList<Item> items;
    private double totalProfit;
    private double capacity;

    public Rucksack(double capacity) {
        this.capacity = capacity;
        items = new ArrayList<>();
        totalWeight = 0;
        totalProfit = 0;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void addItem(Item item) {
        items.add(item);
        totalWeight += item.getWeight();
        totalProfit += item.getProfit();
    }
}
