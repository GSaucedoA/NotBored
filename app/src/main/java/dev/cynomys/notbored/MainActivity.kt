package dev.cynomys.notbored

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import dev.cynomys.notbored.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_NotBored_start)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPreferences = getSharedPreferences(Const.SHARED_NAME, MODE_PRIVATE)

        setContentView(binding.root)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.startButton.setOnClickListener {
            sharedPreferences.edit().apply {
                putString(Const.PARTICIPANTS_COUNT, binding.textInputEditText.text.toString())
                putString(Const.PRICE, binding.textInputPrice.text.toString())
            }.apply()
            startActivity(Intent(this, ActivitiesActivity::class.java))
        }
        binding.textInputEditText.doAfterTextChanged {
            viewModel.canStart(
                it.toString(),
                binding.textInputPrice.text.toString(),
                binding.termsAccepted.isChecked
            )
        }
        binding.textInputPrice.doAfterTextChanged {
            viewModel.canStart(
                binding.textInputEditText.text.toString(),
                it.toString(),
                binding.termsAccepted.isChecked
            )
        }
        binding.termsAndConditions.setOnClickListener {
            startActivity(Intent(this, TermsAndConditions::class.java))
        }
        binding.termsAccepted.setOnCheckedChangeListener { _, isChecked ->
            viewModel.canStart(
                binding.textInputEditText.text.toString(),
                binding.textInputPrice.text.toString(),
                isChecked
            )
        }
    }

    private fun setObservers() {
        viewModel.isValidStart.observe(this, {
            binding.startButton.isEnabled = it
        })
    }
}