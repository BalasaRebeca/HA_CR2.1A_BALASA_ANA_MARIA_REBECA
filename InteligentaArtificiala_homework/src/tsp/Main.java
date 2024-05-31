package tsp;

public class Main {
    public static void main(String[] args) {
        City[] cities = {
                new City(0, 0),
                new City(1, 3),
                new City(4, 3),
                new City(6, 1)
        };

        Problem problem = new Problem(cities);

        BFS bfs = new BFS(problem);
        Result bfsResult = bfs.search();
        System.out.println("BFS Result: Path: " + bfsResult.getPath() + ", Cost: " + bfsResult.getCost());

        AStar aStar = new AStar(problem);
        Result aStarResult = aStar.search();
        System.out.println("A* Result: Path: " + aStarResult.getPath() + ", Cost: " + aStarResult.getCost());

        UniformCostSearch ucs = new UniformCostSearch(problem);
        Result ucsResult = ucs.search();
        System.out.println("Uniform Cost Search Result: Path: " + ucsResult.getPath() + ", Cost: " + ucsResult.getCost());
    }
}
