package com.jjakyo.stopwatch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Chronometer
import com.jjakyo.stopwatch.databinding.ActivityMainBinding
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var time: Long
        var startTime: String = "2023-02-12 17:20:00"       // 시작 시각(string)
        var startMilliSec: Long = 0L     // 시작 시간(millisec)
        var pauseSec: Long = 60     // 일시정지 시간(sec)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime)
        startMilliSec = dateFormat.time

        pauseSec *= 1000    // sec -> millisec

        binding.timer.onChronometerTickListener = Chronometer.OnChronometerTickListener { chronometer ->
            time = System.currentTimeMillis() - startMilliSec   // 현재시간 - 시작시간

            val h = (time / 3600000).toInt()
            val m = (time - h * 3600000).toInt() / 60000
            val s = (time - h * 3600000 - m * 60000).toInt() / 1000
            val hh = if (h < 10) "0$h" else h.toString()
            val mm = if (m < 10) "0$m" else m.toString()
            val ss = if (s < 10) "0$s" else s.toString()

            chronometer.text = "$hh:$mm:$ss"
        }

        binding.startBtn.setOnClickListener {
//            startMilliSec = System.currentTimeMillis()
            binding.timer.start()
        }

        binding.stopBtn.setOnClickListener {
            binding.timer.stop()
        }

    }
}