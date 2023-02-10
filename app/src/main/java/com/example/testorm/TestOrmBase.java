package com.example.testorm;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import orm.Configure;
import orm.ISession;
import orm.Loger;
import orm.Utils;

public class TestOrmBase {
    public void Run(ApplicationInfo applicationInfo, Context baseContext) throws Exception {
        new Configure(baseContext.getFilesDir()+ "/test_data.sqlite", baseContext,  true);
        ISession ses=Configure.getSession();
        ses.dropTable(Test1.class);
        ses.createTableIfNotExists(Test1.class);
        List<Test1> list4=ses.getList(Test1.class,null);
        Thread thread=new Thread(() -> {
            while (true){
                ISession iSession=Configure.getSession();
                iSession.beginTransaction();
                try{
                    iSession.insert(new Test1());
                    List<Test1> list=iSession.getList(Test1.class,null);
                    Loger.LogI("__1__"+list.size());
                    iSession.commitTransaction();
                }catch (Exception e){
                    Loger.LogE(e.getMessage());
                }finally {
                    iSession.endTransaction();
                }
            }

        });
        thread.start();

        Thread thread2=new Thread(() -> {
            while (true){
                ISession iSession=Configure.getSession();
                iSession.beginTransaction();
                try{
                    iSession.insert(new Test1());
                    List<Test1> list=iSession.getList(Test1.class,null);
                    Loger.LogI("__2__"+list.size());
                    iSession.commitTransaction();
                }catch (Exception e){
                    Loger.LogE(e.getMessage());
                }finally {
                    iSession.endTransaction();
                }
            }
        });
        thread2.start();
        //Object dds=  Configure.getSession().executeScalar("SELECT name FROM sqlite_master WHERE type='table' AND name='test1';", null);
        //if(dds==null){
        //    Configure.getSession().createTable(Test1.class);
        //}
        //String ss=null;
        //String sss= Utils.trimAll(ss,new Character[]{'`','[','\'',']',' '});

       //List<Class> listClass= Configure.getClassForInit(baseContext);

        //if(ses.tableExists(Test1.class)==true){
        //    Configure.getSession().dropTable(Test1.class);
        //}
        //if(ses.tableExists(Test1.class)==false){
        //    Configure.getSession().createTable(Test1.class);
        //}
       // int creater= ses.CreateAllTablesIfDoesExist(baseContext);
        Test1 dd=new Test1();
        dd.name="sdsdsd";
        dd.longs=12132388;
        dd.aShort=34;
        dd.aByte=45;
        dd.inte = 35;



        try{
            ses.beginTransaction();
            ses.insert(dd);


            List<Test1> list =new ArrayList<>();
            list.add(TestFactory(12));
            list.add(TestFactory(13));
            list.add(TestFactory(14));
            Configure.bulk(Test1.class,list,ses);


            ses.commitTransaction();
        }catch (Exception ex){
           //
            throw new Exception(ex);
        }finally {
            ses.endTransaction();
        }
        //List<Test1> list23=Configure.getSession().getList(Test1.class, " 'id'= 1 ;", null);
        //Test1 df=Configure.getSession().get(Test1.class,dd.id);
        //df.aByte=100;
        //df.longs=100;
        //df.aShort=100;
        //Configure.getSession().update(df);
        //ISession ses1=Configure.getSession();
        //Object d=ses1.executeScalar("select count(*)  from \"test\"");
        //List<Test1> list2=ses1.getList(Test1.class,null);
        //Test1 test1=list2.get(0);
        //test1.name="simple tack";
        //int dsd=ses1.update(test1);
        //ses1.delete(test1);
        //List<Test1> list=ses1.getList(Test1.class,"1=1 order by inte");
//
//
        //List<Test1> list3=ses1.getList(Test1.class," inte = @1",13);
    }
    private Test1 TestFactory(int id){
        Test1 dd=new Test1();
        dd.name="sdsdsd";
        dd.longs=12132388;
        dd.aShort=34;
        dd.aByte=45;
        byte[] rr=new byte[2];
        rr[0]=3;
        rr[1]=45;

        dd.inte = id;
        return  dd;
    }
}
