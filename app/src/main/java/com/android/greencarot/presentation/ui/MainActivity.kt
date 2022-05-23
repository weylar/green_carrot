package com.android.greencarot.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.greencarot.databinding.ActivityMainBinding
import com.android.greencarot.helper.show
import com.android.greencarot.model.User
import com.android.greencarot.presentation.adapter.UserAdapter
import com.android.greencarot.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    val result = mutableListOf<User>()

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadData()
    }

    private fun loadData() {
        val adapter = UserAdapter(result)
        binding?.recyclerView?.adapter = adapter
        viewModel.name.onEach {
            binding?.loader?.show(result.isEmpty())
            if(it != null) result.add(it)
            adapter.notifyItemChanged(result.lastIndex)


        }.launchIn(lifecycleScope)
    }
}