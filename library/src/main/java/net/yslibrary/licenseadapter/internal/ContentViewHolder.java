package net.yslibrary.licenseadapter.internal;

import android.view.View;
import android.widget.TextView;
import net.yslibrary.licenseadapter.LicenseEntry;
import net.yslibrary.licenseadapter.R;

public class ContentViewHolder extends LicenseViewHolder<ContentWrapper> {

  private TextView content;

  public ContentViewHolder(View itemView) {
    super(itemView);
    content = Views.byId(itemView, R.id.license);
  }

  @Override
  public void bind(ContentWrapper item) {
    LicenseEntry entry = item.entry();

    content.setText(entry.license().text);
  }
}
