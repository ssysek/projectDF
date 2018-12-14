package labs;

import java.util.Objects;

public class COOLValue extends Value{
	private int index;
	private Value body;
	
	public COOLValue(int i, Value v)
	{
		index = i;
		body = v;
	}
	
	public int GetIndex()
	{
		return index;
	}
	
	public void SetIndex(int x)
	{
		index = x;
	}
	
	public Value Get()
	{
		return body;
	}
	
	public void SetValue(Value x)
	{
		body = x;
	}


    public String toString()
    {
        return '('+String.valueOf(index)+','+String.valueOf(body)+')';
    }

    public Value add(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public Value sub(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public  Value mul(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public  Value div(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");

    }

    public  Value pow(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public boolean eq(Value v)
    {
        return this.toString().equals(v.toString());
    }

    public boolean lte(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public boolean lt(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public boolean gte(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public boolean gt(Value v)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }

    public boolean neq(Value v)
    {
        return !this.eq(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        COOLValue coolValue = (COOLValue) o;
        return index == coolValue.index &&
                Objects.equals(body, coolValue.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, body);
    }

    public Value create(String s)
    {
        throw new RuntimeException("Invalid operation for COOValue type");
    }
    public Object getInstance(){
	    return this.body;
    }

}
