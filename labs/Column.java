package labs;
import java.util.ArrayList;

public class Column implements Cloneable
{
    public String columnName;
    public   Class<? extends Value> columnType;
    public ArrayList<Value> col;
    public Column(String name, Class<? extends Value> type)
    {
        //Class<?> class_def = null;

        columnName = name;
        columnType = type;
        /*try{
            class_def = Class.forName(type);
        } catch (Exception e) {
            System.out.println("Invalid type.");
        }*/

        col = new ArrayList<>();
    }

    public COOLColumn ColumnToCOOLColumn(Object hide)
    {
        ArrayList COOLcol = new ArrayList<COOLValue>();
        int j=0, originalSize = col.size(), current;
        while(j<originalSize)
        {
            current = col.indexOf(hide);
            if(current != 0)
                COOLcol.add(new COOLValue(j,col.get(0)));
            col.remove(0);
            j++;
        }
        COOLColumn result = new COOLColumn(columnName,columnType);
        result.col = COOLcol;
        return result;
    }

    @Override
    protected Column clone() {
    try {
        return (Column) super.clone();
    } catch (CloneNotSupportedException e) {
        System.out.println(this.getClass().getName() + " nie implementuje Cloneable...");
        return null;
    }
}
}