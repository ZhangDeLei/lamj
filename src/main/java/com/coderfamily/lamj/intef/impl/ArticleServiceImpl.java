package com.coderfamily.lamj.intef.impl;

import com.coderfamily.lamj.dao.ArticleMapper;
import com.coderfamily.lamj.intef.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ZhangDL
 * @date 2018/3/14 15:30
 */
@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
}
