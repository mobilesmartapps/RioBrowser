package acr.browser.lightning.browser.home.ui

import acr.browser.lightning.R
import acr.browser.lightning.browser.BrowserActivity
import acr.browser.lightning.browser.di.injector
import acr.browser.lightning.browser.home.adpter.BookmarkHomeAdapter
import acr.browser.lightning.browser.home.ui.viewmodels.BookmarkHomeViewModel
import acr.browser.lightning.browser.home.ui.viewmodels.BookmarkHomeViewModelFactory
import acr.browser.lightning.browser.image.ImageLoader
import acr.browser.lightning.database.bookmark.BookmarkDatabase
import acr.browser.lightning.database.bookmark.BookmarkRepository
import acr.browser.lightning.databinding.FragmentBookmarkHomeBinding
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import javax.inject.Inject

class BookmarkHomeFragment : Fragment() {



    private var _binding: FragmentBookmarkHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BookmarkHomeViewModel
    private lateinit var adapter: BookmarkHomeAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
       /* injector.browser2ComponentBuilder()
            .activity(requireActivity())
            .browserFrame(requireActivity().findViewById(R.id.content_frame))
            .toolbarRoot(requireActivity().findViewById(R.id.ui_layout))
            .browserRoot(requireActivity().findViewById(R.id.browser_layout_container))
            .toolbar(requireActivity().findViewById(R.id.toolbar_layout))
            .build()
            .inject(requireActivity() as BrowserActivity)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Create factory and ViewModel manually
        val bookmarkRepository: BookmarkRepository = BookmarkDatabase(requireActivity().application)

        val factory = BookmarkHomeViewModelFactory(bookmarkRepository)
        viewModel = ViewModelProvider(this, factory)[BookmarkHomeViewModel::class.java]

        viewModel.bookmarks.observe(viewLifecycleOwner) { bookmarks ->
            Log.e("TAG_DATA", "bookmarks :${bookmarks.size}")
            adapter.submitList(bookmarks)
        }
       /* adapter = BookmarkHomeAdapter(
            imageLoader = null,
            onClick = { bookmark ->
                (activity as? BrowserActivity)?.openUrlInNewTab(bookmark.url)
            }
        )*/
        binding.bookmarkRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.bookmarkRecyclerView.adapter = adapter



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}