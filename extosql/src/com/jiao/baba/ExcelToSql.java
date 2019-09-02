package com.jiao.baba;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sargeraswang.util.ExcelUtil.*;

import java.io.*;
import java.util.*;
public class ExcelToSql{
    private static Logger LG = LoggerFactory.getLogger(ExcelUtil.class);
    private static  final String SCHEMA_NAME = "schema";
    private static  final String TABLE_NAME = "表名";
    private static  final String FIELD = "字段编码";
    private static  final String FIELD_TYPE = "字段类型";
    private static  final String FIELD_LENGHT = "字段长度";
    private static  final String ISNULL = "非空";
    private static  final String COMMENT = "注释";
    private static  final String PRIMARYKEY = "主键";
    private static  final String INDEX = "唯一索引";
    private static  final String TABLE_ANNOTATION = "表注释";
    private static  final String SEQ = "序列";

    private static  final String CREATES_SQL = "CREATESQL";
    private static  final String INDEX_T = "INDEX";
    private static  final String PK = "PK";
    private static  final String COMMENTSQL = "COMMENTSQL";

    private static  final String CREATES_MYSQL_SQL = "CREATEMYSQLSQL";	
  @SuppressWarnings("unchecked")
  public void toSql(String excelPath, String outPath) throws FileNotFoundException {
    File f = new File(excelPath);
    InputStream inputStream= new FileInputStream(f);
    ExcelLogs logs =new ExcelLogs();
    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);

    List<String> primaryKeys = null;
    List<String> indexs = null;
    Map<String, Map<String, Object>>  tableMaps = new HashMap();
    Map<String, Object> tableMap = null;
    String tableName = null;
    for(Map m : importExcel){
        LG.info(m.toString());
        // 建表sql
        if (m.get(SCHEMA_NAME) != null && m.get(TABLE_NAME) != null) {
           tableMap = new HashMap();
           String st = ((String) m.get(SCHEMA_NAME)).trim() + "." + ((String)m.get(TABLE_NAME)).trim();
           tableName = st;
           if (! tableMaps.containsKey(st)) {
               if (m.get(TABLE_ANNOTATION) != null) {
                   StringBuilder commentSql =  new StringBuilder("COMMENT ON TABLE ").append(tableName).append(" \n    IS").append("'" + m.get(TABLE_ANNOTATION) + "'").append(";\n");
                   tableMap.put(COMMENTSQL, commentSql);
               }
               tableMap.put(CREATES_SQL, new StringBuilder("CREATE TABLE ").append(st).append("(\n"));
               tableMap.put("schema", ((String) m.get(SCHEMA_NAME)).trim());
               tableMap.put("tableName", ((String)m.get(TABLE_NAME)).trim());
               tableMap.put("tableAnnotation", m.get(TABLE_ANNOTATION) == null ? "" : (String)m.get(TABLE_ANNOTATION));
               tableMap.put("seq_n", m.get(SEQ) == null ? "" : (String)m.get(SEQ));
               primaryKeys = new ArrayList<>();
               indexs = new ArrayList<>();

               tableMap.put(CREATES_MYSQL_SQL, new StringBuilder("CREATE TABLE ").append(st).append("(\n"));
           }
        }
        if (tableMap == null) {
            throw new RuntimeException("未查找到schem或者表名");
        }
        String field = m.get(FIELD) == null ? null : ((String)m.get(FIELD)).trim();
        if (field != null && field.length() > 0) {
            tableMap.put(CREATES_SQL, ((StringBuilder)tableMap.get(CREATES_SQL)).append("    ").append(field.toUpperCase()).append(" "));
            tableMap.put(CREATES_MYSQL_SQL, ((StringBuilder)tableMap.get(CREATES_MYSQL_SQL)).append("    ").append(field.toUpperCase()).append(" "));
        }
        String fieldType = m.get(FIELD_TYPE) == null ? null : ((String)m.get(FIELD_TYPE)).trim();
        if (fieldType != null && fieldType.length() > 0) {
            tableMap.put(CREATES_SQL, ((StringBuilder)tableMap.get(CREATES_SQL)).append(fieldType.toUpperCase()).append(" "));
            if (fieldType.trim().toUpperCase().startsWith("INTEGER")) {
                fieldType = "INT";
            }else if (fieldType.trim().toUpperCase().startsWith("TIMESTAMP")) {
                if (fieldType.trim().indexOf("(") != -1) {
                    fieldType = fieldType.substring(0, fieldType.trim().indexOf("("));
                }
            }
            tableMap.put(CREATES_MYSQL_SQL, ((StringBuilder)tableMap.get(CREATES_MYSQL_SQL)).append(fieldType.toUpperCase()).append(" "));
        }
        String fieldLength = m.get(FIELD_LENGHT) == null ? null : ((String)m.get(FIELD_LENGHT)).trim();
        if (fieldLength != null && fieldLength.length() > 0) {
            String createSql = tableMap.get(CREATES_SQL).toString();
            tableMap.put(CREATES_SQL, new StringBuilder(createSql.substring(0, createSql.length() - 1)).append("(" + fieldLength + ")").append(" "));
            if (! fieldType.trim().toUpperCase().startsWith("TIMESTAMP")) {
                tableMap.put(CREATES_MYSQL_SQL, new StringBuilder(createSql.substring(0, createSql.length() - 1)).append("(" + fieldLength + ")").append(" "));
            }
        }
        String isNull = m.get(ISNULL) == null ? null : ((String)m.get(ISNULL)).trim();
        if (isNull != null && isNull.length() > 0 && "Y".equals(isNull)) {
            tableMap.put(CREATES_SQL, ((StringBuilder)tableMap.get(CREATES_SQL)).append("NOT NULL").append(" "));
            tableMap.put(CREATES_MYSQL_SQL, ((StringBuilder)tableMap.get(CREATES_MYSQL_SQL)).append("NOT NULL").append(" "));
        }
        StringBuilder ncSql = ((StringBuilder)tableMap.get(CREATES_SQL));
        tableMap.put(CREATES_SQL, new StringBuilder(ncSql.substring(0, ncSql.length() - 1)).append(",").append("\n"));
        // 注释sql
        String comment = m.get(COMMENT) == null ? null : ((String)m.get(COMMENT)).trim();
        if (comment != null && comment.length() > 0) {
            StringBuilder cs = new StringBuilder("COMMENT ON COLUMN ").append(tableName).append(".").append(field).append(" \n    IS ").append("'" + comment + "'").append(";\n");
            StringBuilder commentSql = tableMap.get(COMMENTSQL) == null ? new StringBuilder() : (StringBuilder) tableMap.get(COMMENTSQL);
            commentSql.append(cs);
            tableMap.put(COMMENTSQL, commentSql);

            StringBuilder mysqlSql = ((StringBuilder)tableMap.get(CREATES_MYSQL_SQL)).append("COMMENT ").append("'" + comment + "'").append(" ");
            tableMap.put(CREATES_MYSQL_SQL, mysqlSql);
        }
        StringBuilder msSql = (StringBuilder)tableMap.get(CREATES_MYSQL_SQL);
        tableMap.put(CREATES_MYSQL_SQL, new StringBuilder(msSql.substring(0, msSql.length() - 1)).append(",").append("\n"));

        // 主键
        String primaryKey = m.get(PRIMARYKEY) == null ? null : ((String)m.get(PRIMARYKEY)).trim();
        if (primaryKey != null && primaryKey.length() > 0 && "Y".equals(primaryKey)) {
            primaryKeys.add(field);
            tableMap.put(PK, primaryKeys);
        }
        // 唯一索引
        String index = m.get(INDEX) == null ? null : ((String)m.get(INDEX)).trim();
        if (index != null && index.length() > 0 && "Y".equals(index)) {
            indexs.add(field);
            tableMap.put(INDEX_T, indexs);
        }
        tableMaps.put(tableName, tableMap);
    }

      List<String> sqls = new ArrayList<>();
      String db2_Sql = "";
      String mysql_Sql = "";

      Calendar now = Calendar.getInstance();
      String date = "" +  now.get(Calendar.YEAR) + "-"
              + (now.get(Calendar.MONTH) + 1) + "-"
              + now.get(Calendar.DAY_OF_MONTH);
      String time = now.get(Calendar.HOUR_OF_DAY) + "H"
              + now.get(Calendar.MINUTE) + "M"
              + now.get(Calendar.SECOND) + "S";

      for (Map.Entry<String, Map<String, Object>> m : tableMaps.entrySet()) {
          String key = m.getKey();
          Map<String, Object> value = m.getValue();
          // 主键
          List<String> pks = (List)value.get(PK);
          LG.info("pk [{}]", pks.toString());
          StringBuilder createSql = null;
          StringBuilder mysqlSQL = null;
          if (pks != null && pks.size() > 0) {
               createSql = ((StringBuilder)value.get(CREATES_SQL));
               mysqlSQL = ((StringBuilder)value.get(CREATES_MYSQL_SQL));
                if (pks.size() == 1) {
//                    createSql.append("CONSTRAINT ").append(pks.get(0)).append(" PRIMARY KEY ").append("(" + pks.get(0) + ")").append("\n);");
                    createSql.append("    PRIMARY KEY ").append( pks.get(0)).append("\n);");
                    mysqlSQL.append("    PRIMARY KEY ").append( pks.get(0)).append("\n)");
                }else {
                    createSql.append("    PRIMARY KEY ").append("(");
                    mysqlSQL.append("    PRIMARY KEY ").append("(");
                    for (String pk : pks) {
                        createSql.append(pk).append(",");
                        mysqlSQL.append(pk).append(",");
                    }
                    createSql = new StringBuilder(createSql.substring(0, createSql.length() - 1)).append(")\n);");
                    mysqlSQL = new StringBuilder(mysqlSQL.substring(0, mysqlSQL.length() - 1)).append(")\n)");
                }
          }else {
              throw new RuntimeException("table:"+ key +"未查找到主键");
          }
          // 注释
          String ann = "/*\n" +
                  "DB2\n" +
                  "Source Schema         : "+ value.get("schema") +"\n" +
                  "Target Server Type    : DB2\n" +
                  "Date: " + Calendar.getInstance().getTime() + "\n" +
                  "*/\n" +
                  "-- ----------------------------\n" +
                  "-- " + value.get("tableName") + "\n" +
                  "--" + value.get("tableAnnotation") + "\n" +
                  "-- ----------------------------\n";
          createSql = new StringBuilder(ann).append(createSql);
          mysqlSQL = new StringBuilder(ann.replace("DB2", "MySql")).append(mysqlSQL);
          if (value.get(COMMENTSQL) != null && ((StringBuilder)value.get(COMMENTSQL)).length() > 0) {
              createSql.append("\n").append(value.get(COMMENTSQL)).append("\n");
          }
          // CREATE UNIQUE INDEX COU_INDEX ON CC_LEVFIV_COU(CAL_FV,PRE_DATE);
          // 索引
          List<String> index = value.get(INDEX_T) == null ? new ArrayList<>() : (List) value.get(INDEX_T);
          LG.info("index [{}]", index.toString());
          if (index.size() > 0) {
              StringBuilder unq = new StringBuilder();
              for (String in : indexs) {
                  if (pks.contains(in)) {
                      continue;
                  }
                  unq.append(in.trim()).append(",");
              }
              if (unq.length() > 0) {
                  createSql.append("\n").append("CREATE UNIQUE INDEX ");
                  for (String str : unq.toString().split(",")) {
                      createSql.append(str.charAt(0));
                  }
                  createSql.append("_UE_INDEX ")
                          .append("ON ")
                          .append(value.get("tableName"))
                          .append("(").append(unq.toString().substring(0, unq.length() - 1)).append(");\n\n");
              }
          }
          // 序列
          String seq_n = (String) value.get("seq_n");
          LG.info("seq [{}]", seq_n);
          if (seq_n != null && seq_n.length() > 0) {
              if ("Y".equals(seq_n)) {
                  createSql.append("CREATE SEQUENCE ")
                          .append(value.get("tableName"))
                          .append("_SEQ").append("\n");
              }else {
                  createSql.append("CREATE SEQUENCE ")
                          .append(seq_n).append("\n");
              }
              createSql.append("START WITH 1\n")
                      .append("INCREMENT BY 1\n")
                      .append("minvalue 1\n")
                      .append("MAXVALUE 2147483647\n")
                      .append("NO CYCLE;\n");
          }
          createSql.append("\n\n");
          mysqlSQL.append("ENGINE=MyISAM DEFAULT CHARSET=utf8 ").append("COMMENT='").append(value.get("tableAnnotation")).append("';\n\n");
          db2_Sql += createSql.toString();
          mysql_Sql += mysqlSQL.toString();
          // DB2
          String db2Sql_f = outPath + "/db2/" + date +  "/" + value.get("tableName") + ".sql";
          outFile(db2Sql_f.toString(), createSql.toString());
          // mysql
          String mySqlPath_f = outPath + "/mysql/" + date +  "/" + value.get("tableName") + ".sql";
          outFile(mySqlPath_f.toString(), mysqlSQL.toString());
      }
      // DB2
      String db2Sql = outPath + "/db2/" + date +  "/" + date + ".sql";
      outFile(db2Sql.toString(), db2_Sql);
      // mysql
      String mySqlPath = outPath + "/mysql/" + date +  "/" + date + ".sql";
      outFile(mySqlPath.toString(), mysql_Sql);

  }
    public void outFile(String path, String context) {
        File file = new File(path);

        if (file.exists()) {
            file.delete();
        }
        if (! file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(path, true);
            writer.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}