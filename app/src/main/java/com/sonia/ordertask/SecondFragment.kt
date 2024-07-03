package com.sonia.ordertask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.sonia.ordertask.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentSecondBinding?=null
    var mainActivity:MainActivity?=null
    private lateinit var spinnerAdapter: ArrayAdapter<Order>

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
        // Inflate the layout for this fragment
        binding=FragmentSecondBinding.inflate(layoutInflater)
        spinnerAdapter = ArrayAdapter(mainActivity!!, android.R.layout.simple_list_item_1,mainActivity!!.orderList)
        binding?.spdynamic?.adapter=spinnerAdapter
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.spdynamic?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding?.value?.setText("${mainActivity!!.orderList[position].quantity}")
                binding?.btnorder?.setOnClickListener {
                    mainActivity!!.orderList[position].quantity =
                        mainActivity!!.orderList[position].quantity?.minus(
                            binding?.value?.text.toString().toInt()
                        )
                    mainActivity!!.listadapter.notifyDataSetChanged()
                }
                binding?.btnminus?.setOnClickListener {
                    if ((binding?.value?.text.toString().toInt() - 1) < 1) {
                        Toast.makeText(mainActivity, "No Longer decrement", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                 g       binding?.value?.text =
                            (binding?.value?.text.toString().toInt() - 1).toString()
                    }
                }
                binding?.btnadd?.setOnClickListener {
                    if (binding?.value?.text.toString()
                            .toInt() == mainActivity!!.orderList[position].quantity
                    ) {
                        Toast.makeText(mainActivity, "No Longer incerement", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        binding?.value?.text =
                            (binding?.value?.text.toString().toInt().plus(1)).toString()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}