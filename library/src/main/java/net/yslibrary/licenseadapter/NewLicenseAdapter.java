package net.yslibrary.licenseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.internal.HeaderWrapper;
import net.yslibrary.licenseadapter.internal.LicenseViewHolder;
import net.yslibrary.licenseadapter.internal.Wrapper;

/**
 * Created by yshrsmz on 2016/04/26.
 */
public class NewLicenseAdapter extends RecyclerView.Adapter<LicenseViewHolder> {

  private final List<LicenseEntry> originalDataSet = new ArrayList<>();
  private final List<Wrapper> wrappedDataSet = new ArrayList<>();

  public NewLicenseAdapter(List<LicenseEntry> entries) {
    originalDataSet.addAll(entries);

    for (LicenseEntry entry : originalDataSet) {
      wrappedDataSet.add(new HeaderWrapper(entry));
    }
  }

  @Override
  public int getItemViewType(int position) {
    return super.getItemViewType(position);
  }

  @Override
  public LicenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onBindViewHolder(LicenseViewHolder holder, int position) {
    Wrapper wrapper = getItem(position);

    if (wrapper == null) {
      throw new IllegalStateException("No wrapper for this position: " + position);
    }

    holder.bind(wrapper.type().convert(wrapper));
  }

  @Override
  public int getItemCount() {
    return wrappedDataSet.size();
  }

  private Wrapper getItem(int position) {
    boolean isIndexInRange = position >= 0 && position < wrappedDataSet.size();
    return isIndexInRange ? wrappedDataSet.get(position) : null;
  }
}
