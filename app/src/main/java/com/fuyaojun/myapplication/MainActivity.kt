package com.fuyaojun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fuyaojun.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intent: Intent
        binding.one.setOnClickListener {
            intent=Intent(this,Calculator::class.java)
            startActivity(intent)
        }
        binding.two.setOnClickListener {
            intent= Intent(this,QQLogin::class.java)
            startActivity(intent)
        }

    }
}