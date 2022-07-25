package com.ezatpanah.taptargetview_youtube

import android.R
import android.content.SharedPreferences
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.taptargetview_youtube.databinding.ActivityMainBinding
import com.getkeepsafe.taptargetview.TapTarget
import com.getkeepsafe.taptargetview.TapTargetView


class MainActivity : AppCompatActivity() {

        lateinit var binding: ActivityMainBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var prefEditor :  SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("didShowPrompt", MODE_PRIVATE)
        prefEditor =sharedPref.edit()

        showFabPrompt()

    }

    private fun showFabPrompt() {
        if (!sharedPref.getBoolean("didShowPrompt", false)) {
            TapTargetView.showFor(this,  // `this` is an Activity
                TapTarget.forView(binding.btnFab, "This is a target", "We have the best targets, believe me") // All options below are optional
                    .tintTarget(false)
                    .outerCircleColor(R.color.purple_200),  // Specify the target radius (in dp)
                object : TapTargetView.Listener() {
                    override fun onTargetClick(view: TapTargetView) {
                        super.onTargetClick(view)
                        showButtonPrompt()
                    }
                })
        }
    }

    private fun showButtonPrompt() {
        TapTargetView.showFor(this,
            TapTarget.forView(binding.btnTest, "This is a Test Button", "We have the best targets, believe me") // All options below are optional
                .tintTarget(false)
                .outerCircleColor(R.color.purple_200),
            object : TapTargetView.Listener() {
                override fun onTargetClick(view: TapTargetView) {
                    super.onTargetClick(view)

                    val prefEditor = sharedPref.edit()
                    prefEditor.putBoolean("didShowPrompt", true)
                    prefEditor.apply()
                }
            })
    }
}
