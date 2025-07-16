package acr.browser.lightning.browser.tab

import android.webkit.WebView

/**
 * A TabInitializer that does nothing (no WebView content) for the native home tab.
 */
class NativeHomeTabInitializer : TabInitializer {
    override fun initialize(webView: WebView, headers: Map<String, String>) {
        // No-op: native fragment will be shown instead of WebView content
    }
}