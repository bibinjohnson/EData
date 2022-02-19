package com.whiterabbit.edata.fragment.employee
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.whiterabbit.edata.BaseFragment
import com.whiterabbit.edata.database.EmployeeData
import com.whiterabbit.edata.database.EmployeeDataBase
import com.whiterabbit.edata.databinding.HomeFragmentBinding
import com.whiterabbit.edata.utils.isOnline

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override lateinit var viewModel: HomeViewModel
    lateinit var binding: HomeFragmentBinding
    lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (isOnline(requireContext())) viewModel.getEmployeeData()
        else viewModel.getOfflineData()

        viewModel.onDataUploaded.observe(viewLifecycleOwner, Observer {
            adapter = HomeAdapter(EmployeeDataBase.getDatabase(requireContext()).employeeDao().getAll() as ArrayList<EmployeeData>)
            binding.homeRec.adapter = adapter
        })

        binding.searchEdt.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })


    }

}