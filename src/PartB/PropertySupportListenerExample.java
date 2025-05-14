package PartB;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertySupportListenerExample{


    public static void main(String[] args)
    {

    }

    private static final class ExampleBean {


        private final PropertyChangeSupport property;
        private String data;
        private String oldData;


        public ExampleBean(String data) {

            property = new PropertyChangeSupport(this);
            setData(data);
        }


        public String getData() {

            return data;
        }


        public void setData(String data) {

            oldData = this.data;
            this.data = data;
            property.firePropertyChange("data", oldData, this.data);
        }


        public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {

            property.addPropertyChangeListener(propertyName, listener);
        }


        public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {

            property.removePropertyChangeListener(propertyName, listener);
        }
    }
}
