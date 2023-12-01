package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.model.Category;
import com.suye.personalblog.model.Column;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.model.Visitor;
import com.suye.personalblog.service.*;
import com.suye.personalblog.tool.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 21:27
 */
@Controller
public class MainAndBlogController {

    @Value("${net.address}")
    private String ip;
    @Value("${net.port}")
    private String port;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ConmentService conmentService;
    @Autowired
    private ConmentMessageConversion conmentMessageConversion;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    @Autowired
    private ArchiveMessageConversion archiveMessageConversion;
    @Autowired
    private PaginationTool paginationTool;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView allMessage(@RequestParam(name = "search", required = false) String searchContent,
                                   HttpServletRequest request, HttpServletResponse response,
                                   Model model) {
        System.out.println("lall");
        paginationTool.reset(request, PaginationTool.Category.ALL);
        List<BlogMessageConversion.BlogMessage> recentBlogList = null;
        if (searchContent != null) {
            paginationTool.reset(request, PaginationTool.Category.SEARCH);
            paginationTool.setContent(searchContent, request);
            recentBlogList = blogMessageConversion.getBlogMessageList(blogService.searchContent(searchContent));
        } else {
            recentBlogList = blogMessageConversion.getBlogMessageList(blogService.recentBlogs());
        }

        RunningTrackStacks.resetRuningTrack(request);

        int blogTotal = blogService.blogTotal();
        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("action", "loadMoreAll");
        // model.addAttribute("address",ip+":"+port);
        if (searchContent != null) {
            model.addAttribute("action", "/loadMoreSearch");
            return new ModelAndView("index", "allMessge", model);
        }
        return new ModelAndView("index", "allMessge", model);
    }

    /**
     * 响应博客的分类
     *
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public ModelAndView indexBlog(Model model, HttpServletRequest request) {
        System.out.println("blogs");
        //paginationTool.reset();
        //paginationTool.setCategoryBlog();
        paginationTool.reset(request, PaginationTool.Category.BLOG);
        //RunningTrackStack.addRunningStack(0,"博客","/blogs");
        //RunningTrackStacks.resetRuningTrack();
        //RunningTrackStacks.addRunningTrack("博客","/blogs");
        RunningTrackStacks.resetRuningTrack(request);
        RunningTrackStacks.addRunningTrack(request, "博客", "/blogs");

        int blogTotal = blogService.blogTotal();
        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());

        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());

        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(blogService.recentBlogsNotShuoShuo());
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("action", "loadMoreBlog");
        return new ModelAndView("index", "allMessge", model);
    }

    /**
     * 响应说说的分类
     *
     * @param model
     * @return
     */
    @GetMapping("/shuoshuos")
    public ModelAndView indexShuoShuo(Model model, HttpServletRequest request) {
        System.out.println("shuosshuos");
        //paginationTool.reset();
        //paginationTool.setCategoryShuoShuo();
        paginationTool.reset(request, PaginationTool.Category.SHUOSHUO);
        //RunningTrackStack.addRunningStack(1,"说说","/shuoshuos");
        //RunningTrackStacks.resetRuningTrack();
        //RunningTrackStacks.addRunningTrack("说说","/shuoshuos");
        RunningTrackStacks.resetRuningTrack(request);
        RunningTrackStacks.addRunningTrack(request, "说说", "/shuoshuos");

        int blogTotal = blogService.blogTotal();
        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());

        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(blogService.recentBlogsIsShuoShuo());
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("action", "loadMoreShuoShuo");
        return new ModelAndView("index", "allMessge", model);
    }

    /**
     * 响应加载更多的全部
     *
     * @param model
     * @return
     */
    @GetMapping("/loadMoreAll")
    public ModelAndView loadMoreAll(HttpServletRequest request, Model model) {
        System.out.println("loadMoreAll");
//        List<BlogMessageConversion.BlogMessage> recentBlogList=
//                blogMessageConversion.getBlogMessageList(blogService.loadMoreRecentBlogs(loadMoreAll*7));
        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("action", "loadMoreAll");
        return new ModelAndView("index", "allMessge", model);
    }

    /**
     * 响应加载更多的博客
     *
     * @param model
     * @return
     */
    @GetMapping("/loadMoreBlog")
    public ModelAndView loadMoreBlog(HttpServletRequest request, Model model) {
        System.out.println("loadMoreBlog");
        //loadMoreAll=1;
        //loadMoreShuoShuo=1;
//        List<BlogMessageConversion.BlogMessage> recentBlogList=
//                blogMessageConversion.getBlogMessageList(blogService.loadMoreRecentNotShuoShuo(loadMoreBlog*7));
//
        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));

        //loadMoreBlog++;
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("action", "loadMoreBlog");
        return new ModelAndView("index", "allMessge", model);
    }

    /**
     * 响应加载更多的说说
     *
     * @param model
     * @return
     */
    @GetMapping("/loadMoreShuoShuo")
    public ModelAndView loadMoreShuoShuo(HttpServletRequest request, Model model) {
        System.out.println("loadMoreShuoShuo");
        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(paginationTool.blogList(request));
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("action", "loadMoreShuoShuo");
        return new ModelAndView("index", "allMessge", model);
    }

    @GetMapping("/loadMoreSearch")
    public ModelAndView loadMoreSearch(Model model) {
        System.out.println("loadMoreSearch");
        List<BlogMessageConversion.BlogMessage> recentBlogList =
                blogMessageConversion.getBlogMessageList(paginationTool.blogList());
        model.addAttribute("recentBlogList", recentBlogList);
        model.addAttribute("action", "loadMoreSearch");
        return new ModelAndView("index", "allMessage", model);
    }

    @RequestMapping("/blogDetails/{blogid}")
    public ModelAndView showBlogDetails(@PathVariable("blogid") int blogId, Model model,
                                        HttpServletRequest request) {
        System.out.println("blogdatails");
        int blogTotal = blogService.blogTotal();
        blogService.increaseReadNum(blogId);
        String classicLanguage = ClassicAuotation.getAAuotation();
        int num1 = ClassicAuotation.getANum();
        int num2 = ClassicAuotation.getANum();

        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        BlogMessageConversion.BlogMessage blogMessage =
                blogMessageConversion.getOneBlogMessage(blogService.findOneById(blogId));

        //List<RunningTrackStack.RunningTrack> runningTrackList=RunningTrackStack.getRunningTrackStack();

        //Set<RunningTrackStacks.RunningTrack> runningTrackList=RunningTrackStacks.getRunningTrackSet();
        List<RunningTrackStacks.RunningTrack> runningTrackList = RunningTrackStacks.getRunningTrackList(request);

        List<ConmentMessageConversion.ConmentMessage> recentAllConmentList =
                conmentMessageConversion.findAllConmentsByBlogId(blogId, 0);
        List<Object> conmentPages = conmentMessageConversion.conversionTotal(conmentService.conmnetTotal(blogId));

        model.addAttribute("classicLanguage", classicLanguage);
        model.addAttribute("sum", num1 + num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("blogDatailsMessage", blogMessage);
        model.addAttribute("runningTrackList", runningTrackList);
        model.addAttribute("recentAllConmentList", recentAllConmentList);
        model.addAttribute("conmentPages", conmentPages);
        model.addAttribute("address", ip + ":" + port);
        return new ModelAndView("front/blogDetailsPage", "blogMessage", model);
    }

    @RequestMapping("/archive")
    public ModelAndView showarchivePage(HttpServletRequest request, Model model) {
        System.out.println("archive");
        blogService.increaseReadNum(87);
        //System.out.println("1");
        int blogTotal = blogService.blogTotal();
        // System.out.println("2");
        List<Label> labelList = labelService.getAllLabels();
        // System.out.println("3");
        List<Category> categoryList = categoryService.getAllCategory();
        // System.out.println("4");
        List<Column> columnList = columnService.getAllColumn();
        //System.out.println("5");
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        // System.out.println("6");
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        // System.out.println("7");
        BlogMessageConversion.BlogMessage blogMessage =
                blogMessageConversion.getOneBlogMessage(blogService.findArchive());
        // System.out.println("8");
        List<ArchiveMessageConversion.ArchiveMessage> archiveMessageList =
                archiveMessageConversion.archiveMessageList(blogService.findAllBlogTimeDesc());
        // System.out.println("9");
        RunningTrackStacks.resetRuningTrack(request);
        List<RunningTrackStacks.RunningTrack> runningTrackList = RunningTrackStacks.getRunningTrackList(request);
        //System.out.println("10");
        model.addAttribute("blogMessage", blogMessage);
        model.addAttribute("archiveMessageList", archiveMessageList);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("runningTrackList", runningTrackList);
        return new ModelAndView("front/archivePage", "archivePage", model);
    }

    @RequestMapping("/friend")
    public ModelAndView showFriendChain(Model model) {
        String classicLanguage = ClassicAuotation.getAAuotation();
        int num1 = ClassicAuotation.getANum();
        int num2 = ClassicAuotation.getANum();
        int blogTotal = blogService.blogTotal();
        List<Visitor> friendList = visitorService.findAllFriends();
        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        BlogMessageConversion.BlogMessage blogMessage =
                blogMessageConversion.getOneBlogMessage(blogService.findFirends());

        List<ConmentMessageConversion.ConmentMessage> recentAllConmentList =
                conmentMessageConversion.findAllConmentsByBlogId(88, 0);
        model.addAttribute("classicLanguage", classicLanguage);
        model.addAttribute("sum", num1 + num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        List<Object> conmentPages = conmentMessageConversion.conversionTotal(conmentService.conmnetTotal(11));
        model.addAttribute("blogMessage", blogMessage);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("friendList", friendList);
        model.addAttribute("recentAllConmentList", recentAllConmentList);
        model.addAttribute("conmentPages", conmentPages);
        return new ModelAndView("front/friendChain", "friendChain", model);
    }

    @RequestMapping("/aboutme")
    public ModelAndView showAboutMe(Model model) {
        String classicLanguage = ClassicAuotation.getAAuotation();
        int num1 = ClassicAuotation.getANum();
        int num2 = ClassicAuotation.getANum();
        blogService.increaseReadNum(89);
        int blogTotal = blogService.blogTotal();
        List<Label> labelList = labelService.getAllLabels();
        List<Category> categoryList = categoryService.getAllCategory();
        List<Column> columnList = columnService.getAllColumn();
        List<BlogMessageConversion.BlogMessage> popularBlogList =
                blogMessageConversion.getBlogMessageList(blogService.mostPopularBlog());
        List<ConmentMessageConversion.ConmentMessage> recentConmentList =
                conmentMessageConversion.conmentMessageList(conmentService.recentConment());
        BlogMessageConversion.BlogMessage blogMessage =
                blogMessageConversion.getOneBlogMessage(blogService.findAboutMe());

        List<ConmentMessageConversion.ConmentMessage> recentAllConmentList =
                conmentMessageConversion.findAllConmentsByBlogId(89, 0);

        List<Object> conmentPages = conmentMessageConversion.conversionTotal(conmentService.conmnetTotal(89));
        model.addAttribute("classicLanguage", classicLanguage);
        model.addAttribute("sum", num1 + num2);
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("blogMessage", blogMessage);
        model.addAttribute("blogTotal", blogTotal);
        model.addAttribute("labelList", labelList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("columnList", columnList);
        model.addAttribute("popularBlogList", popularBlogList);
        model.addAttribute("recentConmentList", recentConmentList);
        model.addAttribute("recentAllConmentList", recentAllConmentList);
        model.addAttribute("conmentPages", conmentPages);
        return new ModelAndView("front/aboutMe", "aboutMe", model);
    }

}
