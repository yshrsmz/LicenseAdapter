package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import net.yslibrary.licenseadapter.License;
import net.yslibrary.licenseadapter.R;

public class ContentViewHolder extends LicenseViewHolder<ContentWrapper> {
  private TextView content;

  private final LibrariesHolder holder;

  public ContentViewHolder(View itemView, LibrariesHolder holder) {
    super(itemView);
    content = itemView.findViewById(R.id.license);
    this.holder = holder;
  }

  @Override
  public void bind(@NonNull ContentWrapper item) {
    holder.load(item.entry()).addOnSuccessListener(new OnSuccessListener<License>() {
      @Override
      public void onSuccess(License license) {
        content.setText(license.text);
      }
    });
  }
}
