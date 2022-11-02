package com.lc.sharepreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lc.sharepreference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedPref by lazy {
        getSharedPreferences("sharePreferenceDemo", Context.MODE_PRIVATE)
    }

    companion object {
        const val KEY_SCORE = "key-score"
        const val KEY_USER_NAME = "key-user-name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvScore.text = getScore().toString()

        binding.btnPlus.setOnClickListener {
            val score = getScore() + 1
            binding.tvScore.text = score.toString()
            putScore(score)
        }

        binding.btnMinus.setOnClickListener {
            val score = getScore() - 1
            if (score >= 0) {
                putScore(score)
                binding.tvScore.text = score.toString()
            }
        }
    }

    private fun putScore(score: Int) {
        sharedPref.edit().putInt(KEY_SCORE, score).apply()
    }

    private fun getScore(): Int {
        return sharedPref.getInt(KEY_SCORE, 0)
    }

    private fun putUserName(name: String) {
        sharedPref.edit().putString(KEY_USER_NAME, name).apply()
    }

    private fun getUserName(): String {
        return sharedPref.getString(KEY_USER_NAME, "").orEmpty()
    }
}