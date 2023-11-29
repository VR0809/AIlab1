import java.util.*;

class currentstate {
    int jug1;
    int jug2;

    public currentstate(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        state state = (state) obj;
        return jug1 == state.jug1 && jug2 == state.jug2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }
}

public class DFSWaterJug {

    public static void main(String[] args) {
        solveWaterJug(4, 3, 2);
    }

    public static void solveWaterJug(int jug1Capacity, int jug2Capacity, int targetAmount) {
        state initialState = new state(0, 0);
        state goalState = new state(targetAmount, 0);

        Set<state> visited = new HashSet<>();

        if (dfs(initialState, goalState, jug1Capacity, jug2Capacity, visited)) {
            System.out.println("Solution found:");
            printSolution(goalState);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean dfs(state currentState, state goalState, int jug1Capacity, int jug2Capacity, Set<state> visited) {
        if (currentState.equals(goalState)) {
            return true;
        }

        visited.add(currentState);

        List<state> neighbors = generateNeighbors(currentState, jug1Capacity, jug2Capacity);

        for (state neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor, goalState, jug1Capacity, jug2Capacity, visited)) {
                    printState(currentState);
                    return true;
                }
            }
        }

        return false;
    }

    private static List<state> generateNeighbors(state currentState, int jug1Capacity, int jug2Capacity) {
        List<state> neighbors = new ArrayList<>();

        // Fill jug 1
        neighbors.add(new state(jug1Capacity, currentState.jug2));

        // Fill jug 2
        neighbors.add(new state(currentState.jug1, jug2Capacity));

        // Empty jug 1
        neighbors.add(new state(0, currentState.jug2));

        // Empty jug 2
        neighbors.add(new state(currentState.jug1, 0));

        // Pour jug 1 to jug 2
        int pour1to2 = Math.min(currentState.jug1, jug2Capacity - currentState.jug2);
        neighbors.add(new state(currentState.jug1 - pour1to2, currentState.jug2 + pour1to2));

        // Pour jug 2 to jug 1
        int pour2to1 = Math.min(currentState.jug2, jug1Capacity - currentState.jug1);
        neighbors.add(new state(currentState.jug1 + pour2to1, currentState.jug2 - pour2to1));

        return neighbors;
    }

    private static void printSolution(state state) {
        List<state> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = state.parent;
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            printState(path.get(i));
            System.out.println();
        }
    }

    private static void printState(state state) {
        System.out.println("Jug 1: " + state.jug1 + ", Jug 2: " + state.jug2);
    }
}
