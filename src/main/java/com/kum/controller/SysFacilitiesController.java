package com.kum.controller;

import com.alibaba.fastjson.JSONObject;
import com.kum.domain.AjaxResult;
import com.kum.domain.entity.SysFacilities;
import com.kum.service.SysFacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @version V1.0
 * @Package com.kum.controller
 * @auhter SunGuangJie
 * @date 2021/2/11-6:20 PM
 */

@RestController
@RequestMapping("/system/facilities")
public class SysFacilitiesController {

    @Autowired
    private SysFacilitiesService sysFacilitiesService;

    @GetMapping("/list")
    public AjaxResult getList(){
        return AjaxResult.success(sysFacilitiesService.list());
    }


    @PreAuthorize("@ps.hasPermi('system:facilities:save')")
    @PostMapping("/save")
    public AjaxResult saveFacilities(@RequestBody SysFacilities sysFacilities) {
        sysFacilitiesService.save(sysFacilities);
        return AjaxResult.success();
    }
    @PreAuthorize("@ps.hasPermi('system:facilities:delete')")
    @PostMapping("/delete")
    public AjaxResult deleteFacilities(@RequestBody JSONObject jsonObject) {
        if(sysFacilitiesService.delete(jsonObject.getString("id"))){
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }
}
