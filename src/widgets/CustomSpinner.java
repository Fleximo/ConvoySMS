package widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fleximo on 20.05.17.
 */

public class CustomSpinner extends Spinner {
    String mText = "sdfsdfsdfffffffffsdfsdfsdfsdf1111111111111\n1111111111111111111111111112222222222222222222222";
    TextPaint mTextPaint;
    StaticLayout mStaticLayout;

    HashMap<Long, Long> mListIdToViewIdMap = null;

    public CustomSpinner(Context context) {
        super(context);
        setWillNotDraw(false);
        initLabelView();
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        initLabelView();
    }

    public HashMap<Long, Long> GetListIdToViewIdMap() {
        return mListIdToViewIdMap;
    }

    public void SetListIdToViewIdMap(HashMap<Long, Long> listIdToViewIdMap) {
        mListIdToViewIdMap = listIdToViewIdMap;
    }

    @Override
    public int getSelectedItemPosition() {

        if(mListIdToViewIdMap != null)
            return mListIdToViewIdMap.get(Long.valueOf(super.getSelectedItemPosition())).intValue();

        return super.getSelectedItemPosition();
    }

    @Override
    public long getSelectedItemId() {
        if(mListIdToViewIdMap != null)
            return mListIdToViewIdMap.get(Long.valueOf(super.getSelectedItemPosition()));

        return super.getSelectedItemId();
    }

    @Override
    public Object getItemAtPosition(int position) {
        if(mListIdToViewIdMap != null)
            position = getKeyByValue(position);

        SpinnerAdapter adapter = getAdapter();
        return (adapter == null || position < 0) ? null : adapter.getItem(position);
    }

    public static Object genericInvokMethod(Object obj, String methodName,
                                            int paramCount, Object... params) {
        Method method;
        Object requiredObj = null;
        Object[] parameters = new Object[paramCount];
        Class<?>[] classArray = new Class<?>[paramCount];
        for (int i = 0; i < paramCount; i++) {
            parameters[i] = params[i];
            classArray[i] = params[i].getClass();
        }
        try {
            method = obj.getClass().getDeclaredMethod(methodName, classArray);
            method.setAccessible(true);
            requiredObj = method.invoke(obj, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return requiredObj;
    }

    @Override
    public void setSelection(int position) {
        if(mListIdToViewIdMap != null)
            position = getKeyByValue(position);

        super.setSelection(position);
    }

    private void initLabelView() {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        mTextPaint.setColor(0xFF000000);

        // default to a single line of text
        int width = (int) mTextPaint.measureText(mText);
        mStaticLayout = new StaticLayout(mText, mTextPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);

        // New API alternate
        //
//         StaticLayout.Builder builder = StaticLayout.Builder.obtain(mText, 0, mText.length(), mTextPaint, width)
//                .setAlignment(Layout.Alignment.ALIGN_NORMAL)
//                .setLineSpacing(1, 0) // multiplier, add
//                .setIncludePad(false);
//         mStaticLayout = builder.build();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Tell the parent layout how big this view would like to be
        // but still respect any requirements (measure specs) that are passed down.


        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                //Make or work out measurements for children here (MeasureSpec.make...)
                measureChild (child, widthMeasureSpec, heightMeasureSpec);
            }
        }

        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            width = mStaticLayout.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement;
                    // too long for a single line so relayout as multiline
                    mStaticLayout = new StaticLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
                }
            }
        }

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            height = mStaticLayout.getHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightRequirement);
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        //super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);

        // determine the width
        int width;
        int widthMode = MeasureSpec.getMode(parentWidthMeasureSpec);
        int widthRequirement = MeasureSpec.getSize(parentWidthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement;
        } else {
            width = mStaticLayout.getWidth() + getPaddingLeft() + getPaddingRight();
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement;
                    // too long for a single line so relayout as multiline
                    mStaticLayout = new StaticLayout(mText, mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
                }
            }
        }

        // determine the height
        int height;
        int heightMode = MeasureSpec.getMode(parentHeightMeasureSpec);
        int heightRequirement = MeasureSpec.getSize(parentHeightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement;
        } else {
            height = mStaticLayout.getHeight() + getPaddingTop() + getPaddingBottom();
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightRequirement);
            }
        }

        // Required call: set width and height
        setMeasuredDimension(width, height);
    }

    private int getKeyByValue(int value) {
        long key =  0;

        for(Map.Entry entry: mListIdToViewIdMap.entrySet()){
            if(value == (Long) entry.getValue()) {
                key = (Long) entry.getKey();
                break;
            }
        }

        return (int)key;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
//        mText =  getSelectedItem().toString();
//        mText.replace(' ', '\n');
//        canvas.save();
//        canvas.translate(getPaddingLeft(), getPaddingTop());
//        mStaticLayout  = new StaticLayout(mText, mTextPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
//        mStaticLayout.draw(canvas);
//
//        canvas.restore();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // do as little as possible inside onDraw to improve performance

        // draw the text on the canvas after adjusting for padding
//        canvas.save();
//        canvas.translate(getPaddingLeft(), getPaddingTop());
//        mStaticLayout.draw(canvas);
//        canvas.restore();
    }
}
