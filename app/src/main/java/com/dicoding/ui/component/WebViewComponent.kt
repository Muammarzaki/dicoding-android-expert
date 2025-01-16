package com.dicoding.ui.component

import android.text.Html
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(modifier: Modifier = Modifier, html: String, openToBrowser: Boolean = false) {
    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        if (openToBrowser) {
                            view?.context?.startActivity(
                                android.content.Intent(
                                    android.content.Intent.ACTION_VIEW,
                                    request?.url
                                )
                            )
                        } else {
                            view?.loadUrl(request?.url.toString())
                        }
                        return true;
                    }
                }
            }
        },
        update = {
            it.loadData(html, "text/html", "UTF-8")
        }
    )
}

@Composable
fun TextHtml(
    text: String = "",
    wrap: @Composable (String) -> Unit = {}
) {
    wrap(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString())
}