import java.util.*;

// This class implements a dynamic priority routing algorithm for an open-world
// action role-play game style simulation. It uses a modified A* search where node priorities
// are calculated based on urgency, proximity weight, and player preference.

public class DynamicPriorityRouting {

    // Node represents a quest/objective location in the game world
    static class Node implements Comparable<Node> {
        String name;
        int urgency;
        int proximityWeight;
        int playerPreference;
        List<Edge> edges = new ArrayList<>();
        int priority;
        int costFromStart = Integer.MAX_VALUE;
        Node cameFrom;

        public Node(String name, int urgency, int proximityWeight, int playerPreference) {
            this.name = name;
            this.urgency = urgency;
            this.proximityWeight = proximityWeight;
            this.playerPreference = playerPreference;
            this.priority = calculatePriority();
        }

        // Priority function simulating simulating player needs and task importance
        public int calculatePriority() {
            return urgency * proximityWeight + playerPreference;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    // Directional link with travel cost
    static class Edge {
        Node target;
        int cost;

        public Edge(Node target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {

        // Prompt user to input preferences
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter values for the first objective (1-10): ");
        int urgencyA = scanner.nextInt();
        int proximityWeightA = scanner.nextInt();
        int playerPreferenceA = scanner.nextInt();

        System.out.print("Enter values for the second objective (1-10): ");
        int urgencyB = scanner.nextInt();
        int proximityWeightB = scanner.nextInt();
        int playerPreferenceB = scanner.nextInt();

        System.out.print("Enter values for the third objective (1-10): ");
        int urgencyC = scanner.nextInt();
        int proximityWeightC = scanner.nextInt();
        int playerPreferenceC = scanner.nextInt();

        System.out.print("Enter values for the final objective (1-10): ");
        int urgencyGoal = scanner.nextInt();
        int proximityWeightGoal = scanner.nextInt();
        int playerPreferenceGoal = scanner.nextInt();

        // Build nodes
        Node start = new Node("Start", 1, 1, 1);
        Node a = new Node("A", urgencyA, proximityWeightA, playerPreferenceA);
        Node b = new Node("B", urgencyB, proximityWeightB, playerPreferenceB);
        Node c = new Node("C", urgencyC, proximityWeightC, playerPreferenceC);
        Node goal = new Node("Goal", urgencyGoal, proximityWeightGoal, playerPreferenceGoal);

        // Prompt user to input edge or travel costs
        System.out.println("Enter edge costs: ");
        int costSA = getInput(scanner, "Cost from Start to A");
        int costSB = getInput(scanner, "Cost from Start to B");
        int costBC = getInput(scanner, "Cost from B to C");
        int costAG = getInput(scanner, "Cost from A to Goal");
        int costCG = getInput(scanner, "Cost from C to Goal");

        scanner.close();

        // Create edges
        start.edges.add(new Edge(a, costSA));
        start.edges.add(new Edge(b, costSB));
        b.edges.add(new Edge(c, costBC));
        a.edges.add(new Edge(goal, costAG));
        c.edges.add(new Edge(goal, costCG));

        // Run algorithm
        List<Node> path = aStarSearch(start, goal);

        // Output path
        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.print(node.name + " -> ");
            }
            System.out.println("End");
        } else {
            System.out.println("No path found.");
        }
    }

    private static int getInput(Scanner scanner, String prompt) {
        System.out.print(prompt + " (1–10): ");
        return scanner.nextInt();
    }

    public static List<Node> aStarSearch(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        start.costFromStart = 0;
        start.priority = start.calculatePriority();
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current == goal) {
                return reconstructPath(current);
            }

            for (Edge edge : current.edges) {
                Node neighbor = edge.target;
                int tentativeCost = current.costFromStart + edge.cost;
                if (tentativeCost < neighbor.costFromStart) {
                    neighbor.costFromStart = tentativeCost;
                    neighbor.priority = tentativeCost + neighbor.calculatePriority(); // Priority = g(n) + h(n)
                    neighbor.cameFrom = current;
                    openSet.add(neighbor);
                }
            }
        }

        return null; // No valid path found
    }

    // Builds path from goal to start using backtracking
    public static List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(0, current); // Insert at front
            current = current.cameFrom;
        }
        return path;
    }
}
