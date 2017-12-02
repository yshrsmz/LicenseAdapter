package net.yslibrary.licenseadapter.internal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class LicenseViewHolder<T extends Wrapper> extends RecyclerView.ViewHolder {
  public LicenseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(@NonNull T item);
}
