package com.example.chatapp.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class EmojiPopupFooter extends FrameLayout {

    public int integerValue;

    public EmojiPopupFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getTopOffset() {
        return this.integerValue;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        offsetTopAndBottom(this.integerValue);
    }

    public void setTopOffset(int i) {
        offsetTopAndBottom(Math.max(Math.min(getHeight(), i), 0) - getTop());
        this.integerValue = getTop();
    }

}
