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

    public void UpdateValue(int value)
    {
        BigDecimal old = this.cost;
        BigDecimal temp = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
        this.cost = new Task_SimpleB(temp).cost;
        support.firePropertyChange("Task_CompostB", old, this.cost);
    }

    public static void main(String[] args)
    {

        Task_SimpleB t = new Task_SimpleB(BigDecimal.valueOf(10));
        System.out.println(t.cost);
        t.UpdateValue(19);
        System.out.println(t.cost);
    }
}