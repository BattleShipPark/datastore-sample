package com.battleshippark.datastoresample

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.battleshippark.datastoresample.databinding.ActivityMainBinding
import com.battleshippark.preference.DataStorePreference
import com.battleshippark.proto.DataStoreProto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityMainBinding

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.prefReadBtn.setOnClickListener {
            launch {
                DataStorePreference(this@MainActivity).read().collect {
                    binding.prefText.text = it.toString()
                }
            }
        }

        binding.prefIncBtn.setOnClickListener {
            launch {
                DataStorePreference(this@MainActivity).inc()
            }
        }

        binding.protoReadBtn.setOnClickListener {
            launch {
                DataStoreProto(this@MainActivity).read().collect {
                    binding.protoText.text = it.toString()
                }
            }
        }

        binding.protoIncBtn.setOnClickListener {
            launch {
                DataStoreProto(this@MainActivity).inc()
            }
        }
    }
}