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

package cn.memedai.orientdbdemo.web.controllers;

import cn.memedai.orientdbdemo.facade.businesses.DubboExampleBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : nicholas
 * Created Date: 2016/1/19 15:38
 */
@Controller
public class TestController {

    @Autowired
    private DubboExampleBusiness exampleBusiness;

    @RequestMapping(path = "/test", produces = {"application/json"})
    @ResponseBody
    public String test() {
        return exampleBusiness.ping();
    }

}
