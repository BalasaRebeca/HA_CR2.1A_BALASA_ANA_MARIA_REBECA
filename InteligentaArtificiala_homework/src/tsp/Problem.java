package tsp;

public class Problem {
    private final City[] cities;

    public Problem(City[] cities) {
        this.cities = cities;
    }

    public City[] getCities() {
        return cities;
    }

    public double getDistance(City city1, City city2) {
        double dx = city1.getX() - city2.getX();
        double dy = city1.getY() - city2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
