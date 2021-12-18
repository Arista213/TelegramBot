import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args)
    {
        List<Number> numbers = new ArrayList<>(List.of(1, 2));
        var iterator = numbers.iterator();
        Number result = null;
        if (iterator.hasNext())
        {
            result = iterator.next();
            if (iterator.hasNext()) result = null;
        }

        System.out.println(result);
    }
}
