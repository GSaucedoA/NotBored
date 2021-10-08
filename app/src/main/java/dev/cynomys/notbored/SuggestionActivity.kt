package dev.cynomys.notbored

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.cynomys.notbored.databinding.ActivitySuggestionBinding

class SuggestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuggestionBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(Const.SHARED_NAME, MODE_PRIVATE)

        setContentView(binding.root)
        binding.customToolbar.apply {
            toolbarTitle.text = sharedPreferences.getString(Const.SUGGESTION_TITLE, "Where do you come?")
            toolbarShuffle.visibility = View.INVISIBLE
            toolbarBack.visibility = View.VISIBLE
            toolbarBack.setOnClickListener {
                onBackPressed()
            }
        }

    }
}