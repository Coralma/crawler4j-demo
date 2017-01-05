package com.coral.crawler.mongoDao;

import java.util.List;

import com.coral.crawler.mongoModel.Vehicle;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cccis.base.mongo.MBaseDao;

/**
 * Created by CCC on 2015/11/24.
 */
@Repository(VehicleDao.SPRING_BEAN_NAME)
public class VehicleDao extends MBaseDao<Vehicle> {

    public final static String SPRING_BEAN_NAME = "mongo.dao.VehicleDao";

    public boolean checkSameVehicle(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        List<Vehicle> vehicles = findByQuery(query);
        return vehicles.size() > 0;
    }

    @Override
    public Class getEntityClass() {
        return Vehicle.class;
    }
}
