package ubaya.project.habittracker.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ubaya.project.habittracker.model.UserDao
import ubaya.project.habittracker.model.UserDatabase
import ubaya.project.habittracker.model.Users
import ubaya.project.habittracker.util.buildDb
import kotlin.coroutines.CoroutineContext

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope
{
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val loginResultLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val isLoggedInLD = MutableLiveData<Boolean>()

    private val prefs = getApplication<Application>().
    getSharedPreferences("session", Context.MODE_PRIVATE)

    fun login(username: String, password: String){
        loadingLD.value = true

        launch{
            val db = UserDatabase(getApplication())
            val userDao = db.userDao()

            if (userDao.countUser() == 0) {
                userDao.insert(Users(username = "student", password = "123"))
            }
            val user = userDao.login(username, password)
            if(user != null){
                userDao.setLoggedIn(username)
                loginResultLD.postValue(true)
            }else{
                loginResultLD.postValue(false)
            }
            loadingLD.postValue(false)
        }
    }

    // Diubah dari Boolean langsung (blocking main thread) menjadi LiveData
    // supaya query Room tidak dipanggil synchronous di main thread.
    fun checkIsLoggedIn(){
        launch {
            val db = buildDb(getApplication())
            val loggedIn = db.userDao().getLoggedInUser() != null
            isLoggedInLD.postValue(loggedIn)
        }
    }

    fun logOut(){
        launch {
            val db = buildDb(getApplication())
            db.userDao().setLoggedOut()
        }
    }
}