package cn.iocoder.springboot.lab01.springsecurity.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

import cn.iocoder.springboot.lab01.springsecurity.common.AjaxResult;
import cn.iocoder.springboot.lab01.springsecurity.common.HttpStatus;
import cn.iocoder.springboot.lab01.springsecurity.components.page.PageDomain;
import cn.iocoder.springboot.lab01.springsecurity.components.page.TableDataInfo;
import cn.iocoder.springboot.lab01.springsecurity.components.page.TableSupport;
import cn.iocoder.springboot.lab01.springsecurity.utils.DateUtils;
import cn.iocoder.springboot.lab01.springsecurity.utils.SqlUtil;
import cn.iocoder.springboot.lab01.springsecurity.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 通用数据处理
 *
 * @author Jaquez
 * @date 2021/10/11 13:52
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为 Date 类型
     * spring 常用注解参考：https://mp.weixin.qq.com/s/Nn5gePR9ilUGv1fKOWikww
     *
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     * 
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
