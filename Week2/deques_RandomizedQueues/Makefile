# make MAIN=HelloGoodbye ARGS="Name1 Name2"
# ../../lib/ directory for any library common used along all projects, like alg4s.jar

JC = javac
JV = java
CLASSPATH = bin:$(wildcard ../../lib/*.jar)
SRC = src
BIN = bin
all: compile run

compile:
	$(JC) -d $(BIN) -cp $(CLASSPATH) $(SRC)/$(MAIN).java

run: compile
	$(JV) -cp $(CLASSPATH) $(MAIN) $(ARGS)

clean:
	rm -f $(BIN)/*.class
