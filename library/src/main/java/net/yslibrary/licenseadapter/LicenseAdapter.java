package net.yslibrary.licenseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yshrsmz on 2016/04/15.
 */
public class LicenseAdapter extends RecyclerView.Adapter<LicenseAdapter.ViewHolder> {

  private final List<LicenseEntry> dataSet = new ArrayList<>();

  public LicenseAdapter(List<LicenseEntry> dataSet) {
    this.dataSet.addAll(dataSet);
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_license, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    ((LicenseRowView) holder.itemView).setData(dataSet.get(position));
  }

  @Override
  public int getItemCount() {
    return dataSet.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
