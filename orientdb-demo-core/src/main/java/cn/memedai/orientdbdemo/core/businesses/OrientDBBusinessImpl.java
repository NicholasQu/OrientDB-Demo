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
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.command.OCommandPredicate;
import com.orientechnologies.orient.core.command.script.OCommandFunction;
import com.orientechnologies.orient.core.command.script.OCommandScript;
import com.orientechnologies.orient.core.command.traverse.OTraverse;
import com.orientechnologies.orient.core.db.ODatabaseThreadLocalFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.db.record.ridbag.ORidBag;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.index.OIndex;
import com.orientechnologies.orient.core.metadata.function.OFunction;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.core.sql.filter.OSQLPredicate;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.graph.batch.OGraphBatchInsert;
import com.orientechnologies.orient.graph.gremlin.OGremlinHelper;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.server.distributed.ODistributedServerLog;
import com.tinkerpop.blueprints.*;
import com.tinkerpop.blueprints.impls.orient.*;
import com.tinkerpop.blueprints.util.wrappers.batch.cache.ObjectIDVertexCache;

import java.util.Set;

/**
 * <<Description>>
 * Version     : 1.0
 * Created By  : nicholas
 * Created Date: 2016/2/25 15:39
 */
public class OrientDBBusinessImpl implements OrientDBBusiness {
    private static OrientGraphFactory factory = new OrientGraphFactory(
            "remote:localhost:2424/VehicleHistoryGraph",
            "root", "rootpwd")
            .setupPool(1,10);

    //JAVA API -- Multi-Threading

    @Override
    public String ping() {

        ODatabaseThreadLocalFactory customFactory = new MyCustomRecordFactory();
        Orient.instance().registerThreadDatabaseFactory(customFactory);

        return "OK";
    }

    public static void main(String[] args) {
        OrientGraph graph = factory.getTx();
        ODatabaseDocumentTx db = factory.getDatabase();
        //OObjectDatabaseTx objectDb = new OObjectDatabaseTx ("remote:localhost:2424/VehicleHistoryGraph").open("root", "rootpwd");

        try {
            ////A mixture of auto started and manually started transactions is not allowed. Disable auto transactions for the graph before starting a manual transaction.
            ////graph.begin();
            //Vertex vCustomer1 = graph.addVertex("class:MiCustomer");
            //vCustomer1.setProperty("name","曲健");
            //vCustomer1.setProperty("nickName","曲高和寡");
            //vCustomer1.setProperty("idNo","370685199010231231");
            //vCustomer1.setProperty("mobile","1381111111");
            ////graph.commit();

            //OrientVertex vCustomer2 = graph.addVertex("class:MiCustomer");
            //vCustomer2.setProperty("name","神仙");
            //vCustomer2.setProperty("nickName","神游四方");
            //vCustomer2.setProperty("idNo","31012121212121212");
            //vCustomer2.setProperty("mobile","1892222222");

            //vCustomer2.addEdge("is", vCustomer1);

            //create vertex class & superclass
            //graph.createVertexType("MiCustomer", "Customer");
            //graph.dropVertexType("MiCustomer");

            // Retrieve Vertex
            //OrientVertexType accountVertex = graph.getVertexType("Account");
            //accountVertex.createProperty("id", OType.INTEGER);
            //accountVertex.createProperty("birthDate", OType.DATE);
            //accountVertex.dropProperty("id");
            // Create Unique Nickname
            //accountVertex.createProperty("nick", OType.STRING).setMin("3").setMax("30")
            //        .setMandatory(true).setNotNull(true);
            //accountVertex.createIndex("nickIdx", OClass.INDEX_TYPE.UNIQUE, "nick");
            //
            //// Create User Properties
            //accountVertex.createProperty("name", OType.STRING).setMin("3").setMax("30");
            //accountVertex.createProperty("surname", OType.STRING).setMin("3").setMax("30");
            //accountVertex.createProperty("registeredOn", OType.DATE).setMin("2010-01-01 00:00:00");
            //accountVertex.createProperty("lastAccessOn", OType.DATE).setMin("2010-01-01 00:00:00");

            //OrientElement element = null;
            //
            //element.detach();
            //graph.query().has("name", Compare.EQUAL, "fast");
            //
            //graph.addEdge(null,
            //        graph.getVertices("MiCustomer.mobile", "1381111111").iterator().next(),
            //        vCustomer2, "is");

            //for (Vertex v : graph.getVertices()) {
            //    System.out.println("Loop All Vertices: " + v.getProperty("name"));
            //}
            //for (Vertex v : graph.getVertices("MiCustomer.mobile", "1381111111")) {
            //    System.out.println("Query mobile 1381111111 Vertices: " + v
            //            + "|" + v.getProperty("name")
            //            + "|" + v.getProperty("nickName"));
            //}


            //Iterable<Vertex> customerIt = graph.command(
              //      new OCommandSQL("SELECT EXPAND (in('is')) FROM MiCustomer where name='曲健'"))
                //    .execute();

            //for (Vertex v : customerIt) {
            //    System.out.println("曲健 is " + v.getProperty("name"));
            //}

            //创建Person类上的唯一索引, case-insenstitive
            //graph.createKeyIndex("name", Vertex.class, new Parameter("type", "UNIQUE"), new Parameter("class", "Person"),new Parameter("collate", "ci"));

            //OGremlinHelper.global().create();

            // ******************************************
            // Blueprints  Extension
            //graph.addVertex("class:Customer, cluster:Customer_sh");

            //Detach fetch all record content in memory and resets the connection to the Graph instance.
            //OrientGraphQuery qryResults =  (OrientGraphQuery)graph.query().has("firstName", Compare.EQUAL, "Kyle");
            //for (Vertex v : qryResults.vertices()) {
            //    System.out.println(v.getId());
            //    ((OrientVertex)v).detach();
            //}

            // SQL Batch
            //String cmd = "BEGIN\n";
            //cmd += "LET a = CREATE VERTEX SET script = true\n";
            //cmd += "LET b = SELECT FROM V LIMIT 1\n";
            //cmd += "LET e = CREATE EDGE FROM $a TO $b RETRY 100\n";
            //cmd += "COMMIT\n";
            //cmd += "return $e";
            //
            //OIdentifiable edge = graph.command(new OCommandScript("sql", cmd)).execute();


            //*************************************
            // 批量操作
            //OGraphBatchInsert batch = new OGraphBatchInsert("plocal:your/db", "admin", "admin");
            //batch.begin();
            //batch.createVertex(0, 1L);
            //batch.createEdge(0, 2L);
            //batch.end();

            //说明:先创建vertex1的时候, outbag1里的ridEdge1还没创建, 可以事后再创建没有问题
            //ORecordId ridVertex1 = new ORecordId(9, 0L); // #9:0, the RID of Vertex1, that still does not exist
            //ORecordId ridVertex2 = new ORecordId(9, 1L); // #9:1, the RID of Vertex2, that still does not exist
            //ORecordId ridVertex3 = new ORecordId(9, 2L); // #9:2, the RID of Vertex3, that still does not exist
            //
            //ORecordId ridEdge1 = new ORecordId(10, 0L); // #10:0, the RID of Edge1, that still does not exist
            //ORecordId ridEdge2 = new ORecordId(10, 1L); // #10:1, the RID of Edge2, that still does not exist
            //
            //ODocument vertex1 = new ODocument("VertexClass");
            //vertex1.field("foo", "bar");// set property names and values
            //ORidBag outBag1 = new ORidBag();
            //outBag1.add(ridEdge1); // add the RID of the corresponding outgoing edge
            //vertex1.field("out_EdgeClass", outBag1);
            //db.save(vertex1, "vertexclass"); //make sure that you are saving on the right cluster (vertexclass)

            //Saving documents with links to other not (yet) existing documents is allowed,
            // so saving a vertex that points to a non existing edge will result in a correct operation.
            // The graph consistency will be restored as soon as you create the edge.

            //Set<String> functions = graph.getRawGraph().getMetadata().getFunctionLibrary().getFunctionNames();
            //System.out.println(functions);
            Set<String> functions = db.getMetadata().getFunctionLibrary().getFunctionNames();
            System.out.println(functions);

            //OIndex<?> nameIdx = db.getMetadata().getIndexManager()
            //        .getIndex("Person.firstName");

            //Unique Index
            //OIdentifiable kyle = (OIdentifiable)nameIdx.get("Kyle");
            ////Non unique Index
            //OIndex<?> genderIdx = db.getMetadata().getIndexManager()
            //        .getIndex("Person.gender");
            //Set<OIdentifiable> male = (Set<OIdentifiable>)genderIdx.get("male");


            //OrientDynaElementIterable result = graph.command(new OCommandFunction("createPerson")).execute();
            //System.out.println(result.toString());
            //System.out.println(result.iterator().next());

            //OFunction createPerson = db.getMetadata().getFunctionLibrary().getFunction("createPerson");
            //OFunction createPeople = graph.getRawGraph().getMetadata().getFunctionLibrary().getFunction("createPeople");
            //
            //System.out.println(createPerson);
            //System.out.println(createPeople);
            //
            //Object result = createPerson.execute();
            //System.out.println(result);


            //************************************
            // Object API
            // The OObjectDatabaseTx class is non thread-safe. For this reason use different OObjectDatabaseTx instances by multiple threads.
            // They will share local cache once transactions are committed.
            //MiCustomer miCustomerObj = objectDb.newInstance(MiCustomer.class);
            //miCustomerObj.setIdNo("");
            //objectDb.save(miCustomerObj);
            //
            //objectDb.getEntityManager().registerEntityClass(MiCustomer.class);
            //查不到数据????
            //for (MiCustomer customer : objectDb.browseClass(MiCustomer.class)) {
            //    System.out.println(customer.getName());
            //}

            //**************************************
            //traverse
            for (OIdentifiable id : new OSQLSynchQuery<ODocument>("traverse in, out from #13:4894 while $depth <= 3")) {
                System.out.println(id);
            }

            //for (OIdentifiable id : new OTraverse()
            //        .field("in").field("out")
            //        .target( db.browseClass("Person").iterator() )
            //        .predicate(new OCommandPredicate() {
            //
            //            @Override
            //            public Object evaluate(OIdentifiable oIdentifiable, ODocument oDocument, OCommandContext oCommandContext) {
            //                return ((Integer) oCommandContext.getVariable("depth")) <= 5;
            //            }
            //
            //        })) {
            //
            //    System.out.println(id);
            //}

            //for (OIdentifiable id : new OTraverse()
            //        .field("in").field("out")
            //        .target(db.browseClass("Movie").iterator())
            //        .predicate( new OSQLPredicate("$depth <= 5"))) {
            //
            //    System.out.println(id);
            //}



        } finally {
            graph.shutdown();
            db.close();
            //objectDb.close();
            factory.close();
        }
    }

}
