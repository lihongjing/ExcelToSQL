package com.jiao.baba;
import com.jiao.baba.ExcelToSql;
import java.io.*;
public class MainTest{
	public static void main(String args[]) throws FileNotFoundException{
		ExcelToSql es = new ExcelToSql();
		File f = new File(MainTest.class.getResource("/").getPath());
        es.toSql(f.getPath() + "/source/sql.xlsx", f.getPath() + "/data");
	}
}