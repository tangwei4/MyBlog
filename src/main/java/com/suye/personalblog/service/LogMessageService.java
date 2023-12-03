package com.suye.personalblog.service;

import com.suye.personalblog.model.LogMessage;
import com.suye.personalblog.redisrepository.LogRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LogMessageService {

    @Autowired
    private LogRedisRepository logRedisRepository;

    /**
     * 增加一个系统信息
     * @param action
     */
    public void  addALog(String action){
        logRedisRepository.addLogMessage(action);
    }

    /**
     * 获取所有的系统信息
     * @return
     */
    public List<LogMessage> getLogMessage(){
        List<LogMessage> list=logRedisRepository.getLogMessage();
        List<LogMessage> logMessageList=new ArrayList<>();
        for (LogMessage logMessage:list) {
            logMessageList.add(logMessage);
        }
        //倒序排列
        Collections.sort(logMessageList, Comparator.comparing(LogMessage::getDate).reversed());
        //获取前面5条
        List<LogMessage> logMessages = logMessageList.subList(0, Math.min(logMessageList.size(), 10));
        return logMessages;
    }
}
