package com.dll.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dll.common.model.ResultData;
import com.dll.user.dto.BatchLearnDto;
import com.dll.user.dto.LearnDto;
import com.dll.user.dto.QueryLearnDto;
import org.springframework.web.multipart.MultipartFile;

public interface LearnService extends IService<LearnDto> {
    /**
     * 分页展示学信用户信息
     *
     * @param queryLearnDto
     * @return
     */
    IPage<LearnDto> showLearnUserInfoPage(QueryLearnDto queryLearnDto);

    /**
     * 单个添加学习信息
     *
     * @param learnDto
     */
    void addLearnDto(LearnDto learnDto);

    /**
     * 单个删除学习信息
     *
     * @param learnDto
     */
    void deleteLearnById(LearnDto learnDto);

    /**
     * 批量性的删除学习信息
     *
     * @param queryLearnDto
     */
    void batchDeleteLearnByIds(QueryLearnDto queryLearnDto);

    /**
     * 批量性添加学习者的信息
     *
     * @param batchLearnDto
     */
    void batchLearnUser(BatchLearnDto batchLearnDto);

    /**
     * EXCEl 导入用户数据
     *
     * @param file
     */
    ResultData batchExcelAddLearnUser(MultipartFile file) throws Exception;
}
