package fr.iut.piscinenetptut.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.ui.listOfUser.ListUserActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.buttonAddPool)?.setOnClickListener{
            this@SplashScreenActivity.finish()
            ListUserActivity.start(this@SplashScreenActivity)
        }

    }
}
