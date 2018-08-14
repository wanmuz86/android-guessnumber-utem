package my.edu.utem.guessthenumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView answerTextView;
    EditText guessEditText;
    int numberToGuess;
    int numberOfTries;
    boolean gameOn;
    Button resetBtn, guessBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answerTextView = findViewById(R.id.answerTextView);
        guessEditText = findViewById(R.id.editText);
        resetBtn = findViewById(R.id.resetBtn);
        guessBtn = findViewById(R.id.guessBtn);
        initializeGame();
        Log.d("debug", "generated number is " + numberToGuess);

    }

    private void initializeGame() {
        numberToGuess = (int) (Math.random() * 100);
        numberOfTries = 3;
        gameOn = true;
        answerTextView.setText("");
        resetBtn.setVisibility(View.GONE);
        guessBtn.setVisibility(View.VISIBLE);
    }

    public void guessBtnPressed(View view) {
        if (guessEditText.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_LONG).show();

        } else {
            numberOfTries--;
            int numberGuessed = Integer.parseInt(guessEditText.getText().toString());
            if (numberGuessed > numberToGuess) {
                answerTextView.setText("Number to high. Try again!");
            } else if (numberGuessed < numberToGuess) {
                answerTextView.setText("Number to low. Try again!");
            } else {
                answerTextView.setText("Congratulations you win!");
                gameOn = false;
                resetBtn.setVisibility(View.VISIBLE);
                guessBtn.setVisibility(View.GONE);
            }
            if (numberOfTries == 0 && gameOn) {
                answerTextView.setText("Sorry you lost. The number is " + numberToGuess);
                gameOn = false;
                resetBtn.setVisibility(View.VISIBLE);
                guessBtn.setVisibility(View.GONE);
            }
        }
    }

    public void resetGamePressed(View view) {
        initializeGame();
    }
}
