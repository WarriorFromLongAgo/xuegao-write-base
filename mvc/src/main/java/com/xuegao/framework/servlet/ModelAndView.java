package com.xuegao.framework.servlet;

import java.util.Map;

public class ModelAndView {
    /**
     * 页面模板
     */
    private String view;
    /**
     * 传输的数据
     */
    private Map<String, Object> model;

    public ModelAndView(String view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }

    public ModelAndView() {
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
