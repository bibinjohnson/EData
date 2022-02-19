package com.whiterabbit.edata.fragment.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whiterabbit.edata.R
import com.whiterabbit.edata.database.EmployeeData
import com.whiterabbit.edata.databinding.ItemEmployeeListBinding

import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(val array: ArrayList<EmployeeData>) : RecyclerView.Adapter<HomeAdapter.HomeVH>(),
    Filterable {

    var filterList = ArrayList<EmployeeData>()

    init {
        filterList = array
    }

    inner class HomeVH(val binding: ItemEmployeeListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH =
        HomeVH(ItemEmployeeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.binding.apply {
            holder.itemView.apply {
                filterList[position].let {

                    Glide.with(context)
                        .load(it.image)
                        .into(userImg)

                    userName.text = it.name
                    userCompany.text = it.company


                    setOnClickListener { _ ->
                        findNavController().navigate(
                            R.id.action_homeFragment_to_employeeDetailFragment,
                            bundleOf(
                                "userData" to it
                            )
                        )
                    }
                }
            }
        }

    }


    override fun getItemCount(): Int = filterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = array
                } else {
                    val resultList = ArrayList<EmployeeData>()
                    for (row in array) {

                        if (row.name!!.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<EmployeeData>
                notifyDataSetChanged()
            }

        }
    }

}