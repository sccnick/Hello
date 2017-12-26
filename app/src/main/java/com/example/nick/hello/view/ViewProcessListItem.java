package com.example.nick.hello.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.nick.hello.R;

/**
 * Created by Nick on 13/12/2560.
 */

public class ViewProcessListItem extends BaseCustomViewGroup{
    private TextView name;
    private TextView processId;
    private TextView memUse;
    private TextView cpuUse;

    public ViewProcessListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ViewProcessListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ViewProcessListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ViewProcessListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.layout_viewprocess, this);
    }

    private void initInstances() {
        // findViewById here
        name = (TextView) findViewById(R.id.process_name);
        processId = (TextView) findViewById(R.id.process_id);
        memUse = (TextView) findViewById(R.id.cpu_use);
        cpuUse = (TextView) findViewById(R.id.memmory_use);
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

    public void setProcessId(String processId) {
        this.processId.setText(processId);
    }

    public void setMemUse(String memUse) {
        this.memUse.setText(memUse);
    }

    public void setCpuUse(String cpuUse) {
        this.cpuUse.setText(cpuUse);
    }

}

