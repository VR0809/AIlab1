import java.util.*;

class JugState implements Comparable<JugState> {
    int jug1;
    int jug2;
    JugState parent;
    int cost;

    public JugState(int jug1, int jug2, JugState parent, int cost) {
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.parent = parent;
        this.cost = cost;
    }

    @Override
    public int compareTo(JugState other) {
        return Integer.compare(cost, other.cost);
    }
}

public class AstarWaterJug {

    public static void main(String[] args) {
        int jug1Capacity = 4;
        int jug2Capacity = 3;
        int targetAmount = 2;

        solveWaterJug(jug1Capacity, jug2Capacity, targetAmount);
    }

    public static void solveWaterJug(int jug1Capacity, int jug2Capacity, int targetAmount) {
        PriorityQueue<JugState> openList = new PriorityQueue<>();
        Set<String> visitedStates = new HashSet<>();

        JugState initialState = new JugState(0, 0, null, 0);
        openList.add(initialState);

        while (!openList.isEmpty()) {
            JugState currentState = openList.poll();

            if (currentState.jug1 == targetAmount || currentState.jug2 == targetAmount) {
                printSolution(currentState);
                return;
            }

            String currentStateKey = currentState.jug1 + "," + currentState.jug2;
            if (!visitedStates.contains(currentStateKey)) {
                visitedStates.add(currentStateKey);

                List<JugState> neighbors = generateNeighbors(currentState, jug1Capacity, jug2Capacity);

                for (JugState neighbor : neighbors) {
                    if (!visitedStates.contains(neighbor.jug1 + "," + neighbor.jug2)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        System.out.println("No solution found.");
    }

    private static List<JugState> generateNeighbors(JugState currentState, int jug1Capacity, int jug2Capacity) {
        List<JugState> neighbors = new ArrayList<>();

        // Fill jug1
        neighbors.add(new JugState(jug1Capacity, currentState.jug2, currentState, currentState.cost + 1));

        // Fill jug2
        neighbors.add(new JugState(currentState.jug1, jug2Capacity, currentState, currentState.cost + 1));

        // Empty jug1
        neighbors.add(new JugState(0, currentState.jug2, currentState, currentState.cost + 1));

        // Empty jug2
        neighbors.add(new JugState(currentState.jug1, 0, currentState, currentState.cost + 1));

        // Pour water from jug1 to jug2
        int pour1to2 = Math.min(currentState.jug1, jug2Capacity - currentState.jug2);
        neighbors.add(new JugState(currentState.jug1 - pour1to2, currentState.jug2 + pour1to2, currentState, currentState.cost + 1));

        // Pour water from jug2 to jug1
        int pour2to1 = Math.min(currentState.jug2, jug1Capacity - currentState.jug1);
        neighbors.add(new JugState(currentState.jug1 + pour2to1, currentState.jug2 - pour2to1, currentState, currentState.cost + 1));

        return neighbors;
    }

    private static void printSolution(JugState finalState) {
        List<JugState> path = new ArrayList<>();
        while (finalState != null) {
            path.add(finalState);
            finalState = finalState.parent;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            printState(path.get(i));
            System.out.println();
        }
    }

    private static void printState(JugState state) {
        System.out.println("Jug 1: " + state.jug1 + " | Jug 2: " + state.jug2);
    }
}