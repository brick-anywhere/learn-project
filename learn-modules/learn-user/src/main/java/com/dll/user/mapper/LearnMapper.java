package com.dll.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dll.user.dto.LearnDto;
import com.dll.user.dto.QueryLearnDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface LearnMapper extends BaseMapper<LearnDto> {
    /**
     * 分页查询学习用户的信息
     *
     * @param queryLearnDto
     * @return
     */
    IPage<LearnDto> showLearnUserInfoPage(IPage<LearnDto> page, @Param("queryLearnDto") QueryLearnDto queryLearnDto);

    /**
     * 单个添加学习信息
     *
     * @param learnDto
     */
    void addLearnDto(@Param("learnDto") LearnDto learnDto);

    /**
     * 修改单个学校者的 删除标志位  0未删除 1 删除
     *
     * @param learnDto
     */
    void updateDeleteFlagLearnById(@Param("learnDto") LearnDto learnDto);

    /**
     * 批量性的修改学习者的 删除
     *
     * @param ids
     */
    void batchUpdateDeleteFlagLearnByIds(@Param("deFlag") Integer deFlag, @Param("ids") List<Integer> ids);

    /**
     * 批量添加学习者的信息
     *
     * @param learnList
     */
    void batchInsertLearnUser(List<LearnDto> learnList);
}
