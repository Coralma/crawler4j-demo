package com.coral.crawler.model.pcauto;

/**
 * Created by ccc on 2018/1/12.
 */
public class PcAutoItem {

    private String Item;
    private PcAutoModelExcessId[] ModelExcessIds;
    private Integer ItemType;
    private String Name;

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public PcAutoModelExcessId[] getModelExcessIds() {
        return ModelExcessIds;
    }

    public void setModelExcessIds(PcAutoModelExcessId[] modelExcessIds) {
        ModelExcessIds = modelExcessIds;
    }

    public Integer getItemType() {
        return ItemType;
    }

    public void setItemType(Integer itemType) {
        ItemType = itemType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
