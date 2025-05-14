import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
            Task_simple t1 = new Task_simple(BigDecimal.valueOf(10));
            Task_simple t2 = new Task_simple(BigDecimal.valueOf(10));
            List<Task_simple> list = new ArrayList<Task_simple>();
            list.add(t1);
            list.add(t2);
            Task_compost tc = new Task_compost(list);
            System.out.println(tc.cost);
            t1= t1.UpdateValue(2);
            System.out.println(tc.cost);
    }
}