package com.wanru.service;

import com.wanru.dto.PageDTO;
import com.wanru.dto.QuestionDto;
import com.wanru.exception.CustomizeErrorCode;
import com.wanru.exception.CustomizeException;
import com.wanru.mapper.QuestionMapper;
import com.wanru.mapper.UserMapper;
import com.wanru.model.Question;
import com.wanru.model.QuestionExample;
import com.wanru.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FangWei on 2020-06-04.
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO list(Integer page, Integer size) {
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());//拿到总记录数
        Integer totalPage = 0;//总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        //offset公式 size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        PageDTO pageDTO = new PageDTO();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        pageDTO.setPageHelper(totalPage, page, size);
        pageDTO.setQuestionDtos(questionDtoList);
        return pageDTO;
    }

    public PageDTO profileList(Integer id, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);//拿到总记录数
        Integer totalPage = 0;//总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        //offset公式 size*(page-1)
        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(id);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        PageDTO pageDTO = new PageDTO();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        pageDTO.setPageHelper(totalPage, page, size);
        pageDTO.setQuestionDtos(questionDtoList);
        return pageDTO;
    }

    public QuestionDto getById(Integer id) {
        QuestionDto questionDto = new QuestionDto();
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        BeanUtils.copyProperties(question, questionDto);
        return questionDto;
    }

    public void addOrUpdate(Question question) {
        if (question.getId() != null && !"".equals(question.getId())) {
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setModifiedTime(System.currentTimeMillis());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            int i = questionMapper.updateByExampleSelective(question, example);
            if (i != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        } else {
            question.setCreateTime(System.currentTimeMillis());
            question.setModifiedTime(System.currentTimeMillis());
            questionMapper.insert(question);
        }
    }

    public void incView(Integer id) {
        Question question = new Question();
        question.setViewCount(1);
        question.setId(id);
        questionMapper.incView(question);
    }
}
