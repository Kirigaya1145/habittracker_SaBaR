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
import androidx.lifecycle.viewModelScope
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ubaya.project.habittracker.model.HabitDatabase

class ListViewModel (application: Application): AndroidViewModel(application) {
    val habitLD = MutableLiveData<ArrayList<Habit>>()
    val loadingLD = MutableLiveData<Boolean>()
    val selectedHabitLD = MutableLiveData<Habit>()

    private val db = HabitDatabase(application)
    //val prefs = getApplication<Application>().getSharedPreferences("habits_prefs", Context.MODE_PRIVATE)
    val gson = Gson()

    fun refresh(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){loadingLD.value = true}
            //val json = prefs.getString("habits", null)
            val habits = db.habitDao().getAllHabits()
//            if(json == null){
//                habitLD.value = defaultHabbits()
//            } else{
//                val type = object : TypeToken<ArrayList<Habit>>() {}.type
//                habitLD.value = gson.fromJson(json, type)
//            }
            if (habits.isEmpty()) {
                defaultHabits().forEach { db.habitDao().insert(it) }
                val defaults = db.habitDao().getAllHabits()
                withContext(Dispatchers.Main) {
                    habitLD.value = ArrayList(defaults)
                    loadingLD.value = false
                }
            } else {
                withContext(Dispatchers.Main) {
                    habitLD.value = ArrayList(habits)
                    loadingLD.value = false
                }
            }
        }
    }

    fun addHabit(newHabit: Habit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.habitDao().insert(newHabit)
            }
            refresh()
        }
    }

    fun increaseProgress(habit: Habit) {
        if (habit.progress < habit.targets) {
            habit.progress++
            viewModelScope.launch(Dispatchers.IO) {
                db.habitDao().update(habit)
                withContext(Dispatchers.Main) {
                    val list = habitLD.value ?: arrayListOf()
                    habitLD.value = list
                }
            }
        }
    }

    fun decreaseProgress(habit: Habit) {
        if (habit.progress > 0) {
            habit.progress--
            viewModelScope.launch(Dispatchers.IO) {
                db.habitDao().update(habit)
                withContext(Dispatchers.Main) {
                    val list = habitLD.value ?: arrayListOf()
                    habitLD.value = list
                }
            }
        }
    }

    fun updateHabit(updatedHabit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            db.habitDao().update(updatedHabit)
            refresh()
        }
    }
    fun fetchHabitById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = db.habitDao().getHabitById(id)
            withContext(Dispatchers.Main) {
                selectedHabitLD.value = habit
            }
        }
    }
    fun defaultHabits() = arrayListOf(
        Habit("1", "Exercise",
            "Daily workout routine", "exercise", 30, "minutes", 15),
        Habit("2", "Drink Water",
            "Stay hydrated throughout the day", "water", 8, "glasses", 3),
        Habit("3", "Read Books",
            "Expand your knowledge", "book", 20, "pages", 20)
    )
//    fun increaseProgress(habit: Habit){
//        if(habit.progress < habit.targets){
//            habit.progress++
//            val list = habitLD.value ?: arrayListOf()
//            prefs.edit { putString("habits", gson.toJson(list)) }
//            habitLD.value = list
//        }
//    }
//    fun decreaseProgress(habit: Habit){
//        if(habit.progress > 0){
//            habit.progress--
//            val list = habitLD.value ?: arrayListOf()
//            prefs.edit { putString("habits", gson.toJson(list)) }
//            habitLD.value = list
//        }
//    }
//    fun addHabit(newHabit: Habit) {
//        val json = prefs.getString("habits", null)
//        val currentList: ArrayList<Habit>
//        if (json != null) {
//            val type = object : TypeToken<ArrayList<Habit>>() {}.type
//            currentList = gson.fromJson(json, type)
//        } else {
//            currentList = defaultHabits()
//        }
//        currentList.add(newHabit)
//        prefs.edit { putString("habits", gson.toJson(currentList)) }
//        habitLD.value = currentList
//    }
}