package com.anand.nutrition_fit.network

import com.anand.nutrition_fit.model.ApprovedFoodData
import io.reactivex.Observable

class RestApiRepository(private val apiService: RestApiService) {

    fun getApprovedFoodListData(): Observable<ApprovedFoodData> {
        return apiService.getApprovedFoodListData()
    }

}