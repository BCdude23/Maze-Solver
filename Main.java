import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    static String[][] maze;

    public static void main(String[] args) throws FileNotFoundException {

        //scanning file and getting dimensions
        File file = new File("maze.dat");
        Scanner scan = new Scanner(file);
        String[] dimensions = scan.nextLine().split(" ");
        int xDim = Integer.parseInt(dimensions[0]);
        int yDim = Integer.parseInt(dimensions[1]);
        maze = new String[xDim][yDim];

        //searches for entrance of maze which is marked + while populating array
        int startCol = -1;
        int startRow = -1;

        for (int row = 0; row < xDim; row++) {
            String line = scan.nextLine();
            for (int col = 0; col < Math.min(line.length(), yDim); col++) {
                maze[row][col] = String.valueOf(line.charAt(col));
                if (maze[row][col].equals("+")) {
                    startCol = col;
                    startRow = row;
                } 
            }
        }
        scan.close();

        //start program with entrance point of maze
        new Main().move(startRow, startCol);
    }

//draws the finished maze
    private void printArr() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println("Maze Solved!");
        System.exit(0);
    }

//move method
    private void move(int x, int y) {
        if (checkNorth(maze, x, y, " ", "-")) {
            goNorth(x, y, false);
        }
        else if (checkSouth(maze, x, y, " ", "-")) {
            goSouth(x, y, false);
        }
        else if (checkEast(maze, x, y, " ", "-")) {
            goEast(x, y, false);
        }
        else if (checkWest(maze, x, y, " ", "-")) {
            goWest(x, y, false);
        }
        else 
            backTrack(x, y);
    }

//backtrack method
    private void backTrack(int x, int y) {
        if (checkNorth(maze, x, y, "+", "-")) {
            goNorth(x, y, true);
        }
        else if (checkSouth(maze, x, y, "+", "-")) {
            goSouth(x, y, true);
        }
        else if (checkEast(maze, x, y, "+", "-")) {
            goEast(x, y, true);
        }
        else if (checkWest(maze, x, y, "+", "-")) {
            goWest(x, y, true);
        }
        else{
            System.out.println("Maze Unsolvable.");
            printArr();
        }
    }


    //check north
    private boolean checkNorth(String[][] maze, int row, int col, String nextStep, String Exit) {
        if (row == 0 || maze[row-1][col] == "X" || maze[row-1][col] == ".") {
            return false;
        }
        if (maze[row-1][col].equals(Exit)) {
            printArr();
        }
        if (maze[row-1][col].equals(nextStep)) {
            return true;
        }
        return false;
    }
    //check south
    private boolean checkSouth(String[][] maze, int row, int col, String nextStep, String Exit) {
        if (row == maze.length-1 || maze[row+1][col] == "X" || maze[row+1][col] == ".") {
            return false;
        }
        if (maze[row+1][col].equals(Exit)) {
            printArr();
        }
        if (maze[row+1][col].equals(nextStep)) {
            return true;
        }
        return false;
    }
    //check east
    private boolean checkEast(String[][] maze, int row, int col, String nextStep, String Exit) {
        if (col == maze[0].length-1 || maze[row][col+1] == "X" || maze[row][col+1] == ".") {
            return false;
        }
        if (maze[row][col+1].equals(Exit)) {
            printArr();
        }
        if (maze[row][col+1].equals(nextStep)) {
            return true;
        }
        return false;
    }
    //check west
    private boolean checkWest(String[][] maze, int row, int col, String nextStep, String Exit) {
        if (col == 0 || maze[row][col-1] == "X" || maze[row][col-1] == ".") {
            return false;
        }
        if (maze[row][col-1].equals(Exit)) {
            printArr();
        }
        if (maze[row][col-1].equals(nextStep)) {
            return true;
        }
        return false;
    }

//go north method
    private void goNorth(int x, int y, boolean goingBack) {
        if (!goingBack) {
            maze[x][y] = "+";
            maze[x-1][y] = "+";
            move(x-1, y);
        }
        else if (goingBack) {
            maze[x][y] = ".";
            maze[x-1][y] = "+";
            move(x-1, y);
        }
//go south method
    }
    private void goSouth(int x, int y, boolean goingBack) {
        if (!goingBack) {
            maze[x][y] = "+";
            maze[x+1][y] = "+";
            move(x+1, y);
        }
        else if (goingBack) {
            maze[x][y] = ".";
            maze[x+1][y] = "+";
            move(x+1, y);
        }
    }
    //go east method
    private void goEast(int x, int y, boolean goingBack) {
        if (!goingBack) {
            maze[x][y] = "+";
            maze[x][y+1] = "+";
            move(x, y+1);
        }
        else if (goingBack) {
            maze[x][y] = ".";
            maze[x][y+1] = "+";
            move(x, y+1);
        }
    }
    //go west method
    private void goWest(int x, int y, boolean goingBack) {
        if (!goingBack) {
            maze[x][y] = "+";
            maze[x][y-1] = "+";
            move(x, y-1);
        }
        else if (goingBack) {
            maze[x][y] = ".";
            maze[x][y-1] = "+";
            move(x, y-1);
        }
    }
}
