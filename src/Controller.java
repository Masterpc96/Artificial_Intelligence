import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class Controller {

    private ArrayList<Route> routes;



    public ArrayList<Double> setRoutes(ArrayList<Route> routes) {
        ArrayList<Double> evaluated = new ArrayList<>();
        this.routes = routes;
        for (int i = 0; i < routes.size(); i++) {
            routes.get(i).evaluate();
            evaluated.add(routes.get(i).getRatio());
        }
        return evaluated;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void initRobbery(String path) {
        Trial trial = new Trial(path);
        routes = new ArrayList<>(Main.popSize);

        for (int i = 0; i < Main.popSize; i++) {
            // rand population
            routes.add(new Route(i, trial.getNodes(), trial.getMaxSpeed(), trial.getMinSpeed(), trial.getKnapsackCapacity(), true));
            // collect items
            routes.get(i).evaluate();
        }
    }


    // robi dobrze
    public Route crossover(Route father, Route mother, int cut, int id) {
        List<Node> fatherList = father.getNodes();
        List<Node> motherList = mother.getNodes();

        ArrayList<Node> childList = new ArrayList<>();
        for (int i = 0; i < fatherList.size(); i++) {
            if (i < cut) {
                Node node = fatherList.get(i);
                childList.add(node);
            } else {
                Node node = motherList.get(i);
                if (!childList.contains(node)) {
                    childList.add(motherList.get(i));
                }
            }
        }

        if (childList.size() < fatherList.size()) {
            for (Node n : motherList) {
                if (!childList.contains(n)) {
                    childList.add(n);
                }
            }
        }


        Route route = new Route(id, childList, father.getvMax(), father.getvMin(), father.getCapacity(), false);

        return route;
    }

    // to też dobrze robi
    public Route muttation(Route route) {
        int percent = route.getNodes().size();
        percent *= Main.genPercent;
        percent /= 100;
        Random r = new Random();
        int first;
        int second;
        for (int i = 0; i < percent; i++) {
            first = r.nextInt(route.getNodes().size());
            second = r.nextInt(route.getNodes().size());

            Node temp = route.getNodes().get(first);
            route.getNodes().set(first, route.getNodes().get(second));
            route.getNodes().set(second, temp);
        }
        return route;
    }

    // zwraca dobry
    public Route tour() {
        ArrayList<Route> r = new ArrayList<>();

        Random random = new Random();
        Comparator<Route> comparator = new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {
                return (int) (o2.getRatio() - o1.getRatio());
            }
        };

        for (int i = 0; i < Main.tourSize; i++) {
            int nextint = random.nextInt(Main.popSize);
            r.add(routes.get(nextint));
        }

        Collections.sort(r, comparator);

        return r.get(0);
    }

}
