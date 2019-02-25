#------------------------------------------------------------------------------
#
# Ashley Lavizadeh
# alavizad
# pa1
#
#       
#
#------------------------------------------------------------------------------ 

MAINCLASS  = Lex
JAVAC      = javac 
JAVASRC    = $(wildcard *.java)
SOURCES    = $(JAVASRC) makefile README
CLASSES    = $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES = $(patsubst %.class, %*.class, $(CLASSES))
JARFILE    = $(MAINCLASS) 

all: $(JARFILE)
	
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
     
submit: README Makfile Lex.java ListClient.java List.java ListClient.java
	submit cmps101-pt.f18 README pa1 Makefile Lex.java List.java ListClient.java
