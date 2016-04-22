package net.yslibrary.licenseadapter;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

  private View child;
  private TextView library;
  private TextView author;
  private TextView link;
  private TextView type;
  private TextView license;
  private ImageView arrow;

  private int animDuration = DEF_ANIM_DURATION;
  private boolean isAnimationRunning;
  private boolean isOpened;
  private LicenseEntry data;

  public LicenseRowView(Context context) {
    this(context, null);
  }

  public LicenseRowView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LicenseRowView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  void onClickGroup(View view) {
    if (!data.hasContent()) {
      return;
    }

    Views.disableClick(view, animDuration);
    license.setText(data.isLoaded() ? data.license().text : "Loading...");
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

  public void setData(LicenseEntry data) {
    this.data = data;
    library.setText(data.name());
    author.setText(data.author());
    link.setText(data.link());
    link.setFocusable(false);
    link.setFocusableInTouchMode(false);
    type.setText(data.license().name);

    int visibility = VISIBLE;
    if (data.license().name == null || "".equals(data.license().name)) {
      visibility = GONE;
    }
    type.setVisibility(visibility);

    if (data.hasContent()) {
      arrow.setVisibility(VISIBLE);
    } else {
      arrow.setVisibility(INVISIBLE);
    }

    hideNow();
  }

  private void init() {
    View view = LayoutInflater.from(getContext()).inflate(R.layout.license, null);
    addView(view);

    child = Views.byId(view, R.id.child);
    View group = Views.byId(view, R.id.group);
    library = Views.byId(view, R.id.library);
    author = Views.byId(view, R.id.author);
    link = Views.byId(view, R.id.link);
    type = Views.byId(view, R.id.licenseType);
    license = Views.byId(view, R.id.license);
    arrow = Views.byId(view, R.id.arrow);

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
        child.getLayoutParams().height =
            interpolatedTime == 1.0F ? -2 : (int) ((float) targetHeight * interpolatedTime);
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
          child.getLayoutParams().height =
              initialHeight - (int) ((float) initialHeight * interpolatedTime);
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
