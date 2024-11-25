#KATOUZIAN Pouria s192865

all:
	mkdir -p bin && javac -d bin -classpath graphics.jar:bin src/*.java

run:
	cd bin; java -cp ../graphics.jar: Main

archive:
	tar -czf S-192865.tar.gz  graphics.jar Makefile resources src *.pdf

clear:
	rm -r bin *.tar.gz
