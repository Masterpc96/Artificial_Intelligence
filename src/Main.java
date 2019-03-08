import java.util.ArrayList;

public class Main {

    public static void main(String args[]){


        Trial trial = new Trial("data/easy_0.ttp");
        ArrayList<Route> routes = new ArrayList<>(100);
        for (int i=0; i <100; i++){
            routes.add(new Route(i,trial.getPlaces(), trial.getMaxSpeed(), trial.getMinSpeed(), trial.getKnapsackCapacity()));
            System.out.println(routes.get(i).toString());
            System.out.println();
        }

    }
}
