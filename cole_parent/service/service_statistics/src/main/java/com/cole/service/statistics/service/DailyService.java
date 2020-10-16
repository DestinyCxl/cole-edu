package com.cole.service.statistics.service;

import com.cole.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Cxl
 * @since 2020-10-14
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);

    Map<String,Map<String,Object>> getChartData(String begin, String end);
}
