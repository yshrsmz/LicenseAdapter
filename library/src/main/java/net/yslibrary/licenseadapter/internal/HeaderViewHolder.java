package net.yslibrary.licenseadapter.internal;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.yslibrary.licenseadapter.Library;
import net.yslibrary.licenseadapter.R;

public class HeaderViewHolder extends LicenseViewHolder<HeaderWrapper> {
  private ImageView arrow;
  private TextView libraryName;
  private TextView author;
  private TextView licenseType;
  private TextView url;

  public HeaderViewHolder(@NonNull View itemView) {
    super(itemView);

    arrow = itemView.findViewById(R.id.arrow);
    libraryName = itemView.findViewById(R.id.library);
    author = itemView.findViewById(R.id.author);
    licenseType = itemView.findViewById(R.id.licenseType);
    url = itemView.findViewById(R.id.url);

    // use TextView color for arrow color
    int arrowColor = libraryName.getCurrentTextColor();
    arrow.setColorFilter(arrowColor);
  }

  @Override
  public void bind(@NonNull HeaderWrapper item) {
    Library entry = item.entry();

    @DrawableRes int arrowRes = R.drawable.ic_expand_more_black_24dp;
    if (item.isExpanded()) {
      arrowRes = R.drawable.ic_expand_less_black_24dp;
    }
    arrow.setImageResource(arrowRes);

    int arrowVisibility = View.VISIBLE;
    if (!entry.hasContent()) {
      arrowVisibility = View.GONE;
    }
    arrow.setVisibility(arrowVisibility);

    libraryName.setText(entry.name());

    author.setText(entry.author());

    String type = entry.license().name;
    licenseType.setVisibility(TextUtils.isEmpty(type) ? View.GONE : View.VISIBLE);
    licenseType.setText(type);

    String url = entry.license().url;
    this.url.setVisibility(TextUtils.isEmpty(url) ? View.GONE : View.VISIBLE);
    this.url.setText(url);
  }
}
