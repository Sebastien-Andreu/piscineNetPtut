package fr.iut.piscinenetptut.ui.visitDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.library.extension.toTreatFor

class VisitDetailsActivity: AppCompatActivity(), VisitDetailsMvc.Listeners {

    companion object {
        private val TAG : String = "VisitDetailsActivity"

        fun start(context: Context) {
            try {

                context.startActivity(Intent(context, VisitDetailsActivity::class.java))
            } catch (exception: Exception) {
                exception.toTreatFor(TAG)
            }
        }
    }

    private lateinit var visitDetailsMvcImpl: VisitDetailsMvcImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)

            supportActionBar?.title = getString(R.string.VisitDetails)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)


            visitDetailsMvcImpl = VisitDetailsMvcImpl(this, this)
            setContentView(visitDetailsMvcImpl.root)

        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        this@VisitDetailsActivity.finish()
    }
}