multimodule-sbt-dependencies
============================
Document my expectations for dependencies and transitive dependencies in SBT
in the presence of scope constraints.

Known Issues
------------
At the time of this writing SBT fails to constrain the dependencies as
expected and will allow compile dependencies to test-scoped dependencies
when another dependency provides a compile scoped dependency on it.

I have commented out all of the dependency problems that are correctly compile
failures already and left the remaining issue
**ModuleXClass#compileDependencyOnZIsInvalid** in place since it compiles with
SBT when it should not.

Here is the output on SBT 0.13.0:

```plain
$ sbt test:compile
Loading /usr/share/sbt/bin/sbt-launch-lib.bash
[info] Set current project to multimodule-sbt-dependencies (in build file:/home/alain_odea/Documents/multimodule-sbt-dependencies/)
[success] Total time: 1 s, completed Feb 11, 2014 9:10:01 PM
```

I expect the following output (hypothetical, I hand-edited what you see below):

```plain
$ sbt test:compile
Loading /usr/share/sbt/bin/sbt-launch-lib.bash
[info] Set current project to multimodule-sbt-dependencies (in build file:/home/alain_odea/Documents/multimodule-sbt-dependencies/)
[info] Compiling 1 Scala source to /home/alain_odea/Documents/multimodule-sbt-dependencies/moduleX/target/scala-2.10/classes...
[error] /home/alain_odea/Documents/multimodule-sbt-dependencies/moduleX/src/main/scala/ModuleXClass.scala:4: not found: type ModuleZClass
[error]   val compileDependencyOnZIsInvalid = new ModuleZClass
[error]                                           ^
[error] one error found
[error] (moduleX/compile:compile) Compilation failed
[error] Total time: 2 s, completed Feb 11, 2014 9:13:09 PM
```
