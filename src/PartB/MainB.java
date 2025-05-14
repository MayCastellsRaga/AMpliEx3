package PartB;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class MainB
{



        public static void main(String[] args)
        {
            Task_SimpleB t1 = new Task_SimpleB(BigDecimal.valueOf(10));
            Task_SimpleB t2 = new Task_SimpleB(BigDecimal.valueOf(10));
            List<Task_SimpleB> list = new ArrayList<Task_SimpleB>();
            list.add(t1);
            list.add(t2);
            Task_compostB tc = new Task_compostB(list);
            System.out.println(tc.cost);
            t1= t1.UpdateValue(12);
            System.out.println(tc.cost);
        }

}
