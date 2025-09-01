package com.dll.user.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author dll
 * @date 2025-07-09 18:45
 */
@Data
@Accessors(chain = true)
public class BatchLearnDto implements Serializable {

    private List<LearnDto> learnList;
}
