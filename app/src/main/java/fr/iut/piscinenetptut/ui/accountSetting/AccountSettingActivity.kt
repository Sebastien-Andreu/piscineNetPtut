package fr.iut.piscinenetptut.ui.accountSetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class AccountSettingActivity : Fragment(), AccountSettingActivityMvc.Listenners {

    private val TAG: String = "HomeActivity"

    lateinit var accountSettingActivityMvcImpl: AccountSettingActivityMvcImpl

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountSettingActivityMvcImpl = AccountSettingActivityMvcImpl(this, inflater.context)
        return accountSettingActivityMvcImpl.root
    }
}