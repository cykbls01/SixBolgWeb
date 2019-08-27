package com.example.demo.Controller;


import com.example.demo.Dao.BlogDao;
import com.example.demo.Entity.Blog;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BlogRepository;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
public class JumpController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private CommentRepository commentRepository;


    @RequestMapping(value = "/user/info")
    public String indextozone(HttpSession session, Model model) throws ParseException {
        User user=(User)session.getAttribute("user");
        List<Blog> blogList= BlogDao.findbyAuthorid(user.getId(),blogRepository);
        session.setAttribute("blognumber",blogList.size());
        model.addAttribute("blogs",blogList);
        model.addAttribute("blognumber",blogList.size());
        return "Zone";
    }

    @RequestMapping(value ="/Addblog.html")
    public String addblog(HttpSession session, Map<String,Object> map)
    {
        if(session.getAttribute("status")==null)
            session.setAttribute("status","false");
        System.out.println(session.getAttribute("status"));
        if(session.getAttribute("status").equals("true"))
        {
            return "redirect:/Addblog";


        }
        else
        {
            map.put("msg","请先登录");
            return "Login";

        }

    }







}
