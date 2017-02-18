/*
 * -------------------------------------------------------------------------------------
 * Mi-Me Confidential
 *
 * Copyright (C) 2015 Shanghai Mi-Me Financial Information Service Co., Ltd.
 * All rights reserved.
 *
 * No part of this file may be reproduced or transmitted in any form or by any means,
 * electronic, mechanical, photocopying, recording, or otherwise, without prior
 * written permission of Shanghai Mi-Me Financial Information Service Co., Ltd.
 * -------------------------------------------------------------------------------------
 */

package cn.memedai.orientdb-demo.core.businesses;

import cn.memedai.framework.hibernate.dao.BaseDao;
import cn.memedai.framework.hibernate.dao.BaseDaoImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : nicholas
 * Created Date: 2016/1/27 18:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml",
        "classpath*:applicationContext-dao.xml",
        "classpath*:applicationContext-fm-dao.xml"})
@Transactional
public class ExampleTest {

    @Autowired
    private BaseDao baseDao = new BaseDaoImpl();

    @Before
    public void setUp() {

        //Transaction t = baseDao.getHibernateTemplate().getSessionFactory().getCurrentSession().getTransaction();
        //t.begin();
        //新建数据
        Object entity = null;
        baseDao.save(entity);

        //t.commit();
    }
}
