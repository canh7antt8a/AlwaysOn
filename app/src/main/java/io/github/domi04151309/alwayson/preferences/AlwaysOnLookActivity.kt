package io.github.domi04151309.alwayson.preferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import androidx.preference.PreferenceManager
import io.github.domi04151309.alwayson.R
import io.github.domi04151309.alwayson.objects.Theme

class AlwaysOnLookActivity : AppCompatActivity() {

    private var value: String = "google"
    private lateinit var prefs: SharedPreferences
    private lateinit var preview: ImageView
    private lateinit var googleBtn: RadioButton
    private lateinit var samsungBtn: RadioButton
    private lateinit var secondSamsungBtn: RadioButton
    private lateinit var oneplusBtn: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        Theme.set(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ao_look)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        preview = findViewById(R.id.preview)
        googleBtn = findViewById(R.id.googleBtn)
        samsungBtn = findViewById(R.id.samsungBtn)
        secondSamsungBtn = findViewById(R.id.secondSamsungBtn)
        oneplusBtn = findViewById(R.id.oneplusBtn)

        googleBtn.setOnClickListener{
            preview.setImageResource(R.drawable.always_on_0)
            value = "google"
        }

        samsungBtn.setOnClickListener{
            preview.setImageResource(R.drawable.always_on_1)
            value = "samsung"
        }

        secondSamsungBtn.setOnClickListener{
            preview.setImageResource(R.drawable.always_on_2)
            value = "samsung2"
        }

        oneplusBtn.setOnClickListener{
            preview.setImageResource(R.drawable.always_on_3)
            value = "oneplus"
        }
    }

    override fun onStart() {
        super.onStart()
        value = prefs.getString("ao_style", "google") ?:"google"
        when (value) {
            "google" -> {
                preview.setImageResource(R.drawable.always_on_0)
                googleBtn.isChecked = true
            }
            "samsung" -> {
                preview.setImageResource(R.drawable.always_on_1)
                samsungBtn.isChecked = true
            }
            "samsung2" -> {
                preview.setImageResource(R.drawable.always_on_2)
                secondSamsungBtn.isChecked = true
            }
            "oneplus" -> {
                preview.setImageResource(R.drawable.always_on_3)
                oneplusBtn.isChecked = true
            }
        }
    }

    override fun onStop() {
        super.onStop()
        prefs.edit().putString("ao_style", value).apply()
    }
}
