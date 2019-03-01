package org.springmvc.service;

import org.springframework.stereotype.Service;

/**
 * 业务接口：站在“使用者”角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */

public interface AnnoService {
    /**
     * @Description 根据标注的信息存入标注信息表中
     * @param patientId
     * @param studyInstanceUid
     * @param sopInstanceUid
     * @param x1Left
     * @param y1Left
     * @param x2Right
     * @param y2Right
     * @return
     */
  int insertAnnoInfo(String annoId, String patientId, String studyInstanceUid, String seriesInstanceUid, String sopInstanceUid, Float x1Left, Float y1Left, Float x2Right, Float y2Right);

  /**
     * @Description 根据ajax传过来的字符串数组返回一个包含标注框坐标信息的二维数组
     * @param array
     * @return
     */
  float[][] getCoordinateInfo(String[] array);

  /**
   * @Description 根据sopUid删除标注
   * @param sopUid
   * @return
   */
  int deleteAnnoInfo(String sopUid);


}
