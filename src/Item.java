public class Item {
    private int index;
    private int profit;
    private int weight;

    public Item(int index, int profit, int weight) {
        this.index = index;
        this.profit = profit;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }
}
