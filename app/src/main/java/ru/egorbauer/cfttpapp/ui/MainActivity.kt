package ru.egorbauer.cfttpapp.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.egorbauer.cfttpapp.databinding.ActivityMainBinding
import ru.egorbauer.cfttpapp.domain.entity.User

class MainActivity : AppCompatActivity() {

    private var adapter: MainActivityRecyclerAdapter = MainActivityRecyclerAdapter(this::onItemClick)

    private fun onItemClick(number:Int) {

        startActivity(Intent(this, SecondActivity::class.java).apply {
            putExtra("number", number)
        })

    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val vmMain:MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
    }

    private fun getData(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vmMain.userStateFlow.collect{
                        uiBind(it)
                    }
                }
            }
        }


    private fun uiBind(data:List<User>){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.cardViewRecycler.layoutManager = layoutManager
        adapter.setUpdatedData(data)
        binding.cardViewRecycler.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            if (isInternetAvailable()) {
                vmMain.updateUsers()
                getData()
                binding.swipeRefresh.isRefreshing = false
            }
            else {
                Toast.makeText(this, "No Internet Connection",Toast.LENGTH_SHORT).show()
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        return capabilities != null
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}