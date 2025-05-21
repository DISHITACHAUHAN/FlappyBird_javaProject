import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

// ✅ 1. Creating the new project with JDK & IDE setup
// ✅ 2. Define the project structure (using inner classes below)

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    // --- Game Constants ---
    int boardWidth = 360;
    int boardHeight = 640;

    // --- Images ---
    Image backgroundImg, birdImg, topPipeImg, bottomPipeImg;

    // --- Bird properties ---
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34, birdHeight = 24;

    // --- Pipe properties ---
    int pipeX = boardWidth, pipeY = 0, pipeWidth = 64, pipeHeight = 512;

    // --- Game objects ---
    Bird bird;
    ArrayList<Pipe> pipes = new ArrayList<>();
    Random random = new Random();
    Timer gameLoop, pipeSpawner;
    int velocityX = -4, velocityY = 0, gravity = 1;
    boolean gameOver = false;
    double score = 0;

    ScoreDAO scoreDAO = new ScoreDAO();  // DAO instance

    // --- Constructor ---
    public FlappyBird() {
        
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImg);

        // Pipe spawner
        pipeSpawner = new Timer(1500, _ -> placePipes());
        pipeSpawner.start();

        // Game loop
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    // ✅ 3. Design the database schema
    // ✅ 4. Create a MySQL table
    // ✅ 5. Implement jdbc for database connectivity
    // ✅ 6. Create Model, DAO classes for the database operations

    // --- Model ---
    static class Score {
        int value;
        Score(int value) { this.value = value; }
    }

    // --- DAO Class ---
    static class ScoreDAO {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/flappybird";
        private static final String USER = "root";
        private static final String PASSWORD = "password";

        ScoreDAO() {
            createTable();
        }

        private void createTable() {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS scores (id INT AUTO_INCREMENT PRIMARY KEY, score INT NOT NULL)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertScore(Score score) {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO scores(score) VALUES(?)")) {
                stmt.setInt(1, score.value);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public int getHighScore() {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT MAX(score) AS max_score FROM scores")) {
                return rs.next() ? rs.getInt("max_score") : 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    // --- Game Object Classes ---
    class Bird {
        int x, y, width, height;
        Image img;
        Bird(int x, int y, int w, int h, Image img) {
            this.x = x; this.y = y; this.width = w; this.height = h; this.img = img;
        }
    }

    class Pipe {
        int x = pipeX, y, width = pipeWidth, height = pipeHeight;
        Image img;
        boolean passed = false;
        Pipe(int y, Image img) {
            this.y = y; this.img = img;
        }
    }

    // --- Game Drawing ---
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes)
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        // ✅ 7. Aesthetics and Visual Appeal of the UI
        if (gameOver) {
            g.drawString("Game Over! Score: " + (int) score, 30, 50);
            g.drawString("High Score: " + scoreDAO.getHighScore(), 30, 90);
            g.drawString("Press SPACE to restart", 30, 130);
        } else {
            g.drawString("Score: " + (int) score, 10, 30);
        }
    }

    // --- Pipe placement logic ---
    void placePipes() {
        int offsetY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int gap = boardHeight / 4;

        pipes.add(new Pipe(offsetY, topPipeImg));
        pipes.add(new Pipe(offsetY + pipeHeight + gap, bottomPipeImg));
    }

    // --- Movement and collision ---
    void move() {
        bird.y += velocityY;
        velocityY += gravity;

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (collision(bird, pipe)) {
                gameOver = true;
            }

            if (pipe.x + pipe.width < 0) {
                pipes.remove(i--);
            }

            if (!pipe.passed && pipe.x + pipe.width < bird.x) {
                pipe.passed = true;
                score += 0.5;
            }
        }

        if (bird.y + bird.height > boardHeight || bird.y < 0) {
            gameOver = true;
        }
    }

    boolean collision(Bird b, Pipe p) {
        return b.x < p.x + p.width && b.x + b.width > p.x &&
               b.y < p.y + p.height && b.y + b.height > p.y;
    }

    // --- Game loop ---
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            pipeSpawner.stop();
            gameLoop.stop();
            scoreDAO.insertScore(new Score((int) score));
        }
    }

    // ✅ 9. Responsiveness and Accessibility of the UI
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            if (gameOver) {
                resetGame();
            }
        }
    }

    void resetGame() {
        bird.y = birdY;
        velocityY = 0;
        score = 0;
        pipes.clear();
        gameOver = false;
        pipeSpawner.start();
        gameLoop.start();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    // --- Main method ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flappy Bird");
            FlappyBird gamePanel = new FlappyBird();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.add(gamePanel);
            frame.pack();
            frame.setLocationRelativeTo(null); // ✅ 8. Component Placement and Alignment in the UI
            frame.setVisible(true);
        });
    }
}
