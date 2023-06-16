package com.example.recipes2

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


class StoperFragment: Fragment(), View.OnClickListener {
    private var seconds: Int = 0
    private var running: Boolean = false
    private var wasRunning: Boolean = false
    private var alarm: Boolean = false
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
            alarm = savedInstanceState.getBoolean("alarm")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout: View = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStoper(layout)
        val startButton = layout.findViewById<View>(R.id.start_button) as Button
        startButton.setOnClickListener(this)
        val stopButton = layout.findViewById<View>(R.id.stop_button) as Button
        stopButton.setOnClickListener(this)
        val resetButton = layout.findViewById<View>(R.id.reset_button) as Button
        resetButton.setOnClickListener(this)
        val rootView = requireActivity().window.decorView.rootView
        snackbar = Snackbar.make(rootView, "Koniec odliczania", Snackbar.LENGTH_INDEFINITE)

        return layout
    }

    fun onEnd(){
        running = false
        alarm = true
        snackbar.show()
    }

    private fun startTimer(time:Int)
    {
        seconds = time * 60
        running = true
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    fun onClickStart() {
        if(!running && !alarm){
            running = true
            val time  = view?.findViewById<View>(R.id.time_edit_text) as EditText
            val currTime = time.text.toString().toInt() ?:0
            startTimer(currTime)
        }
    }

    fun onClickStop() {
        alarm = false
        snackbar.dismiss()
    }

    fun onClickReset() {
        running = false
        seconds = 0
    }


    private fun runStoper(view: View) {
        val timeView = view.findViewById<View>(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%02d:%02d", minutes, secs)
                timeView.text = time
                if (running) {
                    seconds--
                    if(seconds==0)
                    {
                        onEnd()
                    }
                }
                handler.postDelayed(this, 100)
            }
        })
    }
}

