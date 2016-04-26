package net.yslibrary.licenseadapter.internal;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yshrsmz on 2016/04/26.
 */
public abstract class LicenseViewHolder<T extends Wrapper> extends RecyclerView.ViewHolder {
  public LicenseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(T item);
}
