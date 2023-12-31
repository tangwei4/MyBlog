package com.suye.personalblog.service;

import com.suye.personalblog.redisrepository.SensitivewordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveService {

    @Autowired
    private SensitivewordRepository sensitivewordRepository;

    /**
     * 获得所有的敏感词汇
     * @return
     */
    public List<String> findSensitiveWords(){
        return sensitivewordRepository.getSensitiveKeyWords();
    }

    /**
     * 新增一个敏感词汇
     * @param word
     */
    public boolean addSensitiveWord(String word){
        return sensitivewordRepository.addASensitiveWord(word);
    }

    /**
     * 删除一个敏感词汇
     * @param word
     */
    public void  deleteSensitiveWord(String word){
        sensitivewordRepository.deleteASensitiveWord(word);
    }



}
