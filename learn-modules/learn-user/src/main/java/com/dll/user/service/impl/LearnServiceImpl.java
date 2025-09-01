package com.dll.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dll.common.config.ExcelTitleConfig;
import com.dll.common.model.ResultData;
import com.dll.user.dto.BatchLearnDto;
import com.dll.user.dto.LearnDto;
import com.dll.user.dto.QueryLearnDto;
import com.dll.user.mapper.LearnMapper;
import com.dll.user.service.LearnService;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dll
 * @date 2025-07-08 17:36
 */
@Service
public class LearnServiceImpl extends ServiceImpl<LearnMapper, LearnDto> implements LearnService {

    @Override
    public IPage<LearnDto> showLearnUserInfoPage(QueryLearnDto queryLearnDto) {
        IPage<LearnDto> page = new Page<>(queryLearnDto.getPageNo(), queryLearnDto.getPageSize());
        IPage<LearnDto> ret = baseMapper.showLearnUserInfoPage(page, queryLearnDto);
        return ret;
    }

    @Override
    public void addLearnDto(LearnDto learnDto) {
        baseMapper.addLearnDto(learnDto);
    }

    @Override
    public void deleteLearnById(LearnDto learnDto) {
        learnDto.setDeFlag(1);
        baseMapper.updateDeleteFlagLearnById(learnDto);
    }

    @Override
    public void batchDeleteLearnByIds(QueryLearnDto queryLearnDto) {
        queryLearnDto.setPageNo(1);
        baseMapper.batchUpdateDeleteFlagLearnByIds(queryLearnDto.getPageNo(), queryLearnDto.getIds());
    }

    @Override
    public void batchLearnUser(BatchLearnDto batchLearnDto) {
        baseMapper.batchInsertLearnUser(batchLearnDto.getLearnList());
    }

    @Override
    public ResultData batchExcelAddLearnUser(MultipartFile file) throws Exception {
        Integer excelRowConfig = 200;
        ResultData<Map<String, Object>> resultData = new ResultData<>();
        Map<String, Object> resMap = new HashMap<>();
        String errorId = "";
        String fileName = file.getOriginalFilename();
        //校验文件 数量是否超过限额 200 文件是否是 xls  或者xlsx
        ResultData x = fileValid(file, fileName);
        if (x != null) {
            return x;
        }

        boolean notNull = false;
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一个sheet页的内容
        if (sheet != null) {
            notNull = true;
        }
        //处理模板头
        Row fist = sheet.getRow(0);
        //判断excel的列标题是否和模板一致
        boolean b = checkColTitle(fist);
        if (b) {
            return ResultData.failed("模板表头有误");
        }

        if (sheet.getLastRowNum() > excelRowConfig) {
            System.out.println(sheet.getLastRowNum());
            return ResultData.failed("单表格数据超过" + excelRowConfig + "条，请多个表格导入");
        }
        //处理数据
        List<LearnDto> rights = Lists.newArrayListWithExpectedSize(sheet.getLastRowNum());
        handleDataTest(sheet, rights);
        System.out.println(rights.toString());
        baseMapper.batchInsertLearnUser(rights);
        return null;
    }


    private ResultData fileValid(MultipartFile file, String fileName) {
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return ResultData.failed("文件类型不正确");
        }
        long size = file.getSize();
        if (file.getOriginalFilename() == null || file.getOriginalFilename().equals("") || size == 0) {
            return ResultData.failed("文件不能为空");
        }
        return null;
    }

    /**
     * 标题校验
     *
     * @param fist
     * @return
     */
    private boolean checkColTitle(Row fist) {
        for (int i = 0; i < ExcelTitleConfig.LEARN_EXCEL_TITLE.size(); i++) {
            if (!ExcelTitleConfig.LEARN_EXCEL_TITLE.get(i).equals(fist.getCell(i).getStringCellValue().trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对数据进行验证
     *
     * @param sheet
     * @param rights
     */
    private void handleDataTest(Sheet sheet, List<LearnDto> rights) {
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            // 若该行 为null  则必然为空 进入进入下一行数据读取
            if (row == null) {
                continue;
            }
            //2020-05-28 dll  增加 表格 验证
            int rowCells = sheet.getRow(r).getPhysicalNumberOfCells();//通过sheet表单对象 获取 行对象 进而 获取 单元格 个数
            if (rowCells == 0) {
                continue;
            }
            // 通过判断单元格1 和单元格3的数据 若为null  则进入下一行数据
            if (filterCellDataByCellStatus(row)) {
                continue;
            } else {

            }
            LearnDto ssr = new LearnDto();

            //判断该Cell中是否有数据 是否是空字符串 如果不是则赋值
            if (estimateCellHaveData(row, 0)) {
                ssr.setUserName(getTrimData(row, 0));
                //注：导入的信息中至少要有姓名
                ssr.setDeFlag(0);
                ssr.setCreateTime(new Date());
                ssr.setUpdateTime(new Date());
                ssr.setUserInfo("没人介绍");
            }
            if (estimateCellHaveData(row, 1)) {
                ssr.setUserInfo(getTrimData(row, 1));
            }
            if (estimateCellHaveData(row, 2)) {
                ssr.setLesson(getTrimData(row, 2));
            }
            if (ssr.getUserName() == null) {
                ssr = null;
            } else {
                rights.add(ssr);
            }

        }
    }

    private String getTrimData(Row row, int i) {
        return row.getCell(i).getStringCellValue().trim();
    }

    /**
     * 判断该单元格是否为空
     *
     * @param row
     * @param cellNum
     * @return
     */
    private Boolean estimateCellHaveData(Row row, Integer cellNum) {
        if (row.getCell(cellNum) == null) {//通过 单元格 进行验证
            return false;
        } else {
            Cell cell0 = row.getCell(cellNum);
            cell0.setCellType(CellType.STRING);
            if ("".equals(row.getCell(cellNum).getStringCellValue().trim())) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 通过单元格第一个和第三个 过滤数据 第一个第三个没数据 则进入下一行
     *
     * @param row
     * @return
     */
    private boolean filterCellDataByCellStatus(Row row) {
        Boolean ce0 = false;
        Boolean ce2 = false;
        if (row.getCell(0) == null) {
            ce0 = true;
        } else {
            Cell cell00 = row.getCell(0);
            cell00.setCellType(CellType.STRING);
            if ("".equals(row.getCell(0).getStringCellValue().trim())) {
                ce0 = true;
            }
        }
        if (row.getCell(2) == null) {
            ce2 = true;
        } else {
            Cell cell111 = row.getCell(2);
            cell111.setCellType(CellType.STRING);
            if ("".equals(row.getCell(2).getStringCellValue().trim())) {
                ce2 = true;
            }
        }
        if (ce0 && ce2) {
            return true;
        }
        String userName = row.getCell(0).getStringCellValue().trim();
        Cell cell2 = row.getCell(2);
        cell2.setCellType(CellType.STRING);
        String lesson = row.getCell(2).getStringCellValue().trim();
        if ("".equals(userName) && userName.equals(lesson)) {
            return true;
        }
        return false;
    }
};

