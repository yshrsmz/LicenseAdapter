package net.yslibrary.licenseadapter.internal;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.OpenSourceLibrary;
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

    colorAccent = Utils.getIntValueFromAttribute(itemView.getContext(), androidx.appcompat.R.attr.colorAccent);
    normalTextColor = name.getCurrentTextColor();

    itemView.setOnClickListener(this);
    name.setOnClickListener(this);

    expand.setColorFilter(normalTextColor); // Hack to simplify dark mode
  }

  public void bind(@NonNull ExpandableLibrary library) {
    expandableLibrary = library;
    bind(expandableLibrary.getLibrary());
  }

  private void bind(@NonNull Library library) {
    name.setText(library.getName());
    author.setText(library.getAuthor());
    name.setTextColor(library instanceof OpenSourceLibrary ? colorAccent : normalTextColor);

    updateExpandedStatus(false);
  }

  @Override
  public void onClick(View v) {
    if (v == itemView) {
      expandableLibrary.setExpanded(
          expandableLibrary.getLibrary().hasContent() && !expandableLibrary.isExpanded());
      updateExpandedStatus(true);
    } else if (v == name) {
      launchLibraryUrl(expandableLibrary.getLibrary());
    } else {
      throw new IllegalStateException("Unknown view: " + v);
    }
  }

  private void launchLibraryUrl(@NonNull Library library) {
    if (library instanceof OpenSourceLibrary) {
      launchUri(Uri.parse(((OpenSourceLibrary) library).getSourceUrl()));
    } else {
      onClick(itemView);
    }
  }

  private void updateExpandedStatus(boolean animate) {
    expand.setVisibility(expandableLibrary.getLibrary().hasContent() ? View.VISIBLE : View.GONE);

    float rotation = expandableLibrary.isExpanded() ? 180 : 0;
    if (animate) {
      expand.animate().rotation(rotation).start();
    } else {
      expand.setRotation(rotation);
    }
  }
}
