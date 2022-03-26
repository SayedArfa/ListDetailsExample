package com.sarfa.listdetailsexample.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sarfa.listdetailsexample.core.base.BaseFragment
import com.sarfa.listdetailsexample.databinding.FragmentItemDetailsBinding
import com.sarfa.listdetailsexample.ui.util.ext.loadFromUrl

class ItemDetailsFragment : BaseFragment() {
    private lateinit var viewBinding: FragmentItemDetailsBinding
    private val args: ItemDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentItemDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.itemImageview.loadFromUrl(args.item.image_urls.firstOrNull())
        viewBinding.nameTv.text = args.item.name
        viewBinding.priceTv.text = args.item.price
    }
}