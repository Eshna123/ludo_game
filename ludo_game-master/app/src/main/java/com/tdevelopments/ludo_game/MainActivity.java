// MainActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LudoGame ludoGame;
    private TextView diceValueText;
    private TextView playerScoresText;
    private Button rollDiceButton;
    private Button newGameButton;
    private Button pauseButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        diceValueText = findViewById(R.id.diceValue);
        playerScoresText = findViewById(R.id.playerScores);
        rollDiceButton = findViewById(R.id.rollDiceButton);
        newGameButton = findViewById(R.id.newGameButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        // Initialize game with two players
        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        ludoGame = new LudoGame(players);

        // Roll dice button listener
        rollDiceButton.setOnClickListener(v -> {
            if (!ludoGame.isGameOver()) {
                int diceValue = ludoGame.rollDice();
                diceValueText.setText("Dice: " + diceValue);
                updateScores();
                if (ludoGame.isGameOver()) {
                    Player winner = ludoGame.determineWinner();
                    playerScoresText.setText("Winner: " + winner.getName() + " with " + winner.getScore() + " points!");
                }
            }
        });

        // New Game button listener
        newGameButton.setOnClickListener(v -> {
            ludoGame.resetGame();
            diceValueText.setText("Dice: ");
            playerScoresText.setText("Scores reset.");
        });

        // Reset button listener
        resetButton.setOnClickListener(v -> ludoGame.resetGame());

        // Pause button listener (implementation depends on desired
