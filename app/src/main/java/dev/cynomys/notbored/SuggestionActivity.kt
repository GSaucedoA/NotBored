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

        var category: String? = null
        if (title == "Random") {
            binding.categoryLayout.visibility = View.VISIBLE
            category = resources.getStringArray(R.array.activitiesList).random()
        }
        setInfo(
            sharedPreferences.getString(Const.SUGGESTION, "Error"),
            sharedPreferences.getString(Const.PARTICIPANTS_COUNT, "0"),
            getPrice(0f),
            category
        )
        binding.tryAnother.setOnClickListener {
            tryAnother()
        }
    }

    private fun setInfo(
        suggestionTitle: String?,
        participantsCount: String?,
        price: String?,
        category: String?
    ) {
        suggestionTitle?.let {
            binding.suggestionTitle.text = it
        }
        participantsCount?.let {
            binding.participantsCount.text = it
        }
        price?.let {
            binding.priceString.text = it
        }
        category?.let {
            binding.categoryText.text = it
        }
    }

    private fun tryAnother() {
        setInfo(
            resources.getStringArray(R.array.activitiesSuggestions).random(),
            null,
            null,
            resources.getStringArray(R.array.activitiesList).random()
        )
    }

    private fun getPrice(price: Float): String {
        var stringPrice = "Free"
        if (price > 0 && price <= 0.3) stringPrice = "Low"
        else if (price > 0.3 && price <= 0.6) stringPrice = "medium"
        else if (price > 0.6) stringPrice = "High"
        return stringPrice
    }
}