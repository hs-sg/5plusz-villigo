package com.splusz.villigo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
public class GaraController {
   
	// html과 css를 확인하기 위한 컨트롤러 임니다. 
	// 가라 컨트롤러 임니다
   @GetMapping("/post/create")
   public void  postcreate() {

   }
   @GetMapping("/car/create")
   public void carcreate() {
	   
   }

}
