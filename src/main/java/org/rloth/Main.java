package org.rloth;

import static org.rloth.ViewSpotFinder.findViewSpots;

public class Main {

    private static final String MESH_FILE_SMALL = "src/main/resources/mesh.json";
    private static final String MESH_FILE_MEDIUM = "src/main/resources/mesh_x_sin_cos_10000.json";
    private static final String MESH_FILE_LARGE = "src/main/resources/mesh_x_sin_cos_20000.json";
    private static final int MAX_VIEW_SPOTS = 20000;

    public static void main(String[] args) {
        isValidArgs(args);
        try {
            System.out.println(findViewSpots(getMeshFilePath(args[0]), parseNumberOfViewSpots(args[1])));
        } catch (NumberFormatException e) {
            printError("Invalid number format " + e.getMessage());
        } catch (IllegalArgumentException e) {
            printError("Invalid argument " + e.getMessage());
        } catch (Exception e) {
            printError("Unexpected error " + e.getMessage());
        }
    }

    private static void isValidArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java -jar view-spot-finder.jar <mesh file> <number-of-view-spots>,\nboth arguments are required");
        } else if (!args[0].matches("[012]")) {
            throw new IllegalArgumentException("Mesh file argument must be 0, 1, or 2");
        } else if (!args[1].matches("\\d+")) {
            throw new IllegalArgumentException("Number of view spots argument must be a positive integer");
        }
    }

    private static int parseNumberOfViewSpots(String arg) {
        int numberOfViewSpots = Integer.parseInt(arg);
        if (numberOfViewSpots < 0) {
            throw new IllegalArgumentException("Number of view spots argument must be a positive integer");
        } else if (numberOfViewSpots > MAX_VIEW_SPOTS) {
            numberOfViewSpots = MAX_VIEW_SPOTS;
        }
        return numberOfViewSpots;
    }

    private static String getMeshFilePath(String arg) {
        switch (arg) {
            case "0":
                return MESH_FILE_SMALL;
            case "1":
                return MESH_FILE_MEDIUM;
            case "2":
                return MESH_FILE_LARGE;
            default:
                throw new IllegalArgumentException("Mesh file argument must be 0, 1, or 2");
        }
    }

    private static void printError(String message) {
        System.err.println(message);
        System.exit(1);
    }
}