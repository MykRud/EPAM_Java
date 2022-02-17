package task_advanced.task_2.database;

import task_advanced.task_2.businessLogic.collections.Container;

public interface Formatter {

    public <T> void read(Container<T> container, String file);

    public <T> void save(Container<T> container, String file);
}
