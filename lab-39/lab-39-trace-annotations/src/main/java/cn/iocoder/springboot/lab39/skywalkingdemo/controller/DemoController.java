package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/trace_annotations")
    @Trace(operationName = "trace_annotations") // 实现 SkyWalking 指定方法的追踪，会创建一个 SkyWalking LocalSpan。同时，可以通过 operationName 属性，设置操作名。
    public String echo() {
        // 自定义 SkyWalking Span，设置该 LocalSpan 的标签
        ActiveSpan.tag("mp", "芋道源码");
        // ActiveSpan.error(); // 将当前 Span 标记为出错状态
        // ActiveSpan.error(String errorMsg); // 将当前 Span 标记为出错状态，并带上错误信息
        // ActiveSpan.error(Throwable throwable) // 将当前 Span 标记为出错状态，并带上 Throwable
        // ActiveSpan.debug(String debugMsg) // 在当前 Span 添加一个 debug 级别的日志信息
        // ActiveSpan.info(String infoMsg) // 在当前 Span 添加一个 info 级别的日志信息
        System.out.println("当前示例 traceId：" + TraceContext.traceId());
        // 返回
        return "trace_annotations";
    }

}
