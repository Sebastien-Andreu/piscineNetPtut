package fr.iut.piscinenetptut.ui.accountSetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.entities.Register
import fr.iut.piscinenetptut.library.extension.toTreatFor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class AccountSettingActivity : AppCompatActivity(), AccountSettingActivityMvc.Listenners {

    companion object {
        private val TAG: String = "HomeActivity"

        private val EXTRA_REGISTER_DETAIL: String = "EXTRA_REGISTER_DETAIL"

        val json = Json(JsonConfiguration.Stable)

        fun start(
            context: Context,
            register: Register) {
            try {
                context.startActivity(Intent(context, AccountSettingActivity::class.java)
                    .putExtra(EXTRA_REGISTER_DETAIL, json.stringify(Register.serializer(), register)))

            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    lateinit var accountSettingActivityMvcImpl: AccountSettingActivityMvcImpl
    lateinit var register: Register

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = "Account setting"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            this.register = json.parse(Register.serializer(),intent.getStringExtra(EXTRA_REGISTER_DETAIL)!!)

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