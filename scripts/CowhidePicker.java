package scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import scripts.tasks.Take;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "Cowhide picker", description = "Picks up cowhides and banks them in lumbridge bank", properties = "author=Nedas Topic=999; client=4;")

public class CowhidePicker extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new Take(ctx));
    }

    @Override
    public void poll() {

        for(Task task : taskList){
            if(task.activate()){
                task.execute();
                break;
            }
        }

    }

}
