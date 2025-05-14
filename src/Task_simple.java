import java.math.BigDecimal;
import java.math.RoundingMode;

public class Task_simple extends Task
{
    protected Task_simple(BigDecimal cost)
    {
        super(cost);
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
    }

    public Task_simple UpdateValue(int value)
    {
        BigDecimal old = this.cost;
        BigDecimal temp = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
        this.cost = new Task_simple(temp).cost;
        setChanged();
        notifyObservers(new CostChanged(old, this.cost));
        return new Task_simple(temp);
    }

    public static void main(String[] args)
    {
        Task_simple t = new Task_simple(BigDecimal.valueOf(10));
        System.out.println(t.cost);
        t = t.UpdateValue(19);
        System.out.println(t.cost);
    }


    public record CostChanged(BigDecimal oldCost, BigDecimal newCost)
    {
        @Override
        public BigDecimal oldCost()
        {
            return oldCost;
        }

        @Override
        public BigDecimal newCost()
        {
            return newCost;
        }
    }

}
