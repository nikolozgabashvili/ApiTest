package com.example.apitest.fragments

import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.apitest.R
import com.example.apitest.databinding.FragmentInfoBinding
import com.squareup.picasso.Picasso
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class InfoFragment : Fragment() {
    private val args: InfoFragmentArgs by navArgs()
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var user: TextView
    private lateinit var button: Button
    private lateinit var date: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        val info = args.cardList
        val red = info.rgb?.red
        val green = info.rgb?.green
        val blue = info.rgb?.blue
        title.text = info.title
        user.text = info.userName



        val inputDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        val outputDate = SimpleDateFormat("hh:mm/dd-MM", Locale.getDefault())

        val date2 = inputDate.parse(info.dateCreated)
        val formattedDate = outputDate.format(date2)
        date.text = formattedDate


        if (red != null && green != null && blue != null)
            button.setBackgroundColor(Color.rgb(red, green, blue))
        Picasso.get().load(info.imageUrl).into(image)
    }

    fun init() {
        image = binding.imageView
        title = binding.title
        user = binding.username
        button = binding.colorButton
        date = binding.datetime
    }

}