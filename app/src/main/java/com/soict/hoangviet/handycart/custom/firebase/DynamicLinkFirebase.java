package com.soict.hoangviet.handycart.custom.firebase;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.soict.hoangviet.handycart.ui.master.MasterFragment;

public class DynamicLinkFirebase {
    private static DynamicLinkFirebase instance = null;

    public static DynamicLinkFirebase getInstance() {
        if (instance == null) instance = new DynamicLinkFirebase();
        return instance;
    }

    public void createDynamicLink(DynamicLinkListener listener, String title, String query, int id) {
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://carthandy.page.link?" + query + "=" + id))
                .setDomainUriPrefix("https://carthandy.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder("com.soict.hoangviet.handycart")
                                .setFallbackUrl(Uri.parse("https://www.timdatxe.com/"))
                                .setMinimumVersion(0)
                                .build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle(title)
                                .setDescription("Handy mang cả thế giới ẩm thực Hàn Quốc đến ngôi nhà của bạn!")
                                .setImageUrl(Uri.parse(""))
                                .build())
                // Set parameters
                // ...
                .buildShortDynamicLink()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Short link created
                        Uri shortLink = task.getResult().getShortLink();
                        Uri flowchartLink = task.getResult().getPreviewLink();
                        listener.onSuccess(shortLink);
                    } else {
                        listener.onError();
                    }
                });
    }

    public void receviveDynamicLink(Intent activityIntent, ReceiverListener listener) {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(activityIntent)
                .addOnSuccessListener(pendingDynamicLinkData -> {
                    // Get deep link from result (may be null if no link is found)
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                    }
                    listener.onSuccess(deepLink);
                })
                .addOnFailureListener(e -> listener.onError(e));
    }

    public interface DynamicLinkListener {
        void onSuccess(Uri uri);

        void onError();
    }

    public interface ReceiverListener {
        void onSuccess(Uri deepLink);

        void onError(Exception e);
    }
}
