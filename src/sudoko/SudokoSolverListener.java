package sudoko;

public interface SudokoSolverListener {

    void tileChanged(Location tileLocation, int newValue);    
}

