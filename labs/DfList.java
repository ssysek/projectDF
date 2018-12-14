package labs;

import java.util.ArrayList;
import java.util.LinkedList;

public class DfList implements Groupby {
    public LinkedList<DataFrame> ldf = new LinkedList<>();
    public String[] names;
    public DfList(LinkedList<DataFrame> ll, String[] names) {
        this.ldf = ll;
        this.names = names;
    }
    public DataFrame max() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                int c = 0;
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName)) {
                        in = true;
                    }
                }
                if (!in) {
                    Value max = j.col.get(0);
                    for (Value k : j.col) {

                            if (k.gt(max)) {
                                max = k;
                            }
                    }
                    values[counter] = max;
                } else {
                    values[counter] = j.col.get(0);
                }
                c++;
                counter++;
            }
                df.addRow(values);

        }
        return df;
    }
    public DataFrame min() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                int c = 0;
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName)) {
                        in = true;
                    }
                }
                if (!in) {
                    Value min = j.col.get(0);
                    for (Value k : j.col) {

                            if (k.lt(min)) {
                                min = k;
                            }

                    }
                    values[counter] = min;
                } else {
                    values[counter] = j.col.get(0);
                }
                c++;
                counter++;
            }
                df.addRow(values);

        }
        return df;
    }
    public DataFrame mean() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                int c = 0;
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName) || j.columnType == StringValue.class || j.columnType == DateTimeValue.class) {
                        in = true;
                    }
                }
                if (!in) {
                    Value val = j.col.get(0);
                    int cp = 0;
                    for (Value k : j.col) {
                        if (cp > 0) {

                                val = val.add(k);
                            }

                        cp++;
                    }

                        values[counter] = val.div(new DoubleValue(j.col.size() + 0.0));

                } else {
                    values[counter] = j.col.get(0);
                }
                c++;
                counter++;
            }
                df.addRow(values);

        }
        return df;
    }
    public DataFrame sum() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                int c = 0;
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName) || j.columnType == StringValue.class || j.columnType == DateTimeValue.class) {
                        in = true;
                    }
                }
                if (!in) {
                    Value val = j.col.get(0);
                    int cp = 0;
                    for (Value k : j.col) {
                        if (cp > 0) {
                                val = val.add(k);
                            }

                        cp++;
                    }
                    values[counter] = val;
                } else {
                    values[counter] = j.col.get(0);
                }
                c++;
                counter++;


                df.addRow(values);
            }
        }
        return df;
    }
    public DataFrame std() {

        return null;
    }
    public DataFrame var() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                int c = 0;
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName) || j.columnType == StringValue.class || j.columnType == DateTimeValue.class) {
                        in = true;
                    }
                }
                if (!in) {
                        Value val = j.col.get(0);
                        int cp = 0;
                        for (Value k : j.col) {
                            if (cp > 0) {
                                val = val.add(k);
                            }
                            cp++;
                        }
                        Value var = (j.col.get(0).sub(val)).mul((j.col.get(0).sub(val)));
                        cp = 0;
                        for (Value k : j.col) {
                            if (cp > 0) {

                                var = var.add(k.sub(val).mul(k.sub(val)));

                            }
                            cp++;
                        }

                        values[counter] = var.div(new DoubleValue(j.col.size() + 0.0));


                                    }
                else {
                    values[counter] = j.col.get(0);
                }
                c++;
                counter++;
            }
                df.addRow(values);

        }
        return df;
    }
    public DataFrame mediana() {
        String[] names = new String[ldf.get(0).columns.size()];
        ArrayList<Class<? extends Value>> types = new ArrayList<>(0);
        int counter = 0;
        for (Column i : ldf.get(0).columns) {
            names[counter] = i.columnName;
            types.add(i.columnType);
            counter++;
        }
        DataFrame df = new DataFrame(names, types);
        for (DataFrame i : ldf) {
            counter = 0;
            Value[] values = new Value[i.columns.size()];
            for (Column j : i.columns) {
                boolean in = false;
                for (String x : this.names) {
                    if (x.equals(j.columnName) || j.columnType == StringValue.class || j.columnType == DateTimeValue.class) {
                        in = true;
                    }
                }
                if (!in) {
                    if (j.col.size() % 2 == 0) {
                        values[counter] = j.col.get(j.col.size() / 2);
                    } else {
                            values[counter] = (j.col.get((j.col.size() / 2) - 1).add(j.col.get((j.col.size() / 2) + 1))).div(new DoubleValue(2.0));

                    }
                } else {
                    values[counter] = j.col.get(0);
                }
                counter++;
            }

                df.addRow(values);

        }
        return df;
    }
}