package net.yslibrary.licenseadapter.internal;

import androidx.annotation.NonNull;
import net.yslibrary.licenseadapter.Library;

public final class ExpandableLibrary {
  public interface ExpandListener {
    void onExpand(@NonNull ExpandableLibrary library, boolean expanded);
  }

  private final Library library;
  private final ExpandListener listener;

  private boolean expanded;

  public ExpandableLibrary(@NonNull Library library, @NonNull ExpandListener listener) {
    this.library = library;
    this.listener = listener;
  }

  @NonNull
  public Library getLibrary() {
    return library;
  }

  public boolean isExpanded() {
    return expanded;
  }

  public void setExpanded(boolean expanded) {
    if (this.expanded == expanded) return;

    this.expanded = expanded;
    listener.onExpand(this, expanded);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ExpandableLibrary that = (ExpandableLibrary) o;

    return expanded == that.expanded && library.equals(that.library);
  }

  @Override
  public int hashCode() {
    int result = library.hashCode();
    result = 31 * result + (expanded ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ExpandableLibrary{" +
        "library=" + library +
        ", expanded=" + expanded +
        '}';
  }
}
