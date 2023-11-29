import java.util.HashSet;
import java.util.Set;

class Stte {
    int jug1;
    int jug2;

    public Stte(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Stte state = (Stte) obj;
        return jug1 == state.jug1 && jug2 == state.jug2;
    }

    @Override
    public int hashCode() {
        return jug1 * 31 + jug2;
    }
}

public class DLSWaterJug {

    public static void main(String[] args) {
        solveWaterJug(4, 3, 2);
    }

    public static void solveWaterJug(int jug1Capacity, int jug2Capacity, int targetAmount) {
        Stte initialState = new Stte(0, 0);
        Stte goalState = new Stte(targetAmount, 0);

        Set<Stte> visited = new HashSet<>();

        if (depthLimitedSearch(initialState, goalState, jug1Capacity, jug2Capacity, 0, visited)) {
            System.out.println("Solution found:");
            printSolution(goalState);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean depthLimitedSearch(Stte currentState, Stte goalState, int jug1Capacity, int jug2Capacity, int depth, Set<Stte> visited) {
        if (currentState.equals(goalState)) {
            return true;
        }

        if (depth == 0) {
            return false;
        }

        visited.add(currentState);

        for (Stte neighbor : generateNeighbors(currentState, jug1Capacity, jug2Capacity)) {
            if (!visited.contains(neighbor)) {
                if (depthLimitedSearch(neighbor, goalState, jug1Capacity, jug2Capacity, depth - 1, visited)) {
                    System.out.println("Depth: " + (depth - 1));
                    printSolution(currentState);
                    return true;
                }
            }
        }

        return false;
    }

    private static Iterable<Stte> generateNeighbors(Stte currentState, int jug1Capacity, int jug2Capacity) {
        Set<Stte> neighbors = new HashSet<>();

        // Fill jug 1
        neighbors.add(new Stte(jug1Capacity, currentState.jug2));

        // Fill jug 2
        neighbors.add(new Stte(currentState.jug1, jug2Capacity));

        // Empty jug 1
        neighbors.add(new Stte(0, currentState.jug2));

        // Empty jug 2
        neighbors.add(new Stte(currentState.jug1, 0));

        // Pour jug 1 to jug 2
        int pour1to2 = Math.min(currentState.jug1, jug2Capacity - currentState.jug2);
        neighbors.add(new Stte(currentState.jug1 - pour1to2, currentState.jug2 + pour1to2));

        // Pour jug 2 to jug 1
        int pour2to1 = Math.min(currentState.jug2, jug1Capacity - currentState.jug1);
        neighbors.add(new Stte(currentState.jug1 + pour2to1, currentState.jug2 - pour2to1));

        return neighbors;
    }

    private static void printSolution(Stte state) {
        System.out.println("Jug 1: " + state.jug1 + ", Jug 2: " + state.jug2);
    }
}
