package com.exa.app.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.exa.app.databinding.ActivityMainBinding



/**
 * @author wwq
 * @description:
 * @date :2022/3/14
 */

class MainActivity:AppCompatActivity(){
      private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWindow()
    }


    private fun initWindow() {
        //通过WindowInsetsControllerCompat可以简化状态栏、导航栏、键盘控制
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowCompat.getInsetsController(window, findViewById(android.R.id.content)).apply {
            isAppearanceLightStatusBars=true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}