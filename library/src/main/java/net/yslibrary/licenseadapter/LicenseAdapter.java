package net.yslibrary.licenseadapter;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import net.yslibrary.licenseadapter.internal.ContentViewHolder;
import net.yslibrary.licenseadapter.internal.ContentWrapper;
import net.yslibrary.licenseadapter.internal.HeaderViewHolder;
import net.yslibrary.licenseadapter.internal.HeaderWrapper;
import net.yslibrary.licenseadapter.internal.LibrariesHolder;
import net.yslibrary.licenseadapter.internal.LicenseViewHolder;
import net.yslibrary.licenseadapter.internal.ViewType;
import net.yslibrary.licenseadapter.internal.Wrapper;

public class LicenseAdapter extends RecyclerView.Adapter<LicenseViewHolder> {
  private final List<Wrapper> wrappedDataSet = new ArrayList<>();
  private LibrariesHolder holder;

  public LicenseAdapter(@NonNull List<Library> entries) {
    for (Library entry : entries) {
      wrappedDataSet.add(new HeaderWrapper(entry));
    }
  }

  @Override
  public int getItemViewType(int position) {
    Wrapper wrapper = getItem(position);

    if (wrapper == null) {
      throw new IllegalStateException("No wrapper for this position: " + position);
    }
    return wrapper.type().ordinal();
  }

  @Override
  public LicenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();

    if (holder == null) {
      if (context instanceof FragmentActivity) {
        holder = ViewModelProviders.of((FragmentActivity) context).get(LibrariesHolder.class);
      } else if (context instanceof Activity) {
        holder = new LibrariesHolder(((Activity) context).getApplication());
      } else {
        holder = new LibrariesHolder((Application) context.getApplicationContext());
      }
    }

    ViewType type = ViewType.values()[viewType];
    LayoutInflater inflater = LayoutInflater.from(context);
    View view;

    switch (type) {
      case HEADER:
        view = inflater.inflate(R.layout.license_header, parent, false);
        return new HeaderViewHolder(view);

      case CONTENT:
        view = inflater.inflate(R.layout.license_content, parent, false);
        return new ContentViewHolder(view, holder);

      default:
        throw new IllegalArgumentException(
            "No ViewHolder exists for ViewType: " + String.valueOf(viewType));
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void onBindViewHolder(final LicenseViewHolder holder, final int position) {
    final Wrapper wrapper = getItem(position);

    if (wrapper == null) {
      throw new IllegalStateException("No wrapper for this position: " + position);
    }

    ViewType viewType = wrapper.type();
    // bind variable, not event
    holder.bind(viewType.convert(wrapper));

    switch (viewType) {
      case HEADER:
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int position = holder.getAdapterPosition();

            if (!wrapper.entry().hasContent()) {
              return;
            }

            boolean expanded = wrapper.isExpanded();

            if (expanded) {
              int removed = collapse(position);
              notifyItemChanged(position);
              notifyItemRemoved(removed);
            } else {
              int added = expand(position);
              notifyItemChanged(position);
              notifyItemInserted(added);
            }
          }
        });
        break;
      case CONTENT:
        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int position = holder.getAdapterPosition();

            int removed = collapse(position - 1);
            notifyItemChanged(position - 1);
            notifyItemRemoved(removed);
          }
        });
        break;
    }
  }

  @SuppressWarnings("ConstantConditions")
  private int expand(int headerPosition) {
    int added = headerPosition + 1;
    Wrapper headerItem = getItem(headerPosition);
    headerItem.setExpanded(true);

    ContentWrapper contentWrapper = new ContentWrapper(headerItem.entry());

    wrappedDataSet.add(added, contentWrapper);
    return added;
  }

  private int collapse(int headerPosition) {
    Wrapper headerItem = getItem(headerPosition);
    Wrapper contentItem = getItem(headerPosition + 1);

    headerItem.setExpanded(false);
    contentItem.setExpanded(false);

    int removed = headerPosition + 1;
    wrappedDataSet.remove(removed);

    return removed;
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
