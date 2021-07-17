package cn.iocoder.springboot.lab75.activitidemo.controller;

import cn.iocoder.springboot.lab75.activitidemo.dto.ModelerDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/activiti/modeler")
public class ModelerController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 模型列表页跳转
     * @return
     */
    @GetMapping("/listUI")
    public ModelAndView listUI(ModelAndView mav) {
        List<Model> modelList = repositoryService.createModelQuery().list();
        mav.addObject("modelList", modelList);
        mav.setViewName("/activiti/modeler/list");
        return mav;
    }


    /**
     * 模型添加页跳转
     * @return
     */
    @GetMapping("/saveUI")
    public String saveUI() {
        return "/activiti/modeler/form";
    }


    /**
     * 添加模型
     * @param modelerDto
     * @throws Exception
     */
    @PostMapping("/save")
    @ResponseBody
    public String save(ModelerDto modelerDto){

        //创建模型
        Model modelData = repositoryService.newModel();

        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        //模型名称
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelerDto.getName());
        //模型版本
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelerDto.getVersion());
        //模型详情
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, modelerDto.getDescription());
        //以字符串信息存储进信息属性中
        modelData.setMetaInfo(modelObjectNode.toString());

        //模型名称
        modelData.setName(modelerDto.getName());
        //模型标识
        modelData.setKey(modelerDto.getKey());
        //模型类型
        modelData.setCategory(modelerDto.getCategory());

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

        return "添加成功!";
    }

    /**
     * 设计模型页面跳转
     * @return
     */
    @GetMapping("/designUI")
    public void designUI(HttpServletResponse response, String id) throws IOException {
        response.sendRedirect("/static/activiti/modeler.html?modelId=" + id);
    }



    /**
     * 模型部署
     * @param id
     * @return
     */
    @PostMapping("/deploy")
    @ResponseBody
    public String deploy(String id) throws IOException {
        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return "模型数据为空，请先设计流程并成功保存，再进行发布!";
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size() == 0){
            return "数据模型不符要求，请至少设计一条主线流程!";
        }

        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .key(modelData.getKey())
                .category(modelData.getCategory())
                .tenantId(modelData.getTenantId())
                .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                .deploy();
        modelData.setDeploymentId(deployment.getId());

        repositoryService.saveModel(modelData);
        return "模型部署成功!";
    }


    /**
     * 删除模型
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        repositoryService.deleteModel(id);
        return "删除成功!";
    }

}
