package com.anand.nutrition_fit.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.anand.nutrition_fit.R


class ApprovedFoodSubCategoriesAdapter(
    private val applicationContext: Context,
    private val subCategoriesKeys: ArrayList<String>,
    private val subCategoriesListData: HashMap<String, List<String>>,
    private val colorCode: String
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return subCategoriesKeys.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return subCategoriesListData[subCategoriesKeys[p0]]?.size!!
    }

    override fun getGroup(p0: Int): Any {
        return subCategoriesKeys[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return subCategoriesListData[subCategoriesKeys[p0]]?.get(p1)!!
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val listHeader = getGroup(p0) as String
        val layoutInflater = applicationContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView = layoutInflater.inflate(R.layout.list_group, null)
        val expandedListHeaderTextView = convertView.findViewById<TextView>(R.id.text_list_header)
        expandedListHeaderTextView.text = listHeader.toUpperCase()
        expandedListHeaderTextView.setTextColor(Color.parseColor(colorCode))
        val expandableListView = p3 as ExpandableListView
        expandableListView.expandGroup(p0)
        return convertView
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val expandedListText = getChild(p0, p1) as String
        val layoutInflater = applicationContext
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val convertView = layoutInflater.inflate(R.layout.item_list, null)
        val expandedListTextView = convertView.findViewById<TextView>(R.id.text_list_item)
        expandedListTextView.text = expandedListText
        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

}