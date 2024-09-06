package com.tencent.effect.beautykit.model;

import android.text.TextUtils;
import android.util.ArrayMap;


import com.tencent.xmagic.XmagicConstant;

import com.tencent.xmagic.XmagicConstant.EffectName;
import java.util.List;
import java.util.Map;

public class TEUIProperty {



    public String displayName;
    public String displayNameEn;
    public String icon;
    public String resourceUri; // If this resource is locally integrated, configure the local resource path here. If it is downloaded from the network, configure the download address of the resource here.
    public String downloadPath; // For resources downloaded from the network, the local folder path where they are saved.

    public List<TEUIProperty> propertyList;
    public TESDKParam sdkParam;
    public List<TESDKParam> paramList;

    public TEUIProperty parentUIProperty;
    public UICategory uiCategory;
    public TEMotionDLModel dlModel = null;


    private int uiState = 0;



    public int getUiState() {
        return uiState;
    }

    public void setUiState(int uiState) {
        this.uiState = uiState;
    }

    public static class UIState {
        public static int CHECKED_AND_IN_USE = 2;
        public static int IN_USE = 1;
        public static int INIT = 0;
    }

    public enum UICategory {
        BEAUTY,
        BODY_BEAUTY,
        LUT,
        MOTION,
        MAKEUP,
        BEAUTY_TEMPLATE,
        SEGMENTATION,
    }


    public static class TESDKParam implements Cloneable {


        public static final String EXTRA_INFO_BG_TYPE_IMG = "0";
        public static final String EXTRA_INFO_BG_TYPE_VIDEO = "1";
        // The value of seg_type, if it is a custom segmentation, use: EXTRA_INFO_SEG_TYPE_CUSTOM, for green screen, use EXTRA_INFO_SEG_TYPE_GREEN.
        public static final String EXTRA_INFO_SEG_TYPE_GREEN = "green_background";
        public static final String EXTRA_INFO_SEG_TYPE_CUSTOM = "custom_background";

        public static final String EXTRA_INFO_KEY_BG_TYPE = "bgType";
        public static final String EXTRA_INFO_KEY_BG_PATH = "bgPath";
        public static final String EXTRA_INFO_KEY_SEG_TYPE = "segType";
        public static final String EXTRA_INFO_KEY_KEY_COLOR = "keyColor";
        public static final String EXTRA_INFO_KEY_MERGE_WITH_CURRENT_MOTION = "mergeWithCurrentMotion";

        public static final String EXTRA_INFO_KEY_LUT_STRENGTH = "makeupLutStrength";



        public String effectName;
        public int effectValue = 0;
        public String resourcePath;
        public Map<String, String> extraInfo;

        @Override
        public TESDKParam clone() throws CloneNotSupportedException {
            return (TESDKParam) super.clone();
        }

        @Override
        public String toString() {
            return "TEParam{" +
                    "effectName='" + effectName + '\'' +
                    ", effectValue=" + effectValue +
                    ", resourcePath='" + resourcePath + '\'' +
                    ", extraInfo=" + extraInfo +
                    '}';
        }



    }


    public  boolean isNoneItem() {
        return (this.sdkParam == null && propertyList == null && paramList == null);
    }


    public boolean isBeautyMakeupNoneItem() {
        if (this.sdkParam != null && TextUtils.isEmpty(this.sdkParam.resourcePath)) {
            switch (this.sdkParam.effectName) {
                case EffectName.BEAUTY_MOUTH_LIPSTICK:
                case EffectName.BEAUTY_FACE_SOFTLIGHT:
                case EffectName.BEAUTY_FACE_RED_CHEEK:
                case EffectName.BEAUTY_FACE_MAKEUP_EYE_SHADOW:
                case EffectName.BEAUTY_FACE_MAKEUP_EYE_LINER:
                case EffectName.BEAUTY_FACE_MAKEUP_EYELASH:
                case EffectName.BEAUTY_FACE_MAKEUP_EYE_SEQUINS:
                case EffectName.BEAUTY_FACE_MAKEUP_EYEBROW:
                case EffectName.BEAUTY_FACE_MAKEUP_EYEBALL:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }


    public enum EffectValueType {
        RANGE_0_0(0, 0),
        RANGE_0_POS100(0, 100),
        RANGE_NEG100_POS100(-100, 100);
        int min;
        int max;

        EffectValueType(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }


    private static final Map<String, EffectValueType> VALUE_TYPE_MAP = new ArrayMap<>();


    static {
        VALUE_TYPE_MAP.put(EffectName.EFFECT_MOTION, EffectValueType.RANGE_0_0);
        VALUE_TYPE_MAP.put(EffectName.EFFECT_SEGMENTATION, EffectValueType.RANGE_0_0);

        VALUE_TYPE_MAP.put(EffectName.BEAUTY_CONTRAST, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_SATURATION, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_IMAGE_WARMTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_IMAGE_TINT, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_IMAGE_BRIGHTNESS, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYE_DISTANCE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYE_ANGLE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYE_WIDTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYE_HEIGHT, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYE_POSITION, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_ANGLE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_DISTANCE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_HEIGHT, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_LENGTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_THICKNESS, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_EYEBROW_RIDGE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_NOSE_WING, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_NOSE_HEIGHT, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_NOSE_BRIDGE_WIDTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_NASION, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_MOUTH_SIZE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_MOUTH_HEIGHT, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_MOUTH_WIDTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_MOUTH_POSITION, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_SMILE_FACE, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_FACE_THIN_CHIN, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BEAUTY_FACE_FOREHEAD, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BODY_ENLARGE_CHEST_STRENGTH, EffectValueType.RANGE_NEG100_POS100);
        VALUE_TYPE_MAP.put(EffectName.BODY_SLIM_ARM_STRENGTH, EffectValueType.RANGE_NEG100_POS100);
    }

    public static EffectValueType getEffectValueType(TESDKParam teParam) {
        EffectValueType type = VALUE_TYPE_MAP.get(teParam.effectName);
        if (type != null) {
            return type;
        } else {
            return EffectValueType.RANGE_0_POS100;
        }
    }


}
