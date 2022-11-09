package sa;

public class SimulatedAnnealing {

    public static double acceptanceProbability(int energy, int newEnergy, double temperature) 
    {
        if (newEnergy < energy) 
        {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        City city = new City(60, 200);
        TourManager.addCity(city);
        City city2 = new City(180, 200);
        TourManager.addCity(city2);
        City city3 = new City(80, 180);
        TourManager.addCity(city3);
        City city4 = new City(140, 180);
        TourManager.addCity(city4);
        City city5 = new City(20, 160);
        TourManager.addCity(city5);
        City city6 = new City(100, 160);
        TourManager.addCity(city6);
        City city7 = new City(200, 160);
        TourManager.addCity(city7);
        City city8 = new City(140, 140);
        TourManager.addCity(city8);
        City city9 = new City(40, 120);
        TourManager.addCity(city9);
        City city10 = new City(100, 120);
        TourManager.addCity(city10);
        
        double temp = 10000;
        double coolingRate = 0.003;
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();
        
        Tour best = new Tour(currentSolution.getTour());
        while (temp > 1) 
        {
            Tour newSolution = new Tour(currentSolution.getTour());
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);
            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) 
            {
                currentSolution = new Tour(newSolution.getTour());
            }
            if (currentSolution.getDistance() < best.getDistance()) 
            {
                best = new Tour(currentSolution.getTour());
            }
            temp *= 1-coolingRate;
        }

        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);
    }
}