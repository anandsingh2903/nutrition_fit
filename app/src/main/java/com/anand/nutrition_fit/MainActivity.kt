package com.anand.nutrition_fit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anand.nutrition_fit.adapters.ApprovedFoodDataAdapter
import com.anand.nutrition_fit.adapters.ApprovedFoodSubCategoriesAdapter
import com.anand.nutrition_fit.model.ApprovedFoodData
import com.anand.nutrition_fit.network.RestApiRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_recyclerview_food_list.*

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideActionBar()
        getApprovedFoodListDataFromApi()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun getApprovedFoodListDataFromApi() {
        val repository= RestApiRepositoryProvider.provideRestApiRepository()
        compositeDisposable.add(repository.getApprovedFoodListData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    it -> processApprovedFoodListData(it)
                },
                {
                        error -> error.printStackTrace()
                }
            ))
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun processApprovedFoodListData(approvedFoodData: ApprovedFoodData) {
        prepareRecyclerView()
        populateApprovedFoodDataOntoRecyclerView(approvedFoodData)
    }

    private fun populateApprovedFoodDataOntoRecyclerView(approvedFoodData: ApprovedFoodData) {
        recyclerview_food_list.adapter = ApprovedFoodDataAdapter(applicationContext, approvedFoodData)
    }

    private fun prepareRecyclerView() {
        val myLayoutManager = LinearLayoutManager(this)
        myLayoutManager.orientation = RecyclerView.VERTICAL

        recyclerview_food_list.setHasFixedSize(true)
        recyclerview_food_list.layoutManager = myLayoutManager
    }
}