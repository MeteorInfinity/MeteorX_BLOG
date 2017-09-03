package com.meteor.xblog.service;

public interface MarkdownService {

    public void commonMark();

    public void kramdown();

    public String multiMarkdown(String markdownStr);

    public void markdown();
}
