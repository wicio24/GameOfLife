package Model;

public class Game
{
    private int[][] tab1;
    private int[][] tab2;
    private int tabSizeHeight;
    private int tabSizeWidth;

    public Game(int tabSizeWidth, int tabSizeHeight)
    {
        this.tabSizeWidth = tabSizeWidth;
        this.tabSizeHeight = tabSizeHeight;


        tab1 = new int[tabSizeHeight][tabSizeWidth];
        tab2 = new int[tabSizeHeight][tabSizeWidth];
    }



    public int[][] getTab1()
    {
        return tab1;
    }

    public int[][] getTab2()
    {
        return tab2;
    }


    public void rules()
    {
        int cont;

        for (int i = 0; i < tabSizeHeight; i++)
        {
            for (int j = 0; j < tabSizeWidth; j++)
            {
                cont = 0;

                if (i == 0 || j == 0 || i == tabSizeHeight - 1 || j == tabSizeWidth - 1) tab1[i][j] = 0;
                else
                {
                    if (tab1[i - 1][j - 1] == 1) cont++;
                    if (tab1[i - 1][j] == 1) cont++;
                    if (tab1[i - 1][j + 1] == 1) cont++;
                    if (tab1[i][j - 1] == 1) cont++;
                    if (tab1[i][j + 1] == 1) cont++;
                    if (tab1[i + 1][j - 1] == 1) cont++;
                    if (tab1[i + 1][j] == 1) cont++;
                    if (tab1[i + 1][j + 1] == 1) cont++;

                    if (cont == 3 && tab1[i][j] == 0) tab2[i][j] = 1;
                    if ((cont == 3 || cont == 2) && tab1[i][j] == 1) tab2[i][j] = 1;
                    if ((cont > 3 && tab1[i][j] == 1) || (cont < 2 && tab1[i][j] == 1)) tab2[i][j] = 0;
                }


            }
        }

        for (int i = 0; i < tabSizeHeight; i++)
        {
            for (int j = 0; j < tabSizeWidth; j++)
            {

                tab1[i][j] = tab2[i][j];
                tab2[i][j] = 0;

            }
        }
    }

    public void clearGrid()
    {
        for (int i = 0; i < tabSizeHeight; i++)
        {
            for (int j = 0; j < tabSizeWidth; j++)
            {
                tab1[i][j] = 0;
                tab2[i][j] = 0;
            }
        }

    }
    public void setTab1Cell(int x, int y)
    {
        if(tab1[y][x] == 1) tab1[y][x] = 0;
        else tab1[y][x] = 1;
    }
}
