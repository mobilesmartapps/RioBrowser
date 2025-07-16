package acr.browser.lightning.browser.home.ui.viewmodels

import acr.browser.lightning.database.bookmark.BookmarkRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

    class BookmarkHomeViewModelFactory(
        private val bookmarkRepository: BookmarkRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BookmarkHomeViewModel(bookmarkRepository) as T
        }
    }
