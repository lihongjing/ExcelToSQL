javac -encoding utf-8 -d class -sourcepath src -classpath .;lib/excel-util-1.2.4.jar @sourcelist.txt
xcopy .\class .\extosql_jar /s
