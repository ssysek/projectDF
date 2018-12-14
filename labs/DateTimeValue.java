package labs;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class DateTimeValue extends Value{
    private Date body;

    public DateTimeValue(Date x)
    {
        body=x;
    }

    public Object Get()
    {
        return body;
    }

    public String toString()
    {
        return body.toString();
    }

    public Value add(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return (new DateTimeValue(new Date(body.getTime()+(long)v.Get())));
        throw (new IllegalArgumentException("Addition unavailable for given type"));
    }

    public Value sub(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return (new DateTimeValue(new Date(body.getTime()-(long)v.Get())));
        throw (new IllegalArgumentException("Addition unavailable for given type"));
    }

    public Value mul(Value v) {throw new RuntimeException("Invalid operation for DateTimeValue type");}
    public Value div(Value v) {throw new RuntimeException("Invalid operation for DateTimeValue type");}
    public Value pow(Value v) {throw new RuntimeException("Invalid operation for DateTimeValue type");}

    public boolean eq(Value v)
    {
        if(v instanceof DateTimeValue)
            return v.Get().equals(body);
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean lte(Value v)
    {
        if(v instanceof DateTimeValue)
            return body.compareTo((Date)v.Get())<=0;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean lt(Value v)
    {
        if(v instanceof DateTimeValue)
            return body.before((Date)v.Get());
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean gte(Value v)
    {
        if(v instanceof DateTimeValue)
            return body.compareTo((Date)v.Get())>=0;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean gt(Value v)
    {
        if(v instanceof DateTimeValue)
            return body.after((Date)v.Get());
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean neq(Value v)
    {
        return !this.eq(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTimeValue that = (DateTimeValue) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    public Value create(String s)
    {
        DateFormat tmp1=null;
        Date tmp=null;
        try
        {
            tmp = tmp1.parse(s);
        } catch(ParseException e)
        {
            System.err.println("Tworzenie daty ze stringa nie zadziałało.");
            e.printStackTrace();
        }
        return new DateTimeValue(tmp);
    }
    public Object getInstance(){
        return this.body;
    }
}
