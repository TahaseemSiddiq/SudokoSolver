package sudoko;

import java.util.ArrayList;

public class SudokoSolver {
    
    private int[][] board;
    private final SudokoSolverListener sudokoSolverListener;
    private Subgrid[] subgrids;
    private ArrayList<Location> emptyTiles;

    public SudokoSolver(int[][] board, SudokoSolverListener sudokoSolverListener){
        this.sudokoSolverListener = sudokoSolverListener;
        this.board = board;
        init();
    }

    private int getSubgridIndex(Location location){
        return ((Location.row/3)*3 + (location.column/3));
    }

    private Location[] getSubgridTiles(int subgridIndex){
        Location[] tiles = new Location[9];
        int index = 0;
        for (int row = (subgridIndex/3)*3;row < (subgridIndex/3)*3+3;row++){
            for (int column = (subgridIndex%3)*3; column < (subgridIndex%3)*3 +3; column++){
                tiles[index] = new Location(row, column);
                index++;
            }
        }
        return tiles;
    }

    private Location[] getColumnTiles(Location location){
        Location[] tiles = new Location[9];
        for (int row = 0;row < 9;row++){
            tiles[row] = new Location(row,location.column);
        }
        return tiles;
    }

    private Location[] getRowTiles(Location location){
        Location[] tiles = new Location[9];
        for(int column = 0;column < 9;column++){
            tiles[column] = new Location(location.row,column);
        }
        return tiles;
    }

    private ArrayList<Integer> takenSubgridNumbers(Location location){
        ArrayList<Integer> takenNumbers = new ArrayList<>();
        Location[] tiles = getSubgridTiles(location);
        for(Location l: tiles){
            int number = board[l.row][l.column];
            if (number != 0){
                takenNumbers.add(number);
            }
        }
        return takenNumbers;
    }

    private ArrayList<Integer> takenColumnNumbers(Location location){
        ArrayList<Integer> takenNumbers = new ArrayList<>();
        Location[] tiles = getColumnTiles(location);
        for (Location l : tiles){
            int number = board[l.row][l.column];
            if (number != 0){
                takenNumbers.add(number);
            }
        }
        return takenNumbers;
    }

    private ArrayList<Integer> takenRowNumbers(Location location){
        ArrayList<Integer> takenNumbers = new ArrayList<>();
        Location[] tiles = getRowTiles(location);
        for (Location l : tiles){
            int number = board[l.row][l.column];
            if (number != 0){
                takenNumbers.add(number);
            }
        }
        return takenNumbers;
    }

    private ArrayList<Integer> getPossibleMoves(Location location){
        
    }
}
