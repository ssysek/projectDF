package labs;

import java.util.Objects;

public class CharValue extends Value
{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharValue charValue = (CharValue) o;
        return body == charValue.body;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "CharValue{" +
                "body=" + body +
                '}';
    }

    private char body;

    public CharValue(char x)
    {
        body=x;
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

    public boolean eq(Value o) {
        if (this == o.getInstance()) return true;
        if (o.getInstance() == null || getClass() != o.getInstance().getClass()) return false;
        CharValue charValue = (CharValue) o.getInstance();
        return body == charValue.body;
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

    public boolean gt(Value val) {
        if (val.getInstance() instanceof Character) {
            Character x = (Character) val.getInstance();
            if (this.body > x) return true;
            else return false;
        }
        else return false;
    }

    public boolean neq(Value v)
    {
        return !this.eq(v);
    }

    public Value create(String s)
    {
        return (new StringValue(s));
    }
    public Object getInstance(){
        return this.body;
    }
}
