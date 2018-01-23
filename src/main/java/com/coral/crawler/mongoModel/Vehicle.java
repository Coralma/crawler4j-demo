package com.coral.crawler.mongoModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cccis.base.mongo.MBaseEntity;

/**
 * Created by CCC on 2015/11/24.
 */
@Document(collection="Vehicle")
public class Vehicle extends MBaseEntity {

    @Id
    private String vid;

    private String docId = "null";

    /** 基本参数 */
    //车型名称
    private String name= "null";
    //厂商指导价(元)
    private String price= "null";
    //厂商
    private String manufacturer= "null";
    //级别
    private String vehicleLevel= "null";
    // 上市时间（New）
    private String marketTime= "null";
    //发动机
    private String engine= "null";
    //变速箱
    private String gearBox= "null";
    //长*宽*高(mm) (size to vehicleSize)
    private String vehicleSize= "null";
    //车身结构
    private String structure= "null";
    //最高车速(km/h)
    private String speed= "null";
    //官方0-100km/h加速(s)
    private String speedUp= "null";
    //实测0-100km/h加速(s)
    private String testedSpeedUp= "null";
    //实测100-0km/h制动(m)
    private String testedBraking= "null";
    //实测油耗(L/100km)
    private String testedFuelConsumption= "null";
    //工信部综合油耗(L/100km)
    private String officalFuelConsumption= "null";
    //实测离地间隙(mm)
    private String groundClearance= "null";
    //整车质保
    private String warranty= "null";


    /**车身*/
    //长度(mm)
    private String vehicleLong= "null";
    //宽度(mm)
    private String vehicleWidth= "null";
    //高度(mm)
    private String vehicleTall= "null";
    //轴距(mm)
    private String wheelBase= "null";
    //前轮距(mm)
    private String frontTread= "null";
    //后轮距(mm)
    private String backTread= "null";
    //最小离地间隙(mm)
    private String minGroundClearance= "null";
    //整备质量(kg)
    private String quality= "null";
    //车身结构
    private String vehicleStyle= "null";
    //车门数(个)
    private String doorNum= "null";
    //座位数(个)
    private String seatNum= "null";
    //油箱容积(L)
    private String tankCapacity= "null";
    //行李厢容积(L)
    private String luggageCapacity= "null";

    /**发动机*/
    //发动机型号
    private String engineModel= "null";
    //排量(mL)
    private String displacement= "null";
    //进气形式
    private String intakeForm= "null";
    //气缸排列形式
    private String intakeType= "null";
    //气缸数(个)
    private String intakeNum= "null";
    //每缸气门数(个)
    private String eachIntakeNum= "null";
    //压缩比
    private String zipRate= "null";
    //配气机构
    private String intakeStructure= "null";
    //缸径(mm)
    private String intakeDiameter= "null";
    //行程(mm)
    private String route= "null";
    //最大马力(Ps)
    private String maxHorsepower= "null";
    //最大功率(kW)
    private String maxPower= "null";
    //最大功率转速(rpm)
    private String maxPowerRpm= "null";
    //最大扭矩(N·m)
    private String maxNm= "null";
    //最大扭矩转速(rpm)
    private String maxRpm= "null";
    //发动机特有技术
    private String engineTechnology= "null";
    //燃料形式
    private String fuelForm= "null";
    //燃油标号
    private String fuelNo= "null";
    //供油方式
    private String fuelFeedingType= "null";
    //缸盖材料
    private String intakeLidMaterial= "null";
    //缸体材料
    private String intakeMaterial= "null";
    //环保标准
    private String envStandards= "null";

    /**变速箱*/
    //变速箱简称
    private String gearBoxName= "null";
    //挡位个数
    private String gearNum= "null";
    //变速箱类型
    private String gearBoxType= "null";

    /** 底盘转向 */
    //驱动方式
    private String driveType= "null";
    //前悬架类型
    private String frontHangerType= "null";
    //后悬架类型
    private String backHangerType= "null";
    //助力类型
    private String assistanceType= "null";
    //车体结构
    private String bodyStructure= "null";

    /** 车轮制动 */
    //前制动器类型
    private String frontBreakingType= "null";
    //后制动器类型
    private String backBreakingType= "null";
    //驻车制动类型
    private String stopBreakingType= "null";
    //前轮胎规格
    private String frontTyreType= "null";
    //后轮胎规格
    private String backTyreType= "null";
    //备胎规格
    private String spareTireType= "null";


    /** 安全装备 */
    //主/副驾驶座安全气囊
    private String airBag= "null";
    private String mainAirBag= "null";
    private String copilotAirBag= "null";
    //前/后排侧气囊
    private String frontBackAirBag= "null";
    private String frontAirBag= "null";
    private String backAirBag= "null";
    //前/后排头部气囊(气帘)
    private String frontBackGasCurtain= "null";
    private String frontGasCurtain= "null";
    private String backGasCurtain= "null";
    //膝部气囊
    private String kneeAirBag= "null";
    //胎压监测装置
    private String tirePressureMonitoring= "null";
    //零胎压继续行驶
    private String zeroTirePressureDrive= "null";
    //安全带未系提示
    private String safetyBeltAlert= "null";
    //ISOFIX儿童座椅接口
    private String isofix= "null";
    //发动机电子防盗
    private String engineAgainstTheft= "null";
    //车内中控锁
    private String carLock= "null";
    //遥控钥匙
    private String remoteKey= "null";
    //无钥匙启动系统
    private String noKeyStart= "null";
    //无钥匙进入系统
    private String noKeyEntry= "null";

    /** 操控配置 */
    //ABS防抱死
    private String ABS= "null";
    //制动力分配(EBD/CBC等)
    private String EBD= "null";
    //刹车辅助(EBA/BAS/BA等)
    private String EBA= "null";
    //牵引力控制(ASR/TCS/TRC等)
    private String ASR= "null";
    //车身稳定控制(ESC/ESP/DSC等)
    private String ESC= "null";
    //上坡辅助
    private String HSA= "null";
    //自动驻车
    private String autoStop= "null";
    //陡坡缓降
    private String steepSlopeSlow= "null";
    //可变悬架
    private String changeableHanger= "null";
    //空气悬架
    private String airHanger= "null";
    //可变转向比
    private String changeableTurn= "null";
    //前桥限滑差速器/差速锁
    private String frontSpeedLock= "null";
    //中央差速器锁止功能
    private String coreSpeedLock= "null";
    //后桥限滑差速器/差速锁
    private String backSpeedLock= "null";

    /** 外部配置 */
    //电动天窗
    private String powerSunroof= "null";
    //全景天窗
    private String panoramicSunroof= "null";
    //运动外观套件
    private String sportAppearancePackage= "null";
    //铝合金轮圈
    private String aluminumRim= "null";
    //电动吸合门
    private String electricDoor= "null";
    //侧滑门
    private String slidingDoors= "null";
    //电动后备厢
    private String electricTrunk= "null";
    //感应后备厢
    private String inductionTrunk= "null";
    //车顶行李架
    private String roofRack= "null";

    /**内部配置*/
    //真皮方向盘
    private String leatherSteeringWheel= "null";
    //方向盘调节
    private String steeringWheelAdjustment= "null";
    //方向盘电动调节
    private String electricControl= "null";
    //多功能方向盘
    private String multifunctionSteeringWheel= "null";
    //方向盘换挡
    private String steeringWheelShift= "null";
    //方向盘加热
    private String steeringWheelHeating= "null";
    //方向盘记忆
    private String steeringWheelMemory= "null";
    //定速巡航
    private String cruise= "null";
    //前/后驻车雷达
    private String parkingRadar= "null";
    private String frontParkingRadar= "null";
    private String backParkingRadar= "null";
    //倒车视频影像
    private String reverseVideoImage= "null";
    //行车电脑显示屏
    private String drivingComputerDisplay= "null";
    //全液晶仪表盘
    private String fullLcdInstrumentPanel= "null";
    //HUD抬头数字显示
    private String HUD= "null";

    /** 座椅配置 */
    //座椅材质
    private String seatMaterial= "null";
    //运动风格座椅
    private String sportsStyleSeats= "null";
    //座椅高低调节
    private String seatHeightAdjustment= "null";
    //腰部支撑调节
    private String lumbarSupportAdjustment= "null";
    //肩部支撑调节
    private String shoulderSupportAdjustment= "null";
    //主/副驾驶座电动调节
    private String driverElectricControl= "null";
    private String mainDriverElectricControl= "null";
    private String copilotDriverElectricControl= "null";
    //第二排靠背角度调节
    private String secondRowBackrest= "null";
    //第二排座椅移动
    private String secondSeatMove= "null";
    //后排座椅电动调节
    private String rearSeatElectricAdjustment= "null";
    //电动座椅记忆
    private String electricChairMemory= "null";
    //前/后排座椅加热
    private String seatHeating= "null";
    private String frontSeatHeating= "null";
    private String backSeatHeating= "null";
    //前/后排座椅通风
    private String seatVentilation= "null";
    private String frontSeatVentilation= "null";
    private String backSeatVentilation= "null";
    //前/后排座椅按摩
    private String seatMassage= "null";
    private String frontSeatMassage= "null";
    private String backSeatMassage= "null";
    //第三排座椅
    private String thirdRowSeat= "null";
    //后排座椅放倒方式
    private String rearSeatsReclineWay= "null";
    //前/后中央扶手
    private String centerArmrest= "null";
    private String frontCenterArmrest= "null";
    private String backCenterArmrest= "null";
    //后排杯架
    private String backCupHolder= "null";

    /** 多媒体配置 */
    //GPS导航系统
    private String gps= "null";
    //定位互动服务
    private String locationService= "null";
    //中控台彩色大屏
    private String consoleColorScreen= "null";
    //蓝牙/车载电话
    private String bluetoothTelephone= "null";
    //车载电视
    private String carTV= "null";
    //后排液晶屏
    private String rearLCD= "null";
    //220V/230V电源
    private String powerSupply= "null";
    //外接音源接口
    private String externalAudioInterface= "null";
    //CD支持MP3/WMA
    private String cdSupportMp3= "null";
    //多媒体系统
    private String multimediaSystem= "null";
    //扬声器品牌
    private String loudspeakersBrand= "null";
    //扬声器数量
    private String loudspeakersNum= "null";

    /** 灯光配置 */
    //近光灯
    private String dippedHeadlight= "null";
    //远光灯
    private String highBeam= "null";
    //日间行车灯
    private String daytimeLights= "null";
    //自适应远近光
    private String adaptiveLights= "null";
    //自动头灯
    private String automaticHeadlights= "null";
    //转向辅助灯
    private String steeringAssistLamp= "null";
    //转向头灯
    private String steeringHeadlamp= "null";
    //前雾灯
    private String frontFogLamp= "null";
    //大灯高度可调
    private String headlightHeightAdjustable= "null";
    //大灯清洗装置
    private String cleaningDevice= "null";
    //车内氛围灯
    private String ambientLighting= "null";

    /** 玻璃/后视镜 */
    //前/后电动车窗
    private String powerWindow= "null";
    private String frontPowerWindow= "null";
    private String backPowerWindow= "null";
    //车窗防夹手功能
    private String antitrappingHandFunction= "null";
    //防紫外线/隔热玻璃
    private String uvGlass= "null";
    //后视镜电动调节
    private String rearviewMirrorAdjustment= "null";
    //后视镜加热
    private String rearviewMirrorHeating= "null";
    //内/外后视镜自动防眩目
    private String antiGlareRearviewMirror= "null";
    private String innerAntiGlareRearviewMirror= "null";
    private String outerAntiGlareRearviewMirror= "null";
    //后视镜电动折叠
    private String rearviewMirrorElectricFolding= "null";
    //后视镜记忆
    private String mirrorMemory= "null";
    //后风挡遮阳帘
    private String sunShade= "null";
    //后排侧遮阳帘
    private String rearSideCurtain= "null";
    //后排侧隐私玻璃
    private String rearSidePrivacyGlass= "null";
    //遮阳板化妆镜
    private String sunVisorMirrors= "null";
    //后雨刷
    private String rearWiper= "null";
    //感应雨刷
    private String wiper= "null";

    /** 空调/冰箱 */
    //空调控制方式
    private String airConditioningControlMode= "null";
    private String mTAirConditioningControlMode= "null";
    private String aTAirConditioningControlMode= "null";
    //后排独立空调
    private String rearIndependentAirConditioning= "null";
    //后座出风口
    private String rearAirOutlet= "null";
    //温度分区控制
    private String temperatureZoneControl= "null";
    //车内空气调节/花粉过滤
    private String carAirConditioning= "null";
    //车载冰箱
    private String carRefrigerator= "null";

    /**高科技配置 */
    //自动泊车入位
    private String automaticParking= "null";
    //发动机启停技术
    private String engineStartStopTechnology= "null";
    //并线辅助
    private String auxiliary= "null";
    //车道偏离预警系统
    private String laneDepartureWarningSystem= "null";
    //主动刹车/主动安全系统
    private String activeSafetySystem= "null";
    //整体主动转向系统
    private String integralActiveSteeringSystem= "null";
    //夜视系统
    private String nightVisionSystem= "null";
    //中控液晶屏分屏显示
    private String controlLCD= "null";
    //自适应巡航
    private String adaptiveCruise= "null";
    //全景摄像头
    private String panoramicCamera= "null";
    //外观颜色
    private String appearanceColor= "null";
    //内饰颜色
    private String interiorColor= "null";


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

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getTestedSpeedUp() {
        return testedSpeedUp;
    }

    public void setTestedSpeedUp(String testedSpeedUp) {
        this.testedSpeedUp = testedSpeedUp;
    }

    public String getTestedBraking() {
        return testedBraking;
    }

    public void setTestedBraking(String testedBraking) {
        this.testedBraking = testedBraking;
    }

    public String getTestedFuelConsumption() {
        return testedFuelConsumption;
    }

    public void setTestedFuelConsumption(String testedFuelConsumption) {
        this.testedFuelConsumption = testedFuelConsumption;
    }

    public String getOfficalFuelConsumption() {
        return officalFuelConsumption;
    }

    public void setOfficalFuelConsumption(String officalFuelConsumption) {
        this.officalFuelConsumption = officalFuelConsumption;
    }

    public String getGroundClearance() {
        return groundClearance;
    }

    public void setGroundClearance(String groundClearance) {
        this.groundClearance = groundClearance;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getVehicleLong() {
        return vehicleLong;
    }

    public void setVehicleLong(String vehicleLong) {
        this.vehicleLong = vehicleLong;
    }

    public String getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(String vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public String getVehicleTall() {
        return vehicleTall;
    }

    public void setVehicleTall(String vehicleTall) {
        this.vehicleTall = vehicleTall;
    }

    public String getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(String wheelBase) {
        this.wheelBase = wheelBase;
    }

    public String getFrontTread() {
        return frontTread;
    }

    public void setFrontTread(String frontTread) {
        this.frontTread = frontTread;
    }

    public String getBackTread() {
        return backTread;
    }

    public void setBackTread(String backTread) {
        this.backTread = backTread;
    }

    public String getMinGroundClearance() {
        return minGroundClearance;
    }

    public void setMinGroundClearance(String minGroundClearance) {
        this.minGroundClearance = minGroundClearance;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getVehicleStyle() {
        return vehicleStyle;
    }

    public void setVehicleStyle(String vehicleStyle) {
        this.vehicleStyle = vehicleStyle;
    }

    public String getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(String doorNum) {
        this.doorNum = doorNum;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(String tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public String getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(String luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public String getIntakeType() {
        return intakeType;
    }

    public void setIntakeType(String intakeType) {
        this.intakeType = intakeType;
    }

    public String getIntakeNum() {
        return intakeNum;
    }

    public void setIntakeNum(String intakeNum) {
        this.intakeNum = intakeNum;
    }

    public String getEachIntakeNum() {
        return eachIntakeNum;
    }

    public void setEachIntakeNum(String eachIntakeNum) {
        this.eachIntakeNum = eachIntakeNum;
    }

    public String getZipRate() {
        return zipRate;
    }

    public void setZipRate(String zipRate) {
        this.zipRate = zipRate;
    }

    public String getIntakeStructure() {
        return intakeStructure;
    }

    public void setIntakeStructure(String intakeStructure) {
        this.intakeStructure = intakeStructure;
    }

    public String getIntakeDiameter() {
        return intakeDiameter;
    }

    public void setIntakeDiameter(String intakeDiameter) {
        this.intakeDiameter = intakeDiameter;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMaxHorsepower() {
        return maxHorsepower;
    }

    public void setMaxHorsepower(String maxHorsepower) {
        this.maxHorsepower = maxHorsepower;
    }

    public String getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(String maxPower) {
        this.maxPower = maxPower;
    }

    public String getMaxPowerRpm() {
        return maxPowerRpm;
    }

    public void setMaxPowerRpm(String maxPowerRpm) {
        this.maxPowerRpm = maxPowerRpm;
    }

    public String getMaxNm() {
        return maxNm;
    }

    public void setMaxNm(String maxNm) {
        this.maxNm = maxNm;
    }

    public String getMaxRpm() {
        return maxRpm;
    }

    public void setMaxRpm(String maxRpm) {
        this.maxRpm = maxRpm;
    }

    public String getEngineTechnology() {
        return engineTechnology;
    }

    public void setEngineTechnology(String engineTechnology) {
        this.engineTechnology = engineTechnology;
    }

    public String getFuelForm() {
        return fuelForm;
    }

    public void setFuelForm(String fuelForm) {
        this.fuelForm = fuelForm;
    }

    public String getFuelNo() {
        return fuelNo;
    }

    public void setFuelNo(String fuelNo) {
        this.fuelNo = fuelNo;
    }

    public String getFuelFeedingType() {
        return fuelFeedingType;
    }

    public void setFuelFeedingType(String fuelFeedingType) {
        this.fuelFeedingType = fuelFeedingType;
    }

    public String getIntakeLidMaterial() {
        return intakeLidMaterial;
    }

    public void setIntakeLidMaterial(String intakeLidMaterial) {
        this.intakeLidMaterial = intakeLidMaterial;
    }

    public String getIntakeMaterial() {
        return intakeMaterial;
    }

    public void setIntakeMaterial(String intakeMaterial) {
        this.intakeMaterial = intakeMaterial;
    }

    public String getEnvStandards() {
        return envStandards;
    }

    public void setEnvStandards(String envStandards) {
        this.envStandards = envStandards;
    }

    public String getGearNum() {
        return gearNum;
    }

    public void setGearNum(String gearNum) {
        this.gearNum = gearNum;
    }

    public String getGearBoxType() {
        return gearBoxType;
    }

    public void setGearBoxType(String gearBoxType) {
        this.gearBoxType = gearBoxType;
    }

    public String getGearBoxName() {
        return gearBoxName;
    }

    public void setGearBoxName(String gearBoxName) {
        this.gearBoxName = gearBoxName;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getFrontHangerType() {
        return frontHangerType;
    }

    public void setFrontHangerType(String frontHangerType) {
        this.frontHangerType = frontHangerType;
    }

    public String getBackHangerType() {
        return backHangerType;
    }

    public void setBackHangerType(String backHangerType) {
        this.backHangerType = backHangerType;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getBodyStructure() {
        return bodyStructure;
    }

    public void setBodyStructure(String bodyStructure) {
        this.bodyStructure = bodyStructure;
    }

    public String getFrontBreakingType() {
        return frontBreakingType;
    }

    public void setFrontBreakingType(String frontBreakingType) {
        this.frontBreakingType = frontBreakingType;
    }

    public String getBackBreakingType() {
        return backBreakingType;
    }

    public void setBackBreakingType(String backBreakingType) {
        this.backBreakingType = backBreakingType;
    }

    public String getStopBreakingType() {
        return stopBreakingType;
    }

    public void setStopBreakingType(String stopBreakingType) {
        this.stopBreakingType = stopBreakingType;
    }

    public String getFrontTyreType() {
        return frontTyreType;
    }

    public void setFrontTyreType(String frontTyreType) {
        this.frontTyreType = frontTyreType;
    }

    public String getBackTyreType() {
        return backTyreType;
    }

    public void setBackTyreType(String backTyreType) {
        this.backTyreType = backTyreType;
    }

    public String getSpareTireType() {
        return spareTireType;
    }

    public void setSpareTireType(String spareTireType) {
        this.spareTireType = spareTireType;
    }

    public String getMainAirBag() {
        return mainAirBag;
    }

    public void setMainAirBag(String mainAirBag) {
        this.mainAirBag = mainAirBag;
    }

    public String getFrontBackAirBag() {
        return frontBackAirBag;
    }

    public void setFrontBackAirBag(String frontBackAirBag) {
        this.frontBackAirBag = frontBackAirBag;
    }

    public String getFrontBackGasCurtain() {
        return frontBackGasCurtain;
    }

    public void setFrontBackGasCurtain(String frontBackGasCurtain) {
        this.frontBackGasCurtain = frontBackGasCurtain;
    }

    public String getKneeAirBag() {
        return kneeAirBag;
    }

    public void setKneeAirBag(String kneeAirBag) {
        this.kneeAirBag = kneeAirBag;
    }

    public String getTirePressureMonitoring() {
        return tirePressureMonitoring;
    }

    public void setTirePressureMonitoring(String tirePressureMonitoring) {
        this.tirePressureMonitoring = tirePressureMonitoring;
    }

    public String getZeroTirePressureDrive() {
        return zeroTirePressureDrive;
    }

    public void setZeroTirePressureDrive(String zeroTirePressureDrive) {
        this.zeroTirePressureDrive = zeroTirePressureDrive;
    }

    public String getSafetyBeltAlert() {
        return safetyBeltAlert;
    }

    public void setSafetyBeltAlert(String safetyBeltAlert) {
        this.safetyBeltAlert = safetyBeltAlert;
    }

    public String getIsofix() {
        return isofix;
    }

    public void setIsofix(String isofix) {
        this.isofix = isofix;
    }

    public String getEngineAgainstTheft() {
        return engineAgainstTheft;
    }

    public void setEngineAgainstTheft(String engineAgainstTheft) {
        this.engineAgainstTheft = engineAgainstTheft;
    }

    public String getCarLock() {
        return carLock;
    }

    public void setCarLock(String carLock) {
        this.carLock = carLock;
    }

    public String getRemoteKey() {
        return remoteKey;
    }

    public void setRemoteKey(String remoteKey) {
        this.remoteKey = remoteKey;
    }

    public String getNoKeyStart() {
        return noKeyStart;
    }

    public void setNoKeyStart(String noKeyStart) {
        this.noKeyStart = noKeyStart;
    }

    public String getNoKeyEntry() {
        return noKeyEntry;
    }

    public void setNoKeyEntry(String noKeyEntry) {
        this.noKeyEntry = noKeyEntry;
    }

    public String getABS() {
        return ABS;
    }

    public void setABS(String ABS) {
        this.ABS = ABS;
    }

    public String getEBD() {
        return EBD;
    }

    public void setEBD(String EBD) {
        this.EBD = EBD;
    }

    public String getEBA() {
        return EBA;
    }

    public void setEBA(String EBA) {
        this.EBA = EBA;
    }

    public String getASR() {
        return ASR;
    }

    public void setASR(String ASR) {
        this.ASR = ASR;
    }

    public String getESC() {
        return ESC;
    }

    public void setESC(String ESC) {
        this.ESC = ESC;
    }

    public String getHSA() {
        return HSA;
    }

    public void setHSA(String HSA) {
        this.HSA = HSA;
    }

    public String getAutoStop() {
        return autoStop;
    }

    public void setAutoStop(String autoStop) {
        this.autoStop = autoStop;
    }

    public String getSteepSlopeSlow() {
        return steepSlopeSlow;
    }

    public void setSteepSlopeSlow(String steepSlopeSlow) {
        this.steepSlopeSlow = steepSlopeSlow;
    }

    public String getChangeableHanger() {
        return changeableHanger;
    }

    public void setChangeableHanger(String changeableHanger) {
        this.changeableHanger = changeableHanger;
    }

    public String getAirHanger() {
        return airHanger;
    }

    public void setAirHanger(String airHanger) {
        this.airHanger = airHanger;
    }

    public String getChangeableTurn() {
        return changeableTurn;
    }

    public void setChangeableTurn(String changeableTurn) {
        this.changeableTurn = changeableTurn;
    }

    public String getFrontSpeedLock() {
        return frontSpeedLock;
    }

    public void setFrontSpeedLock(String frontSpeedLock) {
        this.frontSpeedLock = frontSpeedLock;
    }

    public String getCoreSpeedLock() {
        return coreSpeedLock;
    }

    public void setCoreSpeedLock(String coreSpeedLock) {
        this.coreSpeedLock = coreSpeedLock;
    }

    public String getBackSpeedLock() {
        return backSpeedLock;
    }

    public void setBackSpeedLock(String backSpeedLock) {
        this.backSpeedLock = backSpeedLock;
    }

    public String getPowerSunroof() {
        return powerSunroof;
    }

    public void setPowerSunroof(String powerSunroof) {
        this.powerSunroof = powerSunroof;
    }

    public String getPanoramicSunroof() {
        return panoramicSunroof;
    }

    public void setPanoramicSunroof(String panoramicSunroof) {
        this.panoramicSunroof = panoramicSunroof;
    }

    public String getSportAppearancePackage() {
        return sportAppearancePackage;
    }

    public void setSportAppearancePackage(String sportAppearancePackage) {
        this.sportAppearancePackage = sportAppearancePackage;
    }

    public String getAluminumRim() {
        return aluminumRim;
    }

    public void setAluminumRim(String aluminumRim) {
        this.aluminumRim = aluminumRim;
    }

    public String getElectricDoor() {
        return electricDoor;
    }

    public void setElectricDoor(String electricDoor) {
        this.electricDoor = electricDoor;
    }

    public String getSlidingDoors() {
        return slidingDoors;
    }

    public void setSlidingDoors(String slidingDoors) {
        this.slidingDoors = slidingDoors;
    }

    public String getElectricTrunk() {
        return electricTrunk;
    }

    public void setElectricTrunk(String electricTrunk) {
        this.electricTrunk = electricTrunk;
    }

    public String getInductionTrunk() {
        return inductionTrunk;
    }

    public void setInductionTrunk(String inductionTrunk) {
        this.inductionTrunk = inductionTrunk;
    }

    public String getRoofRack() {
        return roofRack;
    }

    public void setRoofRack(String roofRack) {
        this.roofRack = roofRack;
    }

    public String getLeatherSteeringWheel() {
        return leatherSteeringWheel;
    }

    public void setLeatherSteeringWheel(String leatherSteeringWheel) {
        this.leatherSteeringWheel = leatherSteeringWheel;
    }

    public String getSteeringWheelAdjustment() {
        return steeringWheelAdjustment;
    }

    public void setSteeringWheelAdjustment(String steeringWheelAdjustment) {
        this.steeringWheelAdjustment = steeringWheelAdjustment;
    }

    public String getElectricControl() {
        return electricControl;
    }

    public void setElectricControl(String electricControl) {
        this.electricControl = electricControl;
    }

    public String getMultifunctionSteeringWheel() {
        return multifunctionSteeringWheel;
    }

    public void setMultifunctionSteeringWheel(String multifunctionSteeringWheel) {
        this.multifunctionSteeringWheel = multifunctionSteeringWheel;
    }

    public String getSteeringWheelShift() {
        return steeringWheelShift;
    }

    public void setSteeringWheelShift(String steeringWheelShift) {
        this.steeringWheelShift = steeringWheelShift;
    }

    public String getSteeringWheelHeating() {
        return steeringWheelHeating;
    }

    public void setSteeringWheelHeating(String steeringWheelHeating) {
        this.steeringWheelHeating = steeringWheelHeating;
    }

    public String getSteeringWheelMemory() {
        return steeringWheelMemory;
    }

    public void setSteeringWheelMemory(String steeringWheelMemory) {
        this.steeringWheelMemory = steeringWheelMemory;
    }

    public String getCruise() {
        return cruise;
    }

    public void setCruise(String cruise) {
        this.cruise = cruise;
    }

    public String getParkingRadar() {
        return parkingRadar;
    }

    public void setParkingRadar(String parkingRadar) {
        this.parkingRadar = parkingRadar;
    }

    public String getReverseVideoImage() {
        return reverseVideoImage;
    }

    public void setReverseVideoImage(String reverseVideoImage) {
        this.reverseVideoImage = reverseVideoImage;
    }

    public String getDrivingComputerDisplay() {
        return drivingComputerDisplay;
    }

    public void setDrivingComputerDisplay(String drivingComputerDisplay) {
        this.drivingComputerDisplay = drivingComputerDisplay;
    }

    public String getFullLcdInstrumentPanel() {
        return fullLcdInstrumentPanel;
    }

    public void setFullLcdInstrumentPanel(String fullLcdInstrumentPanel) {
        this.fullLcdInstrumentPanel = fullLcdInstrumentPanel;
    }

    public String getHUD() {
        return HUD;
    }

    public void setHUD(String HUD) {
        this.HUD = HUD;
    }

    public String getSeatMaterial() {
        return seatMaterial;
    }

    public void setSeatMaterial(String seatMaterial) {
        this.seatMaterial = seatMaterial;
    }

    public String getSportsStyleSeats() {
        return sportsStyleSeats;
    }

    public void setSportsStyleSeats(String sportsStyleSeats) {
        this.sportsStyleSeats = sportsStyleSeats;
    }

    public String getSeatHeightAdjustment() {
        return seatHeightAdjustment;
    }

    public void setSeatHeightAdjustment(String seatHeightAdjustment) {
        this.seatHeightAdjustment = seatHeightAdjustment;
    }

    public String getLumbarSupportAdjustment() {
        return lumbarSupportAdjustment;
    }

    public void setLumbarSupportAdjustment(String lumbarSupportAdjustment) {
        this.lumbarSupportAdjustment = lumbarSupportAdjustment;
    }

    public String getShoulderSupportAdjustment() {
        return shoulderSupportAdjustment;
    }

    public void setShoulderSupportAdjustment(String shoulderSupportAdjustment) {
        this.shoulderSupportAdjustment = shoulderSupportAdjustment;
    }

    public String getDriverElectricControl() {
        return driverElectricControl;
    }

    public void setDriverElectricControl(String driverElectricControl) {
        this.driverElectricControl = driverElectricControl;
    }

    public String getSecondRowBackrest() {
        return secondRowBackrest;
    }

    public void setSecondRowBackrest(String secondRowBackrest) {
        this.secondRowBackrest = secondRowBackrest;
    }

    public String getSecondSeatMove() {
        return secondSeatMove;
    }

    public void setSecondSeatMove(String secondSeatMove) {
        this.secondSeatMove = secondSeatMove;
    }

    public String getRearSeatElectricAdjustment() {
        return rearSeatElectricAdjustment;
    }

    public void setRearSeatElectricAdjustment(String rearSeatElectricAdjustment) {
        this.rearSeatElectricAdjustment = rearSeatElectricAdjustment;
    }

    public String getElectricChairMemory() {
        return electricChairMemory;
    }

    public void setElectricChairMemory(String electricChairMemory) {
        this.electricChairMemory = electricChairMemory;
    }

    public String getSeatHeating() {
        return seatHeating;
    }

    public void setSeatHeating(String seatHeating) {
        this.seatHeating = seatHeating;
    }

    public String getSeatVentilation() {
        return seatVentilation;
    }

    public void setSeatVentilation(String seatVentilation) {
        this.seatVentilation = seatVentilation;
    }

    public String getSeatMassage() {
        return seatMassage;
    }

    public void setSeatMassage(String seatMassage) {
        this.seatMassage = seatMassage;
    }

    public String getThirdRowSeat() {
        return thirdRowSeat;
    }

    public void setThirdRowSeat(String thirdRowSeat) {
        this.thirdRowSeat = thirdRowSeat;
    }

    public String getRearSeatsReclineWay() {
        return rearSeatsReclineWay;
    }

    public void setRearSeatsReclineWay(String rearSeatsReclineWay) {
        this.rearSeatsReclineWay = rearSeatsReclineWay;
    }

    public String getCenterArmrest() {
        return centerArmrest;
    }

    public void setCenterArmrest(String centerArmrest) {
        this.centerArmrest = centerArmrest;
    }

    public String getBackCupHolder() {
        return backCupHolder;
    }

    public void setBackCupHolder(String backCupHolder) {
        this.backCupHolder = backCupHolder;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getLocationService() {
        return locationService;
    }

    public void setLocationService(String locationService) {
        this.locationService = locationService;
    }

    public String getConsoleColorScreen() {
        return consoleColorScreen;
    }

    public void setConsoleColorScreen(String consoleColorScreen) {
        this.consoleColorScreen = consoleColorScreen;
    }

    public String getBluetoothTelephone() {
        return bluetoothTelephone;
    }

    public void setBluetoothTelephone(String bluetoothTelephone) {
        this.bluetoothTelephone = bluetoothTelephone;
    }

    public String getCarTV() {
        return carTV;
    }

    public void setCarTV(String carTV) {
        this.carTV = carTV;
    }

    public String getRearLCD() {
        return rearLCD;
    }

    public void setRearLCD(String rearLCD) {
        this.rearLCD = rearLCD;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public String getExternalAudioInterface() {
        return externalAudioInterface;
    }

    public void setExternalAudioInterface(String externalAudioInterface) {
        this.externalAudioInterface = externalAudioInterface;
    }

    public String getCdSupportMp3() {
        return cdSupportMp3;
    }

    public void setCdSupportMp3(String cdSupportMp3) {
        this.cdSupportMp3 = cdSupportMp3;
    }

    public String getMultimediaSystem() {
        return multimediaSystem;
    }

    public void setMultimediaSystem(String multimediaSystem) {
        this.multimediaSystem = multimediaSystem;
    }

    public String getLoudspeakersBrand() {
        return loudspeakersBrand;
    }

    public void setLoudspeakersBrand(String loudspeakersBrand) {
        this.loudspeakersBrand = loudspeakersBrand;
    }

    public String getLoudspeakersNum() {
        return loudspeakersNum;
    }

    public void setLoudspeakersNum(String loudspeakersNum) {
        this.loudspeakersNum = loudspeakersNum;
    }

    public String getDippedHeadlight() {
        return dippedHeadlight;
    }

    public void setDippedHeadlight(String dippedHeadlight) {
        this.dippedHeadlight = dippedHeadlight;
    }

    public String getHighBeam() {
        return highBeam;
    }

    public void setHighBeam(String highBeam) {
        this.highBeam = highBeam;
    }

    public String getDaytimeLights() {
        return daytimeLights;
    }

    public void setDaytimeLights(String daytimeLights) {
        this.daytimeLights = daytimeLights;
    }

    public String getAdaptiveLights() {
        return adaptiveLights;
    }

    public void setAdaptiveLights(String adaptiveLights) {
        this.adaptiveLights = adaptiveLights;
    }

    public String getAutomaticHeadlights() {
        return automaticHeadlights;
    }

    public void setAutomaticHeadlights(String automaticHeadlights) {
        this.automaticHeadlights = automaticHeadlights;
    }

    public String getSteeringAssistLamp() {
        return steeringAssistLamp;
    }

    public void setSteeringAssistLamp(String steeringAssistLamp) {
        this.steeringAssistLamp = steeringAssistLamp;
    }

    public String getSteeringHeadlamp() {
        return steeringHeadlamp;
    }

    public void setSteeringHeadlamp(String steeringHeadlamp) {
        this.steeringHeadlamp = steeringHeadlamp;
    }

    public String getFrontFogLamp() {
        return frontFogLamp;
    }

    public void setFrontFogLamp(String frontFogLamp) {
        this.frontFogLamp = frontFogLamp;
    }

    public String getHeadlightHeightAdjustable() {
        return headlightHeightAdjustable;
    }

    public void setHeadlightHeightAdjustable(String headlightHeightAdjustable) {
        this.headlightHeightAdjustable = headlightHeightAdjustable;
    }

    public String getCleaningDevice() {
        return cleaningDevice;
    }

    public void setCleaningDevice(String cleaningDevice) {
        this.cleaningDevice = cleaningDevice;
    }

    public String getAmbientLighting() {
        return ambientLighting;
    }

    public void setAmbientLighting(String ambientLighting) {
        this.ambientLighting = ambientLighting;
    }

    public String getPowerWindow() {
        return powerWindow;
    }

    public void setPowerWindow(String powerWindow) {
        this.powerWindow = powerWindow;
    }

    public String getAntitrappingHandFunction() {
        return antitrappingHandFunction;
    }

    public void setAntitrappingHandFunction(String antitrappingHandFunction) {
        this.antitrappingHandFunction = antitrappingHandFunction;
    }

    public String getUvGlass() {
        return uvGlass;
    }

    public void setUvGlass(String uvGlass) {
        this.uvGlass = uvGlass;
    }

    public String getRearviewMirrorHeating() {
        return rearviewMirrorHeating;
    }

    public void setRearviewMirrorHeating(String rearviewMirrorHeating) {
        this.rearviewMirrorHeating = rearviewMirrorHeating;
    }

    public String getAntiGlareRearviewMirror() {
        return antiGlareRearviewMirror;
    }

    public void setAntiGlareRearviewMirror(String antiGlareRearviewMirror) {
        this.antiGlareRearviewMirror = antiGlareRearviewMirror;
    }

    public String getRearviewMirrorElectricFolding() {
        return rearviewMirrorElectricFolding;
    }

    public void setRearviewMirrorElectricFolding(String rearviewMirrorElectricFolding) {
        this.rearviewMirrorElectricFolding = rearviewMirrorElectricFolding;
    }

    public String getMirrorMemory() {
        return mirrorMemory;
    }

    public void setMirrorMemory(String mirrorMemory) {
        this.mirrorMemory = mirrorMemory;
    }

    public String getSunShade() {
        return sunShade;
    }

    public void setSunShade(String sunShade) {
        this.sunShade = sunShade;
    }

    public String getRearSideCurtain() {
        return rearSideCurtain;
    }

    public void setRearSideCurtain(String rearSideCurtain) {
        this.rearSideCurtain = rearSideCurtain;
    }

    public String getRearSidePrivacyGlass() {
        return rearSidePrivacyGlass;
    }

    public void setRearSidePrivacyGlass(String rearSidePrivacyGlass) {
        this.rearSidePrivacyGlass = rearSidePrivacyGlass;
    }

    public String getSunVisorMirrors() {
        return sunVisorMirrors;
    }

    public void setSunVisorMirrors(String sunVisorMirrors) {
        this.sunVisorMirrors = sunVisorMirrors;
    }

    public String getRearWiper() {
        return rearWiper;
    }

    public void setRearWiper(String rearWiper) {
        this.rearWiper = rearWiper;
    }

    public String getWiper() {
        return wiper;
    }

    public void setWiper(String wiper) {
        this.wiper = wiper;
    }

    public String getAirConditioningControlMode() {
        return airConditioningControlMode;
    }

    public void setAirConditioningControlMode(String airConditioningControlMode) {
        this.airConditioningControlMode = airConditioningControlMode;
    }

    public String getRearIndependentAirConditioning() {
        return rearIndependentAirConditioning;
    }

    public void setRearIndependentAirConditioning(String rearIndependentAirConditioning) {
        this.rearIndependentAirConditioning = rearIndependentAirConditioning;
    }

    public String getRearAirOutlet() {
        return rearAirOutlet;
    }

    public void setRearAirOutlet(String rearAirOutlet) {
        this.rearAirOutlet = rearAirOutlet;
    }

    public String getTemperatureZoneControl() {
        return temperatureZoneControl;
    }

    public void setTemperatureZoneControl(String temperatureZoneControl) {
        this.temperatureZoneControl = temperatureZoneControl;
    }

    public String getCarAirConditioning() {
        return carAirConditioning;
    }

    public void setCarAirConditioning(String carAirConditioning) {
        this.carAirConditioning = carAirConditioning;
    }

    public String getCarRefrigerator() {
        return carRefrigerator;
    }

    public void setCarRefrigerator(String carRefrigerator) {
        this.carRefrigerator = carRefrigerator;
    }

    public String getAutomaticParking() {
        return automaticParking;
    }

    public void setAutomaticParking(String automaticParking) {
        this.automaticParking = automaticParking;
    }

    public String getEngineStartStopTechnology() {
        return engineStartStopTechnology;
    }

    public void setEngineStartStopTechnology(String engineStartStopTechnology) {
        this.engineStartStopTechnology = engineStartStopTechnology;
    }

    public String getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(String auxiliary) {
        this.auxiliary = auxiliary;
    }

    public String getLaneDepartureWarningSystem() {
        return laneDepartureWarningSystem;
    }

    public void setLaneDepartureWarningSystem(String laneDepartureWarningSystem) {
        this.laneDepartureWarningSystem = laneDepartureWarningSystem;
    }

    public String getActiveSafetySystem() {
        return activeSafetySystem;
    }

    public void setActiveSafetySystem(String activeSafetySystem) {
        this.activeSafetySystem = activeSafetySystem;
    }

    public String getIntegralActiveSteeringSystem() {
        return integralActiveSteeringSystem;
    }

    public void setIntegralActiveSteeringSystem(String integralActiveSteeringSystem) {
        this.integralActiveSteeringSystem = integralActiveSteeringSystem;
    }

    public String getNightVisionSystem() {
        return nightVisionSystem;
    }

    public void setNightVisionSystem(String nightVisionSystem) {
        this.nightVisionSystem = nightVisionSystem;
    }

    public String getControlLCD() {
        return controlLCD;
    }

    public void setControlLCD(String controlLCD) {
        this.controlLCD = controlLCD;
    }

    public String getAdaptiveCruise() {
        return adaptiveCruise;
    }

    public void setAdaptiveCruise(String adaptiveCruise) {
        this.adaptiveCruise = adaptiveCruise;
    }

    public String getPanoramicCamera() {
        return panoramicCamera;
    }

    public void setPanoramicCamera(String panoramicCamera) {
        this.panoramicCamera = panoramicCamera;
    }

    public String getAppearanceColor() {
        return appearanceColor;
    }

    public void setAppearanceColor(String appearanceColor) {
        this.appearanceColor = appearanceColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getAirBag() {
        return airBag;
    }

    public void setAirBag(String airBag) {
        this.airBag = airBag;
    }

    public String getCopilotAirBag() {
        return copilotAirBag;
    }

    public void setCopilotAirBag(String copilotAirBag) {
        this.copilotAirBag = copilotAirBag;
    }

    public String getFrontAirBag() {
        return frontAirBag;
    }

    public void setFrontAirBag(String frontAirBag) {
        this.frontAirBag = frontAirBag;
    }

    public String getBackAirBag() {
        return backAirBag;
    }

    public void setBackAirBag(String backAirBag) {
        this.backAirBag = backAirBag;
    }

    public String getFrontGasCurtain() {
        return frontGasCurtain;
    }

    public void setFrontGasCurtain(String frontGasCurtain) {
        this.frontGasCurtain = frontGasCurtain;
    }

    public String getBackGasCurtain() {
        return backGasCurtain;
    }

    public void setBackGasCurtain(String backGasCurtain) {
        this.backGasCurtain = backGasCurtain;
    }

    public String getFrontParkingRadar() {
        return frontParkingRadar;
    }

    public void setFrontParkingRadar(String frontParkingRadar) {
        this.frontParkingRadar = frontParkingRadar;
    }

    public String getBackParkingRadar() {
        return backParkingRadar;
    }

    public void setBackParkingRadar(String backParkingRadar) {
        this.backParkingRadar = backParkingRadar;
    }

    public String getMainDriverElectricControl() {
        return mainDriverElectricControl;
    }

    public void setMainDriverElectricControl(String mainDriverElectricControl) {
        this.mainDriverElectricControl = mainDriverElectricControl;
    }

    public String getCopilotDriverElectricControl() {
        return copilotDriverElectricControl;
    }

    public void setCopilotDriverElectricControl(String copilotDriverElectricControl) {
        this.copilotDriverElectricControl = copilotDriverElectricControl;
    }

    public String getFrontSeatHeating() {
        return frontSeatHeating;
    }

    public void setFrontSeatHeating(String frontSeatHeating) {
        this.frontSeatHeating = frontSeatHeating;
    }

    public String getBackSeatHeating() {
        return backSeatHeating;
    }

    public void setBackSeatHeating(String backSeatHeating) {
        this.backSeatHeating = backSeatHeating;
    }

    public String getFrontSeatVentilation() {
        return frontSeatVentilation;
    }

    public void setFrontSeatVentilation(String frontSeatVentilation) {
        this.frontSeatVentilation = frontSeatVentilation;
    }

    public String getBackSeatVentilation() {
        return backSeatVentilation;
    }

    public void setBackSeatVentilation(String backSeatVentilation) {
        this.backSeatVentilation = backSeatVentilation;
    }

    public String getFrontSeatMassage() {
        return frontSeatMassage;
    }

    public void setFrontSeatMassage(String frontSeatMassage) {
        this.frontSeatMassage = frontSeatMassage;
    }

    public String getBackSeatMassage() {
        return backSeatMassage;
    }

    public void setBackSeatMassage(String backSeatMassage) {
        this.backSeatMassage = backSeatMassage;
    }

    public String getFrontCenterArmrest() {
        return frontCenterArmrest;
    }

    public void setFrontCenterArmrest(String frontCenterArmrest) {
        this.frontCenterArmrest = frontCenterArmrest;
    }

    public String getBackCenterArmrest() {
        return backCenterArmrest;
    }

    public void setBackCenterArmrest(String backCenterArmrest) {
        this.backCenterArmrest = backCenterArmrest;
    }

    public String getFrontPowerWindow() {
        return frontPowerWindow;
    }

    public void setFrontPowerWindow(String frontPowerWindow) {
        this.frontPowerWindow = frontPowerWindow;
    }

    public String getBackPowerWindow() {
        return backPowerWindow;
    }

    public void setBackPowerWindow(String backPowerWindow) {
        this.backPowerWindow = backPowerWindow;
    }

    public String getmTAirConditioningControlMode() {
        return mTAirConditioningControlMode;
    }

    public void setmTAirConditioningControlMode(String mTAirConditioningControlMode) {
        this.mTAirConditioningControlMode = mTAirConditioningControlMode;
    }

    public String getaTAirConditioningControlMode() {
        return aTAirConditioningControlMode;
    }

    public void setaTAirConditioningControlMode(String aTAirConditioningControlMode) {
        this.aTAirConditioningControlMode = aTAirConditioningControlMode;
    }

    public String getInnerAntiGlareRearviewMirror() {
        return innerAntiGlareRearviewMirror;
    }

    public void setInnerAntiGlareRearviewMirror(String innerAntiGlareRearviewMirror) {
        this.innerAntiGlareRearviewMirror = innerAntiGlareRearviewMirror;
    }

    public String getOuterAntiGlareRearviewMirror() {
        return outerAntiGlareRearviewMirror;
    }

    public void setOuterAntiGlareRearviewMirror(String outerAntiGlareRearviewMirror) {
        this.outerAntiGlareRearviewMirror = outerAntiGlareRearviewMirror;
    }

    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }

    public String getVehicleLevel() {
        return vehicleLevel;
    }

    public void setVehicleLevel(String vehicleLevel) {
        this.vehicleLevel = vehicleLevel;
    }

    public String getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(String vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getRearviewMirrorAdjustment() {
        return rearviewMirrorAdjustment;
    }

    public void setRearviewMirrorAdjustment(String rearviewMirrorAdjustment) {
        this.rearviewMirrorAdjustment = rearviewMirrorAdjustment;
    }
}
