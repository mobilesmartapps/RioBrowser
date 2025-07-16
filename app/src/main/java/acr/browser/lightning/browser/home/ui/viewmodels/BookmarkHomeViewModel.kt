package acr.browser.lightning.browser.home.ui.viewmodels

import acr.browser.lightning.database.Bookmark
import acr.browser.lightning.database.bookmark.BookmarkRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class BookmarkHomeViewModel  (
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _bookmarks = MutableLiveData<List<Bookmark>>()
    val bookmarks: LiveData<List<Bookmark>> = _bookmarks

    private val disposables = CompositeDisposable()

    init {
        loadBookmarks()
    }

    private fun loadBookmarks() {
        disposables.add(
            bookmarkRepository.getBookmarksFromFolderSorted(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    _bookmarks.value = list.filterIsInstance<Bookmark.Entry>()
                }, { _bookmarks.value = emptyList() })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}