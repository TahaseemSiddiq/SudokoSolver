package sudoko;

public class SolverThread extends Thread{

    private int[][] board;
    private SudokoSolverListener sudokoSolverListener;

    public SolverThread(int[][] board, SudokoSolverListener sudokoSolverListener){
        this.board = board;
        this.sudokoSolverListener = sudokoSolverListener;
    }

    @Override
    public void run(){
        SudokoSolver sudokoSolver = new SudokoSolver(board, sudokoSolverListener);
        sudokoSolver.startSolve();
    }
   
}
