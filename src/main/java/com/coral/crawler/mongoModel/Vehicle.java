package com.coral.crawler.mongoModel;

import com.coral.mongo.MBaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by CCC on 2015/11/24.
 */
@Document(collection="Vehicle")
public class Vehicle extends MBaseEntity {

    @Id
    private String vid;
    //车型名称
    private String name;
    //厂商指导价(元)
    private String price;
    //厂商
    private String manufacturer;
    //级别
    private String level;
    //发动机
    private String engine;
    //变速箱
    private String gearBox;
    //长*宽*高(mm)
    private String size;
    //最高车速(km/h)
    private String speed;
    //官方0-100km/h加速(s)
    private String speedUp;
    //发动机型号
    private String engineModel;
    //排量(mL)
    private String displacement;
    //进气形式
    private String intakeForm;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSpeedUp() {
        return speedUp;
    }

    public void setSpeedUp(String speedUp) {
        this.speedUp = speedUp;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getIntakeForm() {
        return intakeForm;
    }

    public void setIntakeForm(String intakeForm) {
        this.intakeForm = intakeForm;
    }
}
