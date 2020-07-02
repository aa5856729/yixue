package com.yixue.loxc.system.controller;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.vo.LoginLogVo;
import com.yixue.loxc.pojo.vo.PageRelust;
import com.yixue.loxc.system.service.TLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
@RestController
@RequestMapping("/system/loginlog")
@CrossOrigin
public class TLoginLogController {
    @Autowired
    private TLoginLogService tLoginLogService;

    @PostMapping("/query")
   public Result<TLoginLogEntity> querylog(@RequestParam String username, @RequestParam(value ="beginDate",required = false) String beginDate,
                                           @RequestParam(value ="endDate",required = false) String endDate, @RequestParam(value ="loginResult",required = false) String loginResult,
                                           @RequestParam(value ="currentPage",required = false) String currentPage) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        LoginLogVo logVo=null;
        if (beginDate!=null && endDate!=null && loginResult!=null && currentPage!=null){
             logVo = new LoginLogVo(username,dateFormat.parse(beginDate),dateFormat.parse(endDate),Integer.parseInt(loginResult),Integer.parseInt(currentPage));
        }else {
             logVo = new LoginLogVo();
             logVo.setUsername(username);
             logVo.setLoginResult(3);
        }

        //List<TLoginLogEntity> list=tLoginLogService.allLog();

        int indexPage=1;
        if (currentPage != null) {
            indexPage=Integer.parseInt(currentPage);
        }
        IPage<TLoginLogEntity> page =tLoginLogService.userloglist(indexPage,logVo);
        if (page!=null){
            //创建一个实体类 把分页数据和集合数据放里面
            PageRelust pageRelust=new PageRelust();
            pageRelust.setCurrentPage((int)page.getCurrent());
            pageRelust.setTotalPage((int)page.getPages());  //总页数
            pageRelust.setListData(page.getRecords());
            return new Result(200,"数据加载成功",pageRelust);
        }
        return new Result(300,"数据加载失败");
   }


}
