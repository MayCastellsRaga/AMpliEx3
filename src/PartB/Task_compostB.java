package PartB;

import com.sun.tools.javac.Main;

import java.beans.PropertyChangeListener;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.*;


public class Task_compostB extends TaskB implements PropertyChangeListener
{
        List<Task_SimpleB> subtask = new ArrayList<>();


        protected Task_compostB(List<Task_SimpleB> subtask)
        {
            super(BigDecimal.valueOf(1));
            this.subtask = subtask;
            Task_SimpleB ts = new Task_SimpleB(BigDecimal.valueOf(1));
            BigDecimal temp = BigDecimal.valueOf(0);
            for (Task_SimpleB task : subtask)
            {
                task.support.addPropertyChangeListener(this);
                temp = temp.add(task.cost);
            }
            this.cost = temp;
        }

        private BigDecimal updateCost(BigDecimal old, BigDecimal act) throws IllegalAccessException
        {
            this.cost = this.cost.subtract(old);
            this.cost = this.cost.add(act);
            this.cost = cost.setScale(2, RoundingMode.HALF_UP);
            if (this.cost.signum() <= 0) throw new IllegalArgumentException("cost must be positive");
            return null;
        }

        public static void main(String[] args)
        {
            Task_SimpleB t1 = new Task_SimpleB(BigDecimal.valueOf(10));
            Task_SimpleB t2 = new Task_SimpleB(BigDecimal.valueOf(10));
            List<Task_SimpleB> list = new ArrayList<Task_SimpleB>();
            list.add(t1);
            list.add(t2);
            Task_compostB tc = new Task_compostB(list);
            System.out.println(tc.cost);
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        BigDecimal act = (BigDecimal) evt.getNewValue();
        BigDecimal old = (BigDecimal) evt.getOldValue();

        try
        {
            updateCost(old,act);
        } catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}
