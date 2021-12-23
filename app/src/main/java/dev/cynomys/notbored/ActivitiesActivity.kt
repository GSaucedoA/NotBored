package dev.cynomys.notbored

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dev.cynomys.notbored.databinding.ActivityActivitiesBinding


class ActivitiesActivity : AppCompatActivity() {
    private val viewModel: ActivitiesViewModel by viewModels()
    private lateinit var binding: ActivityActivitiesBinding
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(Const.SHARED_NAME, MODE_PRIVATE)

        setContentView(binding.root)

        binding.customToolbar.apply {
            toolbarTitle.text = getString(R.string.activities)
            toolbarBack.setOnClickListener {
                onBackPressed()
            }
            toolbarShuffle.setOnClickListener {
                randomSuggestion()
                passTitle("Random")
                startActivity(Intent(applicationContext, SuggestionActivity::class.java))
            }
        }
        binding.recyclerView.adapter =
            ActivitiesAdapter(resources.getStringArray(R.array.activitiesList), sharedPreferences)
    }

    private fun randomSuggestion() {
        val randomSuggestion = resources.getStringArray(R.array.activitiesSuggestions)
        val randomPos = (randomSuggestion.indices).random()
        sharedPreferences.edit().apply {
            putString(Const.SUGGESTION, randomSuggestion.get(randomPos))
        }.commit()
    }

    private fun passTitle(name: String) {
        sharedPreferences.edit().apply {
            putString(Const.SUGGESTION_TITLE, name)
        }.commit()
    }
}