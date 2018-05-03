package Model;

import javafx.concurrent.Task;
import Controller.Controller;

public class DrawerTask extends Task
{

    private Controller controller;
    private boolean stopFlag;


    public DrawerTask(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    protected Object call() throws InterruptedException
    {

        while (!stopFlag)
        {

            controller.drawCanvas();
            Thread.sleep(500);
        }
        return 1;
    }


    public void setStopFlag(boolean stopFlag)
    {
        this.stopFlag = stopFlag;
    }
}
