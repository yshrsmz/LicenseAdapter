package net.yslibrary.licenseadapter;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import net.yslibrary.licenseadapter.internal.ExpandableLibrary;
import net.yslibrary.licenseadapter.internal.LibrariesHolder;
import net.yslibrary.licenseadapter.internal.LibraryViewHolder;
import net.yslibrary.licenseadapter.internal.LicenseViewHolder;
import net.yslibrary.licenseadapter.internal.ViewHolderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link RecyclerView.Adapter} which displays expandable items with a library and its license.
 * <p>
 * For optimal performance, the {@link RecyclerView} using this adapter should be attached a support
 * library context such as the {@link AppCompatActivity}.
 *
 * @see Library
 * @see Licenses
 */
public final class LicenseAdapter extends RecyclerView.Adapter<ViewHolderBase>
    implements ExpandableLibrary.ExpandListener {
  private static final int TYPE_LIBRARY = 0;
  private static final int TYPE_LICENSE = 1;

  private final List<ExpandableLibrary> libraries;
  private LibrariesHolder holder;

  /**
   * Construct a new adapter to display a list of libraries and their licenses.
   *
   * @param libraries the libraries to display
   */
  public LicenseAdapter(@NonNull List<Library> libraries) {
    List<ExpandableLibrary> wrappedLibraries = new ArrayList<>();
    for (Library library : libraries) {
      wrappedLibraries.add(new ExpandableLibrary(library, this));
    }
    this.libraries = Collections.unmodifiableList(wrappedLibraries);
  }

  @Override
  public int getItemViewType(int position) {
    return position % 2 == 0 ? TYPE_LIBRARY : TYPE_LICENSE;
  }

  @Override
  public int getItemCount() {
    return libraries.size() * 2;
  }

  @Override
  public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
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

    if (viewType == TYPE_LIBRARY) {
      return new LibraryViewHolder(LayoutInflater.from(context)
          .inflate(R.layout.library, parent, false));
    } else if (viewType == TYPE_LICENSE) {
      return new LicenseViewHolder(LayoutInflater.from(context)
          .inflate(R.layout.license, parent, false), holder);
    } else {
      throw new IllegalStateException("Unknown view type: " + viewType);
    }
  }

  @Override
  public void onBindViewHolder(ViewHolderBase holder, int position) {
    int type = getItemViewType(position);
    if (type == TYPE_LIBRARY) {
      holder.bind(libraries.get(position / 2));
    } else if (type == TYPE_LICENSE) {
      holder.bind(libraries.get((position - 1) / 2));
    } else {
      throw new IllegalStateException("Unknown view type: " + type);
    }
  }

  @Override
  public void onExpand(@NonNull ExpandableLibrary library, boolean expanded) {
    int index = libraries.indexOf(library);

    if (index == -1) {
      throw new IllegalStateException("Could not find library: " + library);
    }

    notifyItemRangeChanged(index * 2, 2);
  }
}
