package sudoko;

import java.util.Comparator;

public class SortByEmptyTiles implements Comparator<Subgrid> {
    @Override
    public int compare(Subgrid o1, Subgrid o2) {
        return Integer.compare(o1.emptyTiles.size(), o2.emptyTiles.size());
    }
}
