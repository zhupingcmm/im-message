package com.customer.hangzhou.controller;

import com.customer.hangzhou.controller.vo.req.HangzhouCustomerStaffCreateReqVO;
import com.customer.hangzhou.controller.vo.req.HangzhouCustomerStaffUpdateReqVO;
import com.customer.hangzhou.controller.vo.resp.HangzhouCustomerStaffRespVO;
import com.customer.hangzhou.converter.HangzhouCustomerStaffConverter;
import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import com.customer.hangzhou.service.HangzhouCustomerStaffService;
import com.ocbc.project.infrastructure.utils.DateUtils;
import com.ocbc.project.infrastructure.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/customerStaffs/hangzhou/")
public class HangzhouCustomerStaffController {

    private final HangzhouCustomerStaffService customerStaffService;

    @PostMapping("/")
    public Result<HangzhouCustomerStaffRespVO> addCustomerStaff(@RequestBody @Validated HangzhouCustomerStaffCreateReqVO customerStaffCreateReqVO) {
        HangzhouCustomerStaff customerStaff = HangzhouCustomerStaffConverter.INSTANCE.convertCreateReq(customerStaffCreateReqVO);
        customerStaff = customerStaffService.createCustomerStaff(customerStaff);
        return Result.success( HangzhouCustomerStaffConverter.INSTANCE.convertResp(customerStaff));
    }

    @PutMapping("/")
    public Result<HangzhouCustomerStaffRespVO> updateCustomerStaff(@RequestBody @Validated HangzhouCustomerStaffUpdateReqVO doctorUpdateReqVO) {
        HangzhouCustomerStaff customerStaff = HangzhouCustomerStaffConverter.INSTANCE.convertUpdateReq(doctorUpdateReqVO);
        return Result.success(HangzhouCustomerStaffConverter.INSTANCE.convertResp(customerStaffService.updateCustomerStaff(customerStaff)));
    }

    @DeleteMapping("/")
    public Result<HangzhouCustomerStaffRespVO> deleteCustomerStaff(@RequestParam ("id") Long id) {
        return Result.success(HangzhouCustomerStaffConverter.INSTANCE.convertResp(customerStaffService.deleteCustomerStaffById(id)));
    }

    @GetMapping("/")
    public Result<Page<HangzhouCustomerStaff>> getAllCustomerStaffs(@RequestParam(value = "nickname", required = false) String nickname,
                                                                    @RequestParam ("updatedTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date updatedTime,
                                                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                                                          @RequestParam(value = "size", defaultValue = "10") int size
                                                                          ) {
        Page<HangzhouCustomerStaff> customerStaffs = customerStaffService.findAllCustomerStaffs(nickname, updatedTime, PageRequest.of(page, size));

        return Result.success(customerStaffs);
    }

    @GetMapping("/updated")
    public Result<List<HangzhouCustomerStaffRespVO>> getCustomerStaffsByUpdatedTime(@RequestParam ("updatedTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date updatedTime) {

        List<HangzhouCustomerStaff> customerStaffs = customerStaffService.findCustomerStaffsByUpdatedTime(updatedTime);

        return Result.success(HangzhouCustomerStaffConverter.INSTANCE.convertListResp(customerStaffs));
    }


    @GetMapping("/updated/count")
    public Result<Long> getCustomerStaffsCountByUpdatedTime(@RequestParam ("updatedTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date updatedTime)  {
        List<HangzhouCustomerStaff> customerStaffs = customerStaffService.findCustomerStaffsByUpdatedTime(updatedTime);

        return Result.success((long) customerStaffs.size());
    }



}
