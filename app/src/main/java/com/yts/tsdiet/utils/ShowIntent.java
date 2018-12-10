package com.yts.tsdiet.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.yts.tsdiet.R;


public class ShowIntent {

    public static void emailSend(Context context) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        String[] address = {context.getString(R.string.contact_email)};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.contact_us));
        try {
            context.startActivity(email);
        } catch (Exception e) {
            ToastMake.make(context, context.getString(R.string.error_email));
        }
    }

    public static void invite(Context context) {
        Intent intent = new AppInviteInvitation.IntentBuilder(context.getString(R.string.shared_title))
                .setMessage(context.getString(R.string.shared_message))
                .setDeepLink(Uri.parse(context.getString(R.string.invitation_deep_link)))
                .setCallToActionText(context.getString(R.string.shared_call_to_action_text))
                .build();
        ((Activity) context).startActivityForResult(intent,111);
    }

    public static void reviews(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.yts.tsdiet"));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
