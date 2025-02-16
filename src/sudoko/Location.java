package sudoko;

public class Location {

    public int row = -1;
    public int column = -1
     
    public Location(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Location location){
            if (location.row == row && location.column == column){
                return true;
            }
        }
        return false;
    }
}