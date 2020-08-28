package com.anand.nutrition_fit.model

import com.google.gson.annotations.SerializedName

data class ApprovedFoodData(@SerializedName("categories")
                            val categories : ArrayList<FoodCategories>)

data class FoodCategories(@SerializedName("category")
                          val category : Category)

data class Category(@SerializedName("subcategories")
                    val subcategories : ArrayList<SubCategories>,
                    @SerializedName("categoryName")
                    val categoryName : String,
                    @SerializedName("colorCode")
                    val colorCode : String,
                    @SerializedName("servingSize")
                    val servingSize : String)

data class SubCategories(@SerializedName("items")
                         val items : ArrayList<String>,
                         @SerializedName("subCategoryname")
                         val subCategoryname : String)