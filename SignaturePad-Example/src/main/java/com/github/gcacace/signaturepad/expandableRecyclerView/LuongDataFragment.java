package com.github.gcacace.signaturepad.expandableRecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import it.gcacace.signaturepad.R;

public class LuongDataFragment extends android.support.v4.app.Fragment
{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.luongdatafragment, container, false);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.pb);
        final WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.loadUrl("https://medium.com/@cdmunoz/offline-first-android-app-with-mvvm-dagger2-rxjava-livedata-and-room-part-4-2b476142e769");
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url)
            {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
