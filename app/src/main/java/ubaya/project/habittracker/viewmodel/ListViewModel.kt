package ubaya.project.habittracker.viewmodel

import android.R
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import ubaya.project.habittracker.model.Habit
import androidx.core.content.edit
import com.google.gson.reflect.TypeToken

class ListViewModel (application: Application): AndroidViewModel(application) {
    val habitLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()

    val prefs = getApplication<Application>().getSharedPreferences("habits_prefs", Context.MODE_PRIVATE)
    val gson = Gson()

    fun refresh(){
        loadingLD.value = true
        val json = prefs.getString("habits", null)

        if(json == null){
            habitLD.value = defaultHabbits()
        } else{
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            habitLD.value = gson.fromJson(json, type)
        }
        loadingLD.value = false
    }
    fun defaultHabbits() = arrayListOf(
        Habit("1", "Exercise",
            "Daily workout routine", "exercise", 30, "minutes", 15),
        Habit("2", "Drink Water",
            "Stay hydrated throughout the day", "water", 8, "glasses", 3),
        Habit("3", "Read Books",
            "Expand your knowledge", "book", 20, "pages", 20)
    )
    fun increaseProgress(habit: Habit){
        if(habit.progress < habit.targets){
            habit.progress++
            val list = habitLD.value ?: arrayListOf()
            prefs.edit { putString("habits", gson.toJson(list)) }
            habitLD.value = list
        }
    }
    fun decreaseProgress(habit: Habit){
        if(habit.progress > 0){
            habit.progress--
            val list = habitLD.value ?: arrayListOf()
            prefs.edit { putString("habits", gson.toJson(list)) }
            habitLD.value = list
        }
    }
    fun addHabit(newHabit: Habit) {
        val json = prefs.getString("habits", null)
        val currentList: ArrayList<Habit>
        if (json != null) {
            val type = object : TypeToken<ArrayList<Habit>>() {}.type
            currentList = gson.fromJson(json, type)
        } else {
            currentList = defaultHabbits()
        }
        currentList.add(newHabit)
        prefs.edit { putString("habits", gson.toJson(currentList)) }
        habitLD.value = currentList
    }
}