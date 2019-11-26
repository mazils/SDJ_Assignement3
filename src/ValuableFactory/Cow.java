package ValuableFactory;

public class Cow implements Valuable
{
    @Override
    public String getName()
    {
        return "cow";
    }

    @Override
    public int getValue()
    {
        return 150;
    }
}
