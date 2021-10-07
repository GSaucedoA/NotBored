package dev.cynomys.notbored

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dev.cynomys.notbored.databinding.ActivityActivitiesBinding


class Activities : AppCompatActivity() {
    private val viewModel: ActivitiesViewModel by viewModels()
    private lateinit var binding: ActivityActivitiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customToolbar.apply {
            toolbarTitle.text = getString(R.string.activities)
            toolbarBack.setOnClickListener {
                onBackPressed()
            }
            toolbarShuffle.setOnClickListener {
                val array = resources.getStringArray(R.array.activitiesList)
            }
        }
        binding.recyclerView.adapter = ActivitiesAdapter(resources.getStringArray(R.array.activitiesList))
    }
}