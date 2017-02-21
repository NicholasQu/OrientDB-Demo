/*
 * -------------------------------------------------------------------------------------
 * Mi-Me Confidential
 *
 * Copyright (C) 2015 Shanghai Mi-Me Financial Infomation Service Co., Ltd.
 * All rights reserved.
 *
 * No part of this file may be reproduced or transmitted in any form or by any means,
 * electronic, mechanical, photocopying, recording, or otherwise, without prior
 * written permission of Shanghai Mi-Me Financial Infomation Service Co., Ltd.
 * -------------------------------------------------------------------------------------
 */
package cn.memedai.orientdbdemo.core.businesses;

import com.orientechnologies.orient.core.db.ODatabaseDocumentInternal;
import com.orientechnologies.orient.core.db.ODatabaseThreadLocalFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentPool;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : Nicholas
 * Created Date: 2017/2/19 下午5:04
 */
public class MyCustomRecordFactory implements ODatabaseThreadLocalFactory {

    @Override
    public ODatabaseDocumentInternal getThreadDatabase() {
        return null;
    }
}
