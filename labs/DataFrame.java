package labs;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataFrame
{

    public ArrayList<Column> columns;
    public int n=0;
    public DataFrame()
    {
        columns = new ArrayList<>();
    	columns.add(new Column("kol1", COOLValue.class));
    }

//    public DataFrame(String[] names, Class<? extends Value>types[]) {
//        for (int i = 0; i < names.length; i++) {
//            columns.add(new Column(names[i], types[i]));
//        }
//    }

    public DataFrame(String[] colnames, ArrayList<Class<? extends Value>> coltypes)
    {
        columns = new ArrayList<>();
        for(int i=0; i<colnames.length;i++)
            columns.add(new Column(colnames[i],coltypes.get(i)));
    }

//    public DataFrame(String filename, ArrayList<Class<? extends Value>> coltypes)
//    {
//        this(filename, coltypes, true);
//    }

//    public DataFrame(String filename, ArrayList<Class<? extends Value>> coltypes, boolean header)
//    {
//        columns = new ArrayList<>();
//        BufferedReader br = null;
//        try
//        {
//            FileInputStream fstream = new FileInputStream(filename);
//            br = new BufferedReader(new InputStreamReader(fstream));
//            String line, name;
//            String[] colnames, row;
//
//            if((line = br.readLine()) != null && header)
//            {
//                colnames = line.split(",");
//                for(int i=0; i<colnames.length;i++)
//                    columns.add(new Column(colnames[i],coltypes.get(i)));
//            }
//            else if(!header)
//            {
//                Scanner keyboard = new Scanner(System.in);
//                for(int i=0; i<coltypes.size();i++)
//                {
//                    System.out.println("Enter the name of column number" + (i+1));
//                    name = keyboard.nextLine();
//                    columns.add(new Column(name,coltypes.get(i)));
//                }
//            }
//
//            while((line = br.readLine()) != null)
//            {
//                row = line.split(",");
//                for(int i=0; i<row.length;i++)
//                {
//                    Class<? extends Value> clazz = coltypes.get(i);
//                    if( IntegerValue.class == clazz)
//                    {
//                        columns.get(i).col.add(new IntegerValue(Integer.parseInt( row[i] )));
//                        continue;
//                    }
//                    if( FloatValue.class == clazz)
//                    {
//                        columns.get(i).col.add(new FloatValue(Float.parseFloat( row[i] )));
//                        continue;
//                    }
//                    if( DoubleValue.class == clazz)
//                    {
//                        columns.get(i).col.add(new DoubleValue(Double.parseDouble( row[i] )));
//                        continue;
//                    }
//                    if( StringValue.class == clazz)
//                    {
//                        columns.get(i).col.add(new StringValue(row[i]));
//                        continue;
//                    }
//                    if( DateTimeValue.class == clazz)
//                    {
//                        DateTimeValue dtv = null;
//                        columns.get(i).col.add(dtv.create(row[i]));
//                    }
//
//                }
//            }
//        } catch(FileNotFoundException e) {
//            System.err.println("Caught FileNotFoundException: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } catch(IOException e) {
//            System.err.println("Caught IOException: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (null != br)
//            {
//                try{ br.close(); }
//                catch (IOException e) {System.err.println("Caught IOException while closing reader: " + e.getMessage());e.printStackTrace();}
//            }
//        }
//    }

    public DataFrame(String fileName, ArrayList<Class<? extends Value>> types, boolean header) {
        String line = "";
        String cvsSplitBy = ",";
        columns = new ArrayList<>();
        int x=0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            int counter = 0;
            if (header) {
                line = br.readLine();
                String[] col = line.split(cvsSplitBy);
                System.out.println(col.length);
                for (int i = 0; i < types.size(); i++) {
                    columns.add(new Column(col[i], types.get(i)));
                }
            }

            while ((line = br.readLine()) != null) {
                String[] col = line.split(cvsSplitBy);
                Value[] objs = new Value[types.size()];
                if (header) {
                    for (int i = 0; i < types.size(); i++) {
                        if (types.get(i) == IntegerValue.class) {
                            IntegerValue coll = new IntegerValue(Integer.parseInt(col[i]));
                            objs[i] = coll;
                        } else if (types.get(i) == DoubleValue.class) {
                            DoubleValue coll = new DoubleValue(Double.parseDouble(col[i]));
                            objs[i] = coll;
                        } else if (types.get(i) == FloatValue.class) {
                            FloatValue coll = new FloatValue(Float.parseFloat(col[i]));
                            objs[i] = coll;
                        } else if (types.get(i) == StringValue.class) {
                            StringValue coll = new StringValue(col[i]);
                            objs[i] = coll;
                        } else if (types.get(i) == CharValue.class) {
                            char[] colll = col[i].toCharArray();
                            CharValue coll = new CharValue(colll[0]);
                            objs[i] = coll;
//                        } else if (types.get(i) == DateTimeValue.class) {
//                            DateTimeValue coll = new DateTimeValue(col[i]);
//                            objs[i] = coll;
                        }
                    }
                        addRow(objs);
                        x++;

                }

                counter++;
            }

//            System.out.println("zzzzz"+x);
//            System.out.println("asdf"+this.n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int Size()
    {
        return columns.get(0).col.size();
    }

    public Column Get(String colname)
    {
        for(Column c:columns)
            if(c.columnName.equals(colname))
                return c;
        throw (new IllegalArgumentException("No such column"));
    }


    public DataFrame Iloc(int i)
    {
        String[] colnames = new String[columns.size()];
        ArrayList<Class<? extends Value>> coltypes = new ArrayList<>();
        for(int x=0; x<columns.size(); x++)
        {
            colnames[x] = columns.get(x).columnName;
            coltypes.add(columns.get(x).columnType);
        }
        DataFrame rowOfIndex = new DataFrame(colnames, coltypes);
        for(int c=0; c<columns.size(); c++)
        {
            rowOfIndex.columns.get(c).col.add(columns.get(c).col.get(i));
        }
        return rowOfIndex;
    }

    public DataFrame Iloc(int from, int to)
    {
        String[] colnames = new String[columns.size()];
        ArrayList<Class<? extends Value>> coltypes = new ArrayList<>();
        for(int x=0; x<columns.size(); x++)
        {
            colnames[x] = columns.get(x).columnName;
            coltypes.add(columns.get(x).columnType);
        }
        DataFrame rowsOfIndex = new DataFrame(colnames, coltypes);
        for(int i=from; i<to+1; i++)
        {
            for(int c=0; c<columns.size(); c++)
            {
                rowsOfIndex.columns.get(c).col.add(columns.get(c).col.get(i));
            }
        }
        return rowsOfIndex;
    }

    public void addRow(Value[] values) {
        int counter = 0;
        for (Column i : columns) {
            if (i.columnType.isInstance(values[counter])) {
                i.col.add(values[counter]);
            } else {
                i.col.add(null);
            }
            counter++;
        }
        n++;
    }

    private void heapify(String name, int x, int i) {
        for (Column k : columns) {
            if (k.columnName.equals(name)) {
                int largest = i;
                int l = 2*i + 1;
                int r = 2*i + 2;
                    if (l < x && k.col.get(l).gt(k.col.get(largest))) largest = l;
                    if (r < x && k.col.get(r).gt(k.col.get(largest))) largest = r;

                if (largest != i) {
                    for (Column q : columns) {
                        Value swap = q.col.get(i);
                        q.col.set(i, q.col.get(largest));
                        q.col.set(largest, swap);
                    }
                    heapify(name, x, largest);
                }

            }
        }
    }
    public void heapSort(String name) {
        for (Column k : columns) {
            if (k.columnName.equals(name)) {
                int j = k.col.size();
                for (int i = j / 2 - 1; i >= 0; i--)
                    heapify(name, j, i);
                for (int i = j - 1; i >= 0; i--) {
                    for (Column q : columns) {
                        Value temp = q.col.get(0);
                        q.col.set(0, q.col.get(i));
                        q.col.set(i, temp);
                    }
                    heapify(name, i, 0);
                }
            }
        }
    }

    public DfList groupby(String name) {
        String[] names = new String[columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        LinkedList<DataFrame> ldf = new LinkedList<>();
        this.heapSort(name);
        counter = 0;
        for (Column i : columns) {
            if (i.columnName.equals(name)) {
                int whereToStart = 1;
                int whereToStart2 = 0;
                if (i.col.size() > 1) {
                    while (whereToStart < i.col.size()) {
                        DataFrame df = new DataFrame(names, types);
                        Value[] v = new Value[columns.size()];
                        int c = 0;
                        for (Column j : columns) {
                            v[c] = j.col.get(whereToStart2);
                            c++;
                        }
                            df.addRow(v);

                        while (i.col.get(whereToStart).equals(df.columns.get(counter).col.get(df.columns.get(counter).col.size() - 1))) {
                            v = new Value[columns.size()];
                            c = 0;
                            for (Column j : columns) {
                                v[c] = j.col.get(whereToStart);
                                c++;
                            }

                                df.addRow(v);
                            whereToStart++;
                            if (whereToStart == i.col.size()) break;
                        }
                        ldf.add(df);
                        whereToStart2 = whereToStart;
                        whereToStart++;
                    }
                    if(!i.col.get(i.col.size()-1).equals(ldf.get(ldf.size()-1).columns.get(counter).col.get(0))) {
                        DataFrame df = new DataFrame(names, types);
                        Value[] v = new Value[columns.size()];
                        int c = 0;
                        for (Column j : columns) {
                            v[c] = j.col.get(i.col.size()-1);
                            c++;
                        }

                            df.addRow(v);

                        ldf.add(df);
                    }
                } else {
                    DataFrame df = new DataFrame(names, types);
                    Value[] v = new Value[columns.size()];
                    int c = 0;
                    for (Column j : columns) {
                        v[c] = j.col.get(0);
                        c++;
                    }
                        df.addRow(v);

                    ldf.add(df);
                }
            }
            counter++;
        }
        DfList dfList = new DfList(ldf, new String[] {name});
        return dfList;
    }

    public void print() {
        int counter = 0;
        for (Column i : columns) {
            if (counter == columns.size() - 1) {
                System.out.print(i.columnName);
            }
            else {
                System.out.print(i.columnName + " | ");
            }
            counter++;
        }
        System.out.println();
        for (int i = 0; i < columns.size(); i++) {
            System.out.print("-------");
        }
        System.out.println();
        for (int j = 0; j < n; j++) {
            counter = 0;
            for (Column i : columns) {
                Value tmp = i.col.get(j);
                if (counter == columns.size() - 1) {
                    if (tmp == null) {
                        System.out.print("null");
                    } else {
                        if (i.columnType == DateTimeValue.class) {
                            System.out.print(tmp.toString());
                        } else {
                            System.out.print(tmp.getInstance());
                        }
                    }
                }
                else {
                    if (tmp == null) {
                        System.out.print("null" + " | ");
                    } else {
                        if (i.columnType == DateTimeValue.class) {
                            System.out.print(tmp.toString() + "|");
                        } else {
                            System.out.print(tmp.getInstance() + " | ");
                        }
                    }
                }
                counter++;
            }
            System.out.println();
        }
        for (int i = 0; i < columns.size(); i++) {
            System.out.print("-------");
        }
        System.out.println();
        counter = 0;
        for (Column i : columns) {
            if (counter == columns.size() - 1) {
                System.out.print(i.columnType);
            }
            else {
                System.out.print(i.columnType + " | ");
            }
            counter++;
        }
        System.out.println();
    }

    public static void main(String[] argv)
    {
        ArrayList<Class<? extends Value>> types = new ArrayList<>();
        //for(int i=0;i<types.length;i++)
        //   types[i]=DoubleValue.class;
        types.add(CharValue.class);
        types.add(StringValue.class);
        types.add(DoubleValue.class);
        types.add(DoubleValue.class);

        DataFrame test = new DataFrame("/users/maciejsyska/downloads/groupby.csv", types, true);
        System.out.println(test.columns.get(0).columnName);
        System.out.println(test.columns.get(1).columnType);
        System.out.println(test.columns.get(1).col.size());
        //System.out.println(test.Iloc(0,3).columns.get(2).col.get(1).Get());
        //System.out.println(test.Get("x").col.get(15).Get());
        System.out.println(test.Size());

//        DataFrame test2 = new DataFrame("/users/maciejsyska/downloads/sparse.csv", types);
//        SparseDataFrame sdf = new SparseDataFrame(test2,new DoubleValue(0.0)); //Nullptr exception
//        System.out.println(sdf.columns.get(0).columnName);
//        System.out.println(sdf.columns.get(1).columnType);
//        System.out.println(sdf.columns.get(0).col.size());
//        System.out.println(sdf.columns.get(0).col.get(0).Get());
//        System.out.println(sdf.Size());
//
//        DataFrame test3 = sdf.ToDense();
//        System.out.println(test3.columns.get(0).columnName);
//        System.out.println(test3.columns.get(1).columnType);
//        System.out.println(test3.columns.get(1).col.size());
//        System.out.println(test3.Size());

        Value testValue = new IntegerValue(5);
        System.out.println(testValue.add(new IntegerValue(1)).Get());
        System.out.println(testValue.sub(new IntegerValue(1)).Get());
        System.out.println(testValue.mul(new IntegerValue(2)).Get());
        System.out.println(testValue.div(new IntegerValue(3)).Get());
        System.out.println(testValue.pow(new IntegerValue(2)).Get());

        System.out.println(testValue.lt(new IntegerValue(1)));
        System.out.println(testValue.lte(new IntegerValue(1)));
        System.out.println(testValue.gt(new IntegerValue(1)));
        System.out.println(testValue.gte(new IntegerValue(1)));
        System.out.println(testValue.eq(new IntegerValue(1)));
        System.out.println(testValue.neq(new IntegerValue(1)));

        //System.out.println(test.n);
//        DfList grupa= test.groupby("id");
//        for(DataFrame i: grupa.ldf){
//            i.print();
//        }

        //System.out.println(test.n);
        //grupa.ldf.get(0).print();
        //System.out.println("ab");
        //test.print();
    }

    public String printGUI() {
        int counter = 0;
        String result = new String();
        for (Column i : columns) {
            if (counter == columns.size() - 1) {
                //System.out.print(i.name);
                result += i.columnName;
            }
            else {
                result += i.columnName + " | ";
                //System.out.print(i.name + " | ");
            }
            counter++;
        }
        //System.out.println();
        result += "\n";
        for (int i = 0; i < columns.size(); i++) {
            //System.out.print("-------");
            result += "-------";
        }
        //System.out.println();
        result += "\n";
        for (int j = 0; j < n; j++) {
            counter = 0;
            for (Column i : columns) {
                Value tmp = i.col.get(j);
                if (counter == columns.size() - 1) {
                    if (tmp == null) {
                        //System.out.print("null");
                        result += "null";
                    } else {
                        if (i.columnType == DateTimeValue.class) {
                            //System.out.print(tmp.toString());
                            result += tmp.toString();
                        } else {
                            //System.out.print(tmp.getInstance());
                            result += tmp.getInstance();
                        }
                    }
                }
                else {
                    if (tmp == null) {
                        //System.out.print("null" + " | ");
                        result += "null" + " | ";
                    } else {
                        if (i.columnType == DateTimeValue.class) {
                            //System.out.print(tmp.toString() + "|");
                            result += tmp.toString() + " | ";
                        } else {
                            //System.out.print(tmp.getInstance() + " | ");
                            result += tmp.getInstance() + " | ";
                        }
                    }
                }
                counter++;
            }
            //System.out.println();
            result += "\n";
        }
        for (int i = 0; i < columns.size(); i++) {
            //System.out.print("-------");
            result += "-------";
        }
        //System.out.println();
        result += "\n";
        counter = 0;
        for (Column i : columns) {
            if (counter == columns.size() - 1) {
                //System.out.print(i.type);
                result += i.columnType;
            }
            else {
                //System.out.print(i.type + " | ");
                result += i.columnType + " | ";
            }
            counter++;
        }
        //System.out.println();
        result += "\n";
        return result;
    }

    public DfList groupby(String[] names) {
        ArrayList<LinkedList<DataFrame>> arrayList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            arrayList.add(null);
            if (i == 0) {
                arrayList.add(this.groupby(names[0]).ldf);
            } else {
                for (DataFrame df : arrayList.get(arrayList.size()-2)) {
                    arrayList.add(df.groupby(names[i]).ldf);
                }
            }
        }
        LinkedList<DataFrame> finalLL = new LinkedList<>();
        for (int i = arrayList.size() - 1 ; i > 0; i--) {
            if (arrayList.get(i) == null) {
                for (int j = i + 1; j < arrayList.size(); j++) {
                    for (DataFrame ll : arrayList.get(j)) {
                        finalLL.add(ll);
                    }
                }
            }
        }
        DfList dfList = new DfList(finalLL, names);
        return dfList;
    }


}