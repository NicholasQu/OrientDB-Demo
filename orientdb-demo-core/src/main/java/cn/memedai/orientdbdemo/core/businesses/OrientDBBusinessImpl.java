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

package cn.memedai.orientdbdemo.core.businesses;

import cn.memedai.orientdbdemo.core.models.MiCustomer;
import com.orientechnologies.orient.core.metadata.function.OFunction;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.server.distributed.ODistributedServerLog;
import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientElement;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : nicholas
 * Created Date: 2016/2/25 15:39
 */
public class OrientDBBusinessImpl implements OrientDBBusiness {
    private static OrientGraphFactory factory = new OrientGraphFactory(
            "remote:99.48.56.8:2424/VehicleHistoryGraph",
            "root", "rootpwd")
            .setupPool(1,10);



    @Override
    public String ping() {
        return "OK";
    }


    public void demo() {
        factory.getDatabase();

    }

    public static void main(String[] args) {
        OrientGraph db = factory.getTx();

        try {
            ////A mixture of auto started and manually started transactions is not allowed. Disable auto transactions for the graph before starting a manual transaction.
            ////db.begin();
            //Vertex vCustomer1 = db.addVertex("class:MiCustomer");
            //vCustomer1.setProperty("name","曲健");
            //vCustomer1.setProperty("nickName","曲高和寡");
            //vCustomer1.setProperty("idNo","370685199010231231");
            //vCustomer1.setProperty("mobile","1381111111");
            ////db.commit();

            //OrientVertex vCustomer2 = db.addVertex("class:MiCustomer");
            //vCustomer2.setProperty("name","神仙");
            //vCustomer2.setProperty("nickName","神游四方");
            //vCustomer2.setProperty("idNo","31012121212121212");
            //vCustomer2.setProperty("mobile","1892222222");
            //
            //
            //OrientElement element = null;
            //
            //element.detach();
            //db.query().has("name", Compare.EQUAL, "fast");
            //
            //db.addEdge(null,
            //        db.getVertices("MiCustomer.mobile", "1381111111").iterator().next(),
            //        vCustomer2, "is");

            //for (Vertex v : db.getVertices()) {
            //    System.out.println("Loop All Vertices: " + v.getProperty("name"));
            //}
            //for (Vertex v : db.getVertices("MiCustomer.mobile", "1381111111")) {
            //    System.out.println("Query mobile 1381111111 Vertices: " + v
            //            + "|" + v.getProperty("name")
            //            + "|" + v.getProperty("nickName"));
            //}


            //Iterable<Vertex> customerIt = db.command(
              //      new OCommandSQL("SELECT EXPAND (in('is')) FROM MiCustomer where name='曲健'"))
                //    .execute();

            //for (Vertex v : customerIt) {
            //    System.out.println("曲健 is " + v.getProperty("name"));
            //}

            OFunction createPerson = db.getRawGraph().getMetadata().getFunctionLibrary().getFunction("createPerson");
            System.out.println(createPerson);
            Object result = createPerson.execute();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.shutdown();
            factory.close();
        }
    }

}
