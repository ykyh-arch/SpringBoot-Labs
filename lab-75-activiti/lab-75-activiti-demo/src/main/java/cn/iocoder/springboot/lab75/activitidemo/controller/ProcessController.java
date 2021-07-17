package cn.iocoder.springboot.lab75.activitidemo.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程处理器
 * @author Jaquez
 * @date 2021/07/08 22:15
 */
@Controller
@RequestMapping("/activiti/process")
public class ProcessController{

    @Resource
    private RepositoryService repositoryService;


    /**
     * 流程管理页面跳转
     * @return
     */
    @GetMapping("/listUI")
    public ModelAndView listUI(ModelAndView mav) {
        List<ProcessDefinition> processList = repositoryService.createProcessDefinitionQuery().list();
        mav.addObject("processList", processList);
        mav.setViewName("/activiti/process/list");
        return mav;
    }


}
