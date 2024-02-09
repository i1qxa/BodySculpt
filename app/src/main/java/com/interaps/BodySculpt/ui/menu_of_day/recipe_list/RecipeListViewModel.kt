package com.interaps.BodySculpt.ui.menu_of_day.recipe_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.interaps.BodySculpt.data.local.AppDatabase
import com.interaps.BodySculpt.data.remote.RecipeItemShort
import com.interaps.BodySculpt.data.remote.RetrofitService
import com.interaps.BodySculpt.data.remote.appId
import com.interaps.BodySculpt.data.remote.appKey
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application):AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).menuDao()

    private val retrofit = RetrofitService.getInstance()

    val listRecipes = MutableLiveData<List<RecipeItemShort?>>()

    val errorRequest = MutableLiveData<Boolean>()

    fun getRecipeList(query:String) {
        viewModelScope.launch {
            val recipes = mutableListOf<RecipeItemShort?>()
            val response = retrofit.getRecipeResponse(
                "public",
                query,
                appId,
                appKey
            )
            if (response.isSuccessful) {
                if (response.body()?.count == 0){
                    errorRequest.value = true
                    errorRequest.value = false
                }
                response.body()?.hits?.map {
                    recipes.add(it.recipe?.getRecipeShort())
                }
                listRecipes.postValue(recipes)
            }else{
                errorRequest.value = true
                errorRequest.value = false
            }
        }
    }

    fun addRecipeToMenu(item:RecipeItemShort){
        viewModelScope.launch {
            dao.addMenuItem(item.toDBMenuItem())
        }
    }

}