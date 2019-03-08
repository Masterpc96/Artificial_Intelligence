import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Trial {
    private BufferedReader reader;

    private String problemName;
    private String knapsackDataType;
    private int dimension;
    private int numberOfItem;
    private int knapsackCapacity;
    private double minSpeed;
    private double maxSpeed;
    private double rentingRatio;
    private String edgeWeightType;

    private ArrayList<Place> places;


    public Trial(String path) {
        String line;
        try {
            reader = new BufferedReader(new FileReader(path));
            String splitted[];


            // reading settings
            for (int i = 0; i < 9; i++) {
                line = reader.readLine();
                splitted = line.split(":");
                switch (i) {
                    case 0:
                        problemName = splitted[1].trim();
                        break;
                    case 1:
                        knapsackDataType = splitted[1].trim();
                        break;
                    case 2:
                        dimension = Integer.parseInt(splitted[1].trim());
                        break;
                    case 3:
                        numberOfItem = Integer.parseInt(splitted[1].trim());
                        break;
                    case 4:
                        knapsackCapacity = Integer.parseInt(splitted[1].trim());
                        break;
                    case 5:
                        minSpeed = Double.parseDouble(splitted[1].trim());
                        break;
                    case 6:
                        maxSpeed = Double.parseDouble(splitted[1].trim());
                        break;
                    case 7:
                        rentingRatio = Double.parseDouble(splitted[1].trim());
                        break;
                    case 8:
                        edgeWeightType = splitted[1].trim();
                        break;
                }
            }

            reader.readLine();  // header
            places = new ArrayList<>(dimension);
            String splitPlace[];
            // reading places cords
            for (int i = 0; i < dimension; i++) {
                line = reader.readLine();
                splitPlace = line.split("\t");
                places.add(new Place(Integer.parseInt(splitPlace[0].trim()),
                        Double.parseDouble(splitPlace[1].trim()),
                        Double.parseDouble(splitPlace[2].trim())));
            }

            reader.readLine();  // header
            String splitItems[];
            int assigned;
            // assign items to places
            for (int i = 0; i < numberOfItem; i++) {
                line = reader.readLine();
                splitItems = line.split("\t");
                assigned = Integer.parseInt(splitItems[3].trim()) - 1;
                places.get(assigned).addItem(new Item(Integer.parseInt(splitItems[0].trim()),
                        Integer.parseInt(splitItems[1].trim()),
                        Integer.parseInt(splitItems[2].trim())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("problemName ");
        builder.append(problemName);
        builder.append("\nknapsackDataType ");
        builder.append(knapsackDataType);
        builder.append("\ndimension ");
        builder.append(dimension);
        builder.append("\nnumberOfItem ");
        builder.append(numberOfItem);
        builder.append("\nknapsackCapacity ");
        builder.append(knapsackCapacity);
        builder.append("\nminSpeed ");
        builder.append(minSpeed);
        builder.append("\nmaxSpeed ");
        builder.append(maxSpeed);
        builder.append("\nrentingRatio ");
        builder.append(rentingRatio);
        builder.append("\nedgeWeightType ");
        builder.append(edgeWeightType);
        builder.append("\n");
        for (Place place : places){
            builder.append(place.toString());
        }
        return builder.toString();
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String getProblemName() {
        return problemName;
    }

    public String getKnapsackDataType() {
        return knapsackDataType;
    }

    public int getDimension() {
        return dimension;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public int getKnapsackCapacity() {
        return knapsackCapacity;
    }

    public double getMinSpeed() {
        return minSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getRentingRatio() {
        return rentingRatio;
    }

    public String getEdgeWeightType() {
        return edgeWeightType;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }
}
