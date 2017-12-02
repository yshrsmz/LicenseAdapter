package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.R;

public class ContentViewHolder extends LicenseViewHolder<ContentWrapper>
    implements LibrariesHolder.Listener {
  private TextView content;

  private final LibrariesHolder holder;

  public ContentViewHolder(View itemView, LibrariesHolder holder) {
    super(itemView);
    content = itemView.findViewById(R.id.license);
    this.holder = holder;
  }

  @Override
  public void bind(@NonNull ContentWrapper item) {
    holder.load(item.entry(), this);
  }

  @Override
  public void onComplete(@Nullable License license, @Nullable Exception e) {
    if (e == null) {
      //noinspection ConstantConditions
      content.setText(license.text);
    } else {
      content.setText(e.getLocalizedMessage());
    }
  }
}
