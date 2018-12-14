package labs;

import java.util.Objects;

public class StringValue extends Value
{
    private String body;

    public StringValue(String x)
    {
        body=x;
    }

    public String toString()
    {
        return body;
    }

    public Object Get()
    {
        return body;
    }

    public Value add(Value v)
    {
        return new StringValue(body+v.toString());
    }

    public  Value sub(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public  Value mul(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public  Value div(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");

    }

    public  Value pow(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public boolean eq(Value v)
    {
       return body.equals(v.toString());
    }

    public boolean lte(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public boolean lt(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public boolean gte(Value v)
    {
        throw new RuntimeException("Invalid operation for StringValue type");
    }

    public boolean gt(Value v)
    {
        return false;
    }

    public boolean neq(Value v)
    {
        return !this.eq(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    public Value create(String s)
    {
        return (new StringValue(s));
    }
    public Object getInstance(){
        return this.body;
    }
}
