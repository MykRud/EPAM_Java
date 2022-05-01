package task_advanced.task_6.patterns.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookClass implements WebHook{
    private String branch;
    private Event.Type type;
    private List<Event> events = new ArrayList<>();

    public WebHookClass(String branch, Event.Type type) {
        this.branch = branch;
        this.type = type;
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return events;
    }

    @Override
    public void onEvent(Event event) {
        events.add(event);
    }
}
