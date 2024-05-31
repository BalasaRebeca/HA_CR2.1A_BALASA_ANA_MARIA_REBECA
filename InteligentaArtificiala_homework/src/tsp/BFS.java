package tsp;

import java.util.*;

public class BFS {
    private final Problem problem;

    public BFS(Problem problem) {
        this.problem = problem;
    }

    public Result search() {
        City[] cities = problem.getCities();
        Queue<List<City>> queue = new LinkedList<>();
        queue.add(Arrays.asList(cities[0]));

        while (!queue.isEmpty()) {
            List<City> path = queue.poll();
            if (path.size() == cities.length) {
                path.add(cities[0]);
                return new Result(path, calculateCost(path));
            }
            for (City city : cities) {
                if (!path.contains(city)) {
                    List<City> newPath = new ArrayList<>(path);
                    newPath.add(city);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }

    private double calculateCost(List<City> path) {
        double cost = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += problem.getDistance(path.get(i), path.get(i + 1));
        }
        return cost;
    }
}
