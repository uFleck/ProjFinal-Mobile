package bruno.koster.myeleventhapp.model

class User {
    var key: String? = null
    var email: String? = null
    var password: String? = null
    var money: Double? = null

    override fun toString(): String {
        return "User(email=$email, password=$password)"
    }

}