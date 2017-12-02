package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.R;

public class ContentViewHolder extends LicenseViewHolder<ContentWrapper> {

  private TextView content;

  public ContentViewHolder(View itemView) {
    super(itemView);
    content = itemView.findViewById(R.id.license);
  }

  @Override
  public void bind(@NonNull ContentWrapper item) {
    Library entry = item.entry();

    content.setText(entry.license().text);
  }
}
