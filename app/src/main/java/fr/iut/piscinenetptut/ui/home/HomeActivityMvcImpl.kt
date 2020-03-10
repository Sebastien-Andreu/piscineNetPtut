package fr.iut.piscinenetptut.ui.home

import android.content.Context
import android.content.res.Resources
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.github.kittinunf.fuel.gson.gsonDeserializerOf
import com.google.android.material.navigation.NavigationView
import fr.iut.piscinenetptut.R
import fr.iut.piscinenetptut.entities.Account
import fr.iut.piscinenetptut.entities.Language
import fr.iut.piscinenetptut.library.extension.setAppLocale
import fr.iut.piscinenetptut.library.extension.toTreatFor
import fr.iut.piscinenetptut.ui.accountSetting.AccountSettingActivity
import fr.iut.piscinenetptut.ui.customerdetails.CustomerDetailsActivity
import fr.iut.piscinenetptut.ui.listOfCustomer.ListCustomerActivity
import fr.iut.piscinenetptut.ui.listOfEmployee.ListEmployeeActivity
import fr.iut.piscinenetptut.ui.listOfVisit.ListOfVisitActivity
import fr.iut.piscinenetptut.ui.splashscreen.SplashScreenActivity
import fr.iut.piscinenetptut.ui.welcomeCustomer.WelcomeCustomerActivity


class HomeActivityMvcImpl(
    val homeActivity: HomeActivity,
    val context: Context
): HomeActivtyMvc, NavigationView.OnNavigationItemSelectedListener {

    val TAG: String = "HomeActivtyMvcImpl"
    var root: View? = null

    lateinit var drawerLayout: DrawerLayout

    init {
        try {
            root = View.inflate(context, R.layout.activity_main, null)

            homeActivity.setContentView(root)


            if (null != root) {

                drawerLayout = root!!.findViewById(R.id.drawer_layout)

                val toggle = ActionBarDrawerToggle(
                    homeActivity, drawerLayout , root!!.findViewById(R.id.toolbar), 0, 0
                )

                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()
                root!!.findViewById<NavigationView>(R.id.nav_view)?.setNavigationItemSelectedListener(this)

                val headerView = root!!.findViewById<NavigationView>(R.id.nav_view)?.getHeaderView(0)
                headerView!!.findViewById<TextView>(R.id.navBarTextName)?.text = (context.getString(R.string.LabelLogin)  + " "+ Account.register.login)

                when (Account.register.role) {
                    "admin" -> {
                        root!!.findViewById<NavigationView>(R.id.nav_view)?.inflateMenu(R.menu.nav_menu)
                        homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, ListCustomerActivity()).commit()
                        root!!.findViewById<NavigationView>(R.id.nav_view)?.setCheckedItem(R.id.menuListOfCustomer)
                    }
                    "employee" -> {
                        root!!.findViewById<NavigationView>(R.id.nav_view)?.inflateMenu(R.menu.nac_menu_employee)
                        homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, ListCustomerActivity()).commit()
                        root!!.findViewById<NavigationView>(R.id.nav_view)?.setCheckedItem(R.id.menuListOfCustomer)
                    }
                    "customer" -> {
                        root!!.findViewById<NavigationView>(R.id.nav_view)?.inflateMenu(R.menu.nav_menu_customer)
                        homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, WelcomeCustomerActivity()).commit()
                    }
                }
            }
        } catch (exception: Exception) {
            exception.toTreatFor(TAG)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuListOfCustomer -> {
                homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, ListCustomerActivity()).commit()
            }
            R.id.menuListOfVisit -> {
                homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, ListOfVisitActivity()).commit()
            }
            R.id.menuListOfEmployee -> {
                homeActivity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ListEmployeeActivity()).commit()
            }
            R.id.menuSetting -> {
                homeActivity.supportFragmentManager.beginTransaction().replace( R.id.fragment_container, AccountSettingActivity()).commit()
            }
            R.id.menuSignOut -> {
                Account.reset()
                this.homeActivity.finish()
                SplashScreenActivity.start(this.context)
            }
            R.id.menuDetailsPoolCustomer -> {
                CustomerDetailsActivity.start(this.context)
            }
            R.id.language -> {

                if (Language.language == "en-us"){
                    Language.language = "fr"
                } else {
                    Language.language = "en-us"
                }
                setAppLocale(homeActivity.resources)
                homeActivity.finish()
                HomeActivity.start(homeActivity)
            }
        }

        if (item.itemId != R.id.language){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
    }
}
