package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 for cat 1 for puppy 2 for empty
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int playerchance;
    boolean someoneWon=false;
    boolean gameActive=true;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView)view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameActive) {
            gameState[tappedCounter] = playerchance;
            counter.setTranslationY(-2000);
            if (playerchance == 0) {
                counter.setImageResource(R.drawable.cat);
                counter.animate().translationYBy(2000).setDuration(100);
                playerchance = 1;
            } else {
                counter.setImageResource(R.drawable.puppy);
                counter.animate().translationYBy(2000).setDuration(100);
                playerchance = 0;
            }
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //Someone has won
                    gameActive = false;
                    someoneWon=true;
                    String winner = "";
                    if (playerchance == 1) {
                        winner = "Cat";
                    } else
                        winner = "Puppy";
                    TextView Won=(TextView) findViewById(R.id.hasWontextView);
                    Won.setText(winner + " has won!");
                    Button PlayAgain=(Button)findViewById(R.id.playAgainButton);
                    PlayAgain.setVisibility(View.VISIBLE);
                    Won.setVisibility(View.VISIBLE);
                }


            }//end of winning position loop
             if(someoneWon==false){
                boolean allFilledAndDraw=false;
                for(int i=0;i<gameState.length;i++)
                {
                    if(gameState[i]!=2)
                        allFilledAndDraw=true;
                    else {
                        allFilledAndDraw = false;
                        break;
                    }
                }   //end of checking of all filled or not
                if(allFilledAndDraw)
                {
                    gameActive=false;
                    String winner = "";
                    TextView Won=(TextView) findViewById(R.id.hasWontextView);
                    Won.setText("Match Drawn!");
                    Button PlayAgain=(Button)findViewById(R.id.playAgainButton);
                    PlayAgain.setVisibility(View.VISIBLE);
                    Won.setVisibility(View.VISIBLE);
                }
            }//end of inner if(solves triangular bug)
        }//end of if condition which checks whether a place is empty in tic tac toe board or anyone has won due to which game is not active anymore
    }//end of dropIn()

    //when play again is pressed
    public void playOnceMore(View view)
    {
        TextView Won=(TextView) findViewById(R.id.hasWontextView);
        Won.setText("");
        Button PlayAgain=(Button)findViewById(R.id.playAgainButton);
        PlayAgain.setVisibility(View.INVISIBLE);
        Won.setVisibility(View.INVISIBLE);
        Log.i("Info","Error after this");
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        gameActive=true;
        someoneWon=false;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;
        playerchance=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerchance=0;
    }
}
