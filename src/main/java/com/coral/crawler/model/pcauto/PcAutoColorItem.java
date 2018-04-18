package com.coral.crawler.model.pcauto;

/**
 * Created by Administrator on 4/18/2018.
 */
public class PcAutoColorItem {

    private PcAutoColorList[] ColorList;
    private PcAutoColorList[] innerColorList;

    public PcAutoColorList[] getColorList() {
        return ColorList;
    }

    public void setColorList(PcAutoColorList[] colorList) {
        ColorList = colorList;
    }

    public PcAutoColorList[] getInnerColorList() {
        return innerColorList;
    }

    public void setInnerColorList(PcAutoColorList[] innerColorList) {
        this.innerColorList = innerColorList;
    }
}
