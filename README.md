# 🐦Flappy Bird Java Game with MySQL Score Tracking
<br>
<br>
<h3>✅ This project helps improve problem-solving and critical thinking by creating a simple game. It teaches how to handle game logic, collisions, and real-time events using code, encouraging logical thinking and creative solutions.</h3>
<br>
<H3>✅ A desktop version of the classic Flappy Bird game built using Java Swing. This project integrates a MySQL database to store and display high scores using JDBC.</H3>
<br>
<br>
🎮 Features
->  Smooth Flappy Bird gameplay with jumping, pipes, and collision detection.

-> Game Over screen with current and high score display.

-> Scores are saved in a MySQL database automatically after each game.

-> Responsive controls using the SPACE key to jump and restart.

🧱 Tech Stack
-> Java SE (Swing) – for GUI and game logic.

-> MySQL – for score storage.

-> JDBC – for database connectivity.

🗃️ Database Schema
<H4>
CREATE TABLE scores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  score INT NOT NULL
);</H4>
🚀 How to Run
-> Ensure MySQL is running and create a database named flappybird.

-> Update DB credentials in the code (ScoreDAO class).

-> Compile and run FlappyBirdGame.java in your IDE or terminal.

-> Press SPACE to play and restart!

📷 Screenshot
<img width="854" alt="image" src="https://github.com/user-attachments/assets/b6a6a753-efa6-46aa-adf0-fc3b49e3718e" />

📁 Project Structure
👉 FlappyBirdGame – Main class for game UI and logic.

👉 Score – Model for score data.

👉 ScoreDAO – Handles database operations.

🏁 Credits
-> Created as a Java project for demonstrating GUI design, database connectivity, and MVC principles in a single file.

🎯Expected Result 
<h3>-> The game runs smoothly: the bird flies through pipes when the spacebar is pressed, the score increases, and the game resets after hitting an obstacle. It proves that the game works as planned and is fun and interactive.<h3>
<br>

