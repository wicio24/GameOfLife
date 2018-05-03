package Controller;

import Model.DrawerTask;
import Model.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class Controller
{
    private GraphicsContext gc;

    private int pixelSize = 4;

    private int tabSizeHeight;
    private int tabSizeWidth;
    private int startingPoints;
    private Game game;

    private DrawerTask task;


    @FXML
    private Canvas drawCanvas;

    @FXML
    private Button startButtom;

    @FXML
    private Button nextStepButtom;

    @FXML
    private Button stopLoopButtom;

    @FXML
    private TextField textField;


    @FXML
    private void initialize()
    {

        gc = drawCanvas.getGraphicsContext2D();

        tabSizeWidth = (int) gc.getCanvas().getWidth() / pixelSize;
        tabSizeHeight = (int) gc.getCanvas().getHeight() / pixelSize;
        startingPoints = Integer.parseInt(this.textField.getText());
        game = new Game(tabSizeWidth, tabSizeWidth);

    }


    @FXML
    private void handleStart()
    {

        randomNewPoints();
    }


    @FXML
    private void handleNextStep()
    {

        task = new DrawerTask(this);
        task.setStopFlag(false);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        //drawCanvas();


    }


    @FXML
    private void handleStopLoop()
    {
        task.setStopFlag(true);
    }


    public void randomNewPoints()
    {
        clearCanvas();
        startingPoints = Integer.parseInt(textField.getText());
        gc.setFill(Color.BLACK);
        for (int i = 0; i < startingPoints; i++)
        {
            Random r = new Random();
            int x = r.nextInt(tabSizeWidth);
            int y = r.nextInt(tabSizeHeight);
            game.getTab1()[y][x] = 1;
            gc.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
        }
    }

    public void clearCanvas()
    {
        for (int i = 0; i < tabSizeHeight; i++)
        {
            for (int j = 0; j < tabSizeWidth; j++)
            {
                game.getTab1()[i][j] = 0;
                game.getTab2()[i][j] = 0;
            }
        }

        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); // wyczysc plansze
    }

    public void drawCanvas()
    {


        Platform.runLater(() -> {
            for (int i = 0; i < tabSizeHeight; i++)
            {
                for (int j = 0; j < tabSizeWidth; j++)
                {
                    if (game.getTab1()[i][j] == 1)
                    {
                        gc.setFill(Color.BLACK);
                        gc.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                    } else
                    {
                        gc.setFill(Color.WHITE);
                        gc.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                    }

                }
            }
        });

        game.rules();



    }


}
