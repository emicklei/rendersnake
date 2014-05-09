read LASTPID < "main.pid"
kill -9 $LASTPID
rm -f main.pid