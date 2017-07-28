export GFDB_HOME=/app/gfdb
export GF_HOME=/app/gfdb
export GF_JAVA=$JAVA_HOME/bin/java
export CLASSPATH=$GF_HOME/lib/*
export PATH=$GF_HOME/bin:$PATH
export JAVA_ARGS="-Dgfsh.log-level=info -Dgfsh.log-dir=$GFDB_HOME/logs"

gfsh start locator --name=gflocator --port=30607 --locators=127.0.0.1[30607] --log-level=info --dir=/app/gfdb/locator --initial-heap=512m --max-heap=512m --enable-cluster-configuration=false
