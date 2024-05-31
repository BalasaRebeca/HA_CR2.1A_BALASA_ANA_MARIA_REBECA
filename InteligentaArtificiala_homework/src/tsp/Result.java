package tsp;

import java.util.List;

public class Result {
    private final List<City> path;
    private final double cost;

    public Result(List<City> path, double cost) {
        this.path = path;
        this.cost = cost;
    }

    public List<City> getPath() {
        return path;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Path: " + path + ", Cost: " + cost;
    }
}
