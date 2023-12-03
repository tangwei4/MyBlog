package com.suye.personalblog.tool;

import com.suye.personalblog.model.Blog;
import com.suye.personalblog.model.Conment;
import com.suye.personalblog.model.Visitor;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ConmentMessageConversion {

    @Autowired
    BlogService blogService;
    @Autowired
    VisitorService visitorService;
    @Autowired
    ConmentService conmentService;

    /**
     * 获取评论信息列表
     * @param list
     * @return
     */
    public  List<ConmentMessage> conmentMessageList(List<Conment> list){
        List<ConmentMessage> conmentMessages=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Conment conment=list.get(i);
            Visitor visitor=visitorService.findOneById(conment.getVisitor_id());
            Blog blog=blogService.findOneById(conment.getBlog_id());
            String blogName=blog.getTitle();
            int blogId=blog.getId();
            Date date=new Date(conment.getConment_time().getTime()-8*60*60*1000);
            ConmentMessage conmentMessage=new ConmentMessage(conment.getId(),
                                                            conment.getContent(),
                                                            TimeConversion.timeConversion(conment.getConment_time()),
                                                            visitor,blogName,conment.getVotenum(),conment.getCainum(),
                                                            blogId,date);
            conmentMessages.add(conmentMessage);
        }
        return conmentMessages;
    }

    /**
     * 通过博客id找到所有没有父评论的评论
     * @param blogId
     * @param offset
     * @return
     */
    public List<ConmentMessage> findAllConmentsByBlogId(int blogId,int offset){
        List<Conment> parentConments=conmentService.findConmentIsParentByBlogId(blogId,offset);
        List<ConmentMessage> parentConmentMessages=conmentMessageList(parentConments);
        findChilds(parentConmentMessages);
        return parentConmentMessages;
    }

    /**
     * 找寻该评论下的子评论
     * @param parentConments
     */
    private void findChilds(List<ConmentMessage> parentConments){
        if (parentConments==null){
            return;
        }
        for (int i=0;i<parentConments.size();i++){
            System.out.println(parentConments.get(i).getId()+"的孩子有:");
            List<ConmentMessage> childConments=conmentMessageList(conmentService.findConmentsByConmentParentID(parentConments.get(i).getId()));
            parentConments.get(i).setChild(childConments);
            for (int j=0;j<childConments.size();j++){
                System.out.print(childConments.get(j).getId()+" ");
            }
            findChilds(childConments);
        }
    }

    /**
     * 页面转化
     * @param total
     * @return
     */
    public  List<Object> conversionTotal(int total){
        int pages=0;
        if (total%5==0){
            pages=total/5;
        }else {
            pages=total/5+1;
        }
        List<Object> list=new ArrayList<>();
        for (int i=0;i<pages;i++){
            list.add(new Object());
        }
        return list;
    }

    public int conmentPages(int total){
        int pages=0;
        if (total%5==0){
            return pages=total/5;
        }else {
            return pages=total/5+1;
        }
    }

    public static class ConmentMessage{
        private int id;
        private String content;
        private String time;
        //private String visitorName;
        private String blogName;
        private Visitor visitor;
        private int votenum;
        private int cainum;
        private List<ConmentMessage> child;
        private int blogId;
        private Date date;

        public ConmentMessage(int id,String content,String time,Visitor visitor,String blogName,int votenum,int cainum,int blogId,Date date){
            this.id=id;
            this.content=content;
            this.time=time;
            this.visitor=visitor;
            this.blogName=blogName;
            this.votenum=votenum;
            this.cainum=cainum;
            this.blogId=blogId;
            this.date=date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

//        public String getVisitorName() {
//            return visitorName;
//        }

//        public void setVisitorName(String visitorName) {
//            this.visitorName = visitorName;
//        }

        public String getBlogName() {
            return blogName;
        }

        public void setBlogName(String blogName) {
            this.blogName = blogName;
        }

        public Visitor getVisitor() {
            return visitor;
        }

        public void setVisitor(Visitor visitor) {
            this.visitor = visitor;
        }

        public List<ConmentMessage> getChild() {
            return child;
        }

        public void setChild(List<ConmentMessage> child) {
            this.child = child;
        }

        public int getVotenum() {
            return votenum;
        }

        public void setVotenum(int votenum) {
            this.votenum = votenum;
        }

        public int getCainum() {
            return cainum;
        }

        public void setCainum(int cainum) {
            this.cainum = cainum;
        }

        public int getBlogId() {
            return blogId;
        }

        public void setBlogId(int blogId) {
            this.blogId = blogId;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
