package com.whiterabbit.edata.fragment.employee_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.whiterabbit.edata.database.EmployeeData
import com.whiterabbit.edata.databinding.EmployeeDetailFragmentBinding
import com.whiterabbit.edata.utils.fetch

class EmployeeDetailFragment : Fragment() {
    companion object {
        fun newInstance() = EmployeeDetailFragment()
    }

    lateinit var viewModel: EmployeeDetailViewModel
    lateinit var binding: EmployeeDetailFragmentBinding
    var passed: EmployeeData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmployeeDetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[EmployeeDetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        passed = arguments?.getParcelable("userData")

        binding.ScrollingImage.fetch(passed?.image!! ?: "")
        binding.CollpaseTool.title = passed?.name!! ?: ""
        binding.companyTxt.text = passed?.company!! ?: ""
        binding.emailTxt.text = passed?.email ?: ""
        binding.websiteTxt.text = passed?.website ?: ""


        binding.BckBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}