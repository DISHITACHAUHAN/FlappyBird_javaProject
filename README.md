# 🐦Flappy Bird Java Game with MySQL Score Tracking
<br>
</H4>✅ This project helps improve problem-solving and critical thinking by creating a simple game. It teaches how to handle game logic, collisions, and real-time events using code, encouraging logical thinking and creative solutions.
<BR>
✅ A desktop version of the classic Flappy Bird game built using Java Swing. This project integrates a MySQL database to store and display high scores using JDBC.
<br></H4>

<H4>🎮 Features</H4>

->  Smooth Flappy Bird gameplay with jumping, pipes, and collision detection.
<BR>
-> Game Over screen with current and high score display.
<BR>
-> Scores are saved in a MySQL database automatically after each game.
<BR>
-> Responsive controls using the SPACE key to jump and restart.
<BR>
<BR>
<h4>📄 Main Code File </h4>
<i>FlappyBird.java</i>
<br>
<br>
This is the main Java file that contains the entire game logic, UI (using Swing), and MySQL database integration. It includes:
<br>
->Bird and pipe rendering
<br>
-> Keyboard controls
<br>
-> Collision detection
<br>
-> Game loop and timer
<br>
-> Score saving and high score retrieval using JDBC
<br>
<H4>🧱 Tech Stack</H4>
-> Java SE (Swing) – for GUI and game logic.
<BR>
-> MySQL – for score storage.
<BR>
-> JDBC – for database connectivity.
<BR>
<BR>
<H4>🗃️ Database Schema</H4>

CREATE TABLE scores (<BR>
  id INT AUTO_INCREMENT PRIMARY KEY,<BR>
  score INT NOT NULL<BR>
);<BR>
<BR>
<H4>🚀 How to Run</H4>

-> Ensure MySQL is running and create a database named flappybird.
<BR>
-> Update DB credentials in the code (ScoreDAO class).,
<BR>
-> Compile and run FlappyBirdGame.java in your IDE or terminal.
<BR>
-> Press SPACE to play and restart!
<BR>
<BR>
<H4>📷 Screenshot</H4>

<img width="854" alt="image" src="https://github.com/user-attachments/assets/b6a6a753-efa6-46aa-adf0-fc3b49e3718e" />
<BR>
<BR>
<H4>📁 Project Structure </H4>
-> FlappyBirdGame – Main class for game UI and logic.
<BR>
-> Score – Model for score data.
<BR>
-> ScoreDAO – Handles database operations.
<BR>
<BR>
<H4>🏁 Credits</H4>
-> Created as a Java project for demonstrating GUI design, database connectivity, and MVC principles in a single file.
<BR>
<H4>🎯 Expected Result:</H4>
<B>The game runs smoothly: the bird flies through pipes when the spacebar is pressed, the score increases, and the game resets after hitting an obstacle. It proves that the game works as planned and is fun and interactive.
