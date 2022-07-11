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
    lateinit var prefManager : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefManager = PreferenceManager.getDefaultSharedPreferences(this)

        showFabPrompt()

    }

    private fun showFabPrompt(){
        if (!prefManager.getBoolean("didShowPrompt", false)){
            TapTargetView.showFor(this,  // `this` is an Activity
                TapTarget.forView(binding.btnFab, "This is a target", "We have the best targets, believe me") // All options below are optional
                    .tintTarget(false)
                    .outerCircleColor(R.color.holo_purple),  // Specify the target radius (in dp)
                object : TapTargetView.Listener() {
                    override fun onTargetClick(view: TapTargetView) {
                        super.onTargetClick(view)
                        showButtonPrompt()
                    }
                })
        }
    }

    private fun showButtonPrompt(){
        TapTargetView.showFor(this,
            TapTarget.forView(binding.btnTest, "This is a Test Button", "We have the best targets, believe me") // All options below are optional
                .tintTarget(false)
                .outerCircleColor(R.color.holo_purple),
            object : TapTargetView.Listener() {
                override fun onTargetClick(view: TapTargetView) {
                    super.onTargetClick(view)
                    val prefEditor = prefManager.edit()
                    prefEditor.putBoolean("didShowPrompt", true)
                    prefEditor.apply()
                }
            })
    }

}