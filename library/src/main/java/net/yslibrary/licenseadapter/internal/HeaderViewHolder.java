package net.yslibrary.licenseadapter.internal;

import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.yslibrary.licenseadapter.LicenseEntry;
import net.yslibrary.licenseadapter.R;

public class HeaderViewHolder extends LicenseViewHolder<HeaderWrapper> {

  private ImageView arrow;

  private TextView libraryName;

  private TextView author;

  private TextView licenseType;

  private TextView link;

  public HeaderViewHolder(View itemView) {
    super(itemView);

    arrow = Views.byId(itemView, R.id.arrow);
    libraryName = Views.byId(itemView, R.id.library);
    author = Views.byId(itemView, R.id.author);
    licenseType = Views.byId(itemView, R.id.licenseType);
    link = Views.byId(itemView, R.id.link);

    // use TextView color for arrow color
    int arrowColor = libraryName.getCurrentTextColor();
    arrow.setColorFilter(arrowColor);
  }

  @Override
  public void bind(HeaderWrapper item) {
    LicenseEntry entry = item.entry();

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

    String type = entry.license() != null ? entry.license().name : "";
    licenseType.setVisibility(TextUtils.isEmpty(type) ? View.GONE : View.VISIBLE);
    licenseType.setText(type);

    link.setText(entry.license().url);
  }
}
