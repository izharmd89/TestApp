package com.bws.izharassignment.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.bws.izharassignment.R
import com.bws.izharassignment.constants.Common
import kotlinx.android.synthetic.main.activity_viewsource.*


class LoadSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewsource)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(Common.sourceURL)
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

            return if (url == null || url.startsWith("http://") || url.startsWith("https://")) {
                false
            } else try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
                progressBar.visibility = View.GONE
                true
            } catch (e: Exception) {
                Log.i("TAG", "shouldOverrideUrlLoading Exception:$e")
                progressBar.visibility = View.GONE
                true
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}