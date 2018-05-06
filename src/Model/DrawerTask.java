package Model;

import javafx.concurrent.Task;
import Controller.Controller;

public class DrawerTask implements Runnable
{

    private Controller controller;
    private boolean stopFlag = false;


    public DrawerTask(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void run()
    {

        while (!stopFlag)
        {

            controller.drawCanvas();
            try
            {
                Thread.sleep(500);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }


    public void setStopFlag(boolean stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    public boolean isStopFlag()
    {
        return stopFlag;
    }
}
