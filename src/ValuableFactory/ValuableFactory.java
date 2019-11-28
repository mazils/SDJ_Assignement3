package ValuableFactory;

import java.util.HashMap;
import java.util.Map;

public class ValuableFactory {
    private static Map<String, Valuable> valuableMap = new HashMap<>();

    public Valuable getValuable(String valuableType) {
        Valuable valuable = valuableMap.get(valuableType);
        if (valuable == null)
        {
            switch (valuableType)
            {
                case "diamond":
                {
                    valuable = new Diamond();
                    break;
                }

                case "cow":
                {
                    valuable = new Cow();
                    break;
                }

                case "ruby":
                {
                    valuable = new Ruby();
                }
            }
            valuableMap.put(valuableType,valuable);
        }
        return valuable;
    }
}
