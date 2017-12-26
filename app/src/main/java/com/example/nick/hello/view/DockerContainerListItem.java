package com.example.nick.hello.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nick.hello.R;
/**
 * Created by Nick on 8/12/2560.
 */

public class DockerContainerListItem extends BaseCustomViewGroup {

    private TextView name;
    private TextView image;
    private TextView status;
    private ImageView ivStatus;
    private ImageView ivControl;
    private ImageView ivRename;

    public DockerContainerListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public DockerContainerListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public DockerContainerListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public DockerContainerListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_container, this);
    }

    private void initInstances() {
        // findViewById here
        name = (TextView) findViewById(R.id.container_name);
        image = (TextView) findViewById(R.id.container_image);
//        status = (TextView) findViewById(R.id.container_status);
        ivStatus = (ImageView) findViewById(R.id.ivStateColor);
//        ivControl = (ImageView) findViewById(R.id.ivControl);
//        ivRename = (ImageView) findViewById(R.id.ivRename);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setImage(String image) {
        this.image.setText(image);
    }

//    public void setStatus(String status) {
//        this.status.setText(status);
//    }

    public void setIvStatus(int color) { this.ivStatus.setColorFilter(ContextCompat.getColor(getContext(),color));}

//    public ImageView getIvControl() {
//        return ivControl;
//    }
//
//    public ImageView getIvRename() {
//        return ivRename;
//    }

}
