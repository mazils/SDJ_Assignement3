package ValuableFactory;

public class Diamond implements Valuable
{
    @Override
    public String getName()
    {
        return "diamond";
    }

    @Override
    public int getValue()
    {
        return 100;
    }
}
