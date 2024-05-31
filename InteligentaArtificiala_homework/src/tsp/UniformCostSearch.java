package tsp;

import java.util.*;

public class UniformCostSearch {
    private final Problem problem;

    public UniformCostSearch(Problem problem) {
        this.problem = problem;
    }

    public Result search() {
        City[] cities = problem.getCities();
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(node -> node.g));
        Map<List<City>, Double> costSoFar = new HashMap<>();
        List<City> startPath = Collections.singletonList(cities[0]);

        openList.add(new Node(startPath, 0.0));
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
                        openList.add(new Node(newPath, newCost));
                    }
                }
            }
        }
        return null;
    }

    private static class Node {
        List<City> path;
        double g;

        Node(List<City> path, double g) {
            this.path = path;
            this.g = g;
        }
    }
}
