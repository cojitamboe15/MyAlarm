package com.android.myalarm

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.myalarm.databinding.FragmentTimerBinding

/**
 */
class TimerFragment : Fragment() {
    private var _binding: FragmentTimerBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }


    // HAVE TO ADD A WAY TO HIDE THE START BUTTON ONCE IT HAS ALREADY STARTED
    // HAVE TO ADD A WAY TO HIDE THE START BUTTON ONCE IT HAS ALREADY STARTED
    // HAVE TO ADD A WAY TO HIDE THE START BUTTON ONCE IT HAS ALREADY STARTED

    // To "pause" timer, we can do something like
    // click "pause" button
    // this would stop the countdown and freeze the timer on the textview
    // if "pause" button is clicked again while in a "paused" state (will need a boolean flag for this)
    // then create a new startcountdown function that takes in the remaining time of the previous countdown

    // I THINK "millisUntilFinished" in onTick can be used to get the remaining time.

    private var paused = false
    private var started = false




    // NEED CODE TO CHANGE THE PAUSE/RESUME BUTTON BACK TO "START" IF "CANCEL" HAS BEEN CLICKED
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startCountdown.setOnClickListener { v ->
            // timer is now running, so should go into if statement.
            if (!paused) {
                if (!started) {
//                    startCountdown()
                    timer.start()
                    started = true
                }
                paused = true // set paused to true because we should now be able to click pause.
                binding.startCountdown.text = getString(R.string.pause)
            } else { // will prob need alot more code to actually pause
                // code
                binding.startCountdown.text = getString(R.string.resume)
                paused = false
            }
        }


        // need to manipulate the "paused" flag still
        binding.stopCountdown.setOnClickListener { v ->
            timer.cancel()
            started = false
            binding.startCountdown.text = getString(R.string.start)
        }


    }

    /**
     * Starts a countdown (timer) based on the parameters given. Uses 30,000 milliseconds
     * (30 seconds) as a default value for now.
     */
        val timer = object : CountDownTimer(30000, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                binding.countdownView.text = "seconds remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                Toast.makeText(activity, "Time is up", Toast.LENGTH_SHORT).show();
            }
        }



}