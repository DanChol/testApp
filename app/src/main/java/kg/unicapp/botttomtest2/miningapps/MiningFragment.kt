package kg.unicapp.botttomtest2.miningapps

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import kg.unicapp.botttomtest2.airdrops.AirdropAdapter
import kg.unicapp.botttomtest2.airdrops.DropsData
import kg.unicapp.botttomtest2.databinding.FragmentAirdropBinding
import kg.unicapp.botttomtest2.databinding.FragmentMiningBinding


class MiningFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMiningBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var appsDataArrayList: ArrayList<AppsData>
    private lateinit var appsAdapter: MiningsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMiningBinding.inflate(inflater)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.miningRecycle
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        appsDataArrayList = arrayListOf()
        appsAdapter = MiningsAdapter(appsDataArrayList)
        recyclerView.adapter = appsAdapter
        getUserData()
    }
    private fun getUserData() {
        db = FirebaseFirestore.getInstance()
        db.collection("Mapps").
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
                        appsDataArrayList.add( dc.document.toObject(AppsData::class.java))
                    }
                }
                appsAdapter.notifyDataSetChanged()

            }

        })


    }

    companion object {
        const val TAG = "B"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}