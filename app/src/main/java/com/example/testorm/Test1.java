package com.example.testorm;

import orm.MapAppendCommandCreateTable;
import orm.MapColumn;
import orm.IActionOrm;
import orm.MapIndex;
import orm.MapPrimaryKey;
import orm.MapTable;


@MapAppendCommandCreateTable({"CREATE INDEX IF NOT EXISTS test_inte ON 'test' ('inte');"})
@MapTable("'test'")
public class Test1 implements IActionOrm<Test1> {
    @MapPrimaryKey("id")
    public int id;

    @MapColumn("name")
    public  String name;


    @MapIndex
    @MapColumn("elong")
    public long longs;

    @MapColumn("inte")
    public Integer inte;

    @MapColumn("short")
    public short aShort;

    @MapColumn("abyte")
    public byte aByte;




    @Override
    public void actionBeforeUpdate(Test1 test1) {

    }

    @Override
    public void actionAfterUpdate(Test1 test1) {

    }

    @Override
    public void actionBeforeInsert(Test1 test1) {

    }

    @Override
    public void actionAfterInsert(Test1 test1) {

    }

    @Override
    public void actionBeforeDelete(Test1 test1) {

    }

    @Override
    public void actionAfterDelete(Test1 test1) {

    }
}

