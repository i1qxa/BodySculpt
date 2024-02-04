package com.interaps.BodySculpt.ui.persona_iInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interaps.BodySculpt.data.local.person.PersonDBEntity
import com.interaps.BodySculpt.data.local.person.PersonDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonalInfoViewModel @Inject constructor (private val personDao: PersonDao):ViewModel() {

    val personInfoFlow = personDao.getPersonDataFlow()

    private fun updatePersonInfo(personInfo:PersonDBEntity){
        viewModelScope.launch(Dispatchers.IO) {
            personDao.updatePersonInfo(personInfo)
        }
    }

    fun tryToUpdatePersonInfo(currentWeight:Double, wishWeight:Double, height:Int, gender:Int){
        updatePersonInfo(PersonDBEntity(1,gender,currentWeight,wishWeight,height))
    }

}