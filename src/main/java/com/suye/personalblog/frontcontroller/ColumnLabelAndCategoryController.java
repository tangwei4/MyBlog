package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.Column;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.CategoryService;
import com.suye.personalblog.service.ColumnService;
import com.suye.personalblog.service.LabelService;
import com.suye.personalblog.tool.BlogMessageConversion;
import com.suye.personalblog.tool.PaginationTool;
import com.suye.personalblog.tool.RunningTrackStack;
import com.suye.personalblog.tool.RunningTrackStacks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-30
 * Time: 15:42
 */
@Controller
public class ColumnLabelAndCategoryController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    @Autowired
    private PaginationTool paginationTool;

    @RequestMapping("/column/{columnId}")
    public ModelAndView showColumn(@PathVariable("columnId")int columnId, Model model,
                                   HttpServletRequest request){
//        System.out.println();
        //paginationTool.reset();
        //paginationTool.setCategoryColumn();
        //paginationTool.setColumnId(columnId);
        paginationTool.reset(request, PaginationTool.Category.COLUMN);
        paginationTool.setColumnId(columnId,request);
        String loadMoreHref="/loadMoreColumn";
        Column column=columnService.findOneByColumnId(columnId);
        //RunningTrackStack.addRunningStack(column.getName(),"/column/"+columnId);
        RunningTrackStacks.resetRuningTrack(request);
        RunningTrackStacks.addRunningTrack(request,column.getName(),"/column/"+columnId);
        List<RunningTrackStacks.RunningTrack> runningTrackList=RunningTrackStacks.getRunningTrackList(request);
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByColumnId(columnId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/loadMoreColumn")
    public ModelAndView loadMoreBlogsColumn(HttpServletRequest request,Model model){
        String loadMoreHref="/loadMoreColumn";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/label/{labelId}")
    public ModelAndView showLabel(@PathVariable("labelId")int labelId, Model model,
                                  HttpServletRequest request){
        //paginationTool.reset();
        //paginationTool.setCategoryLabel();
        //paginationTool.setLabelId(labelId);
        paginationTool.reset(request, PaginationTool.Category.LABEL);
        paginationTool.setLabelId(labelId,request);
        String loadMoreHref="/loadMoreLabel";
        Label label =labelService.findLabelById(labelId);

        //RunningTrackStack.addRunningStack(label.getName(),"/label/"+labelId);
        RunningTrackStacks.resetRuningTrack(request);
        RunningTrackStacks.addRunningTrack(request,label.getName(),"/label/"+labelId);
        List<RunningTrackStacks.RunningTrack> runningTrackList=RunningTrackStacks.getRunningTrackList(request);

        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByLabelId(labelId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);
    }

    @RequestMapping("/loadMoreLabel")
    public ModelAndView loadMoreBlogsLabel(HttpServletRequest request,Model model){
        String loadMoreHref="/loadMoreLabel";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }


    @RequestMapping("/category/{categoryId}")
    public ModelAndView showCategory(@PathVariable("categoryId")int categoryId,Model model,HttpServletRequest request){
//       paginationTool.reset();
//       paginationTool.setCategoryCategory();
//       paginationTool.setCategoryId(categoryId);
//       String loadMoreHref="/loadMoreCategory";
//       Category categor=categoryService.findOneById(categoryId);
//       RunningTrackStack.addRunningStack(categor.getName(),"/category"+categor.getId());
//       List<RunningTrackStack.RunningTrack> runningTrackList=RunningTrackStack.getRunningTrackStack();
//       List<BlogMessageConversion.BlogMessage> blogMessageList=
//               blogMessageConversion.getBlogMessageList(paginationTool.blogList());
//        model.addAttribute("blogMessage",blogMessageList);
//        model.addAttribute("runningTrackList",runningTrackList);
//        model.addAttribute("loadMorehref",loadMoreHref);
//        return new ModelAndView("front/columnpage","allMessage",model);


        //paginationTool.reset();
        //paginationTool.setCategoryCategory();
        //paginationTool.setCategoryId(categoryId);
        paginationTool.reset(request, PaginationTool.Category.CATEGORY);
        paginationTool.setCategoryId(categoryId,request);
        String loadMoreHref="/loadMoreCategory";
        Category category =categoryService.findOneById(categoryId);
        //RunningTrackStack.addRunningStack(category.getName(),"/category/"+categoryId);
        RunningTrackStacks.resetRuningTrack(request);
        RunningTrackStacks.addRunningTrack(request,category.getName(),"/category/"+categoryId);
        List<RunningTrackStacks.RunningTrack> runningTrackList=RunningTrackStacks.getRunningTrackList(request);
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(blogService.findBlogsByCategoryId(categoryId,0));
        model.addAttribute("loadMoreHref",loadMoreHref);
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("runningTrackList",runningTrackList);
        return new ModelAndView("front/columnpage","allMessge",model);

    }

    @RequestMapping("/loadMoreCategory")
    public ModelAndView loadMoreBlogsCategory(HttpServletRequest request,Model model){
        String loadMoreHref="/loadMoreCategory";
        List<BlogMessageConversion.BlogMessage> recentBlogList=
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));
        model.addAttribute("recentBlogList",recentBlogList);
        model.addAttribute("loadMoreHref",loadMoreHref);
        return new ModelAndView("front/columnpage","allMessge",model);
    }
}
