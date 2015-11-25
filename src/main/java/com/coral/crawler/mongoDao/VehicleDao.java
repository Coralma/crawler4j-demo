package com.coral.crawler.mongoDao;

import com.coral.crawler.mongoModel.Vehicle;
import com.coral.mongo.MBaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by CCC on 2015/11/24.
 */
@Repository(VehicleDao.SPRING_BEAN_NAME)
public class VehicleDao extends MBaseDao<Vehicle> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.VehicleDao";

    @Override
    public Class getEntityClass() {
        return Vehicle.class;
    }
}
