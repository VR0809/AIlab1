import java.util.*;

class Stat {
    public Stat parent;
    int jug1;
    int jug2;

    public Stat(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Stat state = (Stat) obj;
        return jug1 == state.jug1 && jug2 == state.jug2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }
}

public class BFSWaterJug {

    public static void main(String[] args) {
        solveWaterJug(4, 3, 2);
    }

    public static void solveWaterJug(int jug1Capacity, int jug2Capacity, int targetAmount) {
        Stat initialState = new Stat(0, 0);
        Stat goalState = new Stat(targetAmount, 0);

        Queue<Stat> queue = new LinkedList<>();
        Set<Stat> visited = new HashSet<>();

        queue.add(initialState);
        visited.add(initialState);

        while (!queue.isEmpty()) {
            Stat currentState = queue.poll();

            if (currentState.equals(goalState)) {
                printSolution(currentState);
                return;
            }

            List<Stat> neighbors = generateNeighbors(currentState, jug1Capacity, jug2Capacity);

            for (Stat neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("No solution found.");
    }

    private static List<Stat> generateNeighbors(Stat currentState, int jug1Capacity, int jug2Capacity) {
        List<Stat> neighbors = new ArrayList<>();

        // Fill jug 1
        neighbors.add(new Stat(jug1Capacity, currentState.jug2));

        // Fill jug 2
        neighbors.add(new Stat(currentState.jug1, jug2Capacity));

        // Empty jug 1
        neighbors.add(new Stat(0, currentState.jug2));

        // Empty jug 2
        neighbors.add(new Stat(currentState.jug1, 0));

        // Pour jug 1 to jug 2
        int pour1to2 = Math.min(currentState.jug1, jug2Capacity - currentState.jug2);
        neighbors.add(new Stat(currentState.jug1 - pour1to2, currentState.jug2 + pour1to2));

        // Pour jug 2 to jug 1
        int pour2to1 = Math.min(currentState.jug2, jug1Capacity - currentState.jug1);
        neighbors.add(new Stat(currentState.jug1 + pour2to1, currentState.jug2 - pour2to1));

        return neighbors;
    }

    private static void printSolution(Stat state) {
        List<Stat> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = state.parent;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            printState(path.get(i));
            System.out.println();
        }
    }

    private static void printState(Stat state) {
        System.out.println("Jug 1: " + state.jug1 + ", Jug 2: " + state.jug2);
    }
}
