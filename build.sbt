name := "multimodule-sbt-dependencies"

version := "0.1"

organization := "com.alainodea"

scalaVersion := "2.10.2"

lazy val allScopes = "compile;test;test->test"

lazy val moduleX = project.in(file("moduleX")).dependsOn(moduleY % allScopes, moduleZ % "test")

lazy val moduleY = project.in(file("moduleY")).dependsOn(moduleZ % allScopes)

lazy val moduleZ = project.in(file("moduleZ"))
