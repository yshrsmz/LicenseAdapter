package net.yslibrary.licenseadapter.internal;

import android.net.Uri;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ViewHolderBase extends RecyclerView.ViewHolder {
  private final int colorPrimary;

  public ViewHolderBase(View itemView) {
    super(itemView);

    colorPrimary = Utils.getIntValueFromAttribute(itemView.getContext(), androidx.appcompat.R.attr.colorPrimary);
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
