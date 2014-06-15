export CLASSPATH=./bin:./@jar-name@:$CLASSPATH
java -Xmx512m -Djava.util.logging.config.file=log.properties "$@"


