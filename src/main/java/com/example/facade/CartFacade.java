package com.example.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

/**
 * Created by tzurc on 4/15/2017.
 */
@Component
public class CartFacade {


    private HttpSession httpSession;

}
