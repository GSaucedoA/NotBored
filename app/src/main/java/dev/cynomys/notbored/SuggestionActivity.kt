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

        val title = sharedPreferences.getString(Const.SUGGESTION_TITLE, "Where do you come?")
        binding.customToolbar.apply {
            toolbarTitle.text = title
            toolbarShuffle.visibility = View.INVISIBLE
            toolbarBack.visibility = View.VISIBLE
            toolbarBack.setOnClickListener {
                onBackPressed()
            }
        }

        binding.suggestionTitle.text = sharedPreferences.getString(Const.SUGGESTION, "Error")
        binding.participantsCount.text = sharedPreferences.getString(Const.PARTICIPANTS_COUNT, "0")
        if (title == "Random") {
            binding.categoryLayout.visibility = View.VISIBLE
            binding.categoryText.text = resources.getStringArray(R.array.activitiesList).random()
        }
    }
}