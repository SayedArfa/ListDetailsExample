package com.sarfa.listdetailsexample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sarfa.domain.model.response.base.ErrorType
import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.listdetailsexample.core.base.BaseFragment
import com.sarfa.listdetailsexample.core.di.ViewModelFactory
import com.sarfa.listdetailsexample.databinding.FragmentItemListBinding
import javax.inject.Inject

class ItemListFragment : BaseFragment() {
    private lateinit var viewBinding: FragmentItemListBinding
    private val itemListAdapter = ItemListAdapter().apply {
        onProjectClickedListener = {
            findNavController().navigate(
                ItemListFragmentDirections.actionItemListFragmentToItemDetailsFragment(
                    it
                )
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ItemListViewModel>
    private val itemListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ItemListViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemListViewModel.loadList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentItemListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.itemsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            adapter = itemListAdapter

        }
        itemListViewModel.itemsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                ResponseWrapper.Loading -> {
                    viewBinding.progressBar.visibility = View.VISIBLE
                }
                is ResponseWrapper.Success -> {
                    viewBinding.progressBar.visibility = View.GONE
                    itemListAdapter.submitList(it.data.items)
                }
                is ResponseWrapper.Error -> {
                    viewBinding.progressBar.visibility = View.GONE
                    when (it.errorType) {
                        is ErrorType.ApiError -> {}
                        is ErrorType.JsonParseError -> {}
                        is ErrorType.NetworkError -> {}
                        ErrorType.NoInternetError -> {}
                        is ErrorType.UnknownError -> {}
                    }
                    snackBar =
                        Snackbar.make(viewBinding.root, "Error: ${it}", Snackbar.LENGTH_INDEFINITE)
                            .apply {
                                setAction("retry") {
                                    itemListViewModel.loadList()
                                    dismiss()
                                }
                                show()
                            }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}