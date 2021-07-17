package cn.iocoder.springboot.lab75.activitidemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 由于在线流程设计器需要 Model 才能初始化，所以在跳转到该页面去需要创建 Model
 *
 * @author jaquez
 * @date 2021/07/08 21:28
 **/
@Controller
@RequestMapping("/modelerdemo")
public class ModelerDemoController {
    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 添加模型
     */
    @GetMapping("/save")
    public void save(HttpServletResponse response) throws IOException {

        //创建模型
        Model modelData = repositoryService.newModel();

        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        //模型名称
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "请假");
        //模型版本
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        //模型详情
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, "请假详情");
        //以字符串信息存储进信息属性中
        modelData.setMetaInfo(modelObjectNode.toString());
        //模型名称
        modelData.setName("请假");
        //模型key
        modelData.setKey("leave");

        //完善ModelEditorSource，这里固定的
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");

        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.set("stencilset", stencilSetNode);

        //添加模型
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));

        response.sendRedirect("/static/activiti/modeler.html?modelId=" + modelData.getId());
    }

}
