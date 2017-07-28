export GF_HOME=/app/gfdb
export GF_JAVA=$JAVA_HOME/bin/java
export CLASSPATH=$GF_HOME/lib/*
export PATH=$GF_HOME/bin:$PATH
export JAVA_ARGS="-Dgfsh.log-level=info -Dgfsh.log-dir=$GF_HOME/logs"

gfsh --e "connect --locator=127.0.0.1[55221]" --e "stop server --name=gfserver"
