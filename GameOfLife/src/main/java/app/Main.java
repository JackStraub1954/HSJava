package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import edu.uweo.javaintro.game_of_life.Board;
import edu.uweo.javaintro.game_of_life.Cell;
import edu.uweo.javaintro.game_of_life.ControlEvent;
import edu.uweo.javaintro.game_of_life.ControlListener;
import edu.uweo.javaintro.game_of_life.Controls;
import edu.uweo.javaintro.game_of_life.Neighborhood;

public class Main implements ActionListener, ControlListener
{
    private Board board;
    private boolean         running         = false;
    private JFileChooser    fileChooser     = new JFileChooser();
    private Controls        controls        = new Controls();
    private Thread          controlThread   = null;
    private Runner          runner          = new Runner();

    public static void main(String[] args)
    {
        Main app = new Main();
        app.execute();
    }

    public Main()
    {
        String userDir = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(userDir));
    }

    private void execute()
    {
        board = new Board(100);
        board.addActionListener(this);
        SwingUtilities.invokeLater(board);
        controls.addControlListener(this);
        controls.start();
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (controls.isInteractive())
        {
            Cell cell = (Cell) evt.getSource();
            cell.toggleAlive();
            board.setCell(cell);
            board.refresh();
        }
    }

    public void controlActivated(ControlEvent evt)
    {
        String text = evt.getLabel();
        switch ( text )
        {
        case Controls.RUN_LABEL:
            doRun();
            break;
        case Controls.STEP_LABEL:
            doStep();
            break;
        case Controls.SAVE_LABEL:
            doSave();
            break;
        case Controls.OPEN_LABEL:
            doOpen();
            break;
        case Controls.CLEAR_LABEL:
            doClear();
            break;
        case Controls.EXIT_LABEL:
            doExit();
            break;
        default:
            // new Throwable().printStackTrace();
            System.err.println("eh?");
            break;
        }
    }

    public void sliderAdjusted(ControlEvent evt)
    {
        runner.updateGPS();
        if (controlThread != null && controlThread.isAlive())
        {
            controlThread.interrupt();
        }
    }

    private void doClear()
    {
        board.clear();
        board.refresh();
    }

    private void doRun()
    {
        running = !running;
        if (running)
        {
            controlThread = new Thread(runner, "Runner");
            controlThread.start();
        }
    }

    private void doStep()
    {
        nextState(board.getCells());
    }

    private void doSave()
    {
        int rcode = fileChooser.showSaveDialog(null);
        if (rcode == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try (FileOutputStream fStream = new FileOutputStream(file);)
            {

                ObjectOutputStream oStream = new ObjectOutputStream(fStream);
                oStream.writeObject(board.getCells());
            } catch (IOException exc)
            {
                JOptionPane.showMessageDialog(null, "Save failure");
                exc.printStackTrace();
            }
        }
    }

    private void doOpen()
    {
        int rcode = fileChooser.showOpenDialog(null);
        if (rcode == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try (FileInputStream fStream = new FileInputStream(file);)
            {
                ObjectInputStream oStream = new ObjectInputStream(fStream);
                boolean[][] cells = (boolean[][]) oStream.readObject();
                board.setCells(cells);
                board.refresh();
            } catch (IOException | ClassNotFoundException
                | ClassCastException exc)
            {
                JOptionPane.showMessageDialog(null, "Open failure");
                exc.printStackTrace();
            }
        }
    }

    private void doExit()
    {
        System.exit(0);
    }

    /*
     * Rules: 1. Off-board cells are always dead. 2. A live cell with fewer than
     * two live neighbors dies. 3. A live cell with two or three live neighbors
     * remains alive. 4. A live cell with more than three live neighbors dies.
     * 5. A dead cell with exactly three live neighbors becomes alive.
     */
    private void nextState(boolean[][] cells)
    {
        int len = cells.length;
        boolean[][] temp = new boolean[cells.length][cells.length];
        Neighborhood neigh = new Neighborhood();
        for (int row = 0; row < len; ++row)
            for (int col = 0; col < len; ++col)
            {
                neigh.reset(row, col, cells);
                int count = neigh.getLivingCellCount();
                if (count < 2 || count > 3)
                    temp[row][col] = false;
                else if (count == 3)
                    temp[row][col] = true;
                else
                    temp[row][col] = cells[row][col];
            }

        board.setCells(temp);
        board.refresh();
    }

    private static void pause(long millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException exc)
        {
            // don't care
        }
    }

    public class Runner implements Runnable
    {
        private long millis;

        public void run()
        {
            updateGPS();
            while (running)
            {
                nextState(board.getCells());
                pause(millis);
            }
        }

        public void updateGPS()
        {
            double maxGPS = controls.getMaxGenerationsPerSecond();
            double val = controls.getSliderValue();
            double gps = val * maxGPS;
            millis = (int) (1000 / gps);
        }
    }
}
