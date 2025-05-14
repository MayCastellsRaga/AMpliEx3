import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Task_compost extends Task implements Observer
{
    List<Task_simple> subtask = new ArrayList<Task_simple>();


    protected Task_compost(List<Task_simple> subtask)
    {
        super(BigDecimal.valueOf(1));
        this.subtask = subtask;
        Task_simple ts = new Task_simple(BigDecimal.valueOf(1));
        BigDecimal temp = BigDecimal.valueOf(0);
        for (Task task : subtask)
        {
            task.addObserver(this);
            temp = temp.add(task.cost);
        }
        this.cost = temp;
    }

    private void updateCost(Object arg) throws IllegalAccessException
    {
        Task_simple.CostChanged values = (Task_simple.CostChanged) arg;
        BigDecimal old = values.oldCost();
        BigDecimal act = values.newCost();
        this.cost = this.cost.subtract(old);
        this.cost = this.cost.add(act);
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
    }
    @Override
    public void update(Observable o, Object arg)
    {
        System.out.println("hey");
        try
        {
            updateCost(arg);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
