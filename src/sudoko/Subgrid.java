package sudoko;

import java.util.ArrayList;

public class Subgrid {
    int subgridIndex;
    ArrayList<Location> emptyTiles;

    public Subgrid(int subgridIndex, ArrayList<Location> emptyTiles){
        this.subgridIndex = subgridIndex;
        this.emptyTiles = emptyTiles;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Subgrid s){
            return(subgridIndex == s.subgridIndex && emptyTiles.equals(s.emptyTiles));
        }
        return false;
    }
}
