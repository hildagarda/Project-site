package com.example.demo.service;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAllQuestion(){
        return questionRepository.findAll();
    }
    public Question findQuestionById(Long id){
        return questionRepository.findById(id).orElseGet(Question::new);
    }
    public Question saveQuestion(String id, String name){
        Question question = new Question();
        if (id != null && !id.isEmpty()) {
            question.setId(Long.parseLong(id));
        }
        question.setName(name);
        return questionRepository.save(question);
    }

}
