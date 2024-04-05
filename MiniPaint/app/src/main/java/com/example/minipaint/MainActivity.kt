package com.example.minipaint

import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    private lateinit var redSeekBar: SeekBar
    private lateinit var greenSeekBar: SeekBar
    private lateinit var blueSeekBar: SeekBar
    private lateinit var applyBackgroundColorButton: Button
    private lateinit var applyPaintColorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        addContentView(myCanvasView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2000))

        redSeekBar = findViewById(R.id.red_seek_bar)
        greenSeekBar = findViewById(R.id.green_seek_bar)
        blueSeekBar = findViewById(R.id.blue_seek_bar)
        applyBackgroundColorButton = findViewById(R.id.button_apply_background_color)
        applyPaintColorButton = findViewById(R.id.button_apply_paint_color)

        applyBackgroundColorButton.setOnClickListener {
            val backgroundColor = getColorFromSeekBar(redSeekBar.progress, greenSeekBar.progress, blueSeekBar.progress)
            myCanvasView.setCanvasColor(backgroundColor)
        }

        applyPaintColorButton.setOnClickListener {
            val paintColor = getColorFromSeekBar(redSeekBar.progress, greenSeekBar.progress, blueSeekBar.progress)
            myCanvasView.setPaintColor(paintColor)
        }

    }

    private fun getColorFromSeekBar(red: Int, green: Int, blue: Int): Int {
        return android.graphics.Color.rgb(red, green, blue)
    }
}