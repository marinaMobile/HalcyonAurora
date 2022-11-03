package com.smile.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.smile.R

class StartGame : AppCompatActivity() {
    lateinit var enemyPic: ImageView
    lateinit var playerPic: ImageView
    val choices = mutableListOf<String>("J", "K", "A")
    var playerscore = 0
    var enemyscore = 0
    lateinit var playerScoreText: TextView
    lateinit var enemyScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)

        MaterialAlertDialogBuilder(this@StartGame)
            .setTitle("Simple Rules")
            .setMessage("J defeats A, K defeats J, A defeats K")
            .setCancelable(false)
            .setPositiveButton("Play"){dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

        enemyPic = findViewById(R.id.enemy_pic)
        playerPic = findViewById(R.id.player_pic)

        playerScoreText = findViewById(R.id.player_score_text)
        enemyScoreText = findViewById(R.id.enemy_score_text)
        win()

    }
    fun rock(v: View){
        playerPic.setImageResource(R.drawable.j)
        val enemychoice = choices.random()
        when (enemychoice){
            "J" -> {enemyPic.setImageResource(R.drawable.j)}

            "K" -> {enemyPic.setImageResource(R.drawable.k)
                enemyscore++
                enemyScoreText.text = enemyscore.toString()

            }

            "A" -> {enemyPic.setImageResource(R.drawable.a)
                playerscore++
                playerScoreText.text = playerscore.toString()

            }

        }


    }

    fun paper(v: View){
        playerPic.setImageResource(R.drawable.k)
        val enemychoice = choices.random()
        when (enemychoice){
            "J" -> {enemyPic.setImageResource(R.drawable.j)
                playerscore++
                playerScoreText.text = playerscore.toString()}

            "K" -> {enemyPic.setImageResource(R.drawable.k)}

            "A" -> {enemyPic.setImageResource(R.drawable.a)
                enemyscore++
                enemyScoreText.text = enemyscore.toString()}

        }
        win()
    }

    fun scissors(v: View) {
        playerPic.setImageResource(R.drawable.a)
        val enemychoice = choices.random()
        when (enemychoice) {
            "J" -> {
                enemyPic.setImageResource(R.drawable.j)
                enemyscore++
                enemyScoreText.text = enemyscore.toString()
            }

            "K" -> {
                enemyPic.setImageResource(R.drawable.k)
                playerscore++
                playerScoreText.text = playerscore.toString()
            }

            "A" -> {
                enemyPic.setImageResource(R.drawable.a)
            }

        }
        win()
    }

    private fun win() {
        val plscr = playerscore.toString()
        val enscr = enemyscore.toString()
        val intent = Intent(this@StartGame, FinishAct::class.java)
        intent.putExtra("key", plscr)
        intent.putExtra("key2", enscr)

        if (playerscore > 2 && playerscore > enemyscore) {

            startActivity(intent)

        } else if (enemyscore > 2 && enemyscore > playerscore ) {

            startActivity(intent)

        }
    }

}