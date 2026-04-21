package ubaya.project.habittracker.model

data class Habit(
    val id: String,
    var nama: String,
    var desc: String,
    var icon: String,
    var targets: Int,
    var status: String,
    var progress: Int){
    
}