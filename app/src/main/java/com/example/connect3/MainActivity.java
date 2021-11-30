package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean player1 = true;
    String [] board;

    public void createBoard()
    {
        board = new String[9];
        for(int i =0; i<=8; i++)
            board[i] = "empty";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBoard();
    }

    public void clicked (View view)
    {
        String imageViewId = getResources().getResourceEntryName(view.getId());
        ImageView imageView = (ImageView) (view);
        imageView.animate().alpha(1).setDuration(2000);
        int tag = Integer.parseInt(imageView.getTag().toString());

        TextView tv = (TextView) findViewById(R.id.textView);
        if(player1) {
            tv.setText("Player 2");
            player1 = false;
            imageView.setImageResource(R.drawable.o);
            board[tag]="player1";
        }
        else {
            tv.setText("Player 1");
            player1 = true;
            imageView.setImageResource(R.drawable.x);
            board[tag]="player2";
        }

        imageView.setEnabled(false);
        if(thereIsAWin(tag))
            tv.setText(board[tag]+ "Wins!");

    }

    public boolean thereIsAWin(int tag)
    {
        if (horizontal(tag) || vertical(tag) || leftToRight(tag) || rightToLeft(tag));
            return true;
    }
    public boolean vertical(int tag)
    {
        int win=0;
        for(int i=0; i<3;i++) {
            for (int j = i; j <= i + 6; j += 3) {
                if(board[tag] == board[j])
                    win++;
            }

            if(win == 3)
                return true;
            win =0;
        }

        return false;
    }

    public boolean horizontal(int tag)
    {
        int win =0;
        for(int i=0;i< board.length-1;i++)
        {
            if(board[tag]==board[i])
                win++;
            if(win == 3)
                return true;
            if(i%3==2)
                win=0;

        }
        return false;
    }

    public boolean leftToRight(int tag)
    {
        if(board[tag]==board[0] && board[0]==board[4] && board[4]==board[8])
            return true;
        return false;
    }

    public boolean rightToLeft(int tag)
    {
        if(board[tag]==board[6] && board[6]==board[4] && board[4]==board[2])
            return true;
        return false;
    }
}