# Rendersnake Site startup script
#
# ernest.micklei@philemonworks.com

java \
    -Dlog4j.configuration=file://`pwd`/../conf/log4j.properties \
    $JAVA_OPTS -server \
    -classpath '../lib/*' \
    org.rendersnake.site.module.RendersakeSiteModule ../conf/rendershark.properties &

# store the java process id for stop.sh
echo $(echo $!) > "main.pid"