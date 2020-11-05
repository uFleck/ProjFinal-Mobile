package bruno.koster.myeleventhapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bruno.koster.myeleventhapp.extensions.toast
import bruno.koster.myeleventhapp.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var mAuth: FirebaseAuth? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null
    private var mUser: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        mUser = mAuth?.currentUser //buscando usuário logado

        //VERIFICAR SE ESTÁ LOGADO
        mAuthStateListener = FirebaseAuth.AuthStateListener {
            if(mUser != null) {
                // já está logado
                val intent = Intent(this@LoginActivity, BankActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        buttonLoginConfirm.setOnClickListener(this)




    }


    override fun onStart() {
        super.onStart()
        mAuth?.addAuthStateListener(mAuthStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthStateListener != null) {
            mAuth?.removeAuthStateListener(mAuthStateListener!!)
        }
    }


    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.buttonLoginConfirm -> {
                login()

            }
        }
    }

    fun login() {
        if(editTextEmail.text.toString().isEmpty() || editTextPassword.text.toString().isEmpty()) {
            toast("digite usuario e/ou senha")
            return
        }
        var user = User()
        user.email = editTextEmail.text.toString()
        user.password = editTextPassword.text.toString()

        mAuth?.signInWithEmailAndPassword(user.email.toString(),
            user.password.toString())
            ?.addOnCompleteListener(object: OnCompleteListener<AuthResult> {
                //task

                override fun onComplete(p0: Task<AuthResult>) {
                    if(!p0.isSuccessful) {
                        toast("Usuário e/ou senha inválido(s)!")
                        return
                    }

                    val intent = Intent(this@LoginActivity, BankActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })

    }

}