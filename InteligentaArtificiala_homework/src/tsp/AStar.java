package tsp;

import java.util.*;

public class AStar {
    private final Problem problem;

    public AStar(Problem problem) {
        this.problem = problem;
    }

    public Result search() {
        City[] cities = problem.getCities();
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.f));
        Map<List<City>, Double> costSoFar = new HashMap<>();
        List<City> startPath = Collections.singletonList(cities[0]);

        openList.add(new Node(startPath, 0.0, heuristic(startPath)));
        costSoFar.put(startPath, 0.0);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.path.size() == cities.length) {
                List<City> finalPath = new ArrayList<>(current.path);
                finalPath.add(cities[0]);
                return new Result(finalPath, current.g + problem.getDistance(current.path.get(current.path.size() - 1), cities[0]));
            }

            for (City city : cities) {
                if (!current.path.contains(city)) {
                    List<City> newPath = new ArrayList<>(current.path);
                    newPath.add(city);
                    double newCost = current.g + problem.getDistance(current.path.get(current.path.size() - 1), city);
                    if (!costSoFar.containsKey(newPath) || newCost < costSoFar.get(newPath)) {
                        costSoFar.put(newPath, newCost);
                        openList.add(new Node(newPath, newCost, newCost + heuristic(newPath)));
                    }
                }
            }
        }
        return null;
    }

    private double heuristic(List<City> path) {
        double remainingCost = 0.0;
        City[] cities = problem.getCities();
        for (City city : cities) {
            if (!path.contains(city)) {
                remainingCost += problem.getDistance(path.get(path.size() - 1), city);
            }
        }
        return remainingCost;
    }

    private static class Node {
        List<City> path;
        double g;
        double f;

        Node(List<City> path, double g, double f) {
            this.path = path;
            this.g = g;
            this.f = f;
        }
    }
}
