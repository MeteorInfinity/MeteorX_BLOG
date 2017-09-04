package com.meteor.xblog.service;

import org.springframework.stereotype.Repository;

@Repository
public interface MarkdownService {

    public void commonMark();

    public void kramdown();

    public String multiMarkdown(String markdownStr);

    public String markdown(String markdownStr);
}
