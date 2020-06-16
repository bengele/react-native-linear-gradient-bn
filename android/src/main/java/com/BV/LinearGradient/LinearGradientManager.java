package com.BV.LinearGradient;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import android.view.View;

public class LinearGradientManager extends SimpleViewManager<LinearGradientView> {

    public static final String REACT_CLASS = "BVLinearGradient";
    public static final String PROP_COLORS = "colors";
    public static final String PROP_LOCATIONS = "locations";
    public static final String PROP_START_POS = "startPoint";
    public static final String PROP_END_POS = "endPoint";
    public static final String PROP_USE_ANGLE = "useAngle";
    public static final String PROP_ANGLE_CENTER = "angleCenter";
    public static final String PROP_ANGLE = "angle";
    public static final String PROP_BORDER_RADII = "borderRadii";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected LinearGradientView createViewInstance(ThemedReactContext context) {
        return new LinearGradientView(context);
    }

    @ReactProp(name = PROP_COLORS)
    public void setColors(LinearGradientView gradientView, ReadableArray colors) {
        int[] _colors = new int[colors.size()];
        for (int i = 0; i < _colors.length; i++) {
            _colors[i] = colors.getInt(i);
        }
        gradientView.setColors(_colors);
    }

    @ReactProp(name = PROP_LOCATIONS)
    public void setLocations(LinearGradientView gradientView, ReadableArray locations) {
        if (locations != null) {
            float[] _locations = new float[locations.size()];
            for (int i = 0; i < _locations.length; i++) {
                _locations[i] = (float) locations.getDouble(i);
            }
            gradientView.setLocations(_locations);
        }
    }

    @ReactProp(name = PROP_START_POS)
    public void setStartPosition(LinearGradientView gradientView, ReadableArray startPos) {
        float[] _startPos = new float[]{(float) startPos.getDouble(0), (float) startPos.getDouble(1)};
        gradientView.setStartPosition(_startPos);
    }

    @ReactProp(name = PROP_END_POS)
    public void setEndPosition(LinearGradientView gradientView, ReadableArray endPos) {
        float[] _endPos = new float[]{(float) endPos.getDouble(0), (float) endPos.getDouble(1)};
        gradientView.setEndPosition(_endPos);
    }

    @ReactProp(name = PROP_USE_ANGLE, defaultBoolean = false)
    public void setUseAngle(LinearGradientView gradientView, boolean useAngle) {
        gradientView.setUseAngle(useAngle);
    }

    @ReactProp(name = PROP_ANGLE_CENTER)
    public void setAngleCenter(LinearGradientView gradientView, ReadableArray in) {
        float[] _in = new float[]{(float) in.getDouble(0), (float) in.getDouble(1)};
        gradientView.setAngleCenter(_in);
    }

    @ReactProp(name = PROP_ANGLE, defaultFloat = 45.0f)
    public void setAngle(LinearGradientView gradientView, float angle) {
        gradientView.setAngle(angle);
    }

    // temporary solution until following issue is resolved:
    // https://github.com/facebook/react-native/issues/3198
    @ReactProp(name = PROP_BORDER_RADII)
    public void setBorderRadii(LinearGradientView gradientView, ReadableArray borderRadii) {
        float[] _radii = new float[borderRadii.size()];
        for (int i = 0; i < _radii.length; i++) {
            _radii[i] = PixelUtil.toPixelFromDIP((float) borderRadii.getDouble(i));
        }
        gradientView.setBorderRadii(_radii);
    }

    @ReactProp(name = "text")
    public void setTextContent(LinearGradientView gradientView, String text) {
        gradientView.setText(text);
    }

    @ReactProp(name = "textSize")
    public void setTextSize(LinearGradientView gradientView, int size) {
        gradientView.setTextSize(size);
    }
}
