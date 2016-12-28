package com.coral.crawler.model;

/**
 * Created by CCC on 2015/11/19.
 */
public class VehicleOptionSeries {

    Integer seriesid;
    VehicleOptionTypeItem[] configtypeitems;

    public Integer getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    public VehicleOptionTypeItem[] getConfigtypeitems() {
        return configtypeitems;
    }

    public void setConfigtypeitems(VehicleOptionTypeItem[] configtypeitems) {
        this.configtypeitems = configtypeitems;
    }
}
