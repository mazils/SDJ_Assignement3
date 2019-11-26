package ValuableFactory;

public class Ruby implements Valuable
{
    @Override
    public String getName()
    {
        return "ruby";
    }

    @Override
    public int getValue()
    {
        return 50;
    }
}
