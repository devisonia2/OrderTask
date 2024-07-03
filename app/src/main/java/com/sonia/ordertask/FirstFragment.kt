package com.sonia.ordertask

import android.app.Dialog
import android.media.RouteListingPreference.Item
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonia.ordertask.databinding.CustomDialogBinding
import com.sonia.ordertask.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentFirstBinding? = null
    var mainActivity: MainActivity? = null
    var listAdapter = listadapter(mainActivity!!.orderList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listview?.adapter = listAdapter

        binding?.btnfab?.setOnClickListener {
            var dialogBinding= CustomDialogBinding.inflate(layoutInflater)
            var dialog= Dialog(requireContext()).apply{
                setContentView(dialogBinding.root)
                show()
            }
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            dialogBinding.btnadd.setOnClickListener {
                if (dialogBinding.etitem.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etitem.error = "Enter Item "
                } else if (dialogBinding.etquantity.text?.toString().isNullOrEmpty()) {
                    dialogBinding.etquantity.error = "Enter Quantity"
                }
                else {
                    mainActivity?.orderList?.add(
                        Order(
                            dialogBinding.etitem.text.toString(),
                            dialogBinding.etquantity.text.toString().toInt()
                        )
                    )
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}