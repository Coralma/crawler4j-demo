package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleConfigSeries {

    Integer seriesid;

    VehicleConfigItem[] paramtypeitems;

    public Integer getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    public VehicleConfigItem[] getParamtypeitems() {
        return paramtypeitems;
    }

    public void setParamtypeitems(VehicleConfigItem[] paramtypeitems) {
        this.paramtypeitems = paramtypeitems;
    }
}
