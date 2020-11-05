package bruno.koster.myeleventhapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import bruno.koster.myeleventhapp.R
import bruno.koster.myeleventhapp.extensions.toast
import bruno.koster.myeleventhapp.model.Money
import bruno.koster.myeleventhapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    val database = FirebaseFirestore.getInstance()
    private var mUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            var money = Money()
            money.money = view.EditTextAddMoney.text.toString().toDouble()

//            mAuth = FirebaseAuth.getInstance()
//            mUser = mAuth?.currentUser

//            enviar para o firestore

            database.collection("users")
                    .add(money)
                    .addOnSuccessListener {
                        toast("Dinheiro adicionado com sucesso! ID: ${it.id}")
                    }
                    .addOnFailureListener {
                        toast("Erro ao adicionar dinheiro!")
                    }



//            database.collection("users").document("moneyzin")
//                    .set(money)
//                    .addOnSuccessListener {
//
//                        toast("Dinheiro adicionado com sucesso!")
//                        findNavController().navigate(R.id.action_addFragment_to_bankFragment)
//                    }
//                    .addOnFailureListener {
//                        toast("Erro ao adicionar dinheiro!")
//                    }



//            findNavController().navigate(R.id.action_addFragment_to_bankFragment)
        }

        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_bankFragment)
        }

    }

}