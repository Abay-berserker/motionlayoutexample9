package com.example.motionlayoutexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout

class MainActivity : AppCompatActivity() {

    private lateinit var motionLayout: MotionLayout
    private lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        motionLayout = findViewById(R.id.motionLayout)
        actionButton = findViewById(R.id.actionButton)

        // Button click to toggle animation
        actionButton.setOnClickListener {
            toggleAnimation()
        }

        // Listen for transition completion
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                // Transition started
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                // Transition in progress
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // Update button text based on current state
                updateButtonText(currentId)
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
                // Trigger called
            }
        })
    }

    private fun toggleAnimation() {
        val currentState = motionLayout.currentState
        when (currentState) {
            R.id.start -> motionLayout.transitionToState(R.id.end)
            R.id.end -> motionLayout.transitionToState(R.id.start)
            else -> motionLayout.transitionToState(R.id.end)
        }
    }

    private fun updateButtonText(currentState: Int) {
        val buttonText = when (currentState) {
            R.id.start -> "Expand Header"
            R.id.end -> "Collapse Header"
            else -> "Toggle Animation"
        }
        actionButton.text = buttonText
    }
}