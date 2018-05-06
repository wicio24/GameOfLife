package Controller;

import Model.DrawerTask;
import Model.Game;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private Canvas canvas;

    @FXML
    private Button startButton;

    @FXML
    private Button startLoopButton;

    @FXML
    private Button stopLoopButton;

    @FXML
    private Button nextStepButton;

    @FXML
    private TextField textField;


    @FXML
    private void initialize()
    {

        gc = canvas.getGraphicsContext2D();

        tabSizeWidth = (int) gc.getCanvas().getWidth() / pixelSize;
        tabSizeHeight = (int) gc.getCanvas().getHeight() / pixelSize;
        startingPoints = Integer.parseInt(this.textField.getText());
        game = new Game(tabSizeWidth, tabSizeHeight);
    }


    @FXML
    private void handleMouseClick(MouseEvent mouseEvent)
    {
        int x = (int)mouseEvent.getX();
        int y = (int)mouseEvent.getY();

        if(game.getTab1()[y/pixelSize][x/pixelSize] == 1)
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, pixelSize, pixelSize);
            game.setTab1Cell(x/pixelSize,y/pixelSize);
        }
        else
        {
            gc.setFill(Color.WHITE);
            gc.fillRect(x, y, pixelSize, pixelSize);
            game.setTab1Cell(x/pixelSize,y/pixelSize);
        }

    }


    @FXML
    private void handleStart()
    {
        startLoopButton.setDisable(false);
        nextStepButton.setDisable(false);

        randomNewPoints();

    }

    @FXML
    private void handleStartLoop()
    {
        stopLoopButton.setDisable(false);
        startLoopButton.setDisable(true);

        task = new DrawerTask(this);
        task.setStopFlag(false);
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleStopLoop()
    {
        stopLoopButton.setDisable(true);
        startLoopButton.setDisable(false);

        task.setStopFlag(true);
    }

    @FXML
    private void handleNextStep()
    {
        drawCanvas();
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
        game.clearGrid();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); // wyczysc plansze
    }

    public void drawCanvas()
    {


        Platform.runLater(() -> {
            game.rules();
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





    }


}
