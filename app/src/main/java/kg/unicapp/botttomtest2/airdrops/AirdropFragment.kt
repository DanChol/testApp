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
import kg.unicapp.botttomtest2.databinding.FragmentAirdropBinding


class AirdropFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentAirdropBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var dropsDataArrayList: ArrayList<DropsData>
    private lateinit var airdropAdapter: AirdropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

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
        const val TAG = "A"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}