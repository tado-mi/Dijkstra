# variable for java compiler
JC = javac
J = java

# flags for directories
D  = -d
CD = cd
# remove directories
RM = rm

# damage control
.SUFFIXES: .java .class

# target for creating .class from .java in format:
#	.original_extention.target_extention:
#		rule
.java.class:
	$(JC) $*.java $(D) bin

CLASSES = main.java

# default target definition
default: classes

classes: $(CLASSES:.java=.class)

go:
	$(CD) bin; \
	$(J) main ur HOEING CSB

undo:
	$(CD) bin; \
	$(RM) *.class; \
	$(CD) ..
