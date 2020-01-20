package fr.iut.piscinenetptut.ui.accountSetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.library.extension.toTreatFor

class AccountSettingActivity : AppCompatActivity(), AccountSettingActivityMvc.Listenners {

    companion object {
        private val TAG: String = "HomeActivity"


        fun start(context: Context) {
            try {
                context.startActivity(Intent(context, AccountSettingActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var accountSettingActivityMvcImpl: AccountSettingActivityMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Account setting"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            accountSettingActivityMvcImpl = AccountSettingActivityMvcImpl(this, this)
            setContentView(accountSettingActivityMvcImpl.root)
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}