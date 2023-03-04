package com.example.mittechapp.View.fragment

import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.Model.Items
import com.example.mittechapp.R
import com.example.mittechapp.adapter.CommonAlertAdapter
import com.example.mittechapp.databinding.FragmentCheckingBinding
import com.example.mittechapp.viewholder.GenericViewHolder
import okhttp3.internal.notifyAll

class FragmentChecking : Fragment(R.layout.fragment_checking) {
    lateinit var binding: FragmentCheckingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckingBinding.inflate(layoutInflater)
        var view = binding.root

        return view
    }

}