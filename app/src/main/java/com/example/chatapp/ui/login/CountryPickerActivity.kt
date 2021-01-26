package com.example.chatapp.ui.login

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.ui.adapter.CountryPickerAdapter
import com.example.chatapp.ui.model.CountryTsvData
import com.example.chatapp.ui.model.CountryCallingCodeDetailModel
import com.google.android.material.appbar.MaterialToolbar
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.ArrayList

class CountryPickerActivity : AppCompatActivity(),CountryPickerAdapter.CountryPickerOnItemClickListener {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var searchHolder: View
    private var searchView: SearchView? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var countryPickerAdapter: CountryPickerAdapter
    private lateinit var countryCallingCodeDetailModelData: ArrayList<CountryCallingCodeDetailModel>

    private var ccc: String? = null
    private var country: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_picker)

        val bundle = intent.extras
        ccc = bundle?.getString(COUNTRY_CALLING_CODE_STRING_EXTRA)
        country = bundle?.getString(COUNTRY_STRING_EXTRA)

        toolbar = findViewById<MaterialToolbar>(R.id.title_toolbar).apply {
            title = resources.getString(R.string.pick_a_country)
        }
        setSupportActionBar(toolbar)
        searchHolder = findViewById(R.id.search_holder)
        countryCallingCodeDetailModelData = getCountryData()
        countryPickerAdapter = CountryPickerAdapter(this, this).apply {
            addData(countryCallingCodeDetailModelData)
        }
        recyclerView = findViewById<RecyclerView>(R.id.countryPickerRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@CountryPickerActivity)
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                ).apply {
                    setDrawable(
                        ContextCompat.getDrawable(
                            this@CountryPickerActivity,
                            R.drawable.divider_gray
                        )!!
                    )
                })
            adapter = countryPickerAdapter
        }
    }

    private fun filter(
        countryCallingCodeDetailModelList: List<CountryCallingCodeDetailModel>,
        query: String
    ): ArrayList<CountryCallingCodeDetailModel> {
        val lowerCaseQuery = query.toLowerCase(Locale.ROOT)
        val filteredModelList: ArrayList<CountryCallingCodeDetailModel> = ArrayList()
        for (country in countryCallingCodeDetailModelList) {
            val ccc: String = country.ccc.toLowerCase(Locale.ROOT)
            val countryFirstName = country.countryFirstName.toLowerCase(Locale.ROOT)
            val countrySecondName = country.countrySecondName.toLowerCase(Locale.ROOT)
            if (ccc.contains(lowerCaseQuery)
                || countryFirstName.contains(lowerCaseQuery)
                || countrySecondName.contains(lowerCaseQuery)
            ) {
                filteredModelList.add(country)
            }
        }
        return filteredModelList
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.only_search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_search) {
            if (searchHolder.visibility != View.VISIBLE) {
                if (searchView == null) {
                    layoutInflater.inflate(
                        R.layout.home_search_view_layout,
                        searchHolder as ViewGroup?,
                        true
                    )
                    searchHolder.apply {
                        setBackgroundResource(R.drawable.search_background)
                        findViewById<ImageView>(R.id.search_back).apply {
                            setImageDrawable(
                                InsetDrawable(
                                    ContextCompat.getDrawable(
                                        this@CountryPickerActivity,
                                        R.drawable.ic_back_teal
                                    ), 0
                                )
                            )
                            setOnClickListener { circularAnimationOnClose() }
                        }
                    }
                    searchView = searchHolder.findViewById<SearchView>(R.id.search_view).apply {
                        setIconifiedByDefault(false)
                        maxWidth = Int.MAX_VALUE
                        queryHint = resources.getString(R.string.search_country_hint)
                        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextChange(newText: String?): Boolean {
                                val filteredModelList: ArrayList<CountryCallingCodeDetailModel> =
                                    filter(
                                        countryCallingCodeDetailModelData,
                                        newText ?: ""
                                    )
                                countryPickerAdapter.addData(filteredModelList)
                                recyclerView.scrollToPosition(0)
                                return true
                            }

                            override fun onQueryTextSubmit(query: String?) = false
                        })
                        findViewById<TextView>(R.id.search_src_text)?.apply {
                            setTextColor(getColor(R.color.body_gray))
                            setHintTextColor(getColor(R.color.body_light_gray))
                        }
                        findViewById<ImageView>(R.id.search_mag_icon)?.setImageDrawable(
                            InsetDrawable(
                                ContextCompat.getDrawable(
                                    this@CountryPickerActivity,
                                    R.drawable.ic_back_teal
                                ), 0
                            )
                        )
                        findViewById<ImageView>(R.id.search_close_btn)?.apply {
                            setImageResource(R.drawable.ic_backup_cancel)
                        }
                    }
                }
                toolbar.visibility = View.GONE
                searchHolder.visibility = View.VISIBLE
                toolbar.startAnimation(AlphaAnimation(1.0f, 0.0f).apply {
                    duration = 250L
                })
                if (searchHolder.isAttachedToWindow) {
                    ViewAnimationUtils.createCircularReveal(
                        searchHolder,
                        toolbar.width - 96,
                        toolbar.height - 48,
                        0.0f,
                        toolbar.width.toFloat()
                    ).apply {
                        duration = 220.toLong()
                        start()
                    }
                }
            }
            return true
        }
        return false
    }

    private fun circularAnimationOnClose() {
        if (searchHolder.visibility == View.VISIBLE) {
            toolbar.startAnimation(AlphaAnimation(0.0f, 1.0f).apply {
                duration = 2500L
            })
            ViewAnimationUtils.createCircularReveal(
                searchHolder,
                searchHolder.width - (searchHolder.height / 2),
                searchHolder.height / 2,
                searchHolder.width.toFloat(),
                0.0f
            ).apply {
                duration = 250L
                start()
            }
            searchHolder.startAnimation(AnimationSet(true).apply {
                addAnimation(AlphaAnimation(1.0f, 0.0f))
                addAnimation(
                    TranslateAnimation(
                        0.0f,
                        0.0f,
                        0.0f,
                        (-searchHolder.height).toFloat() / 2
                    )
                )
                duration = 350L
            })
            searchHolder.visibility = View.GONE
            toolbar.visibility = View.VISIBLE
        }
    }

    private fun getCountryData(): ArrayList<CountryCallingCodeDetailModel> {
        val arrayList: ArrayList<CountryCallingCodeDetailModel> = ArrayList()
        val bufferedReader = BufferedReader(
            InputStreamReader(
                resources.openRawResource(R.raw.countries),
                StandardCharsets.UTF_8
            )
        )
        var i = 32
        var bufferedReaderLine = bufferedReader.readLine()
        while (bufferedReaderLine != null) {
            val countryTsvStringArray =
                TextUtils.split(bufferedReaderLine, "\t") ?: throw IllegalArgumentException()
            val c12700iE = CountryTsvData(
                countryTsvStringArray
            )
            arrayList.add(
                CountryCallingCodeDetailModel(
                    c12700iE.countryCallingCodeInt.toString(),
                    c12700iE.realIndex12,
                    c12700iE.country,
                    Drawable.createFromStream(
                        assets.open(
                            "emoji/e${
                                i++.toString().padStart(4, '0')
                            }.png"
                        ), null
                    ) ?: throw FileNotFoundException(),
                    isCountrySelected(c12700iE)
                )
            )
            bufferedReaderLine = bufferedReader.readLine()
        }
        return arrayList
    }

    private fun isCountrySelected(countryTsvData: CountryTsvData): Boolean {
        if (ccc?.length!! > 0) {
            if (ccc?.toInt() == countryTsvData.countryCallingCodeInt && country == countryTsvData.country) {
                return true
            }
        }
        return false
    }

    companion object {
        const val COUNTRY_CALLING_CODE_STRING_EXTRA = "COUNTRY_CALLING_CODE"
        const val COUNTRY_STRING_EXTRA = "COUNTRY";
    }

    override fun onItemClick(ccc: String, countryFirstName: String) {
        val intent = Intent()
        intent.putExtra(COUNTRY_CALLING_CODE_STRING_EXTRA, ccc)
        intent.putExtra(COUNTRY_STRING_EXTRA, countryFirstName)
        setResult(RESULT_OK, intent)
        finish()
    }
}

