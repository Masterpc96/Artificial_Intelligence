import java.util.ArrayList;

public class Place {
    private int index;
    private double x;
    private double y;

    ArrayList<Item> items;

    public Place(int index, double x, double y) {
        this.index = index;
        this.x = x;
        this.y = y;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Index ");
        builder.append(index);
        builder.append("\t");
        builder.append("X ");
        builder.append(x);
        builder.append("\t");
        builder.append("Y ");
        builder.append(y);
        builder.append("\n");

        for (Item item : items){
            builder.append("Index ");
            builder.append(item.getIndex());
            builder.append("\t");
            builder.append(item.getProfit());
            builder.append(" Profit ");
            builder.append("\t");
            builder.append(" Weight ");
            builder.append(item.getWeight());
            builder.append("\n\n");
        }
        return builder.toString();
    }
}
