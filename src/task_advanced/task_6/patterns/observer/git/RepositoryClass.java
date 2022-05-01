package task_advanced.task_6.patterns.observer.git;

import java.util.ArrayList;
import java.util.List;

public class RepositoryClass implements Repository{
    private List<WebHook> webHooks = new ArrayList<>();

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit commit = new Commit(author, changes);
        List<Commit> listOfCommits = new ArrayList<>();
        listOfCommits.add(commit);
        Event event = new Event(Event.Type.COMMIT, branch, listOfCommits);
        List<WebHook> webHooksList = new ArrayList<>();
        for(WebHook currentWebHook : webHooks){
            if(currentWebHook.type() == Event.Type.COMMIT && currentWebHook.branch() == branch) {
                webHooksList.add(currentWebHook);
                currentWebHook.onEvent(event);
            }
        }
        return commit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        List<Commit> unionCommits = new ArrayList<>();
        WebHook sourceWebHook = null;
        WebHook targetWebHook = null;
        for(WebHook currentWebHook : webHooks){
            if(currentWebHook.type() == Event.Type.COMMIT){
                if(currentWebHook.branch() == sourceBranch) {
                    sourceWebHook = currentWebHook;
                } else if(currentWebHook.branch() == targetBranch){
                    targetWebHook = currentWebHook;
                }
            }
        }
        if(sourceWebHook == null || targetWebHook == null){
            return;
        }
        for(Event currentEvent : sourceWebHook.caughtEvents()){
            for(Commit currentCommit : currentEvent.commits()){
                unionCommits.add(currentCommit);
            }
        }

        Event event = new Event(Event.Type.MERGE, targetBranch, unionCommits);
        WebHook foundWebHook = null;
        for(WebHook currentWebHook : webHooks){
            if(currentWebHook.type() == Event.Type.MERGE && currentWebHook.branch() == targetBranch){
                foundWebHook = currentWebHook;
            }
        }
        if(foundWebHook == null)
            return;
        for(Event currentEvent : foundWebHook.caughtEvents()){
            if(currentEvent.type() == Event.Type.MERGE && currentEvent.branch() == targetBranch)
                return;
        }
        foundWebHook.onEvent(event);
    }
}
