package com.example.minipaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    private lateinit var redSlider: SeekBar
    private lateinit var greenSlider: SeekBar
    private lateinit var blueSlider: SeekBar
    private lateinit var applyBackgroundColorButton: Button
    private lateinit var applyPaintColorButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sets the controls as the main view
        setContentView(R.layout.activity_main)

        // Creates and adds the canvas view
        val myCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        addContentView(myCanvasView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1800))

        // Fetches the controls
        redSlider = findViewById(R.id.red_seek_bar)
        greenSlider = findViewById(R.id.green_seek_bar)
        blueSlider = findViewById(R.id.blue_seek_bar)
        applyBackgroundColorButton = findViewById(R.id.button_apply_background_color)
        applyPaintColorButton = findViewById(R.id.button_apply_paint_color)

        // Changes the background color on button press
        applyBackgroundColorButton.setOnClickListener {
            val backgroundColor =
                getColor(redSlider.progress, greenSlider.progress, blueSlider.progress)
            myCanvasView.setCanvasColor(backgroundColor)
        }

        // Changes the paint color on button press
        applyPaintColorButton.setOnClickListener {
            val paintColor =
                getColor(redSlider.progress, greenSlider.progress, blueSlider.progress)
            myCanvasView.setPaintColor(paintColor)
        }

    }

    /**
     * Takes in RGB values and makes a color
     */
    private fun getColor(red: Int, green: Int, blue: Int): Int {
        return android.graphics.Color.rgb(red, green, blue)
    }
}