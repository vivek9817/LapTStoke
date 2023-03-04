package com.example.mittechapp.View.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mittechapp.CommonUtils.CommonUtils
import com.example.mittechapp.CommonUtils.CommonUtils.clickWithDebounce
import com.example.mittechapp.Model.ItemsData
import com.example.mittechapp.R
import com.example.mittechapp.adapter.CommonAlertAdapter
import com.example.mittechapp.databinding.FragmentInformationBinding
import com.example.mittechapp.viewholder.GenericViewHolder
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class Information : Fragment(R.layout.fragment_information) {
    lateinit var binding: FragmentInformationBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInformationBinding.inflate(layoutInflater)
        var view = binding.root
        recyclerView =
            CommonUtils.initializeRecyclerView(
                binding.recyclerItems,
                requireContext()
            )

        onClickOnUi()
        return view
    }

    private fun onClickOnUi() {
        var Arraydata: ArrayList<ItemsData> = ArrayList()

        Arraydata.add(
            ItemsData(
                "15s-fq5007TU", "Laptop Intel Core i3 12th Gen", "$1120.00"
            )
        )

        Arraydata.add(
            ItemsData(
                "HP Pavilion x360", "2-in-1 Laptop 14-ek0078TU", "$2340.00"
            )
        )

        Arraydata.add(
            ItemsData(
                "HP 255 G8", "Laptop 6E3Z0PA", "$2390.00"
            )
        )

        Arraydata.add(
            ItemsData(
                "EcoTank L3210", "Ultra-low-cost printing", "$14999.00"
            )
        )

        Arraydata.add(
            ItemsData(
                "Canon PIXMA TS3370s", "Inkjet Color Printer (Black)", "$8859.00"
            )
        )


        recyclerView.adapter = object : CommonAlertAdapter<Any>(
            R.layout.common_alert_rec_data,
            Arraydata as ArrayList<Any>
        ) {
            override fun bindData(holder: GenericViewHolder<Any>, item: Any) {
                when (item) {
                    CommonUtils.checkTypeCast<ItemsData>(item) -> {
                        var itemsName =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Item
                        holder.itemView.findViewById<TextView>(R.id.item).text = itemsName

                        var catagory =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Category
                        holder.itemView.findViewById<TextView>(R.id.catagry).text = catagory

                        var price =
                            CommonUtils.checkTypeCast<ItemsData>(item)!!.Itemprice
                        holder.itemView.findViewById<TextView>(R.id.price).text = price


                        var i: Int = 0
                        holder.itemView.findViewById<TextView>(R.id.edtQuantity).text = i.toString()
                        holder.itemView.findViewById<TextView>(R.id.txtAddQuantity).setOnClickListener {
                                if (i == 9) {
                                    i = 9
                                    CommonUtils.snakeBarPopUp(
                                        binding.linfrag1,
                                        "Cannot Be More Than 9"
                                    )
                                } else {
                                    i++
                                    holder.itemView.findViewById<TextView>(R.id.edtQuantity).text = i.toString()
                                }
                            }

                        holder.itemView.findViewById<TextView>(R.id.txtNegateQuantity)
                            .setOnClickListener {
                                if (i == 0) {
                                    i = 0
                                    CommonUtils.snakeBarPopUp(
                                        binding.linfrag1,
                                        "Cannot Be Less Than 0"
                                    )
                                } else {
                                    i--
                                    holder.itemView.findViewById<TextView>(R.id.edtQuantity).text = i.toString()
                                }
                            }

                        when (CommonUtils.checkTypeCast<ItemsData>(item)!!.Item) {
                            "15s-fq5007TU" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.hp_s)
                            }
                            "HP Pavilion x360" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.hp_pavillion)
                            }
                            "HP 255 G8" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.hp_g)
                            }
                            "EcoTank L3210" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.espon)
                            }
                            "Canon PIXMA TS3370s" -> {
                                holder.itemView.findViewById<ImageView>(R.id.img1)
                                    .setImageResource(R.drawable.canon)
                            }
                        }

                        holder.itemView.findViewById<LinearLayout>(R.id.commonalertrec).clickWithDebounce { openBottomSheetDialog(CommonUtils.checkTypeCast<ItemsData>(item)) }

                    }
                }
            }

            override fun clickHanlder(pos: Int, item: Any, aView: View) {
                CommonUtils.checkTypeCast<ItemsData>(item).apply {}
            }

        }
    }

    private fun openBottomSheetDialog(itemsData: ItemsData?) {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        bottomSheetDialog.setContentView(R.layout.layout_items_record_full_view)
        val bottomSheetBehavior = bottomSheetDialog.behavior
        bottomSheetDialog.let {
            val bottomSheet = it.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
        bottomSheetBehavior.isDraggable = true
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        bottomSheetDialog.apply {
            when (itemsData?.Item) {
                "15s-fq5007TU" -> {
                    this.findViewById<AppCompatImageView>(R.id.imgSetImage)
                        ?.setImageResource(R.drawable.hp_s)
                    this.findViewById<CollapsingToolbarLayout>(R.id.txtAddTitle)?.title =
                        "Laptop Intel Core i3 12th Gen"
                    this.findViewById<AppCompatTextView>(R.id.txtImgHeading)?.text = "15s-fq5007TU"
                    this.findViewById<AppCompatTextView>(R.id.txtImgBody)?.text = "" +
                            "HP 15s-fq5007TU Laptop Intel Core i3 12th Gen (8 GB/ 512 GB SSD/ Intel® UHD Graphics/ Win 11)\n" +
                            "SKU: FQ5007TU\n" +
                            "Operating System: Windows 11 Home\n" +
                            "\n" +
                            "Processor: 12th Generation Intel® Core™ i3\n" +
                            "\n" +
                            "Memory: 8 GB DDR4-3200 MHz RAM (1 x 8 GB)\n" +
                            "\n" +
                            "Storage: 512 GB PCIe® NVMe™ M.2 SSD\n" +
                            "\n" +
                            "Graphics: Intel® UHD Graphics\n" +
                            "\n" +
                            "Screen: 39.6 cm (15.6) diagonal FHD display\n" +
                            "\n" +
                            "Weight: Starting at 1.69 kg" +
                            "\n" +
                            "1 year (1/1/1) limited warranty includes 1 year of parts and labor. " +
                            "On-site repair. Terms and conditions vary by country. Certain restrictions and exclusions apply." +
                            "\n" +
                            "HP True Vision 720p HD camera with temporal noise reduction and integrated dual array digital microphones" +
                            "\n" +
                            "Full-size, backlit, natural silver keyboard with numeric keypad" +
                            "\n" +
                            "Up to 10 hours and 45 minutes"
                    this.findViewById<AppCompatTextView>(R.id.txtImgContact)?.text = "" +
                            "Contact Person : 7783442676"
                    this.findViewById<AppCompatTextView>(R.id.txtImgAddress)?.text = "" +
                            "No 237, Unique Towers, 17th Main,\n" +
                            "Freedom Fighters Colony,\n" +
                            "Bangalore 560 058 KA India"
                }
                "HP Pavilion x360" -> {
                    this.findViewById<AppCompatImageView>(R.id.imgSetImage)
                        ?.setImageResource(R.drawable.hp_pavillion)
                    this.findViewById<CollapsingToolbarLayout>(R.id.txtAddTitle)?.title =
                        "2-in-1 Laptop 14-ek0078TU"
                    this.findViewById<AppCompatTextView>(R.id.txtImgHeading)?.text =
                        "HP Pavilion x360"
                    this.findViewById<AppCompatTextView>(R.id.txtImgBody)?.text = "" +
                            "HP Pavilion x360 2-in-1 Laptop 14-ek0078TU\n" +
                            "SKU: FQ5007TU\n" +
                            "Operating System: Windows 11 Home\n" +
                            "\n" +
                            "Processor: 12th Generation Intel® Core™ i3\n" +
                            "\n" +
                            "Memory: 8 GB DDR4-3200 MHz RAM (1 x 8 GB)\n" +
                            "\n" +
                            "Storage: 512 GB PCIe® NVMe™ M.2 SSD\n" +
                            "\n" +
                            "Graphics: Intel® UHD Graphics\n" +
                            "\n" +
                            "Screen: 39.6 cm (15.6) diagonal FHD display\n" +
                            "\n" +
                            "Weight: Starting at 1.69 kg" +
                            "\n" +
                            "1 year (1/1/1) limited warranty includes 1 year of parts and labor. " +
                            "On-site repair. Terms and conditions vary by country. Certain restrictions and exclusions apply." +
                            "\n" +
                            "HP True Vision 720p HD camera with temporal noise reduction and integrated dual array digital microphones" +
                            "\n" +
                            "Full-size, backlit, natural silver keyboard with numeric keypad" +
                            "\n" +
                            "Up to 10 hours and 45 minutes"
                    this.findViewById<AppCompatTextView>(R.id.txtImgContact)?.text = "" +
                            "Contact Person : 9875252309"
                    this.findViewById<AppCompatTextView>(R.id.txtImgAddress)?.text = "" +
                            "No 237, Unique Towers, 17th Main,\n" +
                            "Freedom Fighters Colony,\n" +
                            "Bangalore 560 058 KA India"
                }
                "HP 255 G8" -> {
                    this.findViewById<AppCompatImageView>(R.id.imgSetImage)
                        ?.setImageResource(R.drawable.hp_g)
                    this.findViewById<CollapsingToolbarLayout>(R.id.txtAddTitle)?.title =
                        "Laptop 6E3Z0PA"
                    this.findViewById<AppCompatTextView>(R.id.txtImgHeading)?.text = "HP 255 G8"
                    this.findViewById<AppCompatTextView>(R.id.txtImgBody)?.text = "" +
                            "HP 15s-fq5007TU Laptop Intel Core i3 12th Gen (8 GB/ 512 GB SSD/ Intel® UHD Graphics/ Win 11)\n" +
                            "SKU: FQ5007TU\n" +
                            "Operating System: Windows 11 Home\n" +
                            "\n" +
                            "Processor: 12th Generation Intel® Core™ i3\n" +
                            "\n" +
                            "Memory: 8 GB DDR4-3200 MHz RAM (1 x 8 GB)\n" +
                            "\n" +
                            "Storage: 512 GB PCIe® NVMe™ M.2 SSD\n" +
                            "\n" +
                            "Graphics: Intel® UHD Graphics\n" +
                            "\n" +
                            "Screen: 39.6 cm (15.6) diagonal FHD display\n" +
                            "\n" +
                            "Weight: Starting at 1.69 kg" +
                            "\n" +
                            "1 year (1/1/1) limited warranty includes 1 year of parts and labor. " +
                            "On-site repair. Terms and conditions vary by country. Certain restrictions and exclusions apply." +
                            "\n" +
                            "HP True Vision 720p HD camera with temporal noise reduction and integrated dual array digital microphones" +
                            "\n" +
                            "Full-size, backlit, natural silver keyboard with numeric keypad" +
                            "\n" +
                            "Up to 10 hours and 45 minutes"
                    this.findViewById<AppCompatTextView>(R.id.txtImgContact)?.text = "" +
                            "Contact Person : 6654234367"
                    this.findViewById<AppCompatTextView>(R.id.txtImgAddress)?.text = "" +
                            "No 237, Unique Towers, 17th Main,\n" +
                            "Freedom Fighters Colony,\n" +
                            "Bangalore 560 058 KA India"
                }
                "EcoTank L3210" -> {
                    this.findViewById<AppCompatImageView>(R.id.imgSetImage)
                        ?.setImageResource(R.drawable.espon)
                    this.findViewById<CollapsingToolbarLayout>(R.id.txtAddTitle)?.title =
                        "Ultra-low-cost printing"
                    this.findViewById<AppCompatTextView>(R.id.txtImgHeading)?.text =
                        "Ultra-low-cost printing"
                    this.findViewById<AppCompatTextView>(R.id.txtImgBody)?.text = "" +
                            " Ultra-low-cost printing.\n" +
                            "        Print speed - 10 ppm (Black), 5 ppm (Colour)\n" +
                            "        Low print cost : Black - 9 paise | Color - 24 paise\n" +
                            "        *The total print cost (Black+Colour) is 33 paise\n" +
                            "        Up to 3 years of ink included: Includes 8,100 pages in black and 6,500 in colour.\n" +
                            "        Hassle-free ink tank system: Enjoy mess-free refills with key-lock bottles and front-facing tanks.\n" +
                            "        Multifunction printer: Print, copy & scan and borderless photo printing\n" +
                            "Operating System: Windows 11 Home\n" +
                            "\n" +
                            "Processor: 12th Generation Intel® Core™ i3\n" +
                            "\n" +
                            "Memory: 8 GB DDR4-3200 MHz RAM (1 x 8 GB)\n" +
                            "\n" +
                            "Storage: 512 GB PCIe® NVMe™ M.2 SSD\n" +
                            "\n" +
                            "Graphics: Intel® UHD Graphics\n" +
                            "\n" +
                            "Screen: 39.6 cm (15.6) diagonal FHD display\n" +
                            "\n" +
                            "Weight: Starting at 1.69 kg" +
                            "\n" +
                            "1 year (1/1/1) limited warranty includes 1 year of parts and labor. " +
                            "On-site repair. Terms and conditions vary by country. Certain restrictions and exclusions apply." +
                            "\n" +
                            "HP True Vision 720p HD camera with temporal noise reduction and integrated dual array digital microphones" +
                            "\n" +
                            "Full-size, backlit, natural silver keyboard with numeric keypad" +
                            "\n" +
                            "Up to 10 hours and 45 minutes"
                    this.findViewById<AppCompatTextView>(R.id.txtImgContact)?.text = "" +
                            "Contact Person : 6654234367"
                    this.findViewById<AppCompatTextView>(R.id.txtImgAddress)?.text = "" +
                            "No 237, Unique Towers, 17th Main,\n" +
                            "Freedom Fighters Colony,\n" +
                            "Bangalore 560 058 KA India"
                }
                "Canon PIXMA TS3370s" -> {
                    this.findViewById<AppCompatImageView>(R.id.imgSetImage)
                        ?.setImageResource(R.drawable.canon)
                    this.findViewById<CollapsingToolbarLayout>(R.id.txtAddTitle)?.title =
                        "All-in-One Wireless"
                    this.findViewById<AppCompatTextView>(R.id.txtImgHeading)?.text =
                        "All-in-One Wireless Inkjet Color Printer (Black)"
                    this.findViewById<AppCompatTextView>(R.id.txtImgBody)?.text = "" +
                            " Ultra-low-cost printing\n" +
                            "Operating System: Windows 11 Home\n" +
                            "\n" +
                            "Processor: 12th Generation Intel® Core™ i3\n" +
                            "\n" +
                            "Memory: 8 GB DDR4-3200 MHz RAM (1 x 8 GB)\n" +
                            "\n" +
                            "Storage: 512 GB PCIe® NVMe™ M.2 SSD\n" +
                            "\n" +
                            "Graphics: Intel® UHD Graphics\n" +
                            "\n" +
                            "Screen: 39.6 cm (15.6) diagonal FHD display\n" +
                            "\n" +
                            "Weight: Starting at 1.69 kg" +
                            "\n" +
                            "1 year (1/1/1) limited warranty includes 1 year of parts and labor. " +
                            "On-site repair. Terms and conditions vary by country. Certain restrictions and exclusions apply." +
                            "\n" +
                            "HP True Vision 720p HD camera with temporal noise reduction and integrated dual array digital microphones" +
                            "\n" +
                            "Full-size, backlit, natural silver keyboard with numeric keypad" +
                            "\n" +
                            "Up to 10 hours and 45 minutes"
                    this.findViewById<AppCompatTextView>(R.id.txtImgContact)?.text = "" +
                            "Contact Person : 6654234367"
                    this.findViewById<AppCompatTextView>(R.id.txtImgAddress)?.text = "" +
                            "No 237, Unique Towers, 17th Main,\n" +
                            "Freedom Fighters Colony,\n" +
                            "Bangalore 560 058 KA India"
                }
            }
        }
        bottomSheetDialog.show()
    }

}