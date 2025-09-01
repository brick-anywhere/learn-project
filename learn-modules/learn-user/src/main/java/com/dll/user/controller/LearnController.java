package com.dll.user.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dll.common.config.ExcelTitleConfig;
import com.dll.common.exception.BusinessException;
import com.dll.common.model.ResultData;
import com.dll.common.lock.ReturnMsg;
import com.dll.log.monitor.LogUtil;
import com.dll.user.dto.LearnDto;
import com.dll.user.dto.QueryLearnDto;
import com.dll.user.service.LearnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dll
 * @date 2025的点点滴滴-07-08 17:34
 */
@Api(value = "学习用户模块", tags = "学习用户模块")
@RestController
@RequestMapping(path = "/learn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LearnController {
    @Autowired
    private LearnService learnService;

    /**
     * 学习信息展示 带分页
     *
     * @param queryLearnDto
     * @return
     */
    @PostMapping(value = "/showLearnUserInfoPage")
    public ResultData<?> showLearnUserInfoPage(@RequestBody QueryLearnDto queryLearnDto) {
        try {
            System.out.println(queryLearnDto.toString());
            Assert.notNull(queryLearnDto.getPageNo(), "页数不得为空");
            Assert.notNull(queryLearnDto.getPageSize(), "每页信息不得为空");
            IPage<LearnDto> pageInfo = learnService.showLearnUserInfoPage(queryLearnDto);
            return ResultData.ok(pageInfo);
        } catch (BusinessException eB) {
            LogUtil.error("团队成绩查看 , 详情     业务异常:{}" + eB.getMessage());
            return ResultData.failed(eB.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("团队成绩查看 , 详情。{}", e.getMessage());
            return ResultData.failed(e.getMessage());
        }

    }


    /**
     * 学习信息增加
     *
     * @param learnDto
     * @return
     */
    @PostMapping(value = "/addLearnDto")
    public ResultData addLearnDto(@RequestBody LearnDto learnDto) {
        Assert.hasText(learnDto.getUserName(), "用户姓名不得为空");
        System.out.println(learnDto.toString());
        learnService.addLearnDto(learnDto);
        return ResultData.ok("");
    }

    /**
     * 学习信息  删除
     *
     * @param learnDto
     * @return
     */
    @PostMapping(value = "/deleteLearn")
    public ResultData deleteLearn(@RequestBody LearnDto learnDto) {
        System.out.println(learnDto.toString());
        learnService.deleteLearnById(learnDto);
        return ResultData.ok("");
    }


    /**
     * 学习信息  批量删除
     *
     * @param
     * @return
     */
    @PostMapping(value = "/batchDeleteLearn")
    public Object batchDeleteLearnByIds(@RequestBody QueryLearnDto queryLearnDto) {
        System.out.println(queryLearnDto.toString());
        learnService.batchDeleteLearnByIds(queryLearnDto);
        return ResultData.ok("");
    }


    /**
     * 下载报名的Excel模板.
     *
     * @param response
     */
    @RequestMapping(value = "template-down")
    @ApiOperation(value = "下载报名的Excel模板", httpMethod = "POST")
    public void templateDown(HttpServletResponse response, HttpServletRequest request) {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            //自定义标题别名
            writer.addHeaderAlias("userName", ExcelTitleConfig.LEARN_EXCEL_TITLE.get(0));
            writer.addHeaderAlias("userInfo", ExcelTitleConfig.LEARN_EXCEL_TITLE.get(1));
            writer.addHeaderAlias("lesson", ExcelTitleConfig.LEARN_EXCEL_TITLE.get(2));

            //  2020-05-30 dll  调整 每一列 宽度
            //     writer.setColumnWidth(X,Y);  X（列数） :  从0 开始  Y： 字节  （一个汉字约等于2.5 字节  一字节对应一位数字）
            writer.setColumnWidth(0, 10);//姓名
            writer.setColumnWidth(1, 20);//用户信息（非必填）
            writer.setColumnWidth(2, 21);//课程
            writer.setOnlyAlias(true);
            List<LearnDto> dataList = new ArrayList<>();
            LearnDto learnDto = new LearnDto();
//            learnDto.setUserName("QQQ");
//            learnDto.setUserInfo("WWW");
//            learnDto.setLesson("EEEEE");
            dataList.add(learnDto);
            writer.write(dataList, true);
            CellStyle cellStyle = writer.getCellStyle();
            Sheet sheet = writer.getSheet();
            // 设置表格默认列宽度为15个字节
//            sheet.setDefaultColumnWidth((short) 15);

            //设置整个Excel 为文本格式
            Workbook workbook = writer.getWorkbook();
            DataFormat format = workbook.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("@"));
            for (int i = 0; i < ExcelTitleConfig.LEARN_EXCEL_TITLE.size(); i++) {
                sheet.setDefaultColumnStyle(i, cellStyle);
            }

            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=template.xlsx");
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            writer.flush(out, true);
            writer.close();
            IoUtil.close(out);
        }
    }

    @RequestMapping(value = "batchLearnUser")
    @ApiOperation(value = "批量学生报名接口", httpMethod = "POST")
    public ResultData batchLearnUser(@RequestParam("file") MultipartFile file
    ) {
        try {

            learnService.batchExcelAddLearnUser(file);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(ReturnMsg.ERROR.getCode(), "系统异常，稍后再试", null);
        }
    }
}
