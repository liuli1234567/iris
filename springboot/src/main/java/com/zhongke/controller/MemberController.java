package com.zhongke.controller;

import com.github.pagehelper.PageInfo;
import com.zhongke.entity.*;
import com.zhongke.pojo.Member;
import com.zhongke.pojo.MemberGrade;
import com.zhongke.pojo.Spu;
import com.zhongke.service.MemberService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName MemberController
 * @Description 会员
 * @Author liuli
 * @CreateDate 2020/4/4
 * @Version 2.1
 **/
@Api(value = "会员")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired(required = false)
    private MemberService memberService;

    /**
     * @Description 会员列表批量导入
     * @author liuli
     * @date 2020/5/16 11:19
     * @param excelFile
     * @return com.zhongke.entity.Result
     **/
    @RequestMapping("/import_member")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            if (list != null && list.size() > 0){
                List<Member> members = new ArrayList<>();
                for (String[] strings : list) {
                    Member member = new Member();
                    member.setNickName(strings[0]);
                    member.setTel(strings[1]);
                    member.setBirthday(strings[2]);
                    member.setSex(strings[3]);
                    member.setCardMoney(new BigDecimal(strings[4]));
                    member.setCardGral(Integer.parseInt(strings[5]));
                    members.add(member);
                }
                memberService.addAll(members);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(-1,"导入失败",e.getMessage());
        }
        return new Result(0,"批量导入成功");
    }

    @GetMapping("/excel/export")
    public Result export(@RequestParam(name = "tel",required = false) String tel, @RequestParam(name = "memNo",required = false) String memNo,
                         @RequestParam(name = "startTime",required = false) String startTime, @RequestParam(name = "endTime",required = false) String endTime
            , HttpServletResponse response){
        try {
            Member member = new Member();
            member.setTel(tel);
            member.setMemNo(memNo);
            member.setStartTime(startTime);
            member.setEndTime(endTime);
            List<Member> members = memberService.exportMembers(member);
            XSSFWorkbook wb = new XSSFWorkbook();
            //构造sheet
            Sheet sheet = wb.createSheet();
            //创建行
            //标题
            String[] titles = "昵称，电话，生日，性别，卡号，余额（元），积分，创建时间，会员等级".split("，");
            //处理标题
            Row row = sheet.createRow(0);
            int titleIndex = 0;
            for (String title : titles) {
                Cell cell = row.createCell(titleIndex++);
                cell.setCellValue(title);
            }
            int rowIndex = 1;
            Cell cell = null;
            for (Member member1 : members) {
                row = sheet.createRow(rowIndex++);
                //昵称
                cell = row.createCell(0);
                cell.setCellValue(member1.getNickName());
                //电话
                cell = row.createCell(1);
                cell.setCellValue(member1.getTel());
                //生日
                cell = row.createCell(2);
                cell.setCellValue(member1.getBirthday());
                //性别
                cell = row.createCell(3);
                cell.setCellValue(member1.getSex());
                //卡号
                cell = row.createCell(4);
                cell.setCellValue(member1.getMemNo());
                //余额（元）
                cell = row.createCell(5);
                cell.setCellValue(member1.getCardMoney().doubleValue());
                //积分
                cell = row.createCell(6);
                cell.setCellValue(member1.getCardGral());
                //创建时间
                cell = row.createCell(7);
                cell.setCellValue(DateUtil.dateToString(member1.getRegisterTime(),"yyyy-MM-dd HH:mm:ss"));
                //会员等级
                cell = row.createCell(8);
                cell.setCellValue(member1.getGradeName());
            }
            // 完成下载
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            new DownloadUtils().download(os,response,"会员列表.xlsx");
            return new Result(0,"导出成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1,"导出失败！");
        }
    }

    /**
     * @Description 根据条件查询并分页
     * @author liuli
     * @date 2020/4/6 11:17
     * @param member
     * @param page
     * @param size
     * @return com.zhongke.entity.Result<com.github.pagehelper.PageInfo>
     **/
    @PostMapping("/members/{page}/{size}")
    public Result<PageInfo> members(@RequestBody(required = false) Member member, @PathVariable int page, @PathVariable int size){
        try {
            PageInfo<Member> members = memberService.members(member,page,size);
            return new Result<>(0,"查询成功",members);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.memGrades(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }

    /**
     * @Description 查询会员基本信息
     * @author liuli
     * @date 2020/4/5 22:48
     * @param id
     * @return com.zhongke.entity.Result<com.zhongke.pojo.Member>
     **/
    @GetMapping("/findById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')and hasAuthority('add')")
    public Result<Member> findById(@PathVariable int id){
        try {
            Member member = memberService.findById(id);
            return new Result<Member>(0,"查询成功",member);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.findById(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @PostMapping("/update/{id}")
    public Result update(@RequestBody Member member,@PathVariable int id){
        try {
            member.setId(id);
            memberService.update(member);
            return new Result<>(0,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.update(): "+e.getMessage());
            return new Result<>(-1,"更新失败");
        }

    }

    /**
     * @Description 统计时间段的新增会员数和会员总数
     * @author liuli
     * @date 2020/4/6 11:17
     * @param startTime
     * @param endTime
     * @return com.zhongke.entity.Result
     **/
    @GetMapping("/analysis")
    public Result analysis(@RequestParam String startTime,@RequestParam String endTime){
        try {
            Map map = memberService.analysis(startTime+" 00:00:00",endTime+" 23:59:59");
            return new Result<>(0,"查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.analysis(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }

    }

    @GetMapping("/analysisByTime")
    public Result analysisByTime(@RequestParam String startTime, @RequestParam String endTime){
        try {
            Map map = memberService.analysisByTime(startTime,endTime);
            return new Result<>(0,"查询成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MemberController.analysisByTime(): "+e.getMessage());
            return new Result<>(-1,"查询失败");
        }
    }
}