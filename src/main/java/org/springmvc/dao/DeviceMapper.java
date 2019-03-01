package org.springmvc.dao;

import org.springmvc.pojo.Device;

import java.util.List;

public interface DeviceMapper {

    int insert(Device record);

    int insertSelective(Device record);

    List<Device> getDeviceByExamItemCode(String examitemcode);
}