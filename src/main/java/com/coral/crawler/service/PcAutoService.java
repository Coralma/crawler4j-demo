package com.coral.crawler.service;

import com.coral.crawler.model.pcauto.*;
import com.coral.crawler.mongoDao.CrawlURLDao;
import com.coral.crawler.mongoDao.HistoryURLDao;
import com.coral.crawler.mongoDao.VehicleDao;
import com.coral.crawler.mongoModel.CrawlURL;
import com.coral.crawler.mongoModel.HistoryURL;
import com.coral.crawler.mongoModel.Vehicle;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

/**
 * Created by ccc on 2018/1/15.
 */
@Service(PcAutoService.SPRING_BEAN_NAME)
public class PcAutoService {

    public final static String SPRING_BEAN_NAME = "service.PcAutoService";

    private Gson gson = new Gson();

    @Resource(name=VehicleDao.SPRING_BEAN_NAME)
    private VehicleDao dao;

    @Resource(name=CrawlURLDao.SPRING_BEAN_NAME)
    private CrawlURLDao crawlURLDao;

    @Resource(name=HistoryURLDao.SPRING_BEAN_NAME)
    private HistoryURLDao historyURLDao;

    public void currentVehicle(String url,String html,Integer docId) {
        Vehicle[] vehicles = parse(url,html,docId);
        if(vehicles != null) {
            for (Vehicle v : vehicles) {
                v.setCreateDate(new Date());
                v.setLastModifyDate(new Date());
                dao.save(v);
            }
            // save to dao
            CrawlURL crawlURL = new CrawlURL();
            crawlURL.setUrl(url);
            crawlURL.setCid(String.valueOf(docId));
            crawlURL.setCreateDate(new Date());
            crawlURLDao.save(crawlURL);
            // save history
            saveHistoryURL(html, docId);
        }
    }

    public void historyVehicle(String url,String html,Integer docId) {
        // save to dao
        Vehicle[] vehicles = parse(url,html,docId);
        if(vehicles != null) {
            for (Vehicle v : vehicles) {
                v.setCreateDate(new Date());
                v.setLastModifyDate(new Date());
                dao.save(v);
            }
        }
    }

    public Vehicle[] parse(String url,String html,Integer docId) {
        Vehicle[] vehicles;
        // get json
        try {
            PcAutoConfig vehicleConfig = buildVehicleConfig(html);

            int size = vehicleConfig.getBody().getItems()[0].getModelExcessIds().length;
            vehicles = new Vehicle[size];
            for(int i=0;i<size;i++) {
                vehicles[i] = new Vehicle();
                vehicles[i].setDocId(String.valueOf(docId));
            }
            System.out.println("URL: " + url + " , cid: " + String.valueOf(docId));

            for(PcAutoItem item : vehicleConfig.getBody().getItems()) {
                String itemName = item.getName();
                PcAutoModelExcessId[] excessIds = item.getModelExcessIds();
                for(int i=0; i< excessIds.length;i++) {
                    Vehicle v = vehicles[i];
                    PcAutoModelExcessId excessId = excessIds[i];
                    String value = excessId.getValue();
                    //** 基本参数 *//
                    if(itemName.equals("车型名称")) {
                        v.setName(convertValue(value));
                        if(v.getName() != null) {
                            String[] nameSplit = v.getName().split(" ");
                            v.setSeries(nameSplit[0]);
                            if(nameSplit.length>1) {
                                v.setYear(nameSplit[1]);
                            }
                        }
                    } else if(itemName.equals("厂商指导价(元)")) {
                        v.setPrice(convertValue(value));
                    }else if(itemName.equals("厂商")) {
                        v.setManufacturer(convertValue(value));
                    }else if(itemName.equals("级别")) {
                        v.setVehicleLevel(convertValue(value));
                    }else if(itemName.equals("上市时间")) {
                        v.setMarketTime(convertValue(value));
                    }else if(itemName.equals("发动机")) {
                        v.setEngine(convertValue(value));
                    }else if(itemName.equals("变速箱")) {
                        v.setGearBox(convertValue(value));
                    }else if(itemName.equals("长×宽×高(mm)")) {
                        v.setVehicleSize(convertValue(value));
                    }else if(itemName.equals("车身类型")) {
                        v.setStructure(convertValue(value));
                    }else if(itemName.equals("最高车速(km/h)")) {
                        v.setSpeed(convertValue(value));
                    }else if(itemName.equals("官方0-100km/h加速(s)")) {
                        v.setSpeedUp(convertValue(value));
                    }else if(itemName.equals("实测0-100km/h加速(s)")) {
                        v.setTestedSpeedUp(convertValue(value));
                    }else if(itemName.equals("实测100-0km/h制动(m)")) {
                        v.setTestedBraking(convertValue(value));
                    }else if(itemName.equals("实测油耗(L/100km)")) {
                        v.setTestedFuelConsumption(convertValue(value));
                    }else if(itemName.equals("工信部综合油耗(L/100km)")) {
                        v.setOfficalFuelConsumption(convertValue(value));
                    }else if(itemName.equals("实测离地间隙(mm)")) {
                        v.setGroundClearance(convertValue(value));
                    }else if(itemName.equals("整车质保")) {
                        v.setWarranty(convertValue(value));
                        //** 车身 *//*
                    }else if(itemName.equals("长度(mm)")) {
                        v.setVehicleLong(convertValue(value));
                    }else if(itemName.equals("宽度(mm)")) {
                        v.setVehicleWidth(convertValue(value));
                    }else if(itemName.equals("高度(mm)")) {
                        v.setVehicleTall(convertValue(value));
                    }else if(itemName.equals("轴距(mm)")) {
                        v.setWheelBase(convertValue(value));
                    }else if(itemName.equals("前轮距(mm)")) {
                        v.setFrontTread(convertValue(value));
                    }else if(itemName.equals("后轮距(mm)")) {
                        v.setBackTread(convertValue(value));
                    }else if(itemName.equals("最小离地间隙(mm)")) {
                        v.setMinGroundClearance(convertValue(value));
                    }else if(itemName.equals("整备质量(kg)")) {
                        v.setQuality(convertValue(value));
                    }else if(itemName.equals("车身结构")) {
                        v.setVehicleStyle(convertValue(value));
                    }else if(itemName.equals("车门数(个)")) {
                        v.setDoorNum(convertValue(value));
                    }else if(itemName.equals("座位数(个)")) {
                        v.setSeatNum(convertValue(value));
                    }else if(itemName.equals("油箱容积(L)")) {
                        v.setTankCapacity(convertValue(value));
                    }else if(itemName.equals("行李厢容积(L)")) {
                        v.setLuggageCapacity(convertValue(value));

                        //**发动机*//*
                    }else if(itemName.equals("发动机型号")) {
                        v.setEngineModel(convertValue(value));
                    }else if(itemName.equals("排量(mL)")) {
                        v.setDisplacement(convertValue(value));
                    }else if(itemName.equals("进气形式")) {
                        v.setIntakeForm(convertValue(value));
                    }else if(itemName.equals("气缸排列形式")) {
                        v.setIntakeType(convertValue(value));
                    }else if(itemName.equals("气缸数(个)")) {
                        v.setIntakeNum(convertValue(value));
                    }else if(itemName.equals("每缸气门数(个)")) {
                        v.setEachIntakeNum(convertValue(value));
                    }else if(itemName.equals("压缩比")) {
                        v.setZipRate(convertValue(value));
                    }else if(itemName.equals("配气机构")) {
                        v.setIntakeStructure(convertValue(value));
                    }else if(itemName.equals("缸径(mm)")) {
                        v.setIntakeDiameter(convertValue(value));
                    }else if(itemName.equals("行程(mm)")) {
                        v.setRoute(convertValue(value));
                    }else if(itemName.equals("最大马力(PS)")) {
                        v.setMaxHorsepower(convertValue(value));
                    }else if(itemName.equals("最大功率(kW)")) {
                        v.setMaxPower(convertValue(value));
                    }else if(itemName.equals("最大功率转速(rpm)")) {
                        v.setMaxPowerRpm(convertValue(value));
                    }else if(itemName.equals("最大扭矩(N·m)")) {
                        v.setMaxNm(convertValue(value));
                    }else if(itemName.equals("最大扭矩转速(rpm)")) {
                        v.setMaxRpm(convertValue(value));
                    }else if(itemName.equals("发动机特有技术")) {
                        v.setEngineTechnology(convertValue(value));
                    }else if(itemName.equals("燃料形式")) {
                        v.setFuelForm(convertValue(value));
                    }else if(itemName.equals("燃油标号")) {
                        v.setFuelNo(convertValue(value));
                    }else if(itemName.equals("供油方式")) {
                        v.setFuelFeedingType(convertValue(value));
                    }else if(itemName.equals("缸盖材料")) {
                        v.setIntakeLidMaterial(convertValue(value));
                    }else if(itemName.equals("缸体材料")) {
                        v.setIntakeMaterial(convertValue(value));
                    }else if(itemName.equals("环保标准")) {
                        v.setEnvStandards(convertValue(value));
                        //** 变速箱 *//*
                    }else if(itemName.equals("变速箱")) {
                        v.setGearBoxName(convertValue(value));
                    }else if(itemName.equals("挡位个数")) {
                        v.setGearNum(convertValue(value));
                    }else if(itemName.equals("变速箱类型")) {
                        v.setGearBoxType(convertValue(value));

                        //** 底盘转向 *//*
                    }else if(itemName.equals("驱动方式")) {
                        v.setDriveType(convertValue(value));
                    }else if(itemName.equals("前悬挂类型")) {
                        v.setFrontHangerType(convertValue(value));
                    }else if(itemName.equals("后悬挂类型")) {
                        v.setBackHangerType(convertValue(value));
                    }else if(itemName.equals("转向助力类型")) {
                        v.setAssistanceType(convertValue(value));
                    }else if(itemName.equals("车体结构")) {
                        v.setBodyStructure(convertValue(value));
                        //** 车轮制动 *//*
                    }else if(itemName.equals("前制动器类型")) {
                        v.setFrontBreakingType(convertValue(value));
                    }else if(itemName.equals("后制动器类型")) {
                        v.setBackBreakingType(convertValue(value));
                    }else if(itemName.equals("驻车制动类型")) {
                        v.setStopBreakingType(convertValue(value));
                    }else if(itemName.equals("前轮胎规格")) {
                        v.setFrontTyreType(convertValue(value));
                    }else if(itemName.equals("后轮胎规格")) {
                        v.setBackTyreType(convertValue(value));
                    }else if(itemName.equals("备胎规格")) {
                        v.setSpareTireType(convertValue(value));
                    }
                }
            }

            PcAutoConfig vehicleOption = buildVehicleOption(html);
            for(PcAutoItem item : vehicleOption.getBody().getItems()) {
                String itemName = item.getName();
                PcAutoModelExcessId[] excessIds = item.getModelExcessIds();
                for(int i=0; i< excessIds.length;i++) {
                    Vehicle v = vehicles[i];
                    PcAutoModelExcessId excessId = excessIds[i];
                    String value = excessId.getValue();
                    //** 安全装备 *//*
                    if(itemName.equals("主/副驾驶座安全气囊")) {
                        v.setAirBag(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setMainAirBag(rs[0]);
                            v.setCopilotAirBag(rs[1]);
                        }
                    } else if(itemName.equals("前/后排侧气囊")) {
                        v.setFrontBackAirBag(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontAirBag(rs[0]);
                            v.setBackAirBag(rs[1]);
                        }
                    } else if(itemName.equals("前/后排头部气囊(气帘)")) {
                        v.setFrontBackGasCurtain(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontGasCurtain(rs[0]);
                            v.setBackGasCurtain(rs[1]);
                        }
                    } else if(itemName.equals("膝部气囊")) {
                        v.setKneeAirBag(convertValue(value));
                    } else if(itemName.equals("胎压监测装置")) {
                        v.setTirePressureMonitoring(convertValue(value));
                    } else if(itemName.equals("零胎压继续行驶")) {
                        v.setZeroTirePressureDrive(convertValue(value));
                    } else if(itemName.equals("安全带未系提示")) {
                        v.setSafetyBeltAlert(convertValue(value));
                    } else if(itemName.equals("ISO FIX儿童座椅接口")) {
                        v.setIsofix(convertValue(value));
                    } else if(itemName.equals("发动机电子防盗")) {
                        v.setEngineAgainstTheft(convertValue(value));
                    } else if(itemName.equals("车内中控锁")) {
                        v.setCarLock(convertValue(value));
                    } else if(itemName.equals("遥控钥匙")) {
                        v.setRemoteKey(convertValue(value));
                    } else if(itemName.equals("无钥匙启动系统")) {
                        v.setNoKeyStart(convertValue(value));
                    } else if(itemName.equals("无钥匙进入系统")) {
                        v.setNoKeyEntry(convertValue(value));

                        //** 操控配置 *//*
                    } else if(itemName.equals("ABS防抱死")) {
                        v.setABS(convertValue(value));
                    } else if(itemName.equals("制动力分配(EBD/CBC等)")) {
                        v.setEBD(convertValue(value));
                    } else if(itemName.equals("刹车辅助(EBA/BAS/BA等)")) {
                        v.setEBA(convertValue(value));
                    } else if(itemName.equals("牵引力控制(ASR/TCS/TRC等)")) {
                        v.setASR(convertValue(value));
                    } else if(itemName.equals("车身稳定控制(ESC/ESP/DSC等)")) {
                        v.setESC(convertValue(value));
                    } else if(itemName.equals("上坡辅助")) {
                        v.setHSA(convertValue(value));
                    } else if(itemName.equals("自动驻车")) {
                        v.setAutoStop(convertValue(value));
                    } else if(itemName.equals("陡坡缓降")) {
                        v.setSteepSlopeSlow(convertValue(value));
                    } else if(itemName.equals("可变悬挂")) {
                        v.setChangeableHanger(convertValue(value));
                    } else if(itemName.equals("空气悬挂")) {
                        v.setAirHanger(convertValue(value));
                    } else if(itemName.equals("可变转向比")) {
                        v.setChangeableTurn(convertValue(value));
                    } else if(itemName.equals("前桥限滑差速器/差速锁")) {
                        v.setFrontSpeedLock(convertValue(value));
                    } else if(itemName.equals("中央差速器锁止功能")) {
                        v.setCoreSpeedLock(convertValue(value));
                    } else if(itemName.equals("后桥限滑差速器/差速锁")) {
                        v.setBackSpeedLock(convertValue(value));

                        //** 外部配置 *//*
                    } else if(itemName.equals("电动天窗")) {
                        v.setPowerSunroof(convertValue(value));
                    } else if(itemName.equals("全景天窗")) {
                        v.setPanoramicSunroof(convertValue(value));
                    } else if(itemName.equals("运动外观套件")) {
                        v.setSportAppearancePackage(convertValue(value));
                    } else if(itemName.equals("铝合金轮毂")) {
                        v.setAluminumRim(convertValue(value));
                    } else if(itemName.equals("电动吸合门")) {
                        v.setElectricDoor(convertValue(value));
                    } else if(itemName.equals("侧滑门")) {
                        v.setSlidingDoors(convertValue(value));
                    } else if(itemName.equals("电动后备厢")) {
                        v.setElectricTrunk(convertValue(value));
                    } else if(itemName.equals("后备厢感应开启")) {
                        v.setInductionTrunk(convertValue(value));
                    } else if(itemName.equals("车顶行李架")) {
                        v.setRoofRack(convertValue(value));
                        //**内部配置*//*
                    } else if(itemName.equals("皮质方向盘")) {
                        v.setLeatherSteeringWheel(convertValue(value));
                    } else if(itemName.equals("方向盘调节范围")) {
                        v.setSteeringWheelAdjustment(convertValue(value));
                    } else if(itemName.equals("方向盘电动调节")) {
                        v.setElectricControl(convertValue(value));
                    } else if(itemName.equals("多功能方向盘")) {
                        v.setMultifunctionSteeringWheel(convertValue(value));
                    } else if(itemName.equals("方向盘换挡")) {
                        v.setSteeringWheelShift(convertValue(value));
                    } else if(itemName.equals("方向盘加热")) {
                        v.setSteeringWheelHeating(convertValue(value));
                    } else if(itemName.equals("方向盘记忆")) {
                        v.setSteeringWheelMemory(convertValue(value));
                    } else if(itemName.equals("定速巡航")) {
                        v.setCruise(convertValue(value));
                    } else if(itemName.equals("前/后雷达")) {
                        v.setParkingRadar(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontParkingRadar(rs[0]);
                            v.setBackParkingRadar(rs[1]);
                        }
                    } else if(itemName.equals("倒车视频影像")) {
                        v.setReverseVideoImage(convertValue(value));
                    } else if(itemName.equals("行车电脑显示屏")) {
                        v.setDrivingComputerDisplay(convertValue(value));
                    } else if(itemName.equals("全液晶仪表盘")) {
                        v.setFullLcdInstrumentPanel(convertValue(value));
                    } else if(itemName.equals("HUD抬头数字显示")) {
                        v.setHUD(convertValue(value));
                        //** 座椅配置 *//*
                    } else if(itemName.equals("座椅材质")) {
                        v.setSeatMaterial(convertValue(value));
                    } else if(itemName.equals("运动风格座椅")) {
                        v.setSportsStyleSeats(convertValue(value));
                    } else if(itemName.equals("座椅高低调节")) {
                        v.setSeatHeightAdjustment(convertValue(value));
                    } else if(itemName.equals("腰部支撑调节")) {
                        v.setLumbarSupportAdjustment(convertValue(value));
                    } else if(itemName.equals("肩部支撑调节")) {
                        v.setShoulderSupportAdjustment(convertValue(value));
                    } else if(itemName.equals("主/副驾驶座电动调节")) {
                        v.setDriverElectricControl(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setMainDriverElectricControl(rs[0]);
                            v.setCopilotDriverElectricControl(rs[1]);
                        }
                    } else if(itemName.equals("第二排靠背角度调节")) {
                        v.setSecondRowBackrest(convertValue(value));
                    } else if(itemName.equals("第二排座椅移动")) {
                        v.setSecondSeatMove(convertValue(value));
                    } else if(itemName.equals("后排座椅电动调节")) {
                        v.setRearSeatElectricAdjustment(convertValue(value));
                    } else if(itemName.equals("电动座椅记忆")) {
                        v.setElectricChairMemory(convertValue(value));
                    } else if(itemName.equals("前/后排座椅加热")) {
                        v.setSeatHeating(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontSeatHeating(rs[0]);
                            v.setBackSeatHeating(rs[1]);
                        }
                    } else if(itemName.equals("前/后排座椅通风")) {
                        v.setSeatVentilation(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontSeatVentilation(rs[0]);
                            v.setBackSeatVentilation(rs[1]);
                        }
                    } else if(itemName.equals("前/后排座椅按摩")) {
                        v.setSeatMassage(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontSeatMassage(rs[0]);
                            v.setBackSeatMassage(rs[1]);
                        }
                    } else if(itemName.equals("第三排座椅")) {
                        v.setThirdRowSeat(convertValue(value));
                    } else if(itemName.equals("后排座椅放倒方式")) {
                        v.setRearSeatsReclineWay(convertValue(value));
                    } else if(itemName.equals("前/后座中央扶手")) {
                        v.setCenterArmrest(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setFrontCenterArmrest(rs[0]);
                            v.setBackCenterArmrest(rs[1]);
                        }
                    } else if(itemName.equals("后排杯架")) {
                        v.setBackCupHolder(convertValue(value));

                        //** 多媒体配置 *//*
                    } else if(itemName.equals("GPS导航系统")) {
                        v.setGps(convertValue(value));
                    } else if(itemName.equals("定位互动服务")) {
                        v.setLocationService(convertValue(value));
                    } else if(itemName.equals("中控台彩色大屏")) {
                        v.setConsoleColorScreen(convertValue(value));
                    } else if(itemName.equals("蓝牙/车载电话")) {
                        v.setBluetoothTelephone(convertValue(value));
                    } else if(itemName.equals("车载电视")) {
                        v.setCarTV(convertValue(value));
                    } else if(itemName.equals("后排液晶屏")) {
                        v.setRearLCD(convertValue(value));
                    } else if(itemName.equals("220V/230V电源")) {
                        v.setPowerSupply(convertValue(value));
                    } else if(itemName.equals("外接音源接口(AUX/USB/iPod等)")) {
                        v.setExternalAudioInterface(convertValue(value));
                    } else if(itemName.equals("CD/DVD")) {
                        v.setCdSupportMp3(convertValue(value));
                    } else if(itemName.equals("多媒体系统")) {
                        v.setMultimediaSystem(convertValue(value));
                    } else if(itemName.equals("扬声器品牌")) {
                        v.setLoudspeakersBrand(convertValue(value));
                    } else if(itemName.equals("扬声器数量")) {
                        v.setLoudspeakersNum(convertValue(value));
                        //** 灯光配置 *//*
                    } else if(itemName.equals("近光灯")) {
                        v.setDippedHeadlight(convertValue(value));
                    } else if(itemName.equals("远光灯")) {
                        v.setHighBeam(convertValue(value));
                    } else if(itemName.equals("日间行车灯")) {
                        v.setDaytimeLights(convertValue(value));
                    } else if(itemName.equals("自适应远近光灯")) {
                        v.setAdaptiveLights(convertValue(value));
                    } else if(itemName.equals("自动头灯")) {
                        v.setAutomaticHeadlights(convertValue(value));
                    } else if(itemName.equals("转向辅助灯")) {
                        v.setSteeringAssistLamp(convertValue(value));
                    } else if(itemName.equals("随动转向大灯(AFS)")) {
                        v.setSteeringHeadlamp(convertValue(value));
                    } else if(itemName.equals("前雾灯")) {
                        v.setFrontFogLamp(convertValue(value));
                    } else if(itemName.equals("大灯高度可调")) {
                        v.setHeadlightHeightAdjustable(convertValue(value));
                    } else if(itemName.equals("大灯清洗装置")) {
                        v.setCleaningDevice(convertValue(value));
                    } else if(itemName.equals("车内氛围灯")) {
                        v.setAmbientLighting(convertValue(value));

                        //** 玻璃/后视镜 *//*
                    } else if(itemName.equals("前电动车窗")) {
                        v.setFrontPowerWindow(convertValue(value));
                    } else if(itemName.equals("后电动车窗")) {
                        v.setBackPowerWindow(convertValue(value));
                    } else if(itemName.equals("车窗防夹手功能")) {
                        v.setAntitrappingHandFunction(convertValue(value));
                    } else if(itemName.equals("防紫外线/隔热玻璃")) {
                        v.setUvGlass(convertValue(value));
                    } else if(itemName.equals("后视镜电动调节")) {
                        v.setRearviewMirrorAdjustment(convertValue(value));
                    } else if(itemName.equals("外后视镜加热")) {
                        v.setRearviewMirrorHeating(convertValue(value));
                    } else if(itemName.equals("内/外后视镜自动防眩目")) {
                        v.setAntiGlareRearviewMirror(convertValue(value));
                        String[] rs = splitMultipleValue(value);
                        if(rs.length == 2) {
                            v.setInnerAntiGlareRearviewMirror(rs[0]);
                            v.setOuterAntiGlareRearviewMirror(rs[1]);
                        }
                    } else if(itemName.equals("后视镜电动折叠")) {
                        v.setRearviewMirrorElectricFolding(convertValue(value));
                    } else if(itemName.equals("后视镜记忆")) {
                        v.setMirrorMemory(convertValue(value));
                    } else if(itemName.equals("后风挡遮阳帘")) {
                        v.setSunShade(convertValue(value));
                    } else if(itemName.equals("后排侧遮阳帘")) {
                        v.setRearSideCurtain(convertValue(value));
                    } else if(itemName.equals("后排侧隐私玻璃")) {
                        v.setRearSidePrivacyGlass(convertValue(value));
                    } else if(itemName.equals("遮阳板化妆镜")) {
                        v.setSunVisorMirrors(convertValue(value));
                    } else if(itemName.equals("后雨刷")) {
                        v.setRearWiper(convertValue(value));
                    } else if(itemName.equals("感应雨刷")) {
                        v.setWiper(convertValue(value));
                        //** 空调/冰箱 *//*
                    } else if(itemName.equals("空调调节方式")) {
                        v.setAirConditioningControlMode(convertValue(value));
                    } else if(itemName.equals("后排独立空调")) {
                        v.setRearIndependentAirConditioning(convertValue(value));
                    } else if(itemName.equals("后座出风口")) {
                        v.setRearAirOutlet(convertValue(value));
                    } else if(itemName.equals("温度分区控制")) {
                        v.setTemperatureZoneControl(convertValue(value));
                    } else if(itemName.equals("车内空气调节/花粉过滤")) {
                        v.setCarAirConditioning(convertValue(value));
                    } else if(itemName.equals("车载冰箱")) {
                        v.setCarRefrigerator(convertValue(value));
                        //**高科技配置 *//
                    } else if(itemName.equals("自动泊车入位")) {
                        v.setAutomaticParking(convertValue(value));
                    } else if(itemName.equals("发动机启停技术")) {
                        v.setEngineStartStopTechnology(convertValue(value));
                    } else if(itemName.equals("并线辅助")) {
                        v.setAuxiliary(convertValue(value));
                    } else if(itemName.equals("车道偏离预警系统")) {
                        v.setLaneDepartureWarningSystem(convertValue(value));
                    } else if(itemName.equals("主动刹车/主动安全系统")) {
                        v.setActiveSafetySystem(convertValue(value));
                    } else if(itemName.equals("整体主动转向系统")) {
                        v.setIntegralActiveSteeringSystem(convertValue(value));
                    } else if(itemName.equals("夜视系统")) {
                        v.setNightVisionSystem(convertValue(value));
                    } else if(itemName.equals("中控液晶屏分屏显示")) {
                        v.setControlLCD(convertValue(value));
                    } else if(itemName.equals("自适应巡航")) {
                        v.setAdaptiveCruise(convertValue(value));
                    } else if(itemName.equals("全景摄像头")) {
                        v.setPanoramicCamera(convertValue(value));
                    }
                }
            }

            PcAutoColor appearanceColor = buildAppearanceColor(html);
            if(appearanceColor != null) {
                PcAutoColorItem[] items = appearanceColor.getBody().getItems();
                for(int i=0;i<items.length;i++) {
                    String colorStr = "";
                    PcAutoColorItem item = items[i];
                    PcAutoColorList[] colorLists = item.getColorList();
                    int index = 0, colorIndex = colorLists.length - 1;
                    for(PcAutoColorList color : colorLists) {
                        if(index < colorIndex) {
                            colorStr = colorStr + color.getName() + ", ";
                        }
                        if(index == (colorIndex)) {
                            colorStr = colorStr + color.getName();
                        }
                        index++;
                    }
                    vehicles[i].setAppearanceColor(convertValue(colorStr));
                }
            }

            PcAutoColor innerColor = buildInnerColor(html);
            if(innerColor != null) {
                PcAutoColorItem[] items = innerColor.getBody().getItems();
                for(int i=0;i<items.length;i++) {
                    String colorStr = "";
                    PcAutoColorItem item = items[i];
                    PcAutoColorList[] colorLists = item.getInnerColorList();
                    int index = 0, colorIndex = colorLists.length - 1;
                    for(PcAutoColorList color : colorLists) {
                        if(index < colorIndex) {
                            colorStr = colorStr + color.getName() + ", ";
                        }
                        if(index == (colorIndex)) {
                            colorStr = colorStr + color.getName();
                        }
                        index++;
                    }
                    vehicles[i].setInteriorColor(convertValue(colorStr));
                }
            }
             /*else if(itemName.equals("外观颜色")) {
                v.setAppearanceColor(convertValue(value));
            } else if(itemName.equals("内饰颜色")) {
                v.setInteriorColor(convertValue(value));
            }*/
        } catch(Exception e) {
            System.out.println("URL: " + url + " no data.");
/*              e.printStackTrace();*/
            return null;
        }
        return vehicles;
    }

    private String convertValue(String value) {
        if("-".equals(value)) {
            return "无";
        }
        if("●".equals(value)) {
            return "标配";
        }
        if("○".equals(value)) {
            return "选配";
        }
        if("1".equals(value)) {
            return "标配";
        }
        if("2".equals(value)) {
            return "选配";
        }
        if(value.endsWith("%%-**-")) {
            return value.substring(0, value.length()- 6);
        }
        return value;
    }

    public String[] splitMultipleValue(String value) {
        if(value.indexOf("&nbsp;/&nbsp;") > 0) {
            String[] rs = value.split("&nbsp;/&nbsp;");
            rs[0] = convertValue(rs[0].substring(1));
            rs[1] = convertValue(rs[1].substring(1));
            return rs;
        }
        if(value.endsWith("%%-**-")) {
            String sd = value.substring(0, value.length()- 6);
            String[] rs = sd.split("@@");
            rs[0] = convertValue(rs[0].substring(1));
            rs[1] = convertValue(rs[1].substring(1));
            return rs;
        }
        return new String[]{};
    }

    private PcAutoConfig buildVehicleConfig(String html) {
        String startStr = "var config =";
        int jsonStart = html.lastIndexOf(startStr);
        if(jsonStart < 0) {
            throw new RuntimeException("No Data");
        }
        String configJson = html.substring(jsonStart);
        String endStr = "}] }};";
        int jsonEndIndex = configJson.indexOf(endStr);
        configJson = configJson.substring(startStr.length(),jsonEndIndex + (endStr.length()-1)).trim();
        /*System.out.println(configJson);*/
        JsonReader reader = new JsonReader(new StringReader(configJson));
        reader.setLenient(true);
        PcAutoConfig pcAutoConfig = gson.fromJson(reader, PcAutoConfig.class);
        return pcAutoConfig;
    }

    private PcAutoConfig buildVehicleOption(String html) {
        String starStr = "var option =";
        int jsonIndex = html.lastIndexOf(starStr);
        if(jsonIndex < 0) {
            throw new RuntimeException("No Option Data");
        }
        String optionJson = html.substring(jsonIndex);
        String endStr = "}] }};";
        int jsonEndIndex = optionJson.indexOf(endStr);
        optionJson = optionJson.substring(starStr.length(),jsonEndIndex + (endStr.length()-1)).trim();
        PcAutoConfig pcAutoConfig = gson.fromJson(optionJson,PcAutoConfig.class);
        return pcAutoConfig;
    }

    private PcAutoColor buildAppearanceColor(String html) {
        String starStr = "var color =";
        int jsonIndex = html.lastIndexOf(starStr);
        if(jsonIndex < 0) {
            return null;
        }
        String colorJson = html.substring(jsonIndex);
        String endStr = "}] }};";
        int jsonEndIndex = colorJson.indexOf(endStr);
        colorJson = colorJson.substring(starStr.length(),jsonEndIndex+(endStr.length()-1)).trim();
        PcAutoColor pcAutoAppearanceColor = gson.fromJson(colorJson, PcAutoColor.class);
        return pcAutoAppearanceColor;
    }

    private PcAutoColor buildInnerColor(String html) {
        String starStr = "var innerColor =";
        int jsonIndex = html.lastIndexOf(starStr);
        if(jsonIndex < 0) {
            return null;
        }
        String colorJson = html.substring(jsonIndex);
        String endStr = "}] }};";
        int jsonEndIndex = colorJson.indexOf(endStr);
        colorJson = colorJson.substring(starStr.length(),jsonEndIndex+(endStr.length()-1)).trim();
        PcAutoColor pcAutoAppearanceColor = gson.fromJson(colorJson, PcAutoColor.class);
        return pcAutoAppearanceColor;
    }

    private void saveHistoryURL(String html,int cid) {
        try {
            String data = "<div class=\"stopDrop\">";
            int jsonIndex = html.lastIndexOf(data);
            if(jsonIndex < 0) {
                throw new RuntimeException("No History Data");
            }
            String optionJson = html.substring(jsonIndex);
            String endStr = "</div>";
            int jsonEndIndex = optionJson.indexOf(endStr);
            optionJson = optionJson.substring(data.length(),jsonEndIndex + (endStr.length()-1)).trim();
            String[] historyHtmls = optionJson.split("\" target=\"_self\"");
            for(String historyHtml : historyHtmls) {
                String keyword = "<a href=";
                int index = historyHtml.indexOf(keyword);
                if(index > 0) {
                    String url = historyHtml.substring(historyHtml.indexOf(keyword) + keyword.length() + 1, historyHtml.length());
                    url = "http://price.pcauto.com.cn" + url;
                    HistoryURL historyURL = new HistoryURL();
                    historyURL.setUrl(url);
                    historyURL.setCid(String.valueOf(cid));
                    historyURL.setCreateDate(new Date());
                    historyURLDao.save(historyURL);
                    System.out.println(url);

                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(cid + " no any history data.");
        }
    }

    public List<HistoryURL> loadHistoryURL() {
        return historyURLDao.findAll();
    }

    private void initDao() {
        String paths[] = { "META-INF/applicationContext.xml" };
        //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        dao = (VehicleDao) ctx.getBean(VehicleDao.SPRING_BEAN_NAME);
        crawlURLDao = (CrawlURLDao) ctx.getBean(CrawlURLDao.SPRING_BEAN_NAME);
        historyURLDao = (HistoryURLDao)ctx.getBean(HistoryURLDao.SPRING_BEAN_NAME);
    }


    public static void main(String[] args) {
        PcAutoService service = new PcAutoService();
        String[] spv = service.splitMultipleValue("前1@@后1");
        System.out.println(spv);
    }
}
