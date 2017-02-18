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

package cn.memedai.orientdbdemo.facade.forms;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : nicholas
 * Created Date: 2016/1/25 18:53
 */
public class BaseForm implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 定义对象以toString方式显示的样式
     */
    private static final ToStringStyle TO_STRING_STYLE = ToStringStyle.MULTI_LINE_STYLE;

    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
    }

}
