package com.example.launcher;


import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

public abstract class JitterbugActivity extends AppCompatActivity {
    private static final int PRESS_INTERVAL = 150;
    private long mUpKeyUpEventTime = 0;
    private long mDownKeyDownEventTime = 0;
    private boolean mNopress = false;
    private boolean mSinglepress = false;
    private boolean mLongpress = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                    event.startTracking();
                if ((event.getEventTime() - mUpKeyUpEventTime) < PRESS_INTERVAL) {
                    onUpDoublePressed(event);
                }
                else {
                    if (event.getRepeatCount() == 0) {
                        onUpPressed(event);
                        mNopress = false;
                        mSinglepress = true;
                        mLongpress = false;
                    }
                    else if(!event.isCanceled() && mNopress==false ) {
                        onUpLongPressed(event);
                        mNopress = false;
                        mSinglepress = false;
                        mLongpress = false;
                    }
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                    event.startTracking();
                if ((event.getEventTime() - mDownKeyDownEventTime) < PRESS_INTERVAL) {
                    onDownDoublePressed(event);
                }
                else {
                    if (event.getRepeatCount() == 0) {
                        onDownPressed(event);
                        mNopress = false;
                        mSinglepress = true;
                        mLongpress = true;
                    }
                    else if(!event.isCanceled() && mNopress==false) {
                        onDownLongPressed(event);
                        mNopress = false;
                        mSinglepress = false;
                        mLongpress = true;
                    }
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN_LEFT:
                onLeftPressed(event);
                return true;
            case KeyEvent.KEYCODE_DPAD_DOWN_RIGHT:
                onRightPressed(event);
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                onActionPressed(event);
                return true;
            case KeyEvent.KEYCODE_MENU:
                onYesPressed(event);
                return true;
            case KeyEvent.KEYCODE_SEARCH:
                onNoPressed(event);
                return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {
        if((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0){
            if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
                mUpKeyUpEventTime = event.getEventTime();
                mNopress = true;
                mSinglepress = false;
                mLongpress = false;
                return true;
            }
            else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
                mDownKeyDownEventTime = event.getEventTime();
                mNopress = true;
                mSinglepress = false;
                mLongpress = false;
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress( int keyCode, KeyEvent event ) {
        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                    onUpLongPressed(event);
                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                    onDownLongPressed(event);
                return true;
        }

        return super.onKeyLongPress( keyCode, event );
    }

    protected void onNoPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onYesPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onActionPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onLeftPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onRightPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onDownPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onUpPressed(KeyEvent event) { super.onKeyDown(event.getKeyCode(), event); }

    protected void onDownLongPressed(KeyEvent event){ super.onKeyLongPress(event.getKeyCode(), event); }

    protected void onUpLongPressed(KeyEvent event) { super.onKeyLongPress(event.getKeyCode(), event); }

    protected void onDownDoublePressed(KeyEvent event){ super.onKeyDown(event.getKeyCode(), event); }

    protected void onUpDoublePressed(KeyEvent event){ super.onKeyDown(event.getKeyCode(), event); }
}