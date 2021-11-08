package cn.iocoder.springboot.lab58.feigndemo.feign;

import cn.iocoder.springboot.lab58.feigndemo.entity.Contributor;
import cn.iocoder.springboot.lab58.feigndemo.entity.Issue;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

    @RequestLine("POST /repos/{owner}/{repo}/issues")
    void createIssue(Issue issue, @Param("owner") String owner, @Param("repo") String repo);
}
