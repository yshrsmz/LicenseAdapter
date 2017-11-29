package net.yslibrary.licenseadapter.internal;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.R;

public final class LibraryViewHolder extends ViewHolderBase implements View.OnClickListener {
  private final TextView name;
  private final TextView author;
  private final ImageView expand;

  private final int colorAccent;
  private final int normalTextColor;

  private ExpandableLibrary expandableLibrary;

  public LibraryViewHolder(View itemView) {
    super(itemView);

    name = itemView.findViewById(R.id.name);
    author = itemView.findViewById(R.id.author);
    expand = itemView.findViewById(R.id.expand);

    colorAccent = ContextCompat.getColor(itemView.getContext(), R.color.colorAccent);
    normalTextColor = name.getCurrentTextColor();

    itemView.setOnClickListener(this);
    name.setOnClickListener(this);

    // Hack to simplify dark mode
    expand.setColorFilter(normalTextColor);
  }

  public void bind(@NonNull ExpandableLibrary library) {
    expandableLibrary = library;
    bind(expandableLibrary.library);
  }

  private void bind(@NonNull Library library) {
    name.setText(library.name());
    author.setText(library.author());
    name.setTextColor(library instanceof GitHubLibrary ? colorAccent : normalTextColor);

    updateExpandedStatus(false);
  }

  @Override
  public void onClick(View v) {
    if (v == itemView) {
      expandableLibrary.setExpanded(
          expandableLibrary.library.hasContent() && !expandableLibrary.isExpanded());
      updateExpandedStatus(true);
    } else if (v == name) {
      launchLibraryUrl(expandableLibrary.library);
    } else {
      throw new IllegalStateException("Unknown view: " + v);
    }
  }

  private void launchLibraryUrl(@NonNull Library library) {
    Uri uri;
    if (library instanceof GitHubLibrary) {
      uri = Uri.parse(GitHubLibrary.URL_BASE_PUBLIC
          + library.author() + GitHubLibrary.URL_REPO_SPLIT + library.name());
    } else if (library instanceof NoContentLibrary) {
      uri = Uri.parse(library.license().url);
    } else {
      onClick(itemView);
      return;
    }

    launchUri(uri);
  }

  private void updateExpandedStatus(boolean animate) {
    expand.setVisibility(expandableLibrary.library.hasContent() ? View.VISIBLE : View.GONE);

    float rotation = expandableLibrary.isExpanded() ? 180 : 0;
    if (animate) {
      expand.animate().rotation(rotation).start();
    } else {
      expand.setRotation(rotation);
    }
  }
}
