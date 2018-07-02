package net.yslibrary.licenseadapter.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ViewHolderBase extends RecyclerView.ViewHolder {
  private final int colorPrimary;

  public ViewHolderBase(View itemView) {
    super(itemView);

    colorPrimary = Utils.getIntValueFromAttribute(itemView.getContext(), android.R.attr.colorPrimary);
  }

  protected final void launchUri(Uri uri) {
    new CustomTabsIntent.Builder()
        .setToolbarColor(colorPrimary)
        .setShowTitle(true)
        .addDefaultShareMenuItem()
        .enableUrlBarHiding()
        .build()
        .launchUrl(itemView.getContext(), uri);
  }

  public abstract void bind(@NonNull ExpandableLibrary library);
}
