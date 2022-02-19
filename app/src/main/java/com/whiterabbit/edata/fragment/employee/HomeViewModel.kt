package com.whiterabbit.edata.fragment.employee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.whiterabbit.edata.Application
import com.whiterabbit.edata.BaseViewModel
import com.whiterabbit.edata.api.RetrofitClient
import com.whiterabbit.edata.api.models.HomeResponseData
import com.whiterabbit.edata.database.EmployeeData
import com.whiterabbit.edata.database.EmployeeDataBase
import com.whiterabbit.edata.utils.SingleLiveEvent

import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    var usersData: MutableLiveData<List<HomeResponseData>> = MutableLiveData()
    var databaseData: MutableLiveData<ArrayList<EmployeeData>> = MutableLiveData()
    val onDataUploaded = SingleLiveEvent<Unit>()


    fun getEmployeeData() {
        viewModelScope.launch {

            usersData.value = RetrofitClient.apiService.getUserData()

            usersData.value!!.forEach {

                EmployeeDataBase.getDatabase(Application.instance).employeeDao().insert(
                    EmployeeData(
                        it.name!!,
                        it.profileImage,
                        it.company?.name,
                        it.email!!,
                        it.website
                    )
                )

            }

            onDataUploaded.call()
        }
    }

    fun getOfflineData() {
        onDataUploaded.call()
    }

}