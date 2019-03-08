import java.util.ArrayList;

public class Main {

    public static void main(String args[]){


        Trial trial = new Trial("data/easy_1.ttp");
        ArrayList<Route> routes = new ArrayList<>(100);
        for (int i=0; i <100; i++){
            routes.add(new Route(i,trial.getPlaces()));
            System.out.println(routes.get(i).toString());
        }

    }
}
