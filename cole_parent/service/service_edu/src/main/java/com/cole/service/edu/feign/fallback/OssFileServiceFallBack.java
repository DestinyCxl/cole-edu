package com.cole.service.edu.feign.fallback;

import com.cole.common.base.result.R;
import com.cole.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Cxl
 * @since: 2020-09-13
 **/
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R removeFile(String url) {
        return R.error();
    }

    @Override
    public R test() {
        log.info("熔断保护");
        return R.error();
    }
}
