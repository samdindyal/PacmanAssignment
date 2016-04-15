build:
	mkdir -p bin
	javac src/*java -d bin
	echo "Main-Class: GameWindow" > Manifest
	jar cfvm A1.jar Manifest -C bin  .
clean:
	rm -vf bin/*.class Manifest
