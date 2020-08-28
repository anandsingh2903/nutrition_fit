package com.anand.nutrition_fit.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anand.nutrition_fit.R
import com.anand.nutrition_fit.model.ApprovedFoodData
import com.anand.nutrition_fit.model.FoodCategories

class ApprovedFoodDataAdapter(private val applicationContext: Context, private val approvedFoodData: ApprovedFoodData) :
    RecyclerView.Adapter<ApprovedFoodDataAdapter.ApprovedFoodDataAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApprovedFoodDataAdapterViewHolder {
        val view = LayoutInflater.from(applicationContext).inflate(R.layout.item_recyclerview_food_list, parent, false)
        return ApprovedFoodDataAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApprovedFoodDataAdapterViewHolder, position: Int) {
        val approvedFoodListCategories = approvedFoodData.categories[position]
        holder.bind(approvedFoodListCategories)

        val subCategoriesListData = HashMap<String, List<String>>()

        for (subCategory in approvedFoodListCategories.category.subcategories) {
            subCategoriesListData[subCategory.subCategoryname] = subCategory.items
        }

        val expandableListAdapter : ExpandableListAdapter = ApprovedFoodSubCategoriesAdapter(applicationContext, ArrayList(subCategoriesListData.keys),subCategoriesListData, approvedFoodListCategories.category.colorCode)

        holder.listview_subcategories.setAdapter(expandableListAdapter)

        holder.card_categories.setOnClickListener { holder.listview_subcategories.visibility = View.VISIBLE }
    }

    override fun getItemCount(): Int {
        return approvedFoodData.categories.size
    }

    class ApprovedFoodDataAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text_food_category = view.findViewById<TextView>(R.id.text_food_category)
        val text_food_serving_size = view.findViewById<TextView>(R.id.text_food_serving_size)
        val listview_subcategories = view.findViewById<ExpandableListView>(R.id.listview_subcategories)
        val card_categories = view.findViewById<CardView>(R.id.card_categories)

        fun bind(approvedFoodListCategories: FoodCategories) {
            text_food_category.text = approvedFoodListCategories.category.categoryName
            text_food_category.setTextColor(Color.parseColor(approvedFoodListCategories.category.colorCode))

            if (!approvedFoodListCategories.category.servingSize.isNullOrBlank()) {
                text_food_serving_size.text = "(${approvedFoodListCategories.category.servingSize})"
                text_food_serving_size.visibility = View.VISIBLE
            }
        }
    }
}