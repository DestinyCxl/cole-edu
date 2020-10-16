package com.cole.service.edu.feign;

import com.cole.common.base.result.R;
import com.cole.service.edu.feign.fallback.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: Cxl
 * @since: 2020-09-11
 **/
@FeignClient(value = "service-oss",fallback = OssFileServiceFallBack.class)
@Service
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
    /**
     * 测试方法
     * @return
     */
    @GetMapping("/admin/oss/file/test")
    public R test();
}
