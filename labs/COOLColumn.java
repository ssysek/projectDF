package labs;
import java.util.ArrayList;

public class COOLColumn extends Column {

    ArrayList<COOLValue> col;

    public COOLColumn(String name, Class<? extends Value> type)
    {
        super(name, type);
        col = new ArrayList<COOLValue>();
    }

}
