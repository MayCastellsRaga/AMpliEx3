package PartB;

import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.beans.PropertyChangeSupport;

public class Task_SimpleB extends TaskB
{
    public PropertyChangeSupport support;
    protected Task_SimpleB(BigDecimal cost)
    {
        super(cost);
        support = new PropertyChangeSupport(this);
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
    }

    public Task_SimpleB UpdateValue(int value)
    {
        BigDecimal old = this.cost;
        BigDecimal temp = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
        this.cost = new Task_SimpleB(temp).cost;
        support.firePropertyChange("Task_CompostB",old,this.cost);
        return new Task_SimpleB(temp);
    }

    public static void main(String[] args)
    {

        Task_SimpleB t = new Task_SimpleB(BigDecimal.valueOf(10));
        System.out.println(t.cost);
        t = t.UpdateValue(19);
        System.out.println(t.cost);
    }

    public void firePropertyChange(String propertyName, BigDecimal oldValue, BigDecimal newValue)
    {

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
