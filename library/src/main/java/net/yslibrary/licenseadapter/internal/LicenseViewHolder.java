package net.yslibrary.licenseadapter.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.R;

public final class LicenseViewHolder extends ViewHolderBase implements View.OnClickListener, LibrariesHolder.Listener {
  private final LibrariesHolder holder;

  private final TextView licenseName;
  private final TextView license;

  private ExpandableLibrary expandableLibrary;

  public LicenseViewHolder(View itemView, @NonNull LibrariesHolder holder) {
    super(itemView);
    this.holder = holder;

    licenseName = itemView.findViewById(R.id.license_name);
    license = itemView.findViewById(R.id.license);

    licenseName.setOnClickListener(this);
  }

  @Override
  public void bind(@NonNull ExpandableLibrary library) {
    expandableLibrary = library;
    boolean expanded = library.isExpanded();

    licenseName.setText(expandableLibrary.getLibrary().getLicense().getName());

    int visibility = expanded ? View.VISIBLE : View.GONE;
    // Hack to circumvent padding bug. See XML.
    ((FrameLayout) itemView).getChildAt(0).setVisibility(visibility);
    licenseName.setVisibility(visibility);
    license.setVisibility(visibility);

    if (expanded) holder.load(expandableLibrary.getLibrary(), this);
  }

  @Override
  public void onClick(View v) {
    String url = expandableLibrary.getLibrary().getLicense().getUrl();
    if (!TextUtils.isEmpty(url)) {
      launchUri(Uri.parse(url));
    }
  }

  @Override
  public void onComplete(@Nullable License license, @Nullable Exception e) {
    // Since this view holder could be reused for different libraries, ensure it wasn't
    // rebound while we were waiting for the license to load.
    if (expandableLibrary.getLibrary().getLicense().equals(license)) {
      if (e == null) {
        //noinspection ConstantConditions
        this.license.setText(license.getText());
      } else {
        this.license.setText(R.string.license_load_error);
      }
    }
  }
}
