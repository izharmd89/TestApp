package com.bws.izharassignment.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common.arrCases
import com.bws.izharassignment.constants.Common.arrDataStateWise
import com.bws.izharassignment.constants.Common.arrTestData
import com.bws.izharassignment.database.Cases
import com.bws.izharassignment.database.CovidDatabase
import com.bws.izharassignment.database.State
import com.bws.izharassignment.database.Tested
import com.bws.izharassignment.utils.Resources
import com.bws.izharassignment.databinding.ActivityMainBinding
import com.bws.izharassignment.factory.ViewModelFactory
import com.bws.izharassignment.repository.Repository
import com.bws.izharassignment.response.CaseTimeSerese
import com.bws.izharassignment.response.StateWise
import com.bws.izharassignment.response.TestedData
import com.bws.izharassignment.utils.LoadingDialog
import com.bws.izharassignment.utils.NetworkUtils
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var covidViewModel: CovidViewModel
    lateinit var binding: ActivityMainBinding

    lateinit var database: CovidDatabase
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //INSTANCE FOR DATABASE
        database = CovidDatabase.getDatabase(this)

        //INITIALISE VIEW MODEL
        setUpViewModel()

        //CHECKING NETWORK AVAILABLE
        if (NetworkUtils.isNetworkAvailable(this)) {
            callAPI()
        } else {
            populateDataFromDatabase()
        }
    }

    // POPULATE DATA VIA API
    private fun callAPI() {
        val loadingDialog = LoadingDialog.loader(this)
        covidViewModel.callDataAPI()
        covidViewModel.responseLiveData.observe(this, Observer {
            when (it) {
                is Resources.NoInternet -> {
                    loadingDialog.hide()
                }
                is Resources.Loading -> {
                    loadingDialog.show()
                }
                is Resources.Success -> {
                    loadingDialog.hide()

                    //DELETE OLD DATA FROM TABLE
                    GlobalScope.launch {
                        database.covidDAO().deleteCase()
                        database.covidDAO().deleteState()
                        database.covidDAO().deleteTested()
                    }

                    try {
                        val objResult = JSONObject(it.data)
                        val jsonArrayMemes = objResult.getJSONArray("cases_time_series")
                        for (i in 0 until jsonArrayMemes.length()) {
                            val ojbMemes = jsonArrayMemes.getJSONObject(i)
                            val meme =
                                CaseTimeSerese(
                                    ojbMemes.getString("dailyconfirmed"),
                                    ojbMemes.getString("dailydeceased"),
                                    ojbMemes.getString("dailyrecovered"),
                                    ojbMemes.getString("date"),
                                    ojbMemes.getString("dateymd"),
                                    ojbMemes.getString("totalconfirmed"),
                                    ojbMemes.getString("totaldeceased"),
                                    ojbMemes.getString("totalrecovered")
                                )
                            arrCases.add(meme)


                            //SAVE DATA IN CASES TABLE FOR OFF LINE USESE
                            GlobalScope.launch {
                                database.covidDAO().insertCases(
                                    Cases(
                                        0, ojbMemes.getString("dailyconfirmed"),
                                        ojbMemes.getString("dailydeceased"),
                                        ojbMemes.getString("dailyrecovered"),
                                        ojbMemes.getString("date"),
                                        ojbMemes.getString("dateymd"),
                                        ojbMemes.getString("totalconfirmed"),
                                        ojbMemes.getString("totaldeceased"),
                                        ojbMemes.getString("totalrecovered")
                                    )
                                )
                            }
                        }
                        val jsonArrayStateWise = objResult.getJSONArray("statewise")

                        for (i in 0 until jsonArrayStateWise.length()) {

                            val objStateWise = jsonArrayStateWise.getJSONObject(i)
                            val dataStateWise =
                                StateWise(
                                    objStateWise.getString("active"),
                                    objStateWise.getString("confirmed"),
                                    objStateWise.getString("deaths"),
                                    objStateWise.getString("deltaconfirmed"),
                                    objStateWise.getString("deltadeaths"),
                                    objStateWise.getString("deltarecovered"),
                                    objStateWise.getString("lastupdatedtime"),
                                    objStateWise.getString("migratedother"),
                                    objStateWise.getString("recovered"),
                                    objStateWise.getString("state"),
                                    objStateWise.getString("statecode"),
                                    objStateWise.getString("statenotes")
                                )

                            arrDataStateWise.add(dataStateWise)

                            //SAVE DATA IN STATE TABLE FOR OFF LINE USES
                            GlobalScope.launch {
                                database.covidDAO().insertState(
                                    State(
                                        0, objStateWise.getString("active"),
                                        objStateWise.getString("confirmed"),
                                        objStateWise.getString("deaths"),
                                        objStateWise.getString("deltaconfirmed"),
                                        objStateWise.getString("deltadeaths"),
                                        objStateWise.getString("deltarecovered"),
                                        objStateWise.getString("lastupdatedtime"),
                                        objStateWise.getString("migratedother"),
                                        objStateWise.getString("recovered"),
                                        objStateWise.getString("state"),
                                        objStateWise.getString("statecode"),
                                        objStateWise.getString("statenotes")
                                    )
                                )
                            }
                        }


                        val jsonArrayTestDate = objResult.getJSONArray("tested")

                        for (i in 0 until jsonArrayTestDate.length()) {
                            val objTestData = jsonArrayTestDate.getJSONObject(i)

                            val data =
                                TestedData(
                                    objTestData.getString("dailyrtpcrsamplescollectedicmrapplication"),
                                    objTestData.getString("firstdoseadministered"),
                                    objTestData.getString("frontlineworkersvaccinated1stdose"),
                                    objTestData.getString("frontlineworkersvaccinated2nddose"),
                                    objTestData.getString("healthcareworkersvaccinated1stdose"),
                                    objTestData.getString("healthcareworkersvaccinated2nddose"),
                                    objTestData.getString("over45years1stdose"),
                                    objTestData.getString("over45years2nddose"),
                                    objTestData.getString("over60years1stdose"),
                                    objTestData.getString("over60years2nddose"),
                                    objTestData.getString("positivecasesfromsamplesreported"),
                                    objTestData.getString("registration18-45years"),
                                    objTestData.getString("registrationabove45years"),
                                    objTestData.getString("registrationflwhcw"),
                                    objTestData.getString("registrationonline"),
                                    objTestData.getString("registrationonspot"),
                                    objTestData.getString("samplereportedtoday"),
                                    objTestData.getString("seconddoseadministered"),
                                    objTestData.getString("source"),
                                    objTestData.getString("source2"),
                                    objTestData.getString("source3"),
                                    objTestData.getString("source4"),
                                    objTestData.getString("testedasof"),
                                    objTestData.getString("testsconductedbyprivatelabs"),
                                    objTestData.getString("to60yearswithco-morbidities1stdose"),
                                    objTestData.getString("to60yearswithco-morbidities2nddose"),
                                    objTestData.getString("totaldosesadministered"),
                                    objTestData.getString("totaldosesavailablewithstates"),
                                    objTestData.getString("totaldosesavailablewithstatesprivatehospitals"),
                                    objTestData.getString("totaldosesinpipeline"),
                                    objTestData.getString("totaldosesprovidedtostatesuts"),
                                    objTestData.getString("totalindividualsregistered"),
                                    objTestData.getString("totalindividualstested"),
                                    objTestData.getString("totalpositivecases"),
                                    objTestData.getString("totalrtpcrsamplescollectedicmrapplication"),
                                    objTestData.getString("totalsamplestested"),
                                    objTestData.getString("totalsessionsconducted"),
                                    objTestData.getString("totalvaccineconsumptionincludingwastage"),
                                    objTestData.getString("updatetimestamp"),
                                    objTestData.getString("years1stdose"),
                                    objTestData.getString("years2nddose")
                                )
                            arrTestData.add(data)

                            //SAVE DATA IN TESTED TABLE FOR OFF LINE USES
                            GlobalScope.launch {
                                database.covidDAO().insertTested(
                                    Tested(
                                        0,
                                        objTestData.getString("dailyrtpcrsamplescollectedicmrapplication"),
                                        objTestData.getString("firstdoseadministered"),
                                        objTestData.getString("frontlineworkersvaccinated1stdose"),
                                        objTestData.getString("frontlineworkersvaccinated2nddose"),
                                        objTestData.getString("healthcareworkersvaccinated1stdose"),
                                        objTestData.getString("healthcareworkersvaccinated2nddose"),
                                        objTestData.getString("over45years1stdose"),
                                        objTestData.getString("over45years2nddose"),
                                        objTestData.getString("over60years1stdose"),
                                        objTestData.getString("over60years2nddose"),
                                        objTestData.getString("positivecasesfromsamplesreported"),
                                        objTestData.getString("registration18-45years"),
                                        objTestData.getString("registrationabove45years"),
                                        objTestData.getString("registrationflwhcw"),
                                        objTestData.getString("registrationonline"),
                                        objTestData.getString("registrationonspot"),
                                        objTestData.getString("samplereportedtoday"),
                                        objTestData.getString("seconddoseadministered"),
                                        objTestData.getString("source"),
                                        objTestData.getString("source2"),
                                        objTestData.getString("source3"),
                                        objTestData.getString("source4"),
                                        objTestData.getString("testedasof"),
                                        objTestData.getString("testsconductedbyprivatelabs"),
                                        objTestData.getString("to60yearswithco-morbidities1stdose"),
                                        objTestData.getString("to60yearswithco-morbidities2nddose"),
                                        objTestData.getString("totaldosesadministered"),
                                        objTestData.getString("totaldosesavailablewithstates"),
                                        objTestData.getString("totaldosesavailablewithstatesprivatehospitals"),
                                        objTestData.getString("totaldosesinpipeline"),
                                        objTestData.getString("totaldosesprovidedtostatesuts"),
                                        objTestData.getString("totalindividualsregistered"),
                                        objTestData.getString("totalindividualstested"),
                                        objTestData.getString("totalpositivecases"),
                                        objTestData.getString("totalrtpcrsamplescollectedicmrapplication"),
                                        objTestData.getString("totalsamplestested"),
                                        objTestData.getString("totalsessionsconducted"),
                                        objTestData.getString("totalvaccineconsumptionincludingwastage"),
                                        objTestData.getString("updatetimestamp"),
                                        objTestData.getString("years1stdose"),
                                        objTestData.getString("years2nddose")
                                    )
                                )
                            }

                        }

                        //SET UP TAB MENU
                        setUpTab()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                is Resources.Error -> {
                    loadingDialog.hide()
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    //INITIALISE VIEW MODEL
    private fun setUpViewModel() {
        val repository = Repository()
        val factory = ViewModelFactory(repository, this)
        covidViewModel =
            ViewModelProvider(this, factory).get(CovidViewModel::class.java)
    }

    // SET UP TAB MENU
    private fun setUpTab() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cases"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("State"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Test"))

        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        adapter = MyAdapter(
            this, supportFragmentManager,
            binding.tabLayout.tabCount
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tabLayout
            )
        )
        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    //POPULATE DATA FROM ROOM DATA BASE
    fun populateDataFromDatabase() {

        database.covidDAO().getCases().observe(this, Observer {
            if (it.isNotEmpty()) {
                for (i in 0 until it.size) {
                    val data = CaseTimeSerese(
                        it[i].dailyconfirmed,
                        it[i].dailydeceased,
                        it[i].dailyrecovered,
                        it[i].date,
                        it[i].dateymd,
                        it[i].totalconfirmed,
                        it[i].totaldeceased,
                        it[i].totalrecovered
                    )
                    arrCases.add(data)
                }
            } else {
                Toast.makeText(this, "No cases data found", Toast.LENGTH_SHORT).show()
            }

        })

        database.covidDAO().getState().observe(this, Observer {
            if (it.isNotEmpty()) {
                for (i in 0 until it.size) {
                    val data = StateWise(
                        it[i].active,
                        it[i].confirmed,
                        it[i].deaths,
                        it[i].deltaconfirmed,
                        it[i].deltadeaths,
                        it[i].deltarecovered,
                        it[i].lastupdatedtime,
                        it[i].migratedother,
                        it[i].recovered,
                        it[i].state,
                        it[i].statecode,
                        it[i].statenotes,
                    )
                    arrDataStateWise.add(data)
                }


            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
            }
        })

        database.covidDAO().getTested().observe(this, Observer {
            if (it.isNotEmpty()) {
                for (i in 0 until it.size) {
                    val data = TestedData(
                        it[i].dailyrtpcrsamplescollectedicmrapplication,
                        it[i].firstdoseadministered,
                        it[i].frontlineworkersvaccinated1stdose,
                        it[i].frontlineworkersvaccinated2nddose,
                        it[i].healthcareworkersvaccinated1stdose,
                        it[i].healthcareworkersvaccinated2nddose,
                        it[i].over45years1stdose,
                        it[i].over45years2nddose,
                        it[i].over60years1stdose,
                        it[i].over60years2nddose,
                        it[i].positivecasesfromsamplesreported,
                        it[i].registration18_45years,
                        it[i].registrationabove45years,
                        it[i].registrationflwhcw,
                        it[i].registrationonline,
                        it[i].registrationonspot,
                        it[i].samplereportedtoday,
                        it[i].seconddoseadministered,
                        it[i].source,
                        it[i].source2,
                        it[i].source3,
                        it[i].source4,
                        it[i].testedasof,
                        it[i].testsconductedbyprivatelabs,
                        it[i].to60yearswithco_morbidities1stdose,
                        it[i].to60yearswithco_morbidities2nddose,
                        it[i].totaldosesadministered,
                        it[i].totaldosesavailablewithstates,
                        it[i].totaldosesavailablewithstatesprivatehospitals,
                        it[i].totaldosesinpipeline,
                        it[i].totaldosesprovidedtostatesuts,
                        it[i].totalindividualsregistered,
                        it[i].totalindividualstested,
                        it[i].totalpositivecases,
                        it[i].totalrtpcrsamplescollectedicmrapplication,
                        it[i].totalsamplestested,
                        it[i].totalsessionsconducted,
                        it[i].totalvaccineconsumptionincludingwastage,
                        it[i].updatetimestamp,
                        it[i].years1stdose,
                        it[i].years2nddose
                    )
                    arrTestData.add(data)
                }

                 setUpTab()
            } else {
                Toast.makeText(this, "No tested data found", Toast.LENGTH_SHORT).show()
            }
        })

       // setUpTab()
    }
}