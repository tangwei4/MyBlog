package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.tool.BlogMessageConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogMessageConversion blogMessageConversion;

//    @RequestMapping("/blogDetails/{blogid}")
//    public ModelAndView showBlogDetails(@PathVariable("blogid")int blogId, Model model){
//        System.out.println("blogdatails");
//        BlogMessageConversion.BlogMessage blogMessage=
//                blogMessageConversion.getOneBlogMessage(blogService.findOneById(blogId));
//        model.addAttribute("blogMessage",blogMessage);
//        return new ModelAndView("front/blogDetailsPage","blogMessage",model);
//    }

}
