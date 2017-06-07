package com.coral.crawler.service;

import javax.annotation.Resource;
import java.util.List;

import com.coral.crawler.model.VehicleConfig;
import com.coral.crawler.model.VehicleConfigItem;
import com.coral.crawler.model.VehicleConfigParamItem;
import com.coral.crawler.model.VehicleConfigValueItem;
import com.coral.crawler.model.VehicleOption;
import com.coral.crawler.model.VehicleOptionItem;
import com.coral.crawler.model.VehicleOptionTypeItem;
import com.coral.crawler.model.VehicleOptionValueItem;
import com.coral.crawler.mongoDao.CrawlURLDao;
import com.coral.crawler.mongoDao.HistoryURLDao;
import com.coral.crawler.mongoDao.SaleURLDao;
import com.coral.crawler.mongoDao.VehicleDao;
import com.coral.crawler.mongoModel.CrawlURL;
import com.coral.crawler.mongoModel.HistoryURL;
import com.coral.crawler.mongoModel.SaleURL;
import com.coral.crawler.mongoModel.Vehicle;
import com.google.gson.Gson;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by CCC on 2016/12/28.
 */
@Service("AutoHomeParseService")
public class AutoHomeParseService {

    private Gson gson = new Gson();

    @Resource(name = VehicleDao.SPRING_BEAN_NAME)
    private VehicleDao dao;

    @Resource(name = CrawlURLDao.SPRING_BEAN_NAME)
    private CrawlURLDao crawlURLDao;

    @Resource(name = HistoryURLDao.SPRING_BEAN_NAME)
    private HistoryURLDao historyURLDao;

    @Resource(name = SaleURLDao.SPRING_BEAN_NAME)
    private SaleURLDao saleURLDao;

    private boolean isHistory = false;

    public AutoHomeParseService() {
        //initDao();
    }

    public List<CrawlURL> getNoRunCrawlURLs() {
        return crawlURLDao.getCrawlURLs();
    }

    public List<CrawlURL> getAllCrawlURLs() {
        return crawlURLDao.loadAll();
    }

    public boolean checkHistoryUrl(String url) {
        if (url.lastIndexOf("-") > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUrl(String url) {
        if (url.lastIndexOf("-") > 0) {
            return false;
        }
        return true;
    }

    public void saveVehicle(Vehicle vehicle) {
        dao.save(vehicle);
    }

    public boolean checkSameVehicle(String name) {
        return dao.checkSameVehicle(name);
    }

    public void saveHistory(HistoryURL historyURL) {
        historyURLDao.save(historyURL);
    }

    public void saveCrawlURL(CrawlURL crawlURL) {
        crawlURLDao.save(crawlURL);
    }

    public void saveSaleURLDao(SaleURL saleURL) {
        saleURLDao.save(saleURL);
    }

    public List<SaleURL> getAllSaleURLDao() {
        return saleURLDao.findAll();
    }


    public Vehicle[] parse(Page page) throws Exception {
        Vehicle[] vehicles = new Vehicle[0];
        String url = page.getWebURL().getURL();
        int docId = page.getWebURL().getDocid();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();

            // get json
            try {
                VehicleConfig vehicleConfig = buildVehicleConfig(html);

                int size = vehicleConfig.getResult().getParamtypeitems()[0].getParamitems()[0].getValueitems().length;
                vehicles = new Vehicle[size];
                for(int i=0;i<size;i++) {
                    vehicles[i] = new Vehicle();
                    vehicles[i].setDocId(String.valueOf(docId));
                }
                System.out.println("URL: " + url + " , cid: " + String.valueOf(docId));
                /*CrawlURL crawlURL = new CrawlURL();
                crawlURL.setUrl(url);
                crawlURL.setCid(String.valueOf(docId));
                crawlURL.setCreateDate(new Date());
                crawlURLDao.save(crawlURL);*/

                for(VehicleConfigItem item : vehicleConfig.getResult().getParamtypeitems()) {
                    String categoryName = item.getName();
                    for(VehicleConfigParamItem paramItem : item.getParamitems()) {
                        String itemName = convertName(paramItem.getName());
                        VehicleConfigValueItem[] valueItems = paramItem.getValueitems();
                        for(int i=0; i < valueItems.length;i++) {
                            Vehicle v = vehicles[i];
                            String value = valueItems[i].getValue();
                            /** 基本参数 */
                            if(itemName.equals("车型名称")) {
                                v.setName(convertValue(value));
                            } else if(itemName.equals("厂商指导价(元)")) {
                                v.setPrice(convertValue(value));
                            }else if(itemName.equals("厂商")) {
                                v.setManufacturer(convertValue(value));
                            }else if(itemName.equals("级别")) {
                                v.setLevel(convertValue(value));
                            }else if(itemName.equals("发动机")) {
                                v.setEngine(convertValue(value));
                            }else if(itemName.equals("变速箱")) {
                                v.setGearBox(convertValue(value));
                            }else if(itemName.equals("长*宽*高(mm)")) {
                                v.setSize(convertValue(value));
                            }else if(categoryName.equals("基本参数") && itemName.equals("车身结构")) {
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
                                /** 车身 */
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

                                /**发动机*/
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
                            }else if(itemName.equals("最大马力(Ps)")) {
                                v.setMaxHorsepower(convertValue(value));
                            }else if(itemName.equals("最大功率(kW)")) {
                                v.setMaxPower(convertValue(value));
                            }else if(itemName.equals("最大功率转速(rpm)")) {
                                v.setMaxPowerRpm(convertValue(value));
                            }else if(itemName.startsWith("最大扭矩(")) {
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
                                /** 变速箱 */
                            }else if(itemName.equals("简称")) {
                                v.setGearBoxName(convertValue(value));
                            }else if(itemName.equals("挡位个数")) {
                                v.setGearNum(convertValue(value));
                            }else if(itemName.equals("变速箱类型")) {
                                v.setGearBoxType(convertValue(value));

                                /** 底盘转向 */
                            }else if(itemName.equals("驱动方式")) {
                                v.setDriveType(convertValue(value));
                            }else if(itemName.equals("前悬架类型")) {
                                v.setFrontHangerType(convertValue(value));
                            }else if(itemName.equals("后悬架类型")) {
                                v.setBackHangerType(convertValue(value));
                            }else if(itemName.equals("助力类型")) {
                                v.setAssistanceType(convertValue(value));
                            }else if(itemName.equals("车体结构")) {
                                v.setBodyStructure(convertValue(value));

                                /** 车轮制动 */
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
                }

                VehicleOption vehicleOption = buildVehicleOption(html);
                for(VehicleOptionTypeItem item : vehicleOption.getResult().getConfigtypeitems()) {
                    String categoryName = item.getName();
                    VehicleOptionItem[] configitems = item.getConfigitems();
                    for(VehicleOptionItem configitem : configitems) {
                        String itemName = configitem.getName();
                        VehicleOptionValueItem[] valueItems = configitem.getValueitems();
                        for (int i = 0; i < valueItems.length; i++) {
                            Vehicle v = vehicles[i];
                            VehicleOptionValueItem valueItem = valueItems[i];
                            String value = valueItem.getValue();
                            //System.out.println(categoryName + ", " + itemName + ", " + value);
                            /** 安全装备 */
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
                            } else if(itemName.equals("ISOFIX儿童座椅接口")) {
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

                                /** 操控配置 */
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
                            } else if(itemName.equals("可变悬架")) {
                                v.setChangeableHanger(convertValue(value));
                            } else if(itemName.equals("空气悬架")) {
                                v.setAirHanger(convertValue(value));
                            } else if(itemName.equals("可变转向比")) {
                                v.setChangeableTurn(convertValue(value));
                            } else if(itemName.equals("前桥限滑差速器/差速锁")) {
                                v.setFrontSpeedLock(convertValue(value));
                            } else if(itemName.equals("中央差速器锁止功能")) {
                                v.setCoreSpeedLock(convertValue(value));
                            } else if(itemName.equals("后桥限滑差速器/差速锁")) {
                                v.setBackSpeedLock(convertValue(value));

                                /** 外部配置 */
                            } else if(itemName.equals("电动天窗")) {
                                v.setPowerSunroof(convertValue(value));
                            } else if(itemName.equals("全景天窗")) {
                                v.setPanoramicSunroof(convertValue(value));
                            } else if(itemName.equals("运动外观套件")) {
                                v.setSportAppearancePackage(convertValue(value));
                            } else if(itemName.equals("铝合金轮圈")) {
                                v.setAluminumRim(convertValue(value));
                            } else if(itemName.equals("电动吸合门")) {
                                v.setElectricDoor(convertValue(value));
                            } else if(itemName.equals("侧滑门")) {
                                v.setSlidingDoors(convertValue(value));
                            } else if(itemName.equals("电动后备厢")) {
                                v.setElectricTrunk(convertValue(value));
                            } else if(itemName.equals("感应后备厢")) {
                                v.setInductionTrunk(convertValue(value));
                            } else if(itemName.equals("车顶行李架")) {
                                v.setRoofRack(convertValue(value));
                                /**内部配置*/
                            } else if(itemName.equals("真皮方向盘")) {
                                v.setLeatherSteeringWheel(convertValue(value));
                            } else if(itemName.equals("方向盘调节")) {
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
                            } else if(itemName.equals("前/后驻车雷达")) {
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
                                /** 座椅配置 */
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
                            } else if(itemName.equals("前/后中央扶手")) {
                                v.setCenterArmrest(convertValue(value));
                                String[] rs = splitMultipleValue(value);
                                if(rs.length == 2) {
                                    v.setFrontCenterArmrest(rs[0]);
                                    v.setBackCenterArmrest(rs[1]);
                                }
                            } else if(itemName.equals("后排杯架")) {
                                v.setBackCupHolder(convertValue(value));

                                /** 多媒体配置 */
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
                            } else if(itemName.equals("外接音源接口")) {
                                v.setExternalAudioInterface(convertValue(value));
                            } else if(itemName.equals("CD支持MP3/WMA")) {
                                v.setCdSupportMp3(convertValue(value));
                            } else if(itemName.equals("多媒体系统")) {
                                v.setMultimediaSystem(convertValue(value));
                            } else if(itemName.equals("扬声器品牌")) {
                                v.setLoudspeakersBrand(convertValue(value));
                            } else if(itemName.equals("扬声器数量")) {
                                v.setLoudspeakersNum(convertValue(value));
                                /** 灯光配置 */
                            } else if(itemName.equals("近光灯")) {
                                v.setDippedHeadlight(convertValue(value));
                            } else if(itemName.equals("远光灯")) {
                                v.setHighBeam(convertValue(value));
                            } else if(itemName.equals("日间行车灯")) {
                                v.setDaytimeLights(convertValue(value));
                            } else if(itemName.equals("自适应远近光")) {
                                v.setAdaptiveLights(convertValue(value));
                            } else if(itemName.equals("自动头灯")) {
                                v.setAutomaticHeadlights(convertValue(value));
                            } else if(itemName.equals("转向辅助灯")) {
                                v.setSteeringAssistLamp(convertValue(value));
                            } else if(itemName.equals("转向头灯")) {
                                v.setSteeringHeadlamp(convertValue(value));
                            } else if(itemName.equals("前雾灯")) {
                                v.setFrontFogLamp(convertValue(value));
                            } else if(itemName.equals("大灯高度可调")) {
                                v.setHeadlightHeightAdjustable(convertValue(value));
                            } else if(itemName.equals("大灯清洗装置")) {
                                v.setCleaningDevice(convertValue(value));
                            } else if(itemName.equals("车内氛围灯")) {
                                v.setAmbientLighting(convertValue(value));

                                /** 玻璃/后视镜 */
                            } else if(itemName.equals("前/后电动车窗")) {
                                v.setPowerWindow(convertValue(value));
                                String[] rs = splitMultipleValue(value);
                                if(rs.length == 2) {
                                    v.setFrontPowerWindow(rs[0]);
                                    v.setBackPowerWindow(rs[1]);
                                }
                            } else if(itemName.equals("车窗防夹手功能")) {
                                v.setAntitrappingHandFunction(convertValue(value));
                            } else if(itemName.equals("防紫外线/隔热玻璃")) {
                                v.setUvGlass(convertValue(value));
                            } else if(itemName.equals("后视镜电动调节")) {
                                v.setRearviewMirrorElectricAdjustment(convertValue(value));
                            } else if(itemName.equals("后视镜加热")) {
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
                                /** 空调/冰箱 */
                            } else if(itemName.equals("空调控制方式")) {
                                v.setAirConditioningControlMode(convertValue(value));
                                String[] rs = splitMultipleValue(value);
                                if(rs.length == 2) {
                                    v.setmTAirConditioningControlMode(rs[0]);
                                    v.setaTAirConditioningControlMode(rs[1]);
                                } else if(rs.length ==0){
                                    if(value.startsWith("自动")) {
                                        v.setaTAirConditioningControlMode(convertValue(value.substring(2)));
                                        v.setmTAirConditioningControlMode("无");
                                    } else if(value.startsWith("手动")) {
                                        v.setmTAirConditioningControlMode(convertValue(value.substring(2)));
                                        v.setaTAirConditioningControlMode("无");
                                    }
                                }
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
                                /**高科技配置 */
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
                            } else if(itemName.equals("外观颜色")) {
                                v.setAppearanceColor(convertValue(value));
                            } else if(itemName.equals("内饰颜色")) {
                                v.setInteriorColor(convertValue(value));
                            }
                        }
                    }
                }
                /*// save to dao
                for(Vehicle v : vehicles) {
                    v.setCreateDate(new Date());
                    v.setLastModifyDate(new Date());
                    dao.save(v);
                }
                Thread.sleep(5000);*/
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("URL: " + url + " no data.");
                throw new RuntimeException("no Data");
            }
        }
        return vehicles;
    }

    private String convertName(String name) {
        if("<span class='hs_kw0_configpl'></span><span class='hs_kw1_configpl'></span>".equals(name)) {
            return "车型名称";
        }
        if("厂<span class='hs_kw7_configpl'></span><span class='hs_kw8_configpl'></span><span class='hs_kw9_configpl'></span><span class='hs_kw10_configpl'></span>(<span class='hs_kw11_configpl'></span>".equals(name)) {
            return "厂商指导价(元)";
        }
        name.replaceAll("<span class='hs_kw7_configpl'></span>","商");
        return name;
    }

    private String convertValue(String value) {
        value = cleanSpan(replayValue(value));
        if("-".equals(value)) {
            return "无";
        }
        if("●".equals(value)) {
            return "标配";
        }
        if("○".equals(value)) {
            return "选配";
        }
        return value;
    }

    private String replayValue(String value) {
        value = value.replaceAll("<span class='hs_kw22_configpl'></span>","年或");
        value = value.replaceAll("<span class='hs_kw12_configpl'></span>","万");
        return value;
    }

    private String cleanSpan(String value) {
        int spanStar = value.indexOf("<span");
        if(spanStar < 0) {
            return value;
        } else {
            String head = value.substring(0, spanStar);
            int spanEnd = value.indexOf("</span>") + 7; // 7 is the length of </span> string
            String other = value.substring(spanEnd);
            value = head + other;
            return cleanSpan(value);
        }
    }

    public String[] splitMultipleValue(String value) {
        if(value.indexOf("&nbsp;/&nbsp;") > 0) {
            String[] rs = value.split("&nbsp;/&nbsp;");
            rs[0] = convertValue(rs[0].substring(1));
            rs[1] = convertValue(rs[1].substring(1));
            return rs;
        }
        return new String[]{};
    }

    private VehicleConfig buildVehicleConfig(String html) {
        String startStr = "var config =";
        int jsonStart = html.lastIndexOf(startStr);
        if(jsonStart < 0) {
            throw new RuntimeException("No Data");
        }
        String configJson = html.substring(jsonStart);
        String endStr = "}]}]}]}};";
        int jsonEndIndex = configJson.indexOf(endStr);
        configJson = configJson.substring(startStr.length(),jsonEndIndex + (endStr.length()-1)).trim();
        /*System.out.println(configJson);*/
        VehicleConfig vehicleConfig = gson.fromJson(configJson, VehicleConfig.class);
        return vehicleConfig;
    }

    private VehicleOption buildVehicleOption(String html) {
        String starStr = "var option =";
        int jsonIndex = html.lastIndexOf(starStr);
        if(jsonIndex < 0) {
            throw new RuntimeException("No Option Data");
        }
        String optionJson = html.substring(jsonIndex);
        String endStr = "}]}]}]}};";
        int jsonEndIndex = optionJson.indexOf(endStr);
        optionJson = optionJson.substring(starStr.length(),jsonEndIndex + (endStr.length()-1)).trim();
        VehicleOption vehicleOption = gson.fromJson(optionJson,VehicleOption.class);
        return vehicleOption;
    }

    private void initDao() {
        String paths[] = { "META-INF/applicationContext.xml" };
        //这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        dao = (VehicleDao) ctx.getBean(VehicleDao.SPRING_BEAN_NAME);
        crawlURLDao = (CrawlURLDao) ctx.getBean(CrawlURLDao.SPRING_BEAN_NAME);
        historyURLDao = (HistoryURLDao) ctx.getBean(HistoryURLDao.SPRING_BEAN_NAME);
    }

}
