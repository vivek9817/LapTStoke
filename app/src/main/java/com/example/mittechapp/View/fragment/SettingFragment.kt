package com.example.mittechapp.View.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.Model.ItemsData
import com.example.mittechapp.R
import com.example.mittechapp.adapter.CommonAlertAdapter
import com.example.mittechapp.databinding.FragmentInformationBinding
import com.example.mittechapp.databinding.FragmentSettingBinding
import com.example.mittechapp.viewholder.GenericViewHolder

class SettingFragment : Fragment(R.layout.fragment_setting) {

    lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(layoutInflater)
        var view = binding.root


        return view
    }

}