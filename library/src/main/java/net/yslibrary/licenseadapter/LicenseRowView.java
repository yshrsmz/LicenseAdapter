package net.yslibrary.licenseadapter;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yshrsmz on 2016/04/16.
 */
public class LicenseRowView extends FrameLayout {
  private static final int DEF_ANIM_DURATION = 200;
  private final Handler handler = new Handler();

  View child;
  View group;
  TextView library;
  TextView author;
  TextView link;
  TextView type;
  TextView license;
  ImageView arrow;

  private int animDuration = DEF_ANIM_DURATION;
  private boolean isAnimationRunning;
  private boolean isOpened;
  private BaseLicenseEntry data;

  public LicenseRowView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  void onClickGroup(View view) {
    if (!(data instanceof GitLicenseEntry)) {
      return;
    }

    Views.disableClick(view, animDuration);
    license.setText(data.isLoaded() ? data.mLicense.mText : "Loading...");
    if (isOpened) {
      hide();
    } else {
      show();
    }
  }

  boolean onLongClickChild(View view) {
    Views.disableClick(view, animDuration);
    if (isOpened) {
      hide();
    } else {
      show();
    }
    return true;
  }

  public void setData(BaseLicenseEntry data) {
    this.data = data;
    library.setText(data.mLibraryName);
    author.setText(data.mLibraryAuthor);
    link.setText(data.mLibraryLink);
    link.setFocusable(false);
    link.setFocusableInTouchMode(false);
    type.setText(data.mLicense.mName);

    if (data instanceof GitLicenseEntry) {
      arrow.setVisibility(VISIBLE);
    } else {
      arrow.setVisibility(INVISIBLE);
    }

    hideNow();
  }

  private void init() {
    inflate(getContext(), R.layout.license, this);

    child = Views.byId(this, R.id.child);
    group = Views.byId(this, R.id.group);
    library = Views.byId(this, R.id.library);
    author = Views.byId(this, R.id.author);
    link = Views.byId(this, R.id.link);
    type = Views.byId(this, R.id.licenseType);
    license = Views.byId(this, R.id.license);
    arrow = Views.byId(this, R.id.arrow);

    group.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        onClickGroup(v);
      }
    });

    child.setOnLongClickListener(new OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        return onLongClickChild(v);
      }
    });
  }


  public void show() {
    if (!isAnimationRunning) {
      expand();
      isAnimationRunning = true;
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          isAnimationRunning = false;
          arrow.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }
      }, animDuration);
    }
  }

  public void hide() {
    if (!isAnimationRunning) {
      collapse();
      isAnimationRunning = true;
      handler.postDelayed(new Runnable() {
        @Override
        public void run() {
          isAnimationRunning = false;
          arrow.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
      }, animDuration);
    }
  }

  public void hideNow() {
    child.getLayoutParams().height = 0;
    child.invalidate();
    child.setVisibility(GONE);
    isOpened = false;
    arrow.setImageResource(R.drawable.ic_expand_more_black_24dp);
  }

  private void expand() {
    isOpened = true;
    child.measure(-1, -2);
    final int targetHeight = child.getMeasuredHeight();
    child.getLayoutParams().height = 0;
    child.setVisibility(VISIBLE);
    Animation animation = new Animation() {
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        child.getLayoutParams().height = interpolatedTime == 1.0F ? -2 : (int) ((float) targetHeight * interpolatedTime);
        child.requestLayout();
      }

      public boolean willChangeBounds() {
        return true;
      }
    };
    animation.setDuration(animDuration);
    child.startAnimation(animation);
  }

  private void collapse() {
    this.isOpened = false;
    final int initialHeight = child.getMeasuredHeight();
    Animation animation = new Animation() {
      protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (interpolatedTime == 1.0F) {
          child.setVisibility(GONE);
          isOpened = false;
        } else {
          child.getLayoutParams().height = initialHeight - (int) ((float) initialHeight * interpolatedTime);
          child.requestLayout();
        }
      }

      public boolean willChangeBounds() {
        return true;
      }
    };
    animation.setDuration(animDuration);
    child.startAnimation(animation);
  }
}
