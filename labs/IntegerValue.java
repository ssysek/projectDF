package labs;

import java.util.Objects;

public class IntegerValue extends Value
{
	private int body;
	
	public IntegerValue(int x)
	{
		body=x;
	}
	
	public String toString()
	{
		return String.valueOf(body);
	}

	public Object Get()
    {
        return body;
    }
	
    public Value add(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
    	    return (new IntegerValue(body+(int)v.Get()));
        throw (new IllegalArgumentException("Addition unavailable for given type"));
    }
    
    public Value sub(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return (new IntegerValue(body-(int)v.Get()));
        throw (new IllegalArgumentException("Substitution unavailable for given type"));
    }
    
    public  Value mul(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return (new IntegerValue(body*(int)v.Get()));
        throw (new IllegalArgumentException("Multiplication unavailable for given type"));
    }
    
    public  Value div(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
        {
            if((int)v.Get()!=0)
                return(new IntegerValue(body/(int)v.Get()));
            else
                throw (new IllegalArgumentException("Division by zero"));
        }
        else
            throw (new IllegalArgumentException("Division unavailable for given type"));

    }
    
    public  Value pow(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
    	    return(new IntegerValue((int)Math.pow(body, (int)v.Get())));
        else
            throw (new IllegalArgumentException("Exponentiation unavailable for given type"));
    }

    public boolean eq(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
    	    return (int)v.Get()==body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }
    
    public boolean lte(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return body<=(int)v.Get();
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }
    
    public boolean lt(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return body<(int)v.Get();
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }
    
    public boolean gte(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return body>=(int)v.Get();
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }
    
    public boolean gt(Value v)
    {
        if(v instanceof IntegerValue || v instanceof FloatValue || v instanceof DoubleValue)
            return body>(int)v.Get();
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
        IntegerValue that = (IntegerValue) o;
        return body == that.body;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    public Value create(String s)
    {
    	int tmp = Integer.parseInt(s);
    	return (new IntegerValue(tmp));
    }
    public Object getInstance(){
        return this.body;
    }
}
