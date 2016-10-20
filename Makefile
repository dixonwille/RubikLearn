DistDir = dist
SrcDir = src
JFLAGS = -g \
				 -cp ./$(DistDir):./$(DistDir)/Cube:./$(DistDir)/Cube/Canvas
JC = javac
MANIFEST = ./$(SrcDir)/META-INF/MANIFEST.MF
CUBEJAVAFILES = \
	CubeException.java \
	ChangeListener.java \
	ColorType.java \
	SideType.java \
	CubitType.java \
	MoveType.java \
	Cubit.java \
	Side.java \
	Cube.java
CUBECLASSES := $(CUBEJAVAFILES:%.java=$(DistDir)/Cube/%.class)
CANVASJAVAFILES = \
	Default.java \
	Side.java \
	CubeCanvas.java
CANVASCLASSES := $(CANVASJAVAFILES:%.java=$(DistDir)/Cube/Canvas/%.class)
MAINJAVAFILES = \
	CubeEventHandler.java \
	Main.java
MAINCLASSES := $(MAINJAVAFILES:%.java=$(DistDir)/%.class)
.SUFFIXES: .java .class

default: Cube.jar

.PHONY: dir
dir:
	mkdir -p $(DistDir)/Cube/Canvas

$(DistDir)/%.class: $(SrcDir)/%.java
	$(JC) $(JFLAGS) -d ./$(DistDir) $<

.PHONY: classes
classes: dir $(CUBECLASSES) $(CANVASCLASSES) $(MAINCLASSES)

$(DistDir)/styles:$(SrcDir)/styles
	cp -r $< $@

.PHONY: dist
dist: dir classes $(DistDir)/styles

Cube.jar: dist
	jar cmf $(MANIFEST) Cube.jar -C $(DistDir) .

.PHONY: clean
clean:
	$(RM) -r $(DistDir)