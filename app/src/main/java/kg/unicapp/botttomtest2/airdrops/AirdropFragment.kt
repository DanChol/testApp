package kg.unicapp.botttomtest2.airdrops

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import kg.unicapp.botttomtest2.InfoFragment
import kg.unicapp.botttomtest2.databinding.FragmentAirdropBinding
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AirdropFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentAirdropBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var dropsDataArrayList: ArrayList<DropsData>
    private lateinit var airdropAdapter: AirdropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAirdropBinding.inflate(inflater)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.airdropRecycle
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        dropsDataArrayList = arrayListOf()
        airdropAdapter = AirdropAdapter(dropsDataArrayList)
        recyclerView.adapter = airdropAdapter


        getUserData()
    }
    private fun getUserData() {
        db = FirebaseFirestore.getInstance()
        db.collection("Drops").
        addSnapshotListener(object : EventListener<QuerySnapshot> {


            override fun onEvent(

                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if (error != null){

                    Log.e("Firestore Error", error.message.toString())
                    return

                }
                for (dc : DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        dropsDataArrayList.add( dc.document.toObject(DropsData::class.java))
                    }
                }
                airdropAdapter.notifyDataSetChanged()

            }

        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}