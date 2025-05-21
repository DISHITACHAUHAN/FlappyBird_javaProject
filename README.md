# 🐦Flappy Bird Java Game with MySQL Score Tracking
<br>
✅ This project helps improve problem-solving and critical thinking by creating a simple game. It teaches how to handle game logic, collisions, and real-time events using code, encouraging logical thinking and creative solutions.
<BR>
✅ A desktop version of the classic Flappy Bird game built using Java Swing. This project integrates a MySQL database to store and display high scores using JDBC.
<br>

🎮 Features
<BR>
->  Smooth Flappy Bird gameplay with jumping, pipes, and collision detection.

-> Game Over screen with current and high score display.

-> Scores are saved in a MySQL database automatically after each game.

-> Responsive controls using the SPACE key to jump and restart.<H3>
<BR>
🧱 Tech Stack
<BR>
-> Java SE (Swing) – for GUI and game logic.

-> MySQL – for score storage.

-> JDBC – for database connectivity.
<BR>
🗃️ Database Schema

<BR>
CREATE TABLE scores (<BR>
  id INT AUTO_INCREMENT PRIMARY KEY,<BR>
  score INT NOT NULL<BR>
);
<BR>
🚀 How to Run
<BR>
-> Ensure MySQL is running and create a database named flappybird.
<BR>
-> Update DB credentials in the code (ScoreDAO class).
<BR>
-> Compile and run FlappyBirdGame.java in your IDE or terminal.
<BR>
-> Press SPACE to play and restart!
<BR>
📷 Screenshot
<BR>
<img width="854" alt="image" src="https://github.com/user-attachments/assets/b6a6a753-efa6-46aa-adf0-fc3b49e3718e" />
<BR>
<BR>
📁 Project Structure
<BR>
<BR>
👉 FlappyBirdGame – Main class for game UI and logic.
<BR>
<BR>
👉 Score – Model for score data.
<BR>
<BR>
👉 ScoreDAO – Handles database operations.
<BR>
<BR>
🏁 Credits
<BR>
-> Created as a Java project for demonstrating GUI design, database connectivity, and MVC principles in a single file.
<BR>
🎯Expected Result 
<h3>-> The game runs smoothly: the bird flies through pipes when the spacebar is pressed, the score increases, and the game resets after hitting an obstacle. It proves that the game works as planned and is fun and interactive.<h3>
<br>

