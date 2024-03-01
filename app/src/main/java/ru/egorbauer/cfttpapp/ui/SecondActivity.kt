package ru.egorbauer.cfttpapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.egorbauer.cfttpapp.databinding.SecondActivityBinding
import ru.egorbauer.cfttpapp.domain.entity.User

class SecondActivity : AppCompatActivity() {

    private var _binding: SecondActivityBinding? = null
    private val binding get() = _binding!!
    private val vmSecond: SecondActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SecondActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val secondIntent = intent
        val position = secondIntent.getIntExtra("number", 0)
        getData(position)
    }

    private fun getData(position: Int) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vmSecond.userSecondStateFlow.collect {
                    uiBind(it[position])
                }
            }
        }
    }

    private fun uiBind(user: User) {
        with(binding) {
            detailedPhoto.load(user.picture) {
                crossfade(true)
            }
            detailedName.text = user.name
            detailedGender.text = user.gender
            detailedAddress.text = user.location
            detailedPhoneNumber.text = user.phone
            detailedEmail.text = user.email
            detailedAge.text = user.age + " years old"
            detailedBirthday.text = user.dob.removeRange(10, user.dob.length)

            detailedPhoneNumber.setOnClickListener {
                val call = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${user.phone}")
                }
                startActivity(call)
            }

            detailedEmail.setOnClickListener {
                val email = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto: ${user.email}")
                }
                startActivity(email)
            }

            detailedAddress.setOnClickListener {
                val map = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=${user.location}")
                }
                startActivity(map)
            }
        }
    }

}