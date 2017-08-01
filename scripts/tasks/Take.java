package scripts.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import scripts.Task;

import java.util.concurrent.Callable;

public class Take extends Task {

    public static int ITEM_ID = 1739;

    public Take(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().animation()==-1 && ctx.inventory.select().count()<28;
        // jei zaidejo inventory full ir jis nieko nedaro
    }

    @Override
    public void excecute() {
        GroundItem itemToPickup = ctx.groundItems.select().id(ITEM_ID).nearest().poll();
        itemToPickup.interact("Take");

        Condition.wait(new Callable<Boolean>(){
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation()!=-1;
            }
        });
    }
}
