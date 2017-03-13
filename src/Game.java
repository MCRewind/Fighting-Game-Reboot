import java.awt.Graphics;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

	//target frames per second
    public static final int TARGET_FPS = 75;

    //target updates per second
    public static final int TARGET_UPS = 60;

    private final Thread gameLoopThread;
    
    private final Timer timer;
    
    private boolean vSync = false;
    
    public Game() {
    	timer = new Timer();
    	gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
    	run();
    }
    
    //starts the gameloop's thread
    public void start() {
        String osName = System.getProperty("os.name");
        if ( osName.contains("Mac") ) {
            gameLoopThread.run();
        } else {
            gameLoopThread.start();
        }
    }
    
    //does initializations then starts the gameloop, calls cleanup on exit
    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    //calls the window and timer's init methods and passes the game's init method the main window
    protected void init() throws Exception {
        timer.init();
    }
    
    //fixed step gameloop with fps limiting and vsync
    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;
            
            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }
            
            repaint();
            
            if (vSync) {
                sync();
            }
        }
    }

    //vsync method, sleeps thread till current frame has completed
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {}
        }
    }
    
	public void paintComponent(Graphics g) {
		g.drawRect(100, 100, 10, 10);
	}
	
	private void update(float interval) {
		
	}
	
	private void input() {
		
	}
}
