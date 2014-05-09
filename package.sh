mvn install
cd rendersnake
	mvn site:site
	cd ..
mkdir target
cp docs/README.txt target
cd target
	cp -r ../rendersnake/target/site site
	mkdir jars
	cp ../rendersnake/target/rendersnake*.jar jars
	curl http://code.google.com/p/rendersnake/wiki/ReleaseNotes > release-notes.html	
	zip -r ../rendersnake.zip .
	cd ..
