package fr.iut.piscinenetptut.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        findViewById<Button>(R.id.button)?.setOnClickListener {
            this.finish()
            CustomerDetailsActivity.start(this)
        }
    }
}
