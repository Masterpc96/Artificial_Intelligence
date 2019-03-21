import java.util.*;

public class Main {

    public static int tourSize = 10;
    public static int popSize = 100;
    public static int genPercent = 0;

    public static ArrayList<Double> temp = new ArrayList<Double>();


    public static void main(String args[]) {


        ArrayList<Route> newPop = new ArrayList<>(popSize);
        ArrayList<Route> oldPop = new ArrayList<>(popSize);
        ArrayList<Double> best = new ArrayList<>();
        Random r = new Random();
        Route child1;
        Route child2;
        int cut;
        ArrayList<Double> temporary = new ArrayList<>();


        Controller controller = new Controller();
        controller.initRobbery("data/easy_0.ttp");
        oldPop = controller.getRoutes();
        // w tym momencie mam populację startową

        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < popSize / 2; i++) {
                Route mother = controller.tour();
                Route father = controller.tour();

                while (mother == father) {
                    father = controller.tour();
                }

                cut = r.nextInt(father.getNodes().size());
                child1 = controller.crossover(father, mother, cut, i);

                newPop.add(controller.muttation(child1));

                child2 = controller.crossover(mother, father, cut, i);
                newPop.add(controller.muttation(child2));

            }
            // w tym momencie mam nową populację która jest skrzyżowana i zmutowana

            // podmieniam populację
            temporary = controller.setRoutes(new ArrayList<>(newPop));
            //0	-19010.031027038014
            //0	-12993.751570974771
            newPop.clear();

            best.add(Collections.max(temporary));
            best.add(Collections.min(temporary));
            System.out.print(j + "\t");
            System.out.println(best.get(j));
//            oldPop = new ArrayList<>(newPop);
//            newPop.clear();

        }
    }

    public static double calculateSD(ArrayList<Double> numArray) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.size();

        for (double num : numArray) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);
    }


}